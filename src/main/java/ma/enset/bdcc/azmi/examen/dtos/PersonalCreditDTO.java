package ma.enset.bdcc.azmi.examen.dtos;

import lombok.Data;
import ma.enset.bdcc.azmi.examen.entities.PersonalCreditReason;

@Data
public class PersonalCreditDTO extends CreditDTO {
    private PersonalCreditReason reason;
}