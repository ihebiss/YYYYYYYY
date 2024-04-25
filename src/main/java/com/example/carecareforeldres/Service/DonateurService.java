package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.DonateurDTO;
import com.example.carecareforeldres.Entity.Aide;
import com.example.carecareforeldres.Entity.Donateur;
import com.example.carecareforeldres.Entity.Shelter;
import com.example.carecareforeldres.Entity.TypeAide;
import com.example.carecareforeldres.Repository.DonateurRepository;
import com.example.carecareforeldres.Repository.ShelterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class DonateurService implements IDonateurService{
    DonateurRepository donateurRepository;
    ShelterRepository shelterRepository;
    @Override
    public List<Donateur> retrieveAllDonateur() {
        return donateurRepository.findAll();
    }

    @Override
    public Donateur addDonateur(Donateur s) {
        return donateurRepository.save(s);
    }

    @Override
    public Donateur updateDonateur(Donateur s) {
        return donateurRepository.save(s);
    }

    @Override
    public Donateur retrieveDonateur(Long idDonateur) {
        return donateurRepository.findById(idDonateur).get();
    }

    @Override
    public void removeDonateur(Long idDonateur) {
        donateurRepository.deleteById(idDonateur);
    }

    @Override
    public Donateur ajouterDonateurAvecAideEtAffectShelter(Donateur donateur, Aide aide, Long idShelter) {
        Shelter shelter = shelterRepository.findById(idShelter).get();
        shelter.getAides().add(aide);

        donateur.getAides().add(aide);
        aide.getDonateurs().add(donateur);
        return donateurRepository.save(donateur);
    }

    // @Scheduled(cron = "*/10 * * * * *") // Exécution toutes les 10 secondes
    // @Transactional()
    //  public void calculateTotalAmountAndSheltersList() {
    //   List<Donateur> donateurs = donateurRepository.findAll();
    // for (Donateur donateur : donateurs) {
    // Charger explicitement la collection 'aides'
    //    Hibernate.initialize(donateur.getAides());

    //     float montantTotal = 0;

    //   for (Aide aide : donateur.getAides()) {
    //      montantTotal += aide.getMontant();
    //   }

    // Afficher le nom du donateur et le montant total dans la console
    //  log.info("Donateur: {} | Montant total: {}", donateur.getFirstname() + " " + donateur.getLastname(), montantTotal);
    // Vous pouvez ajouter ici la logique pour afficher la liste des shelters qui ont aidé le donateur
    //  }
    //  }
    //
    //
    @Override
    public List<DonateurDTO> calculateTotalAmountAndSheltersList() {
        List<Donateur> donateurs = donateurRepository.findAll();
        return donateurs.stream().map(donateur -> {
            Hibernate.initialize(donateur.getAides());

            float montantTotal = 0;
            int quantiteTotaleVetements = 0;
            int quantiteTotaleMedicaments = 0;
            int dureeTotaleHeuresVolontariat = 0;
            int surfaceTotaleEspace = 0;
            String adresseEspace = "";
            TypeAide typeAide = null;
            for (Aide aide : donateur.getAides()) {
                montantTotal += aide.getMontant();
                typeAide = aide.getTypeAide();
                switch (aide.getTypeAide()) {
                    case CLOTHES:
                        quantiteTotaleVetements += aide.getQuantiteClothes();
                        break;
                    case MEDICAMENT:
                        quantiteTotaleMedicaments += aide.getQuantiteMedication();
                        break;
                    case VOLUNTEER_HOURS:
                        dureeTotaleHeuresVolontariat += aide.getDuree();
                        break;
                    case ADDITIONAL_SPACE:
                        surfaceTotaleEspace += aide.getSurface();
                        adresseEspace = aide.getAddress();
                        break;
                    default:
                        break;
                }
            }

            DonateurDTO donateurDTO = new DonateurDTO();
            donateurDTO.setNom(donateur.getFirstname());
            donateurDTO.setPrenom(donateur.getLastname());
            donateurDTO.setMontantTotal(montantTotal);
            donateurDTO.setShelters(donateur.getAides().stream()
                    .map(aide -> aide.getShelters().stream().map(Shelter::getNomShelter).collect(Collectors.toList()))
                    .flatMap(List::stream).distinct().collect(Collectors.toList()));
            donateurDTO.setQuantiteTotaleVetements(quantiteTotaleVetements);
            donateurDTO.setQuantiteTotaleMedicaments(quantiteTotaleMedicaments);
            donateurDTO.setDureeTotaleHeuresVolontariat(dureeTotaleHeuresVolontariat);
            donateurDTO.setSurfaceTotaleEspace(surfaceTotaleEspace);
            donateurDTO.setAdresseEspace(adresseEspace);
            donateurDTO.setTypeAide(typeAide);
            return donateurDTO;
        }).collect(Collectors.toList());
    }
}

