package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.DTO.DonateurAideShelterDTO;
import com.example.carecareforeldres.DTO.DonateurDTO;
import com.example.carecareforeldres.Entity.Donateur;
import com.example.carecareforeldres.Service.IDonateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Donateur")
public class DonateurController {
    IDonateurService iDonateurService;

    @GetMapping("/allDonateur")
    public List<Donateur> getDonateur() {
        List<Donateur> listDonateurs = iDonateurService.retrieveAllDonateur();
        return listDonateurs;
    }

    @GetMapping("/Donateur/{id}")
    public Donateur retrieveDonateur(@PathVariable("id") Long id) {
        return iDonateurService.retrieveDonateur(id);
    }

    @PostMapping("/addDonateur")
    public Donateur addDonateur(@RequestBody Donateur b) {
        Donateur Donateur = iDonateurService.addDonateur(b);
        return Donateur;
    }

    @PutMapping("/UpdateDonateur")
    public Donateur updateDonateur(@RequestBody Donateur e) {
        Donateur Donateur = iDonateurService.updateDonateur(e);
        return Donateur;
    }

    @DeleteMapping("/RemoveDonateur/{id}")
    public void removeDonateur(@PathVariable("id") Long id) {
        iDonateurService.removeDonateur(id);
    }

    @PutMapping("/affect/{idShelter}")
    @ResponseBody
    public Donateur ajouterDonateurAvecAideEtAffectShelter(@RequestBody DonateurAideShelterDTO donateurAideDTO,@PathVariable("idShelter") Long idShelter) {
        return iDonateurService.ajouterDonateurAvecAideEtAffectShelter(donateurAideDTO.getDonateur(), donateurAideDTO.getAide(), idShelter);


    }

    @GetMapping("/calculateTotalAmountAndSheltersList")
    public List<DonateurDTO> calculateTotalAmountAndSheltersList() {
        return iDonateurService.calculateTotalAmountAndSheltersList();
    }
}

