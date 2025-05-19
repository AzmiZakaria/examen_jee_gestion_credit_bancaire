package ma.enset.bdcc.azmi.examen;

import ma.enset.bdcc.azmi.examen.entities.*;
import ma.enset.bdcc.azmi.examen.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ExamenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            ClientRepository clientRepository,
            CreditRepository creditRepository,
            PersonalCreditRepository personalCreditRepository,
            PaymentRepository paymentRepository
    ) {
        return args -> {
            // Créer quelques clients
            Stream.of("zakaria","Achraf","Hamza","salah").forEach(name -> {
                Client client = new Client();
                client.setName(name);
                client.setEmail(name.toLowerCase().replace(" ", ".") + "@gmail.com");
                clientRepository.save(client);
            });

            // Récupérer la liste des clients
            List<Client> clients = clientRepository.findAll();
            System.out.println("Liste des clients :");
            clients.forEach(client -> {
                System.out.println("ID: " + client.getId() + ", Nom: " + client.getName() + ", Email: " + client.getEmail());
            });

            // Créer un crédit personnel
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            creditPersonnel.setClient(clients.get(0));
            creditPersonnel.setAmount(50000.0);
            creditPersonnel.setInterestRate(2.5);
            creditPersonnel.setRepaymentDuration(12);
            creditPersonnel.setRequestDate(LocalDate.now());
            creditPersonnel.setStatus(CreditStatus.IN_PROGRESS);
            creditPersonnel.setReason(PersonalCreditReason.CAR_PURCHASE);
            creditRepository.save(creditPersonnel);

            // Créer un crédit immobilier
            CreditImmobilier creditImmobilier = new CreditImmobilier();
            creditImmobilier.setClient(clients.get(1));
            creditImmobilier.setAmount(200000.0);
            creditImmobilier.setInterestRate(3.0);
            creditImmobilier.setRepaymentDuration(240);
            creditImmobilier.setRequestDate(LocalDate.now());
            creditImmobilier.setStatus(CreditStatus.ACCEPTED);
            creditImmobilier.setAcceptanceDate(LocalDate.now());
            creditImmobilier.setPropertyType(PropertyType.APARTMENT);
            creditRepository.save(creditImmobilier);

            // Créer un crédit professionnel
            CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
            creditProfessionnel.setClient(clients.get(2));
            creditProfessionnel.setAmount(100000.0);
            creditProfessionnel.setInterestRate(4.0);
            creditProfessionnel.setRepaymentDuration(36);
            creditProfessionnel.setRequestDate(LocalDate.now());
            creditProfessionnel.setStatus(CreditStatus.REJECTED);
            creditProfessionnel.setReason("Equipment");  // Shorter text
            creditProfessionnel.setCompanyName("TechSol");  // Shorter text
            creditRepository.save(creditProfessionnel);

            // Ajouter quelques remboursements
            Credit firstCredit = creditRepository.findAll().get(0);
            
            Rembourcement rembourcement1 = new Rembourcement();
            rembourcement1.setCredit(firstCredit);
            rembourcement1.setAmount(1500.0);
            rembourcement1.setDate(LocalDate.now());
            rembourcement1.setType(PaymentType.MONTHLY_PAYMENT);
            paymentRepository.save(rembourcement1);

            Rembourcement rembourcement2 = new Rembourcement();
            rembourcement2.setCredit(firstCredit);
            rembourcement2.setAmount(5000.0);
            rembourcement2.setDate(LocalDate.now());
            rembourcement2.setType(PaymentType.EARLY_REPAYMENT);
            paymentRepository.save(rembourcement2);

            // Test des requêtes personnalisées
            System.out.println("Clients avec 'zakaria' dans leur nom :");
            clientRepository.findByNameContaining("zakaria").forEach(c -> {
                System.out.println(c.getName() + " : " + c.getEmail());
            });

            System.out.println("\nCrédits en cours :");
            creditRepository.findByStatus(CreditStatus.IN_PROGRESS).forEach(c -> {
                System.out.println("Crédit ID: " + c.getId() + ", Montant: " + c.getAmount());
            });

            System.out.println("\nRemboursements anticipés :");
            paymentRepository.findByType(PaymentType.EARLY_REPAYMENT).forEach(r -> {
                System.out.println("Remboursement ID: " + r.getId() + ", Montant: " + r.getAmount());
            });
        };
    }
}
