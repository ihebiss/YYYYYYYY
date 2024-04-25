package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Commentaire;
import com.example.carecareforeldres.Entity.Evennement;
import com.example.carecareforeldres.Repository.CommantaireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentaireService implements ICommentaireService{

    private final  CommantaireRepository commentaireRepository;
    private final EvennementService evennementService;

    @Override
    public List<Commentaire> getCommentsByEvennementId(Long evennementId) {
        Evennement evennement = evennementService.getEvennementById(evennementId);
        return evennement.getCommentaires();
    }

    @Override
    public Commentaire addCommentToEvennement(Long evennementId, Commentaire commentaire) {
        Evennement evennement = evennementService.getEvennementById(evennementId);
        commentaire.setEvennement(evennement);
        return commentaireRepository.save(commentaire);
    }

     @Override
    public List<Commentaire> getCommentsByEventId(Long eventId) {
        return commentaireRepository.findByEvennementId(eventId);
    }

    @Override
    public Commentaire addComment(Commentaire comment) {
        return commentaireRepository.save(comment);
    }
    @Override
    public Commentaire getCommentaireById(Long id) {
        return commentaireRepository.findById(id).get();
    }

    @Override
    public Commentaire updateCommentaire( Commentaire commentaire) {
            return commentaireRepository.save(commentaire);
        }

    @Override
    public void deleteCommentaire(Long id) {
        commentaireRepository.deleteById(id);
    }



    @Override
    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }
}