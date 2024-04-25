package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedicament;

    private String nomMed;
    private String image;
    private String DescMed;
    private Float prixMed;
    private Date dateMed;//date l'ajout plat délai
    @Enumerated(EnumType.STRING)
    private CatMedicament CatMedicament;
}
