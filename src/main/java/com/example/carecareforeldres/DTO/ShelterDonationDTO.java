package com.example.carecareforeldres.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShelterDonationDTO {
    private Long idShelter;
    private String nomShelter;
    private String cause;
    private String imageCause;
    private float montantTotalAide;
    private float objectifFinancier;
    private float montantRestant;
}
