package ma.enset.bdcc.azmi.examen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("BUSINESS")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditProfessionnel extends Credit {
    @Column(name = "business_reason", length = 255)  // Changed column name and specified length
    private String reason;
    
    @Column(length = 100)
    private String companyName;
}
