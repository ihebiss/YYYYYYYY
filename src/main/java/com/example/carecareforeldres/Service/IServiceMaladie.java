package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Maladie;

import java.util.List;

public interface IServiceMaladie {
    Maladie add(Maladie res);

    List<Maladie> getAll();

    void remove(int idf);

    Maladie update(Maladie res);
}
