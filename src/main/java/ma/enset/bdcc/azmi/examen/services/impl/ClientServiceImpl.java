package ma.enset.bdcc.azmi.examen.services.impl;

import lombok.AllArgsConstructor;
import ma.enset.bdcc.azmi.examen.dtos.ClientDTO;
import ma.enset.bdcc.azmi.examen.entities.Client;
import ma.enset.bdcc.azmi.examen.mappers.CreditMapper;
import ma.enset.bdcc.azmi.examen.repositories.ClientRepository;
import ma.enset.bdcc.azmi.examen.services.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CreditMapper creditMapper;

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = creditMapper.fromClientDTO(clientDTO);
        Client savedClient = clientRepository.save(client);
        return creditMapper.fromClient(savedClient);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return creditMapper.fromClient(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(creditMapper::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Client client = clientRepository.findById(clientDTO.getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        return creditMapper.fromClient(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO getClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        return creditMapper.fromClient(client);
    }

    @Override
    public List<ClientDTO> searchClientsByName(String name) {
        return clientRepository.findByNameContaining(name)
                .stream()
                .map(creditMapper::fromClient)
                .collect(Collectors.toList());
    }
}