package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.IngredientAvecMaladieDTO;
import com.example.carecareforeldres.Entity.Ingredient;
import com.example.carecareforeldres.Entity.Maladie;
import com.example.carecareforeldres.Repository.CuisinierRepository;
import com.example.carecareforeldres.Repository.IngredientRepository;
import com.example.carecareforeldres.Repository.MaladieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService implements IServiceFood{

    IngredientRepository foodReository;
    MaladieRepository maladieRepository;
    CuisinierRepository cuisinierRepository;

    /////////////////////////CRUD//////////////////////////////////////

    @Override
    public Ingredient addFood(Ingredient food) {return foodReository.save(food);}
    @Override
    public List<Ingredient> getAll(){return foodReository.findAll();}

    @Override
    public void remove(int idf) {

       foodReository.deleteIngredientPlatByIdIngredient(idf);
        foodReository.deleteById(idf);
    }

    @Override
    public Ingredient update(Ingredient food) {
        return foodReository.save(food);
    }



    ////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Ingredient addIngredientDTO(IngredientAvecMaladieDTO ingDTO) {
        // Recherche de l'ingrédient existant avec le même nom
        Ingredient existingIngredient = foodReository.findByNomIngredient(ingDTO.getNomIngredient());
        if (existingIngredient != null) {
            existingIngredient.setQuantite(existingIngredient.getQuantite() + ingDTO.getQuantite());
            existingIngredient.setDateAjout(LocalDateTime.now());

            List<Maladie> maladies = existingIngredient.getMaladies();
            for (Integer maladieId : ingDTO.getMaladieIds()) {
                Maladie maladie = maladieRepository.findById(maladieId).orElse(null);
                if (maladie != null) {
                    maladies.add(maladie);
                }
            }
            existingIngredient.setMaladies(maladies);

            return foodReository.save(existingIngredient);
        } else {
            // Si l'ingrédient n'existe pas, créez un nouvel ingrédient
            Ingredient newIngredient = new Ingredient();
            newIngredient.setIdIngredient(ingDTO.getIdIngredient());
            newIngredient.setNomIngredient(ingDTO.getNomIngredient());
            newIngredient.setCalorie(ingDTO.getCalorie());
            newIngredient.setQuantite(ingDTO.getQuantite());
            newIngredient.setDateAjout(LocalDateTime.now());

            List<Maladie> maladies = new ArrayList<>();
            for (Integer maladieId : ingDTO.getMaladieIds()) {
                Maladie maladie = maladieRepository.findById(maladieId).orElse(null);
                if (maladie != null) {
                    maladies.add(maladie);
                }
            }
            newIngredient.setMaladies(maladies);

            return foodReository.save(newIngredient);
        }
    }


}
