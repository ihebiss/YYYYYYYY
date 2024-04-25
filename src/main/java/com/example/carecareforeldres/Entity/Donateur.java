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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Donateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDonateur;
    private String firstname;
    private String lastname;
    private String email;
    private String nutelephone;
    private Integer user;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Aide> aides = new ArrayList<>();
}
