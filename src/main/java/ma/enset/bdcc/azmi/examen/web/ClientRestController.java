package ma.enset.bdcc.azmi.examen.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import ma.enset.bdcc.azmi.examen.dtos.ClientDTO;
import ma.enset.bdcc.azmi.examen.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
@Tag(name = "Client Management", description = "Endpoints for managing clients")
public class ClientRestController {
    private ClientService clientService;

    @GetMapping
    @Operation(summary = "Get all clients", description = "Retrieves a list of all clients")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID", description = "Retrieves a client by their ID")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    @Operation(summary = "Create new client", description = "Creates a new client")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update client", description = "Updates an existing client")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        return clientService.updateClient(clientDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client", description = "Deletes a client by their ID")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search clients by name", description = "Searches for clients by name containing the search term")
    public List<ClientDTO> searchClients(@RequestParam String name) {
        return clientService.searchClientsByName(name);
    }
}