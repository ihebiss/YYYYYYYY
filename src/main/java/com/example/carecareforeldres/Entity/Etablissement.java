package com.example.carecareforeldres.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Etablissement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEtab;
    String nomEtab;
    String numFixe;
    String adresse;
    String image;
    @Enumerated(EnumType.STRING)
    TypeEtab typeEtab;
    Integer nbLits;
    float prixNuit;
     Double x ;
     Double y ;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement",fetch = FetchType.EAGER)
    List<Ambulance>ambulances;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "etablissement")
    Morgue morgue;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement")
    List<Patient> patients;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement")
    List<Medecin>medecins;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etablissement")
    List<Infermier>infermiers;

    @OneToOne(mappedBy = "etablissement")
    private Restaurant restaurant;


}

