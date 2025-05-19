package ma.enset.bdcc.azmi.examen.repositories;

import ma.enset.bdcc.azmi.examen.entities.Rembourcement;
import ma.enset.bdcc.azmi.examen.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PaymentRepository extends JpaRepository<Rembourcement, Long> {
    List<Rembourcement> findByType(PaymentType type);
    List<Rembourcement> findByCreditId(Long creditId);
    List<Rembourcement> findByDateBetween(LocalDate startDate, LocalDate endDate);
}