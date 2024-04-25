package com.example.carecareforeldres.DTO;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ReponseDto {

    private Long id;
    private String contenu;
    private LocalDate date;
}
