package ma.enset.bdcc.azmi.examen.services.impl;

import lombok.AllArgsConstructor;
import ma.enset.bdcc.azmi.examen.dtos.CreditDTO;
import ma.enset.bdcc.azmi.examen.entities.*;
import ma.enset.bdcc.azmi.examen.mappers.CreditMapper;
import ma.enset.bdcc.azmi.examen.repositories.*;
import ma.enset.bdcc.azmi.examen.services.CreditService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {
    private CreditRepository creditRepository;
    private ClientRepository clientRepository;
    private CreditMapper creditMapper;

    @Override
    public CreditDTO saveCredit(CreditDTO creditDTO) {
        Credit credit = new CreditPersonnel(); // Par défaut
        BeanUtils.copyProperties(creditDTO, credit);
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        credit.setClient(client);
        Credit savedCredit = creditRepository.save(credit);
        return creditMapper.fromCredit(savedCredit);
    }

    @Override
    public CreditDTO updateCredit(CreditDTO creditDTO) {
        Credit credit = creditRepository.findById(creditDTO.getId())
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        BeanUtils.copyProperties(creditDTO, credit);
        Credit updatedCredit = creditRepository.save(credit);
        return creditMapper.fromCredit(updatedCredit);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        return creditMapper.fromCredit(credit);
    }

    @Override
    public List<CreditDTO> listCredits() {
        return creditRepository.findAll()
                .stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByStatus(CreditStatus status) {
        return creditRepository.findByStatus(status)
                .stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByClient(Long clientId) {
        return creditRepository.findByClientId(clientId)
                .stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public void approveCreditRequest(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        credit.setStatus(CreditStatus.ACCEPTED);
        credit.setAcceptanceDate(LocalDate.now());
        creditRepository.save(credit);
    }

    @Override
    public void rejectCreditRequest(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        credit.setStatus(CreditStatus.REJECTED);
        creditRepository.save(credit);
    }
}