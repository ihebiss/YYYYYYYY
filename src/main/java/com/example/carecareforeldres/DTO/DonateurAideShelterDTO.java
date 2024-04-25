package com.example.carecareforeldres.DTO;

import com.example.carecareforeldres.Entity.Aide;
import com.example.carecareforeldres.Entity.Donateur;
import com.example.carecareforeldres.Entity.Shelter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DonateurAideShelterDTO {
    private Donateur donateur;
    private Aide aide;
    private Long idShelter;
    private List<Shelter> shelters;
}

