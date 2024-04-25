package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.*;
import com.example.carecareforeldres.Repository.CoungeRepository;
import com.example.carecareforeldres.Repository.CuisinierRepository;
import com.example.carecareforeldres.demo.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import org.springframework.web.ErrorResponseException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CoungeService implements IServiceCounge{
    CoungeRepository coungeRepository ;
    CuisinierRepository cuisinierRepository;


    private final EmailService emailService;

    @Override
    public Counge add(Counge res) {return coungeRepository.save(res);}
    @Override
    public List<Counge> getAll(){
      //  String subject = "réprimander";
        //String message = "               \"Je vous écris pour discuter d'une question importante concernant la qualité de nos plats, en particulier ceux préparés par vous-même. Récemment, nous avons reçu un retour d'information alarmant de la part de nos clients, indiquant que les plats que vous avez préparés ont reçu un taux de mécontentement exceptionnellement élevé.\\n\" +\n" +
          //      "               \"\\n\" +\n" +
            //    "               \"Après avoir analysé les données des avis clients, il est devenu évident que vos plats ont reçu un taux de désapprobation de près de 70%. Cela est inacceptable et met en évidence un problème sérieux qui doit être résolu de toute urgence.\\n\" +\n" +
              //  "               \"\\n\" +\n" +
                //"               \"En tant que cuisinier au sein de notre établissement, vous avez la responsabilité de maintenir les normes les plus élevées en matière de qualité, de fraîcheur et de saveur de nos plats. Les retours négatifs de nos clients sont préoccupants car ils nuisent non seulement à notre réputation, mais aussi à notre capacité à fidéliser notre clientèle existante et à attirer de nouveaux clients.\\n\" +\n" +
     //           "               \"\\n\" +\n" +
       //         "               \"Je vous exhorte donc à examiner sérieusement vos méthodes de travail, à identifier les problèmes potentiels dans la préparation des plats et à prendre des mesures immédiates pour les corriger. Il est impératif que vous redoubliez d'efforts pour garantir que chaque plat que vous préparez rencontre les attentes de nos clients en termes de qualité, de goût et de présentation.\\n\" +\n" +
         //       "               \"\\n\" +\n" +
           //     "               \"De plus, je vous encourage à solliciter des feedbacks réguliers de la part des clients afin de mieux comprendre leurs préférences et leurs préoccupations. La communication ouverte et la rétroaction constructive sont essentielles pour améliorer continuellement nos services et notre offre culinaire.\\n\" +\n" +
//                "               \"\\n\" +\n" +
  //              "               \"Sachez que nous sommes là pour vous soutenir dans votre parcours d'amélioration et que nous sommes disponibles pour vous fournir toute l'aide ou les ressources dont vous pourriez avoir besoin pour réussir dans votre rôle de cuisinier.\\n\" +\n" +
    //            "               \"\\n\" +\n" +
      //          "               \"Toutefois, veuillez prendre conscience que des mesures disciplinaires pourraient être prises si des améliorations significatives ne sont pas observées dans un avenir proche. Nous avons confiance en vos compétences et en votre engagement envers l'excellence, et nous sommes convaincus que vous prendrez les mesures nécessaires pour rectifier la situation.\\n\" +\n" +
        //        "               \"\\n\" +\n" +
          //      "               \"Je vous remercie de votre attention à cette question et de votre engagement continu envers la qualité de notre service. Si vous avez des questions ou des préoccupations, n'hésitez pas à me contacter pour en discuter davantage.\\n\" +\n" +
            //    "               \"\\n\" +\n" +
              //  "               \"Cordialement,\" ";
        //try {
          //  emailService.sendEmail("nasriamin300@gmail.com", subject, message);
       // } catch (Exception e) {
         //   log.error("Erreur lors de l'envoi de l'e-mail de confirmation : " + e.getMessage());
        //}
        return coungeRepository.findAll();}
    public List<Counge> getAllF(Integer idC){
        Cuisinier cuisinier=cuisinierRepository.findById(idC).get();
        return coungeRepository.findCoungeByCuisinierC(cuisinier);
    }

    @Override
    public void remove(int idf) {
        coungeRepository.deleteById(idf);}
    public Counge updateCoungeEtat(Integer id, EtatCounger newEtat) {
        Counge counge = coungeRepository.findById(id).get();
        counge.setEtatCounger(newEtat);
        return coungeRepository.save(counge);
    }




    @Override
    public Counge update(Counge counge, Integer CuisinierId) {
        Cuisinier cuisinier=cuisinierRepository.findById(CuisinierId).get();
        counge.setEtatCounger(EtatCounger.EN_COUR);
        counge.setCuisinierC(cuisinier);
        counge.setDateAjout(LocalDate.now());
        List<Counge> congesDuCuisinier = cuisinier.getCounges();

        boolean aPrisConge = aPrisCongeDerniersQuatreMois(congesDuCuisinier);


        LocalDate dateDebut = counge.getDateDebut(); // Convertir en LocalDate
        LocalDate dateFin = counge.getDateFin(); // Convertir en LocalDate
        LocalDate currentDate = LocalDate.now();
        int joursCongesCetteAnnee = coungeRepository.countCongesByCuisinierAndYear(cuisinier, currentDate);
        int dureeNouvelleConge = (int) ChronoUnit.DAYS.between(dateDebut, dateFin); // Calculer la durée du congé directement avec les LocalDate

        long differenceEnJours = ChronoUnit.DAYS.between(currentDate, dateDebut);

        if (counge.getDateFin().isBefore(counge.getDateDebut()) ) {

            if (differenceEnJours < 3) {
                log.info("La date de début du congé doit être au moins 3 jours après la date actuelle.");
                return null;
            }
            log.info("La date de fin est antérieure à la date de début.");
            return null;
        }
        else {
            if (!aPrisConge) {

                if(counge.getTypeCounger().equals(TypeCounger.COUNGER_MATERNITE) && cuisinier.getSexe().equals(Sexe.FEMME) && dureeNouvelleConge < 45 && coungeRepository.countCongesByCuisinierAndYear(cuisinier,currentDate,TypeCounger.COUNGER_MATERNITE) == 0){
                    return coungeRepository.save(counge);
                }
                if(counge.getTypeCounger().equals(TypeCounger.COUGER_PARENTALE) && cuisinier.getSexe().equals(Sexe.HOMME) && dureeNouvelleConge < 3 && coungeRepository.countCongesByCuisinierAndYear(cuisinier,currentDate,TypeCounger.COUGER_PARENTALE) == 0){
                    return coungeRepository.save(counge);
                }
                log.info("tu a déjà pris un congé récemment");
                return counge;
            }


            if (joursCongesCetteAnnee + dureeNouvelleConge > 30) {
                if(counge.getTypeCounger().equals(TypeCounger.COUNGER_MATERNITE)  && coungeRepository.countCongesByCuisinierAndYear(cuisinier,currentDate,TypeCounger.COUNGER_MATERNITE) == 0){
                    if (cuisinier.getSexe().equals(Sexe.FEMME) && dureeNouvelleConge < 45) {
                        return coungeRepository.save(counge);
                    }
                }
                if(counge.getTypeCounger().equals(TypeCounger.COUGER_PARENTALE) && cuisinier.getSexe().equals(Sexe.HOMME) && dureeNouvelleConge < 3 && coungeRepository.countCongesByCuisinierAndYear(cuisinier,currentDate,TypeCounger.COUGER_PARENTALE) == 0){
                    return coungeRepository.save(counge);
                }
                log.info("Le cuisinier aura plus de 30 jours de congé cette année après l'ajout de cette nouvelle demande de congé.");
                return null;
            }
            return coungeRepository.save(counge);
        }
    }

    @Scheduled(cron = "0 0 11 ? * TUE")
    public void supprimerCongesRefuses() {
        List<Counge> congesRefuses = coungeRepository.findByEtatCounger(EtatCounger.REFUSER);
        coungeRepository.deleteAll(congesRefuses);
        for (Counge v:congesRefuses) {
            log.info("conger de l'id  "+v.getId()+ "   supprimer");
        }
    }

    @Override
    public ResponseEntity<?> DemandeCoungeCuisine(Counge counge, Integer CuisinierId) {
        try {
            Cuisinier cuisinier = cuisinierRepository.findById(CuisinierId).orElseThrow(() -> new NotFoundException("Cuisinier non trouvé"));
            counge.setEtatCounger(EtatCounger.EN_COUR);
            counge.setCuisinierC(cuisinier);
            counge.setDateAjout(LocalDate.now());
            List<Counge> congesDuCuisinier = cuisinier.getCounges();

            boolean aPrisConge;
            try {
                aPrisConge = aPrisCongeDerniersQuatreMois(congesDuCuisinier);
            } catch (Exception e) {
                throw new com.example.carecareforeldres.Entity.ErrorResponseException("Erreur lors de la vérification des congés du cuisinier", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            LocalDate dateDebut;
            LocalDate dateFin;
            LocalDate currentDate = LocalDate.now();
            try {
                dateDebut = counge.getDateDebut();
                dateFin = counge.getDateFin();
                if (dateDebut == null || dateFin == null) {
                    throw new com.example.carecareforeldres.Entity.ErrorResponseException("La date de début ou la date de fin du congé est null.", HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                throw new com.example.carecareforeldres.Entity.ErrorResponseException("Erreur lors de la récupération des dates de congé", HttpStatus.BAD_REQUEST);
            }

            int joursCongesCetteAnnee;
            int dureeNouvelleConge;
            try {
                joursCongesCetteAnnee = coungeRepository.countCongesByCuisinierAndYear(cuisinier, currentDate);
                dureeNouvelleConge = (int) ChronoUnit.DAYS.between(dateDebut, dateFin);
            } catch (Exception e) {
                throw new com.example.carecareforeldres.Entity.ErrorResponseException("Erreur lors du calcul de la durée des congés", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            long differenceEnJours = ChronoUnit.DAYS.between(currentDate, dateDebut);

            if (counge.getDateFin().isBefore(counge.getDateDebut())) {
                if (differenceEnJours < 3) {
                    throw new com.example.carecareforeldres.Entity.ErrorResponseException("La date de début du congé doit être au moins 3 jours après la date actuelle.", HttpStatus.BAD_REQUEST);
                }
                throw new com.example.carecareforeldres.Entity.ErrorResponseException("La date de fin est antérieure à la date de début.", HttpStatus.BAD_REQUEST);
            } else {
                if (!aPrisConge) {
                    if (counge.getTypeCounger().equals(TypeCounger.COUNGER_MATERNITE) && cuisinier.getSexe().equals(Sexe.FEMME) && dureeNouvelleConge < 45 && coungeRepository.countCongesByCuisinierAndYear(cuisinier, currentDate, TypeCounger.COUNGER_MATERNITE) == 0) {
                        coungeRepository.save(counge);
                        return new ResponseEntity<>("Coungé Ajouter avec succès", HttpStatus.OK);
                    }
                    if (counge.getTypeCounger().equals(TypeCounger.COUGER_PARENTALE) && cuisinier.getSexe().equals(Sexe.HOMME) && dureeNouvelleConge < 3 && coungeRepository.countCongesByCuisinierAndYear(cuisinier, currentDate, TypeCounger.COUGER_PARENTALE) == 0) {
                        coungeRepository.save(counge);
                        return new ResponseEntity<>("Coungé Ajouter avec succès", HttpStatus.OK);
                    }
                    throw new com.example.carecareforeldres.Entity.ErrorResponseException("tu a déjà pris un congé récemment", HttpStatus.BAD_REQUEST);
                }

                if (joursCongesCetteAnnee + dureeNouvelleConge > 30) {
                    if (counge.getTypeCounger().equals(TypeCounger.COUNGER_MATERNITE) && coungeRepository.countCongesByCuisinierAndYear(cuisinier, currentDate, TypeCounger.COUNGER_MATERNITE) == 0) {
                        if (cuisinier.getSexe().equals(Sexe.FEMME) && dureeNouvelleConge < 45) {
                            coungeRepository.save(counge);
                            return new ResponseEntity<>("Coungé Ajouter avec succès", HttpStatus.OK);
                        } else {
                            throw new com.example.carecareforeldres.Entity.ErrorResponseException("Le congé de maternité est limité à 45 jours pour les femmes", HttpStatus.BAD_REQUEST);
                        }
                    }
                    if (counge.getTypeCounger().equals(TypeCounger.COUGER_PARENTALE) && cuisinier.getSexe().equals(Sexe.HOMME) && dureeNouvelleConge < 3 && coungeRepository.countCongesByCuisinierAndYear(cuisinier, currentDate, TypeCounger.COUGER_PARENTALE) == 0) {
                        coungeRepository.save(counge);
                        return new ResponseEntity<>("Coungé Ajouter avec succès", HttpStatus.OK);
                    }
                    throw new com.example.carecareforeldres.Entity.ErrorResponseException("Le cuisinier aura plus de 30 jours de congé cette année après l'ajout de cette nouvelle demande de congé.", HttpStatus.BAD_REQUEST);
                }

                coungeRepository.save(counge);
                return new ResponseEntity<>("Coungé Ajouter avec succès", HttpStatus.OK);
            }
        } catch (ErrorResponseException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            throw new com.example.carecareforeldres.Entity.ErrorResponseException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    public boolean aPrisCongeDerniersQuatreMois(List<Counge> congés) {
        LocalDateTime maintenant = LocalDateTime.now();
        LocalDateTime ilYaQuatreMois = maintenant.minusMonths(2);

        for (Counge congé : congés) {
            LocalDate dateDebutConge = congé.getDateDebut();
            LocalDateTime dateDebutLocalDateTime = dateDebutConge.atStartOfDay(); // Convertir LocalDate en LocalDateTime en ajoutant une heure arbitraire

            if (dateDebutLocalDateTime.isAfter(ilYaQuatreMois) && dateDebutLocalDateTime.isBefore(maintenant)) {
                return true;
            }
        }

        return false;
    }

}