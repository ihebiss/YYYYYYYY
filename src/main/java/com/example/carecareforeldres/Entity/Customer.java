package com.example.carecareforeldres.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    private Integer idIngredient;

    private String nomIngredient;
    private Float calorie;
    private Integer quantite;
    private Boolean consommable;
    private LocalDateTime dateAjout;//date l'ajout plat délai

}
