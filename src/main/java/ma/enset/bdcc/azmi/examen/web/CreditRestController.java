package ma.enset.bdcc.azmi.examen.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import ma.enset.bdcc.azmi.examen.dtos.*;
import ma.enset.bdcc.azmi.examen.entities.CreditStatus;
import ma.enset.bdcc.azmi.examen.services.CreditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@AllArgsConstructor
@Tag(name = "Credit Management", description = "Endpoints for managing credits")
public class CreditRestController {
    private CreditService creditService;

    @GetMapping
    @Operation(summary = "Get all credits", description = "Retrieves a list of all credits")
    public List<CreditDTO> getAllCredits() {
        return creditService.listCredits();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get credit by ID", description = "Retrieves a credit by its ID")
    public CreditDTO getCredit(@PathVariable Long id) {
        return creditService.getCreditById(id);
    }

    @PostMapping
    @Operation(summary = "Create new credit", description = "Creates a new credit request")
    public CreditDTO saveCredit(@RequestBody CreditDTO creditDTO) {
        return creditService.saveCredit(creditDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update credit", description = "Updates an existing credit")
    public CreditDTO updateCredit(@PathVariable Long id, @RequestBody CreditDTO creditDTO) {
        creditDTO.setId(id);
        return creditService.updateCredit(creditDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete credit", description = "Deletes a credit by its ID")
    public void deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get credits by status", description = "Retrieves credits filtered by status")
    public List<CreditDTO> getCreditsByStatus(@PathVariable CreditStatus status) {
        return creditService.getCreditsByStatus(status);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get credits by client", description = "Retrieves all credits for a specific client")
    public List<CreditDTO> getCreditsByClient(@PathVariable Long clientId) {
        return creditService.getCreditsByClient(clientId);
    }

    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve credit", description = "Approves a credit request")
    public void approveCredit(@PathVariable Long id) {
        creditService.approveCreditRequest(id);
    }

    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject credit", description = "Rejects a credit request")
    public void rejectCredit(@PathVariable Long id) {
        creditService.rejectCreditRequest(id);
    }
}