package ma.enset.bdcc.azmi.examen.services;

import ma.enset.bdcc.azmi.examen.dtos.PaymentDTO;
import ma.enset.bdcc.azmi.examen.entities.PaymentType;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    PaymentDTO savePayment(PaymentDTO paymentDTO);
    List<PaymentDTO> getPaymentsByCredit(Long creditId);
    List<PaymentDTO> getPaymentsByType(PaymentType type);
    List<PaymentDTO> getPaymentsBetweenDates(LocalDate startDate, LocalDate endDate);
    double calculateTotalPaymentsForCredit(Long creditId);
    double calculateRemainingAmount(Long creditId);
}