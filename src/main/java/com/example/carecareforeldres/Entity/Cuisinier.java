package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cuisinier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idC;
    private String nom;
    private String prenom;
    private LocalDate dateAjout;//date l'ajout plat délai
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    private Float salaire;
    private Boolean disponiblee;
    private Integer user;

    @Enumerated(EnumType.STRING)
    private TypeBadge typeBadge;
    private Integer score;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cuisinier")
    @JsonIgnore
    private List<Plat> plats;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cuisinierC")
    @JsonIgnore
    private List<Counge> counges;

    @ManyToOne
    private Restaurant restaurantC;


}
