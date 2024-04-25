package com.example.carecareforeldres.DTO;

import com.example.carecareforeldres.Entity.TypeRepas;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RepasAvecPlatsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TypeRepas typeRepas;


    private List<Integer> PlatsIds =new ArrayList<>();

}
