package ma.enset.bdcc.azmi.examen.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("MORTGAGE")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
}
