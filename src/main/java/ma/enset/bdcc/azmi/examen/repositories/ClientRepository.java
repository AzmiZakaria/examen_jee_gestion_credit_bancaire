package ma.enset.bdcc.azmi.examen.repositories;

import ma.enset.bdcc.azmi.examen.entities.Client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    List<Client> findByNameContaining(String name);
}