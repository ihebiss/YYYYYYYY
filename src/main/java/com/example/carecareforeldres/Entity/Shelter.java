package com.example.carecareforeldres.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Shelter")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Shelter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShelter;

    private String nomShelter;

    private Long capaciteShelter;

    private String location;

    private String tel;

    private  String description ;
    private  String image ;

    private float montantTotalAide;

    private float objectifFinancier;
    private String cause;
    private String imageCause;

    private Long nbrPlaceDisponible;

    private String statut;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Aide> aides = new ArrayList<>();

    // @OneToMany(cascade = CascadeType.ALL, mappedBy="shelter")
    //  private List<User> homelesses;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="shelter")
    private List<Service> Services;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="shelter")
    private List<Homeless> Homelesss = new ArrayList<>();
}
