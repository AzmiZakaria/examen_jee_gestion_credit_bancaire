package ma.enset.bdcc.azmi.examen.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import ma.enset.bdcc.azmi.examen.dtos.PaymentDTO;
import ma.enset.bdcc.azmi.examen.entities.PaymentType;
import ma.enset.bdcc.azmi.examen.services.PaymentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
@Tag(name = "Payment Management", description = "Endpoints for managing payments")
public class PaymentRestController {
    private PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Record new payment", description = "Records a new payment")
    public PaymentDTO savePayment(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.savePayment(paymentDTO);
    }

    @GetMapping("/credit/{creditId}")
    @Operation(summary = "Get payments by credit", description = "Retrieves all payments for a specific credit")
    public List<PaymentDTO> getPaymentsByCredit(@PathVariable Long creditId) {
        return paymentService.getPaymentsByCredit(creditId);
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get payments by type", description = "Retrieves payments filtered by type")
    public List<PaymentDTO> getPaymentsByType(@PathVariable PaymentType type) {
        return paymentService.getPaymentsByType(type);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get payments by date range", description = "Retrieves payments between two dates")
    public List<PaymentDTO> getPaymentsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return paymentService.getPaymentsBetweenDates(startDate, endDate);
    }

    @GetMapping("/credit/{creditId}/total")
    @Operation(summary = "Get total payments", description = "Calculates total payments made for a credit")
    public double getTotalPayments(@PathVariable Long creditId) {
        return paymentService.calculateTotalPaymentsForCredit(creditId);
    }

    @GetMapping("/credit/{creditId}/remaining")
    @Operation(summary = "Get remaining amount", description = "Calculates remaining amount to be paid for a credit")
    public double getRemainingAmount(@PathVariable Long creditId) {
        return paymentService.calculateRemainingAmount(creditId);
    }
}