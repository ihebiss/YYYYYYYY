package com.example.carecareforeldres.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IngredientAvecMaladieDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngredient;

    private String nomIngredient;
    private float calorie;
    private int quantite;

    private LocalDateTime dateAjout;

    private List<Integer> maladieIds =new ArrayList<>();


}
