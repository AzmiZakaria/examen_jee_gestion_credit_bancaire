package ma.enset.bdcc.azmi.examen.services;

import ma.enset.bdcc.azmi.examen.dtos.ClientDTO;
import java.util.List;

public interface ClientService {
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(ClientDTO clientDTO);
    void deleteClient(Long id);
    ClientDTO getClientByEmail(String email);
    List<ClientDTO> searchClientsByName(String name);
}