package com.example.carecareforeldres.DTO;

import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Morgue;
import com.example.carecareforeldres.Entity.Patient;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorgueDto {
    Long idMorgue;
    Integer nbCadavre;
    List<Patient> patients;
    Etablissement etablissement;

    public static MorgueDto toDto(Morgue Entity){
       return MorgueDto.builder()
                .idMorgue(Entity.getIdMorgue())
                .nbCadavre(Entity.getNbCadavre())
                .patients(Entity.getPatients())
                .etablissement(Entity.getEtablissement())
                .build();
    }
    public static Morgue toEntity(MorgueDto Entity){
        return Morgue.builder()
                .idMorgue(Entity.getIdMorgue())
                .nbCadavre(Entity.getNbCadavre())
                .patients(Entity.getPatients())
                .etablissement(Entity.getEtablissement())
                .build();
    }
}


