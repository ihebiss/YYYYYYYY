package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.LikeDislikePlat;
import com.example.carecareforeldres.Entity.LikeDisliketRate;
import com.example.carecareforeldres.Entity.Patient;
import com.example.carecareforeldres.Entity.Plat;
import com.example.carecareforeldres.Repository.LikeDislikeRepository;
import com.example.carecareforeldres.Repository.PlatRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LikeDislikeService implements IServiceLikeDislike{

    LikeDislikeRepository likeDislikePlatRepository;
    PlatRepository platRepository;

    public int numberOfDisikesofPlat(Integer platId){
        try {
            Plat plat = platRepository.findById(platId).orElse(null);
            if (plat == null) {
                return 0;
            }
            int dislikes = 0;
            for (LikeDislikePlat e : plat.getLikeDislikePlats()) {
                if (e.getLikeDisliketRate().equals(LikeDisliketRate.DISLIKE)) {
                    dislikes++;
                }
            }
            return dislikes;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public int numberOflikesofPlat(Integer platId){
        try {
            Plat plat = platRepository.findById(platId).orElse(null);
            if (plat == null) {
                return 0;
            }
            int likes = 0;
            for (LikeDislikePlat e : plat.getLikeDislikePlats()) {
                if (e.getLikeDisliketRate().equals(LikeDisliketRate.LIKE)) {
                    likes++;
                }
            }
            return likes;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void addOrUpdateReaction(Patient patient, Plat plat, LikeDisliketRate reaction) {
        LikeDislikePlat existingReaction = likeDislikePlatRepository.findByPatientAndPlat(patient, plat);

        int likeCount = plat.getLikePlat() != null ? plat.getLikePlat() : 0;
        int dislikeCount = plat.getDislikePlat() != null ? plat.getDislikePlat() : 0;

        if (existingReaction != null) {
            if (existingReaction.getLikeDisliketRate() == reaction) {
                likeDislikePlatRepository.delete(existingReaction);
                if (reaction.equals(LikeDisliketRate.LIKE)) {
                    likeCount--;
                } else {
                    dislikeCount--;
                }
            } else {
                existingReaction.setLikeDisliketRate(reaction);
                likeDislikePlatRepository.save(existingReaction);
                if (reaction.equals(LikeDisliketRate.LIKE)) {
                    likeCount++;
                    dislikeCount--;
                } else {
                    likeCount--;
                    dislikeCount++;
                }
            }
        } else {
            LikeDislikePlat newReaction = new LikeDislikePlat();
            newReaction.setPatient(patient);
            newReaction.setPlat(plat);
            newReaction.setLikeDisliketRate(reaction);
            likeDislikePlatRepository.save(newReaction);
            if (reaction.equals(LikeDisliketRate.LIKE)) {
                likeCount++;
            } else {
                dislikeCount++;
            }
        }

        plat.setLikePlat(likeCount);
        plat.setDislikePlat(dislikeCount);
        platRepository.save(plat);
    }

}
