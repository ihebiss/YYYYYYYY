package com.example.carecareforeldres.DTO;

import com.example.carecareforeldres.Entity.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtablissementDto {
    Long idEtab;
    String nomEtab;
    String numFixe;
    String adresse;
    @Enumerated(EnumType.STRING)
    TypeEtab typeEtab;
    Integer nbLits;
    float prixNuit;
    String image;
     Double x ;
     Double y ;
    List<Patient> patients;
    List<Ambulance>ambulances;
    List<Medecin>medecins;
    List<Infermier>infermiers;
    Morgue morgue;
    public static EtablissementDto toDto(Etablissement Entity){
       return EtablissementDto.builder()
               .idEtab(Entity.getIdEtab())
               .adresse(Entity.getAdresse())
               .nbLits(Entity.getNbLits())
               .nomEtab(Entity.getNomEtab())
               .typeEtab(Entity.getTypeEtab())
               .numFixe(Entity.getNumFixe())
               .patients(Entity.getPatients())
               .prixNuit(Entity.getPrixNuit())
               .morgue(Entity.getMorgue())
               .ambulances(Entity.getAmbulances())
               .medecins(Entity.getMedecins())
               .infermiers(Entity.getInfermiers())
               .x((Entity.getX()))
               .y((Entity.getY()))
               .image(Entity.getImage())
               .build();
   }
    public static Etablissement toEntity(EtablissementDto Entity){
        return Etablissement.builder()
                .idEtab(Entity.getIdEtab())
                .adresse(Entity.getAdresse())
                .nbLits(Entity.getNbLits())
                .nomEtab(Entity.getNomEtab())
                .typeEtab(Entity.getTypeEtab())
                .numFixe(Entity.getNumFixe())
                .prixNuit(Entity.getPrixNuit())
                .morgue(Entity.getMorgue())
                .ambulances(Entity.getAmbulances())
                .medecins(Entity.getMedecins())
                .infermiers(Entity.getInfermiers())
                .image(Entity.getImage())
                .x((Entity.getX()))
                .y((Entity.getY()))
                .build();
    }

   }
