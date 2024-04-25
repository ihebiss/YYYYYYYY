package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpatient;
    private Integer user;
    String nom;
    String prenom;
    String mail;
    Double x ;
    Double y ;
    private String adresse;
    @Enumerated(EnumType.STRING)
    private TypePatient typatient;
    private Boolean archiver;
    private Float poid;
    private Float longueur;
    @Column(name = "datedeNais")
    private LocalDate datedeNais;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @ManyToOne(cascade = CascadeType.ALL)
    Ambulance ambulance;
    @ManyToOne(cascade = CascadeType.ALL)
    Etablissement etablissement;
    @ManyToOne(cascade = CascadeType.ALL)
    Infermier infermier;
    @ManyToOne(cascade = CascadeType.ALL)
    Morgue morgue;
    @ManyToOne(cascade = CascadeType.ALL)
    Medecin medecin;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
    @JsonIgnore
    List<Repas> repas;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Alert>alerts;



    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "patients")
    @JsonIgnore
    List<Maladie> maladies=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
    List<LikeDislikePlat> likeDislikePlats;


}
