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
@Table(name = "Aide")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Aide implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAide;

    private String description;

    private Float duree;

    private Float montant;


    private Integer quantiteClothes;
    private String typeMedicament;
    private Integer quantiteMedication;

    private Integer surface;
    private String address;

    @Enumerated(EnumType.STRING)
    private TypeAide typeAide;


    @JsonIgnore
    @ManyToMany(mappedBy="aides", cascade = CascadeType.ALL)
    private List<Shelter> shelters = new ArrayList<>();
    @JsonIgnore
    @ManyToMany(mappedBy="aides", cascade = CascadeType.ALL)
    private List<Donateur> donateurs = new ArrayList<>();
}

