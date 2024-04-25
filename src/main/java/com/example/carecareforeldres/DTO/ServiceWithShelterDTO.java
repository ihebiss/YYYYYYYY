package com.example.carecareforeldres.DTO;

import com.example.carecareforeldres.Entity.TypeService;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceWithShelterDTO {
    private Long idService;
    private String nomService;
    private String description;
    private TypeService typeService;
    private Long idShelter;
    private String nomShelter;
}
