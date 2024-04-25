package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ambilancier;
import com.example.carecareforeldres.Entity.Ambulance;
import com.example.carecareforeldres.Repository.AmbilancierRepository;
import com.example.carecareforeldres.Repository.AmbulanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceAmbilancier implements IServiceAmbilancier {
    AmbilancierRepository ambilancierRepository;
    AmbulanceRepository ambulanceRepository;
    @Override
    public Ambilancier addAmbilancier(Ambilancier ambilancier) {
        return ambilancierRepository.save(ambilancier);
    }

    @Override
    public List<Ambilancier> retrieveAllAmbilanciers() {
        return ambilancierRepository.findAll();
    }

    @Override
    public Ambilancier updateAmbilancier(Ambilancier ambilancier) {
        return ambilancierRepository.save(ambilancier);
    }

    @Override
    public Ambilancier retrieveAmbilancier(Integer idAmbilancier) {
        return ambilancierRepository.findById(idAmbilancier).get();
    }

    @Override
    public Ambilancier addAmbulancierAndAssignToAmbulance(Ambilancier ambilancier, Long idAmb) {
        Ambulance ambulance=ambulanceRepository.findById(idAmb).get();
        ambilancier.setAmbulance(ambulance);
        return ambilancierRepository.save(ambilancier);


    }

    @Override
    public Ambilancier UnsignAmbulancierfromAmbulance(Integer idAmbilancier) {
        Ambilancier ambulancier=ambilancierRepository.findById(idAmbilancier).get() ;
         ambulancier.setAmbulance(null);
         ambilancierRepository.save(ambulancier);
        return ambulancier;

    }

    @Override
    public void removeAmbilancier(Integer idAmbilancier) {
        ambilancierRepository.deleteById(idAmbilancier);

    }


}
