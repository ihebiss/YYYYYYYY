package com.example.carecareforeldres.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {
    private Integer idRestaurant;

    private String nomResto;
    private String address;
    private String image;
    private Integer tel;

    private Boolean status_ouv;
    private Long idEtab;
    private String nomEtab;
}
