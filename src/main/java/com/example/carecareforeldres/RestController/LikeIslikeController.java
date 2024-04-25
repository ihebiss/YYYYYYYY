package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.LikeDisliketRate;
import com.example.carecareforeldres.Entity.Patient;
import com.example.carecareforeldres.Entity.Plat;
import com.example.carecareforeldres.Repository.PatientRepository;
import com.example.carecareforeldres.Repository.PlatRepository;
import com.example.carecareforeldres.Service.LikeDislikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("/reactions")
@CrossOrigin("*")public class LikeIslikeController {
    PatientRepository patientRepository;
    LikeDislikeService likeDislikeService;
    PlatRepository platRepository;
    @PostMapping("/patient/{patientId}/plat/{platId}")
    public ResponseEntity<String> addOrUpdateReaction(
            @PathVariable Integer patientId,
            @PathVariable Integer platId,
            @RequestParam  LikeDisliketRate reaction) {


        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NotFoundException("Patient not found"));
        Plat plat = platRepository.findById(platId).orElseThrow(() -> new NotFoundException("Plat not found"));

        likeDislikeService.addOrUpdateReaction(patient, plat, reaction);

        return ResponseEntity.ok("Reaction added/updated successfully");
    }

    @GetMapping("/nbrPostlikes/{platId}")
    public Integer nbrLike( @PathVariable Integer platId){

        return likeDislikeService.numberOflikesofPlat(platId);
    }
    @GetMapping("/nbrPostDislikes/{platId}")
    public Integer nbrDisLike( @PathVariable Integer platId){

        return likeDislikeService.numberOfDisikesofPlat(platId);
    }
}
