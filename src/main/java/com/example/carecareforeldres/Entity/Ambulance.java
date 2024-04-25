package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Ambulance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAmb;
    String marque;
    String matricule;
    boolean busy;
    String image;
    @Enumerated(EnumType.STRING)
    EtatAmb etatAmb;
    LocalDate dateDernEntret;
    @ManyToOne(cascade = CascadeType.ALL)
    Etablissement etablissement;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "ambulance")
    Ambilancier ambilancier;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "ambulance",fetch = FetchType.EAGER)
    List<Patient> patients;

}


