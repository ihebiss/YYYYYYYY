package com.example.carecareforeldres.mapper;

import com.example.carecareforeldres.Entity.Evennement;
import com.example.carecareforeldres.DTO.EvennementDto;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface EvennementMapper {
   //@Mapping(source =  "ali",target = "etat")
   Evennement toEntity(EvennementDto dto);

   EvennementDto toDto(Evennement event);

   List<EvennementDto> toDtos(List<Evennement> entities);


}

