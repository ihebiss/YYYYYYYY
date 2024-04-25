package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Medicament;

import java.util.List;

public interface IServiceMedicament {
    Medicament addMed(Medicament med);

    List<Medicament> getAllMed();

    void remove(int idf);

    Medicament update(Medicament med);
}
