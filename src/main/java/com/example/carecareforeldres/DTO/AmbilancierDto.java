package com.example.carecareforeldres.DTO;

import com.example.carecareforeldres.Entity.Ambulance;
import com.example.carecareforeldres.Entity.Ambilancier;
import com.example.carecareforeldres.Entity.Ambulance;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmbilancierDto {
    Integer idAmbilancier;
     Boolean disponible;
     Integer user;
    String nom;
    String prenom;
    String mail;
    Ambulance ambulance;
    public static AmbilancierDto toDto(Ambilancier Entity){
        return AmbilancierDto.builder()
                .idAmbilancier(Entity.getIdAmbilancier())
                .nom(Entity.getNom())
                .disponible(Entity.getDisponible())
                .user(Entity.getUser())
                .prenom(Entity.getPrenom())
                .mail(Entity.getMail())
                .ambulance(Entity.getAmbulance())
                .build();
    }
    public static Ambilancier toEntity(AmbilancierDto Entity){
        return Ambilancier.builder()
                .idAmbilancier(Entity.getIdAmbilancier())
                .nom(Entity.getNom())
                .disponible(Entity.getDisponible())
                .user(Entity.getUser())
                .prenom(Entity.getPrenom())
                .mail(Entity.getMail())
                .ambulance(Entity.getAmbulance())
                .build();
    }
}
