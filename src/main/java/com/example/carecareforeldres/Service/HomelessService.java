package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Homeless;
import com.example.carecareforeldres.Entity.Shelter;
import com.example.carecareforeldres.Entity.User;
import com.example.carecareforeldres.Repository.HomelessRepository;
import com.example.carecareforeldres.Repository.ShelterRepository;
import com.example.carecareforeldres.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.carecareforeldres.demo.EmailService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class HomelessService implements IHomelessService{
    HomelessRepository homelessRepository ;
    ShelterRepository shelterRepository;
    EmailService emailService;

    @Override
    public List<Homeless> retrieveAllHomeless() {
        return homelessRepository.findAll();
    }

    @Override
    public Homeless addHomeless(Homeless s) {
        return homelessRepository.save(s);
    }

    @Override
    public Homeless updateHomeless(Homeless s) {
        return homelessRepository.save(s);
    }

    @Override
    public Homeless retrieveHomeless(Long idHomeless) {
        return homelessRepository.findById(idHomeless).get();
    }

    @Override
    public void removeHomeless(Long idHomeless) {
        homelessRepository.deleteById(idHomeless);
    }
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> connectHomelessToShelter(Long idHomeless, Long idShelter) {
        Homeless homeless = homelessRepository.findById(idHomeless).orElse(null);
        Shelter shelter = shelterRepository.findById(idShelter).orElse(null);
        int idH = homeless.getUser();

        User user = userRepository.findById(idH).orElse(null);

        if (homeless != null && shelter != null && user != null) {
            Long currentAvailablePlaces = shelter.getNbrPlaceDisponible();

            if (currentAvailablePlaces > 0) {
                // Diminution du nombre de places disponibles dans le refuge
                shelter.setNbrPlaceDisponible(currentAvailablePlaces - 1);
                // Mise à jour du statut du refuge si nécessaire
                if (currentAvailablePlaces - 1 == 0) {
                    shelter.setStatut("Saturé");
                }
                // Association du sans-abri avec le refuge
                homeless.setShelter(shelter);
                // Sauvegarde des modifications dans les repositories
                homelessRepository.save(homeless);
                shelterRepository.save(shelter);

                // Envoi de l'e-mail de confirmation

                String userEmail = user.getEmail();
                log.info("////////////////"+userEmail);
                String subject = "Confirmation d'inscription au refuge";
                String message = "Vous avez été inscrit avec succès au refuge. Merci!";
                try {
                    emailService.sendEmail(userEmail, subject, message);
                } catch (Exception e) {
                    log.error("Erreur lors de l'envoi de l'e-mail de confirmation : " + e.getMessage());
                    // Gérer l'erreur d'envoi d'e-mail
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Erreur lors de l'envoi de l'e-mail de confirmation.");
                }

                return ResponseEntity.ok().body("{\"message\": \"Votre demande a été bien prise en compte, un Email de confirmation vous a été envoyé.\"}");
            } else {
                // Si le refuge est saturé, renvoyer un message d'erreur
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le refuge est saturé, aucune place disponible.");
            }
        } else {
            // Si le sans-abri, le refuge ou l'utilisateur est introuvable, renvoyer un message d'erreur
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le sans-abri, le refuge ou l'utilisateur est introuvable.");
        }
    }






    // recherhceHomelessAvecSaLocation(String location):

    //  recherhcelessByAgeGreaterThan(Integer age):
    //  recherhcelessBySituationMedicale(String situation):


    //public double calculatePercentageOfHomelessWithMedicalNeeds();
    //public service ajoutServiceSpecifiqueSelonHomlessNeeds();
    //public addHomlessAndAssingToShelterWithCapacityCondititon() ;
    // public nbrehomlessDansChaqueSheletrParTypeServie ;
}

