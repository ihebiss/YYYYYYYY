package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.RepasAvecPlatsDTO;
import com.example.carecareforeldres.Entity.*;
import com.example.carecareforeldres.Repository.PatientRepository;
import com.example.carecareforeldres.Repository.PlatRepository;
import com.example.carecareforeldres.Repository.RepasRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RepasService implements IServiceRepas{

    RepasRepository repasRepository;
    PlatRepository platRepository;
    PatientRepository patientREpository;
    @Override
    public Repas addPlat(Repas pt) {

        return repasRepository.save(pt);
    }

    @Override
    public List<Repas> getAll() {
        return repasRepository.findAll();
    }

    @Override
    public void remove(int idf) {
        Repas repas =repasRepository.findById(idf).get();
        repas.setPatient(null);
        repas.setPlats(null);
repasRepository.save(repas);
        repasRepository.deleteById(idf);
    }

    @Override
    public Repas update(Repas res) {
        return repasRepository.save(res);
    }

    @Override
    public Repas addRepasAvecPlats(RepasAvecPlatsDTO repasDTO) {
        Repas repas = new Repas();
        repas.setDateRepas(LocalDate.now());
        repas.setTypeRepas(repasDTO.getTypeRepas());

        List<Plat> plats = new ArrayList<>();
        for (Integer platId : repasDTO.getPlatsIds()) {
            Plat plat = platRepository.findById(platId).orElse(null);
            if (plat != null) {
                plats.add(plat);
            }
        }
        repas.setPlats(plats);

        return repasRepository.save(repas);
    }



    @Transactional
    @Override
    public ResponseEntity<?> AffecterRepasAUser(RepasAvecPlatsDTO repasDTO, Integer idPatient) {
        // Check if the patient has the same disease as the ingredients


        // Create the repas object based on the DTO
        Repas repas = new Repas();
        repas.setDateRepas(LocalDate.now());
        repas.setTypeRepas(repasDTO.getTypeRepas());

        List<Plat> plats = new ArrayList<>();
        for (Integer platId : repasDTO.getPlatsIds()) {
            if (platId == null) continue;
            Plat plat = platRepository.findById(platId).orElse(null);
            if (plat != null) {
                plats.add(plat);
            }
        }
        Patient pa = patientREpository.findById(idPatient).orElseThrow(() -> new RuntimeException("Patient not found"));
        repas.setPatient(pa);
        repas.setPlats(plats);

        if (!testMaladie(repas, idPatient)) {
            log.info("Le patient et les ingrédients ne comportent pas la même maladie : ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le patient et les ingrédients ne comportent pas la même maladie : ");
        }
        // Calculate total calories for the repas
        float totalCalories = calculateTotalCalories(repas);

        // Check if the repas type already exists for the patient today
        if (repasRepository.existsByPatientIdAndTypeRepasAndDate(idPatient, repas.getTypeRepas(), LocalDate.now())) {
            String message = "Tu as déjà un repas de " + repas.getTypeRepas().toString() + " pour aujourd'hui.";
            log.info(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        // Check if the total calories exceed the estimated daily calorie intake
        float estimatedDailyCalories = calculateEstimatedDailyCalories(idPatient);
        log.info("//////////////!//§§§§§§§§"+totalCalories);
        if (totalCalories > estimatedDailyCalories) {
            log.info("Vous avez dépassé les calories allouéees pour votre repas");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vous avez dépassé les calories allouéees pour votre repas");
        }

        // Save the repas and return success response
        repasRepository.save(repas);
        return ResponseEntity.status(HttpStatus.OK).body("Repas Ajoutéé avec succés.");
    }

    private float calculateTotalCalories(Repas repas) {
        float totalCalories = 0;
        for (Plat plat : repas.getPlats()) {
            for (Ingredient ingredient : plat.getIngredients()) {
                totalCalories += ingredient.getCalorie();
            }
        }
        totalCalories += platRepository.calculateCaloriesConsumedByUserToday(repas.getPatient().getIdpatient());
        return totalCalories;
    }

    private float calculateEstimatedDailyCalories(Integer idPatient) {
        Patient patient = patientREpository.findById(idPatient).orElseThrow(() -> new RuntimeException("Patient not found"));
        float longeur = patient.getLongueur();
        float poid = patient.getPoid();
        int age = patientREpository.calculatePatientAgeById(idPatient);
        float nbCalorieEstimee = patient.getSexe().equals(Sexe.HOMME) ? (float) ((10 * poid) + (6.25 * longeur) - (5 * age) + 5) : (float) ((10 * poid) + (6.25 * longeur) - (5 * age) - 161);
        return nbCalorieEstimee;
    }



    public Boolean testMaladie(Repas repas, Integer idPatient) {
        Patient patient = patientREpository.findById(idPatient).orElse(null);

            if (patient == null) {
                log.error("Patient not found with ID: " + idPatient);
                return false;
            }

            List<Maladie> maladiesPatient = patient.getMaladies();
            List<Plat> plat = repas.getPlats();
            List<Maladie> maladiesIngredients = new ArrayList<>();
            List<Ingredient> ingredients = new ArrayList<>();
            for (Plat plat1 : plat) {
                ingredients.addAll(plat1.getIngredients());
            }

            for (Ingredient ingredient : ingredients) {
                maladiesIngredients.addAll(ingredient.getMaladies());
            }

            for (Maladie maladiePatient : maladiesPatient) {
                boolean maladieFound = false;

                for (Maladie maladieIngredient : maladiesIngredients) {
                    if (maladieIngredient.getNom().equals(maladiePatient.getNom())) {
                        maladieFound = true;
                        break;
                    }
                }

                if (!maladieFound) {
                    log.info("Le patient et les ingrédients ne comportent pas la même maladie : " + maladiePatient.getNom());
                    return false;
                }
            }

            return true;
        }


}
