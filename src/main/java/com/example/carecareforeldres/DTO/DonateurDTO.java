package com.example.carecareforeldres.DTO;

import com.example.carecareforeldres.Entity.TypeAide;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class DonateurDTO {
    private String nom;
    private String prenom;
    private float montantTotal;
    private List<String> shelters;
    private int quantiteTotaleVetements;
    private int quantiteTotaleMedicaments;
    private int dureeTotaleHeuresVolontariat;
    private int surfaceTotaleEspace;
    private String adresseEspace;
    private TypeAide typeAide;
}
