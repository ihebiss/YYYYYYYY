package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {
    //float sumCalorieByPlatsRepasPatientIdAndPlatsDatePlat(Integer patientId, LocalDate datePlat);
    @Query("SELECT i FROM Ingredient i WHERE i.consommable= true")
    List<Ingredient> ingredientConsommable();
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM plat_ingredient WHERE ingredient_id = :ingId", nativeQuery = true)
    void deleteIngredientPlatByIdIngredient(@Param("ingId") Integer ingId);
    Ingredient findByNomIngredient(String nomIngredient);
}
