package ma.enset.bdcc.azmi.examen.dtos;

import lombok.Data;
import ma.enset.bdcc.azmi.examen.entities.PropertyType;

@Data
public class MortgageCreditDTO extends CreditDTO {
    private PropertyType propertyType;
}