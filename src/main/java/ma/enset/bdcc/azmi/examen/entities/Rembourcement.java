package ma.enset.bdcc.azmi.examen.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rembourcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;  // Changed from String to LocalDate
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @ManyToOne
    private Credit credit;
}
