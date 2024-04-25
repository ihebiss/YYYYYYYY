package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String contenu;
    private String statut;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Evennement evennement;
    @JsonIgnore
    @OneToMany(mappedBy = "commantaires")
    private List<Reponse> reponses;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User user;


}
