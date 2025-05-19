package ma.enset.bdcc.azmi.examen.dtos;

import lombok.Data;
import ma.enset.bdcc.azmi.examen.entities.PaymentType;
import java.time.LocalDate;

@Data
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private PaymentType type;
    private Long creditId;
}