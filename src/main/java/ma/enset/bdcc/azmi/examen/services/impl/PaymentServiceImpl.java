package ma.enset.bdcc.azmi.examen.services.impl;

import lombok.AllArgsConstructor;
import ma.enset.bdcc.azmi.examen.dtos.PaymentDTO;
import ma.enset.bdcc.azmi.examen.entities.Credit;
import ma.enset.bdcc.azmi.examen.entities.PaymentType;
import ma.enset.bdcc.azmi.examen.entities.Rembourcement;
import ma.enset.bdcc.azmi.examen.mappers.CreditMapper;
import ma.enset.bdcc.azmi.examen.repositories.CreditRepository;
import ma.enset.bdcc.azmi.examen.repositories.PaymentRepository;
import ma.enset.bdcc.azmi.examen.services.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;

    @Override
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        Credit credit = creditRepository.findById(paymentDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        
        Rembourcement payment = creditMapper.fromPaymentDTO(paymentDTO);
        payment.setCredit(credit);
        
        return creditMapper.fromPayment(paymentRepository.save(payment));
    }

    @Override
    public List<PaymentDTO> getPaymentsByCredit(Long creditId) {
        return paymentRepository.findByCreditId(creditId)
                .stream()
                .map(creditMapper::fromPayment)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByType(PaymentType type) {
        return paymentRepository.findByType(type)
                .stream()
                .map(creditMapper::fromPayment)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return paymentRepository.findByDateBetween(startDate, endDate)
                .stream()
                .map(creditMapper::fromPayment)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateTotalPaymentsForCredit(Long creditId) {
        return paymentRepository.findByCreditId(creditId)
                .stream()
                .mapToDouble(Rembourcement::getAmount)
                .sum();
    }

    @Override
    public double calculateRemainingAmount(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        
        double totalPaid = calculateTotalPaymentsForCredit(creditId);
        double totalAmount = credit.getAmount() + (credit.getAmount() * credit.getInterestRate() / 100);
        
        return totalAmount - totalPaid;
    }
}