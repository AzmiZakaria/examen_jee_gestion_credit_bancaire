package ma.enset.bdcc.azmi.examen.repositories;

import ma.enset.bdcc.azmi.examen.entities.CreditPersonnel;
import ma.enset.bdcc.azmi.examen.entities.PersonalCreditReason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalCreditRepository extends JpaRepository<CreditPersonnel, Long> {
    List<CreditPersonnel> findByReason(PersonalCreditReason reason);
}