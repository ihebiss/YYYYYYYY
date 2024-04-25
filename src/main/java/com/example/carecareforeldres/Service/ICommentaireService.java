package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Commentaire;

import java.util.List;

public interface ICommentaireService {


    List<Commentaire> getCommentsByEvennementId(Long evennementId);
    Commentaire addCommentToEvennement(Long evennementId, Commentaire commentaire);
    public Commentaire addComment(Commentaire comment) ;

        Commentaire getCommentaireById(Long id);
    Commentaire updateCommentaire( Commentaire commentaire);
    void deleteCommentaire(Long id);
    List<Commentaire> getAllCommentaires();
    public List<Commentaire> getCommentsByEventId(Long eventId) ;



}
