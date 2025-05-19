package ma.enset.bdcc.azmi.examen.services;

import ma.enset.bdcc.azmi.examen.dtos.*;
import ma.enset.bdcc.azmi.examen.entities.CreditStatus;
import java.util.List;

public interface CreditService {
    CreditDTO saveCredit(CreditDTO creditDTO);
    CreditDTO updateCredit(CreditDTO creditDTO);
    void deleteCredit(Long id);
    CreditDTO getCreditById(Long id);
    List<CreditDTO> listCredits();
    List<CreditDTO> getCreditsByStatus(CreditStatus status);
    List<CreditDTO> getCreditsByClient(Long clientId);
    void approveCreditRequest(Long creditId);
    void rejectCreditRequest(Long creditId);
}