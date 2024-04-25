package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Maladie;
import com.example.carecareforeldres.Repository.MaladieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MaladieService implements IServiceMaladie{

    MaladieRepository maladieRepository ;

    @Override
    public Maladie add(Maladie res) {return maladieRepository.save(res);}
    @Override
    public List<Maladie> getAll(){return maladieRepository.findAll();}

    @Override
    public void remove(int idf) {
        Maladie m=maladieRepository.findById(idf).get();
        m.setPatients(null);
        m.setIngredients(null);
        maladieRepository.save(m);
        maladieRepository.deleteById(idf);}

    @Override
    public Maladie update(Maladie res) {
        return maladieRepository.save(res);
    }

    public Maladie findOrCreateMaladie(String nomMaladie) {
        // Vérifier si la maladie existe déjà dans la base de données
        Optional<Maladie> existingMaladie = maladieRepository.findByNom(nomMaladie);
        if (existingMaladie.isPresent()) {
            // Retourner la maladie existante si elle est trouvée
            return existingMaladie.get();
        } else {
            // Ajouter une nouvelle maladie si elle n'existe pas déjà
            Maladie newMaladie = new Maladie();
            newMaladie.setNom(nomMaladie);
            return maladieRepository.save(newMaladie);
        }
    }
}
