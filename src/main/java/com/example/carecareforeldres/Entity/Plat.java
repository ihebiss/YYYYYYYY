package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlat;

    private String nomPlat;
    private String descPlat;
    private float prixPlat;
    private String image;
    private LocalDate datePlat;//date l'ajout plat délai
    private Integer likePlat;
    private Integer dislikePlat;
    @ManyToMany(mappedBy = "plats")
    @JsonIgnore
    private List<Repas> repas;


    @Enumerated(EnumType.STRING)
    private TypePlat typePlat;
    @Enumerated(EnumType.STRING)
    private TypeRepas typeRepas;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "plat_ingredient",joinColumns = @JoinColumn(name = "plat_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))

    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant restaurant;





    @ManyToOne(cascade = CascadeType.ALL)
    private Cuisinier cuisinier;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "plat")
    List<LikeDislikePlat> likeDislikePlats;



}
