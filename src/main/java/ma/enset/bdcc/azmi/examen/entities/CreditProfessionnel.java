package ma.enset.bdcc.azmi.examen.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProfessionnel extends Credit{
    private String reason;
    private String companyName;

}
