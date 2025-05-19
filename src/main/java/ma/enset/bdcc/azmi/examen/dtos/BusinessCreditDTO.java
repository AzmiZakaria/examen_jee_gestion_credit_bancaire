package ma.enset.bdcc.azmi.examen.dtos;

import lombok.Data;

@Data
public class BusinessCreditDTO extends CreditDTO {
    private String reason;
    private String companyName;
}