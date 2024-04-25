package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Commentaire;
import com.example.carecareforeldres.Service.ICommentaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/commentaires")
public class CommentaireController {


   private final ICommentaireService commentaireService;

//////////////////////////////////////////////////////////////////////////////

    @PostMapping("/event/{eventId}/add-comment")
    public Commentaire addCommentToEvennement(@PathVariable Long eventId, @RequestBody Commentaire commentaire) {
        return commentaireService.addCommentToEvennement(eventId, commentaire);
    }
/////////////////////////////////
    @GetMapping("/event/{id}/comments")
    public List<Commentaire> getCommentsByEvennementId(@PathVariable("id") Long id) {

    return commentaireService.getCommentsByEvennementId(id);
}

    @GetMapping
    public List<Commentaire> getAllCommentaires() {
        List<Commentaire> commentaires = commentaireService.getAllCommentaires();
        return commentaires;


    }

    @GetMapping("/{id}")
    public Commentaire getCommentaireById(@PathVariable("id") Long id) {
        Commentaire commentaire = commentaireService.getCommentaireById(id);
        return commentaire;
    }




    @PutMapping("/{id}")
    public Commentaire updateCommentaire( @RequestBody Commentaire commentaire) {
    return commentaireService.updateCommentaire( commentaire);

        }



    @DeleteMapping("/{id}")
    public void deleteCommentaire(@PathVariable("id") Long id) {
        commentaireService.deleteCommentaire(id);
    }
}


