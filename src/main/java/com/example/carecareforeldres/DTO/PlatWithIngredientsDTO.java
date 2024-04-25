package com.example.carecareforeldres.DTO;

import com.example.carecareforeldres.Entity.TypePlat;
import com.example.carecareforeldres.Entity.TypeRepas;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class PlatWithIngredientsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlat;
    private String nomPlat;
    private float prixPlat;
    private String descPlat;
    private TypePlat typePlat;
    private TypeRepas typeRepas;
    private Integer likePlat;
    private Integer dislikePlat;
    private String image;
    private  Integer CuisinierId;


    private List<Integer> ingredientIds =new ArrayList<>();
    private List<String> nomIngredients =new ArrayList<>();


}
