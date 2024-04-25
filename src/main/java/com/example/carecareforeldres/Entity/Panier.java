package com.example.carecareforeldres.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "panier")
public class Panier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPanier")
    private Long idPanier; // Clé primaire

    private float prix_total;

    private int nombreTotalProduits;
    @OneToOne

    private Commande commande;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "panier_produit",
            joinColumns = @JoinColumn(name = "panier_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private Set<Produit> produits;
    @ElementCollection
    @CollectionTable(name = "panier_produit_quantite", joinColumns = @JoinColumn(name = "panier_id"))
    @MapKeyJoinColumn(name = "produit_id")
    @Column(name = "quantite")
    private Map<Produit, Integer> produitsQuantite = new HashMap<>();;

    public Set<Produit> getProduits() {
        return this.produits;
    }

    public float getPrix_total() {
        return this.prix_total;
    }
    public Map<Produit, Integer> getProduitsQuantite() {
        return produitsQuantite;
    }
    public void setNombreTotalProduits(int nombreTotalProduits) {
        this.nombreTotalProduits = nombreTotalProduits;
    }
}