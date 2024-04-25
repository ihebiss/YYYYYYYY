package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Homeless;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHomelessService {
    List<Homeless> retrieveAllHomeless();

    Homeless addHomeless(Homeless s);

    Homeless updateHomeless(Homeless s);

    Homeless retrieveHomeless(Long idHomeless);

    void removeHomeless(Long idHomeless);

    public ResponseEntity<?> connectHomelessToShelter(Long idHomeless, Long idShelter) ;

}