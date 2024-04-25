package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRestaurant;

    private String nomResto;
    private String address;
    private String image;
    private Integer tel;

    private Boolean status_ouv;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="restaurant")
    @JsonIgnore
    private Set<Plat> Plats;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="restaurantC")
    @JsonIgnore
    private List<Cuisinier> cuisiniers;

    @OneToOne
    @JsonIgnore
    private Etablissement etablissement;


}
