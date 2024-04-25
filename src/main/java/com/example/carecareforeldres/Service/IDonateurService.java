package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.DonateurDTO;
import com.example.carecareforeldres.Entity.Aide;
import com.example.carecareforeldres.Entity.Donateur;

import java.util.List;

public interface IDonateurService {
    List<Donateur> retrieveAllDonateur();

    Donateur addDonateur(Donateur s);

    Donateur updateDonateur(Donateur s);

    Donateur retrieveDonateur(Long idDonateur);

    void removeDonateur(Long idDonateur);

    public Donateur ajouterDonateurAvecAideEtAffectShelter(Donateur donateur, Aide aide, Long idShelter) ;
    public List<DonateurDTO> calculateTotalAmountAndSheltersList();
}
