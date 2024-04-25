package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ambilancier;

import java.util.List;

public interface IServiceAmbilancier {
    public Ambilancier addAmbilancier(Ambilancier ambilancier);
    public List<Ambilancier> retrieveAllAmbilanciers();

    public Ambilancier updateAmbilancier(Ambilancier ambilancier);

    public Ambilancier retrieveAmbilancier(Integer idAmbilancier);
    public Ambilancier addAmbulancierAndAssignToAmbulance (Ambilancier ambilancier, Long idAmb);
    public Ambilancier UnsignAmbulancierfromAmbulance ( Integer idAmbilancier);


    void removeAmbilancier(Integer idAmbilancier);

}
