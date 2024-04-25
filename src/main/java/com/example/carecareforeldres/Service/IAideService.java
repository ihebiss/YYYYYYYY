package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Aide;

import java.util.List;

public interface IAideService {
    List<Aide> retrieveAllAide();

    Aide addAide(Aide a);

    Aide updateAide(Aide a);

    Aide retrieveAide(Long idAide);

    void removeAide(Long idAide);
}
