package ma.enset.bdcc.azmi.examen.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CREDIT_TYPE")
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Credit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate requestDate;
    @Enumerated(EnumType.STRING)
    private CreditStatus status;
    private LocalDate acceptanceDate;
    private Double amount;
    private Integer repaymentDuration;
    private Double interestRate;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY)
    private List<Rembourcement> remboursements;
}
