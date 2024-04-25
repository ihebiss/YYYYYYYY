package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Cuisinier;

import java.util.List;

public interface IServiceCuisinier {
    Cuisinier add(Cuisinier res);

    List<Cuisinier> getAll();

    void remove(int idf);

    Cuisinier update(Cuisinier res);
}
