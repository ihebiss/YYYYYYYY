package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Cuisinier;
import com.example.carecareforeldres.Entity.Restaurant;
import com.example.carecareforeldres.Entity.TypeBadge;
import com.example.carecareforeldres.Entity.User;
import com.example.carecareforeldres.Repository.CuisinierRepository;
import com.example.carecareforeldres.Repository.RestaurantRepository;
import com.example.carecareforeldres.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class CuisinierService implements IServiceCuisinier{
    CuisinierRepository cuisinierRepository;
    RestaurantRepository restaurantRepository;
    UserRepository userRepository;


public List<Cuisinier>top2Score(){
    return cuisinierRepository.findTop2ByOrderByScoreDesc();
}

    public List<Cuisinier> getAllUSers (){
        return this.cuisinierRepository.findAll();
    }
    @Override
    public Cuisinier add(Cuisinier res) {
        res.setScore(4);
        res.setDisponiblee(Boolean.FALSE);
        res.setDateAjout(LocalDate.now());

        return cuisinierRepository.save(res);}
    @Override
    public List<Cuisinier> getAll(){


        return cuisinierRepository.findAll();}

    public Map<String, Integer> getChefStats() {
        Map<String, Integer> chefStats = new HashMap<>();
        chefStats.put(TypeBadge.GOLD.toString(), cuisinierRepository.countByTypeBadge(TypeBadge.GOLD));
        chefStats.put(TypeBadge.SILVER.toString(), cuisinierRepository.countByTypeBadge(TypeBadge.SILVER));
        chefStats.put(TypeBadge.BRONZE.toString(), cuisinierRepository.countByTypeBadge(TypeBadge.BRONZE));
        return chefStats;
    }
    @Override
    public void remove(int idf) {
        cuisinierRepository.deleteById(idf);}

    @Override
    public Cuisinier update(Cuisinier res) {
        return cuisinierRepository.save(res);
    }


    @Scheduled(cron = "0 59 23 L * *")
    public void mettreAJourBadges() throws Exception {


        for(Restaurant restaurant:restaurantRepository.findAll()) {
            List<Cuisinier> cuisiniers = restaurant.getCuisiniers();
            Cuisinier chefAvecPlusDePlats = cuisiniers.stream().max(Comparator.comparingInt(c -> c.getPlats().size())).orElse(null);

            if (chefAvecPlusDePlats != null) {
                chefAvecPlusDePlats.setScore(chefAvecPlusDePlats.getScore() + 15);
                cuisinierRepository.save(chefAvecPlusDePlats);
            }

            int scoreMax = cuisiniers.stream().mapToInt(Cuisinier::getScore).max().orElse(0);

            for (Cuisinier c : cuisiniers) {
                if (c.getScore() == scoreMax) {
                    c.setDisponiblee(Boolean.TRUE);
                    c.setSalaire((float) (c.getSalaire() * 1.1));
                   User user=userRepository.findById(c.getIdC()).get();
                 sendWhatsAppMessage(user.getNtelephone(), "Votre score est le plus élevé!");

                    cuisinierRepository.save(c);
                } else {
                    c.setDisponiblee(Boolean.FALSE);
                }
            }

            for (Cuisinier c : cuisiniers) {
                int score = c.getScore();
                if (score <= 20) {
                    c.setTypeBadge(TypeBadge.BRONZE);
                    c.setScore(1);
                } else if (score < 30) {
                    c.setTypeBadge(TypeBadge.SILVER);
                    c.setScore(2);
                } else {
                    c.setTypeBadge(TypeBadge.GOLD);
                    c.setScore(5);
                }
                cuisinierRepository.save(c);
            }
        }
    }



    private static void sendWhatsAppMessage(String phoneNumber, String message) throws Exception {
        RequestBody body = new FormBody.Builder()
                .add("token", "qpzu69svxgduw96a")
                .add("to", phoneNumber)
                .add("body", message)
                .build();

        Request request = new Request.Builder()
                .url("https://api.ultramsg.com/instance84126/messages/chat")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Erreur lors de l'envoi du message WhatsApp: " + response);
        }

        response.close();
    }
}



