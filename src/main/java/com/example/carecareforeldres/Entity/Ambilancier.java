package com.example.carecareforeldres.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ambilancier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAmbilancier;
    private Boolean disponible;
    private Integer user;
   private String nom;
     private  String prenom;
    private String mail;
    @OneToOne(cascade = CascadeType.ALL)
    Ambulance ambulance;
}
