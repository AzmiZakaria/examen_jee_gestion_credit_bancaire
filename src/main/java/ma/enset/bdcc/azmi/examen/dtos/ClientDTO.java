package ma.enset.bdcc.azmi.examen.dtos;

import lombok.Data;
import java.util.List;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private List<CreditDTO> credits;
}
