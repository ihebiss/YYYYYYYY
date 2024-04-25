package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Repas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private LocalDate dateRepas;

    @Enumerated(EnumType.STRING)
    private TypeRepas typeRepas;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "repas_plat",joinColumns = @JoinColumn(name = "repas_id"), inverseJoinColumns = @JoinColumn(name = "plat_id"))
    private List<Plat> plats;



    @ManyToOne(cascade = CascadeType.ALL)
    private Patient patient;
}
