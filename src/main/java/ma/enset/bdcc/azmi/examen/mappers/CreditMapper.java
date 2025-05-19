package ma.enset.bdcc.azmi.examen.mappers;

import ma.enset.bdcc.azmi.examen.dtos.*;
import ma.enset.bdcc.azmi.examen.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CreditMapper {
    
    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        if(client.getCredits() != null) {
            clientDTO.setCredits(
                client.getCredits()
                    .stream()
                    .map(this::fromCredit)
                    .toList()
            );
        }
        return clientDTO;
    }

    public Client fromClientDTO(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }

    public CreditDTO fromCredit(Credit credit) {
        CreditDTO creditDTO;
        
        if (credit instanceof CreditPersonnel) {
            creditDTO = new PersonalCreditDTO();
            ((PersonalCreditDTO) creditDTO).setReason(
                ((CreditPersonnel) credit).getReason()
            );
        } 
        else if (credit instanceof CreditImmobilier) {
            creditDTO = new MortgageCreditDTO();
            ((MortgageCreditDTO) creditDTO).setPropertyType(
                ((CreditImmobilier) credit).getPropertyType()
            );
        }
        else if (credit instanceof CreditProfessionnel) {
            creditDTO = new BusinessCreditDTO();
            ((BusinessCreditDTO) creditDTO).setReason(
                ((CreditProfessionnel) credit).getReason()
            );
            ((BusinessCreditDTO) creditDTO).setCompanyName(
                ((CreditProfessionnel) credit).getCompanyName()
            );
        }
        else {
            creditDTO = new CreditDTO();
        }
        
        BeanUtils.copyProperties(credit, creditDTO, "client");
        creditDTO.setClientId(credit.getClient().getId());
        return creditDTO;
    }

    public PaymentDTO fromPayment(Rembourcement payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        BeanUtils.copyProperties(payment, paymentDTO);
        paymentDTO.setCreditId(payment.getCredit().getId());
        return paymentDTO;
    }

    public Rembourcement fromPaymentDTO(PaymentDTO paymentDTO) {
        Rembourcement payment = new Rembourcement();
        BeanUtils.copyProperties(paymentDTO, payment);
        return payment;
    }
}