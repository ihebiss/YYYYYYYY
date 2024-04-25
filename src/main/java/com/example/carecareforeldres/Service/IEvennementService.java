package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.EvennementDto;
import com.example.carecareforeldres.Entity.Evennement;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IEvennementService {
        Evennement saveEvennement(EvennementDto evennementDto);
        EvennementDto getEvennementDtoById(Long id);

        Evennement getEvennementById(Long id);
        EvennementDto updateEvennement(EvennementDto evennementDto);
        void deleteEvennement(Long id);
        List<EvennementDto> getAllEvennements();

        List<Object[]> getTopFiveUsersWithMostComments() ;

        List<EvennementDto> getEvennementDtoByDate(Date date);

        EvennementDto toDto(Evennement evennement); // Nouvelle méthode pour la conversion


        boolean isDateOccupied(LocalDate date);
        void registerUserToEvent(Long eventId, Long userId) ;


        //  void registerUserToEvent(Long eventId, Long userId);

}