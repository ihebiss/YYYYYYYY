package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Counge;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceCounge {
    Counge add(Counge res);

    List<Counge> getAll();

    void remove(int idf);

    Counge update(Counge res, Integer CuisinierId);

    ResponseEntity<?> DemandeCoungeCuisine(Counge counge, Integer CuisinierId);
}
