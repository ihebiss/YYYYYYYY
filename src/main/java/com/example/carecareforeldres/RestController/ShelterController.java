package com.example.carecareforeldres.RestController;


import com.example.carecareforeldres.DTO.ShelterDonationDTO;
import com.example.carecareforeldres.Entity.Achievement;
import com.example.carecareforeldres.Entity.Shelter;
import com.example.carecareforeldres.Service.IShelterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/shelter")
public class ShelterController {
    IShelterService iShelterService;

    @GetMapping("/allShelter")
    public List<Shelter> getShelter() {
        List<Shelter> listShelters = iShelterService.retrieveAllShelter();
        return listShelters;
    }
    @GetMapping("/allAchievment")
    public List<Achievement> getAchievemnets() {
        List<Achievement> listAchievment = iShelterService.retrieveAllAchievement();
        return listAchievment;
    }
    @GetMapping("/Shelter/{id}")
    public Shelter retrieveShelter(@PathVariable("id") Long id) {
        return iShelterService.retrieveShelter(id);
    }

    @PostMapping("/addShelter")
    public Shelter addEtudiant(@RequestBody Shelter b) {
        Shelter Shelter = iShelterService.addShelter(b);
        return Shelter;
    }

    @PutMapping("/UpdateShelter")
    public Shelter updateShelter(@RequestBody Shelter e) {
        Shelter Shelter = iShelterService.updateShelter(e);
        return Shelter;
    }

    @DeleteMapping("/RemoveShelter/{id}")
    public void removeShelter(@PathVariable("id") Long id) {
        iShelterService.removeShelter(id);
    }


    @PutMapping("/affect/{idAide}")
    @ResponseBody
    public Shelter assignAideToShelterrrr(@PathVariable("idAide") Long idAide, @RequestBody Shelter shelter) {
        return iShelterService.assignAideToShelter(idAide,shelter);
    }
    @GetMapping("/allShelterDonations")
    public List<ShelterDonationDTO> getAllShelterDonations() {
        return iShelterService.calculerMontantTotalEtRestant();
    }

    //  @PostMapping("/addCauseToShelter/{shelterId}")
    //  public void addCauseToShelter(@PathVariable Long shelterId, @RequestBody ShelterDonationDTO causeDTO) {
    //      iShelterService.addCauseToShelter(shelterId, causeDTO);
    //   }
    @DeleteMapping("/deleteCause/{id}")
    public void deleteCauseFromShelter(@PathVariable("id") Long id) {
        iShelterService.deleteCauseFromShelter(id);
    }

    // @PutMapping("/updateCause/{id}")
    //  public void updateCauseInShelter(@PathVariable("id") Long shelterId, @RequestBody ShelterDonationDTO causeDTO) {
    //    iShelterService.updateCauseInShelter(shelterId, causeDTO);
    //  }
    @PostMapping("/addCauseToShelter/{shelterId}")
    public ResponseEntity<?> addCauseToShelter(@PathVariable Long shelterId, @RequestBody ShelterDonationDTO causeDTO) {
        try {
            iShelterService.addCauseToShelter(shelterId, causeDTO);
            return ResponseEntity.ok("Cause added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add cause: " + e.getMessage());
        }
    }

    @GetMapping("/progress/{shelterId}")
    public void checkProgressAndHandleAchievement(@PathVariable Long shelterId) {
        iShelterService.deleteCauseFromShelterAndRemlitAchievement(shelterId);
    }
}


