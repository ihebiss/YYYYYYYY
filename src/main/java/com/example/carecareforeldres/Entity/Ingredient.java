package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngredient;

    private String nomIngredient;
    private Float calorie;
    private Integer quantite;
    private Boolean consommable;
    private LocalDateTime dateAjout;//date l'ajout plat délai


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "ingredients")
    @JsonIgnore
    private List<Plat> plats = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Maladie> maladies=new ArrayList<>();


}
