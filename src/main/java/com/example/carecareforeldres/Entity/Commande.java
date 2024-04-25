package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "commande")
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCommande")
    private Long idCommande; // Clé primaire
    @Enumerated(EnumType.STRING)
    private StatutsCom statut;
    @Temporal(TemporalType.DATE)
    private Date date_commande;
    private float montantTotal;
    @Enumerated(EnumType.STRING)
    private ModePay modePay;

    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore
    @OneToOne(mappedBy="commande")
    @JsonBackReference
    private Panier panier;

}
