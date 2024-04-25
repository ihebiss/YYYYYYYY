package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.ShelterDonationDTO;
import com.example.carecareforeldres.Entity.Achievement;
import com.example.carecareforeldres.Entity.Shelter;

import java.util.List;

public interface IShelterService {
    List<Shelter> retrieveAllShelter();

    Shelter addShelter(Shelter s);

    Shelter updateShelter(Shelter s);

    Shelter retrieveShelter(Long idShelter);

    void removeShelter(Long idShelter);

    public Shelter assignAideToShelter(Long idAide, Shelter shelter);

    public List<ShelterDonationDTO> calculerMontantTotalEtRestant() ;
    public void addCauseToShelter(Long shelterId, ShelterDonationDTO causeDTO) ;
    public void deleteCauseFromShelter(Long shelterId) ;
    public Shelter updateCauseInShelter(Long shelterId, ShelterDonationDTO causeDTO) ;
    public void deleteCauseFromShelterAndRemlitAchievement(Long shelterId) ;

    List<Achievement> retrieveAllAchievement();


}
