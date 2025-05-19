package ma.enset.bdcc.azmi.examen.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    @Id
    private Long id;
    private LocalDate requestDate;
    private CreditStatus status;
    private LocalDate acceptanceDate;
    private Double amount;
    private Integer repaymentDuration;
    private Double interestRate;

}
