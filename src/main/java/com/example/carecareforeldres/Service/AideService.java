package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Aide;
import com.example.carecareforeldres.Repository.AideRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AideService implements  IAideService {
    AideRepository aideRepository;


    @Override
    public List<Aide> retrieveAllAide() {
        return aideRepository.findAll();
    }

    @Override
    public Aide addAide(Aide s) {
        return aideRepository.save(s);
    }

    @Override
    public Aide updateAide(Aide s) {
        return aideRepository.save(s);
    }

    @Override
    public Aide retrieveAide(Long idAide) {
        return aideRepository.findById(idAide).get();
    }

    @Override
    public void removeAide(Long idAide) {
        aideRepository.deleteById(idAide);
    }
}
