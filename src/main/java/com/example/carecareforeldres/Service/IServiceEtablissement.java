package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Etablissement;

import java.io.IOException;
import java.util.List;

public interface IServiceEtablissement {
    public Etablissement addEtablissement(Etablissement etablissement) throws IOException;
    public List<Etablissement> retrieveAllEtablissements();

    public Etablissement updateEtablissement(Etablissement etablissement)throws IOException;

    public Etablissement retrieveEtablissement(Long idEtab);

    void removeEtablissement(Long idEtab);
    public void Changement_Etat_Ambul();
    public void MaintenanceToFonctionelle();
    public void EtatAmbulance();



}
