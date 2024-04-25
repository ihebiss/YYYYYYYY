package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.ShelterDonationDTO;
import com.example.carecareforeldres.Entity.Achievement;
import com.example.carecareforeldres.Entity.Aide;
import com.example.carecareforeldres.Entity.Shelter;
import com.example.carecareforeldres.Entity.TypeAide;
import com.example.carecareforeldres.Repository.AchievementRepository;
import com.example.carecareforeldres.Repository.AideRepository;
import com.example.carecareforeldres.Repository.HomelessRepository;
import com.example.carecareforeldres.Repository.ShelterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ShelterService implements IShelterService{
    ShelterRepository shelterRepository;
    AideRepository aideRepository;

    AchievementRepository achievementRepository;

    HomelessRepository homelessRepository;
    @Override
    public List<Shelter> retrieveAllShelter() {
        return shelterRepository.findAll();
    }

    @Override
    public Shelter addShelter(Shelter s) {
        return shelterRepository.save(s);
    }

    @Override
    public Shelter updateShelter(Shelter s) {
        return shelterRepository.save(s);
    }

    @Override
    public Shelter retrieveShelter(Long idShelter) {
        return shelterRepository.findById(idShelter).get();
    }

    @Override
    public void removeShelter(Long idShelter) {
        shelterRepository.deleteById(idShelter);
    }

    @Override
    public Shelter assignAideToShelter(Long idAide,Shelter shelter) {
        Aide aide = aideRepository.findById(idAide).get();
        shelter.getAides().add(aide);
        shelterRepository.save(shelter);

        return shelter;
    }

    @Override
    public List<ShelterDonationDTO> calculerMontantTotalEtRestant() {
        List<Shelter> shelters = shelterRepository.findAll();
        List<ShelterDonationDTO> donations = new ArrayList<>();

        for (Shelter shelter : shelters) {
            if (shelter.getCause() != null) {
                float montantTotal = 0;
                for (Aide aide : shelter.getAides()) {
                    if (aide.getTypeAide() == TypeAide.MONEY) {
                        montantTotal += aide.getMontant();
                    }
                }
                float montantRestant = Math.max(shelter.getObjectifFinancier() - montantTotal, 0);


                ShelterDonationDTO donationDTO = new ShelterDonationDTO();
                donationDTO.setIdShelter(shelter.getIdShelter());
                donationDTO.setNomShelter(shelter.getNomShelter());
                donationDTO.setCause(shelter.getCause());
                donationDTO.setMontantTotalAide(shelter.getMontantTotalAide());
                donationDTO.setObjectifFinancier(shelter.getObjectifFinancier());
                donationDTO.setMontantRestant(montantRestant);
                if (shelter.getImageCause() != null) {
                    donationDTO.setImageCause(shelter.getImageCause());
                }


                donations.add(donationDTO);
            }
        }
        return donations;
    }

    //   @Override
    //   public void addCauseToShelter(Long shelterId, ShelterDonationDTO causeDTO) {
    //      Shelter shelter = shelterRepository.findById(shelterId).orElse(null);
    //      if (shelter != null) {
    //          shelter.setCause(causeDTO.getCause());
    //          shelter.setImageCause(causeDTO.getImageCause());
    //         shelter.setObjectifFinancier(causeDTO.getObjectifFinancier());
    //         shelterRepository.save(shelter);
    //      }
    //  }
    @Override
    public void addCauseToShelter(Long shelterId, ShelterDonationDTO causeDTO) {
        Shelter shelter = shelterRepository.findById(shelterId).orElse(null);
        if (shelter != null) {
            // Vérifier si l'abri a déjà une cause
            if (shelter.getCause() != null ) {
                throw new IllegalStateException("Cet abri a déjà une cause en cours. Vous ne pouvez pas ajouter une nouvelle cause.");
                // Ou vous pouvez renvoyer un message d'erreur ou un code approprié au lieu de lancer une exception
            }

            shelter.setCause(causeDTO.getCause());
            shelter.setImageCause(causeDTO.getImageCause());
            shelter.setObjectifFinancier(causeDTO.getObjectifFinancier());

            shelterRepository.save(shelter);
        }
    }
    @Override
    public void deleteCauseFromShelter(Long shelterId) {
        Shelter shelter = shelterRepository.findById(shelterId).orElse(null);
        if (shelter != null) {
            shelter.setCause(null); // Supprime la cause
            shelter.setImageCause(null); // Supprime l'image de la cause
            shelter.setObjectifFinancier(0);
            shelter.setMontantTotalAide(0);// Remet à zéro l'objectif financier
            // Vous pouvez ajouter d'autres attributs à réinitialiser selon vos besoins

            // Enregistre les modifications
            shelterRepository.save(shelter);
        }
    }
    @Override
    public Shelter updateCauseInShelter(Long shelterId, ShelterDonationDTO causeDTO) {
        Shelter shelter = shelterRepository.findById(shelterId).orElse(null);
        if (shelter != null) {
            shelter.setCause(causeDTO.getCause());
            shelter.setImageCause(causeDTO.getImageCause());
            shelter.setObjectifFinancier(causeDTO.getObjectifFinancier());
            shelter.setMontantTotalAide(causeDTO.getMontantTotalAide());
            // Autres attributs de mise à jour selon vos besoins

            return shelterRepository.save(shelter);
        } else {
            return null; // Ou lancez une exception selon vos besoins
        }
    }
    @Override
    public void deleteCauseFromShelterAndRemlitAchievement(Long shelterId) {
        Shelter shelter = shelterRepository.findById(shelterId).orElse(null);
        if (shelter != null) {
            float progress = (shelter.getMontantTotalAide() / shelter.getObjectifFinancier()) * 100;
            if (progress >= 100) {
                Achievement achievement = new Achievement();
                achievement.setIdShelter(shelter.getIdShelter());
                achievement.setNomShelter(shelter.getNomShelter());
                achievement.setCause(shelter.getCause());
                achievement.setImageCause(shelter.getImageCause());
                achievement.setMontantTotalAide(shelter.getMontantTotalAide());
                achievement.setObjectifFinancier(shelter.getObjectifFinancier());

                achievementRepository.save(achievement);

                // Supprimez la cause du Shelter
                shelter.setCause(null);
                shelter.setImageCause(null);
                shelter.setObjectifFinancier(0);
                shelter.setMontantTotalAide(0);
                shelterRepository.save(shelter);
            }
        }
    }

    @Override
    public List<Achievement> retrieveAllAchievement() {
        return achievementRepository.findAll();
    }


}
