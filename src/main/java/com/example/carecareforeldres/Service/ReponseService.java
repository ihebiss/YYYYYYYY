package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Reponse;
import com.example.carecareforeldres.Repository.ReponseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor

public class ReponseService implements IReponseService{


     ReponseRepository reponseRepository;

    @Override
    public Reponse saveReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }


    @Override
    public Reponse getReponseById(Long id) {
        return reponseRepository.findById(id).get();
    }


    @Override
    public Reponse updateReponse( Reponse reponse) {

            return reponseRepository.save(reponse);

    }


    @Override
    public void deleteReponse(Long id) {
        reponseRepository.deleteById(id);
    }


    @Override
    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }
}
