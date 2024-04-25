package com.example.carecareforeldres.DTO;


import lombok.Data;

import java.util.Date;

@Data

public class CommentaireDto {
    private Long id;
    private Date date;
    private String contenu;
    private String statut;

}
