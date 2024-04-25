package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Commande;
import com.example.carecareforeldres.Entity.StatutsCom;
import com.example.carecareforeldres.Repository.CommandeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Slf4j
@AllArgsConstructor
@Service
public class CommandeService implements ICommandeService {
    CommandeRepository commandeRepository;
    private final JavaMailSender javaMailSender;


    @Override
    public List<Commande> getCommandesByDate(Date date) {
        return commandeRepository.findByDateCommande(date);
    }



    @Override
    public List<Commande> getCommandesByMonth(int month) {
        return commandeRepository.findByMonth(month);
    }

    @Override
    public List<Commande> getCommandesByYear(int year) {
        return commandeRepository.findByYear(year);
    }


    @Override
    public int getTotalCommandesByDate(Date date) {
        List<Commande> commandes = getCommandesByDate(date);
        return commandes.size();
    }

    @Override
    public int getTotalCommandesByMonth(int month) {
        return commandeRepository.countByMonth(month);
    }

    @Override
    public int getTotalCommandesByYear(int year) {
        return commandeRepository.countByYear(year);
    }



    @Override
    public void changerStatutCommande(Long idCommande) {
        Optional<Commande> optionalCommande = commandeRepository.findById(Math.toIntExact(idCommande));
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            if (commande.getStatut() == StatutsCom.EN_ATTENTE) {
                commande.setStatut(StatutsCom.EXPEDIEE);
                commandeRepository.save(commande);
                // Envoyer un e-mail à l'utilisateur
                String userEmail = commande.getUser().getEmail();
                String subject = "Votre commande a été expédiée";
                String message = "Votre commande a été expédiée avec succès.";
                sendEmail(userEmail, subject, message);
                log.info("Le statut de la commande a été changé avec succès et un e-mail a été envoyé à l'utilisateur.");
            } else {
                log.warn("Impossible de changer le statut de la commande : le statut actuel n'est pas EN_ATTENTE.");
            }
        } else {
            log.error("La commande avec l'ID {} n'a pas été trouvée.", idCommande);
        }
    }

    @Override
    public List<Commande> getCommandesByUserId(Integer userId) {
        return commandeRepository.findByUserId(userId);
    }

    @Override
    public List<Commande> getCommandesEnAttente() {
        return commandeRepository.findByStatut(StatutsCom.EN_ATTENTE);
    }

    @Override
    public int countCommandesEnAttente() {
        return commandeRepository.countCommandesEnAttente();
    }

    @Override
    public int countCommandesExpediees() {
        return commandeRepository.countCommandesExpediees();
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Override
    public List<Commande> retrieveAllCommandes() {
        return commandeRepository.findAll();
    }
}
