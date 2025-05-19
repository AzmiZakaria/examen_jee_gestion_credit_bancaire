package ma.enset.bdcc.azmi.examen.repositories;

import ma.enset.bdcc.azmi.examen.entities.Credit;
import ma.enset.bdcc.azmi.examen.entities.CreditStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByStatus(CreditStatus status);
    List<Credit> findByClientId(Long clientId);
    List<Credit> findByAmountGreaterThan(Double amount);
}