package com.example.carecareforeldres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evennement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomevennement ;
    private String discription ;
    private LocalDate date;


    private Integer placeMax  ;

    private Float  lng ;
    private Float  lat ;
    private String image ;

    private String etat ;

    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @ManyToMany(mappedBy = "evennements" )
    private List<User> users ;

    @OneToMany(mappedBy = "evennement", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;




}