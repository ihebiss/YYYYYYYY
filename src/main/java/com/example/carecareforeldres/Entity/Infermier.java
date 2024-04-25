package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Infermier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInfermier;
    private Boolean disponiblenf;
    private Integer user;
    String nom;
    String prenom;
    String mail;
    @ManyToOne(cascade = CascadeType.ALL)
    Etablissement etablissement;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "infermier")
    List<Patient> patients;
}
