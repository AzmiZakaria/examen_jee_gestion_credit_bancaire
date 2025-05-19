package ma.enset.bdcc.azmi.examen.dtos;

import lombok.Data;
import ma.enset.bdcc.azmi.examen.entities.CreditStatus;
import java.time.LocalDate;

@Data
public class CreditDTO {
    private Long id;
    private LocalDate requestDate;
    private CreditStatus status;
    private LocalDate acceptanceDate;
    private Double amount;
    private Integer repaymentDuration;
    private Double interestRate;
    private Long clientId;
}