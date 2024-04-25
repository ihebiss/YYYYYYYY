package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Reponse;

import java.util.List;

public interface IReponseService {
    Reponse saveReponse(Reponse reponse);
    Reponse getReponseById(Long id);
    Reponse updateReponse( Reponse reponse);
    void deleteReponse(Long id);
    List<Reponse> getAllReponses();





}