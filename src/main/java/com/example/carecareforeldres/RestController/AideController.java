package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Aide;
import com.example.carecareforeldres.Service.IAideService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Aide")
public class AideController {
    IAideService iAideService;
    @GetMapping("/allAide")
    public List<Aide> getAide() {
        List<Aide> listAides = iAideService.retrieveAllAide();
        return listAides;
    }

    @GetMapping("/Aide/{id}")
    public Aide retrieveAide(@PathVariable("id") Long id) {
        return iAideService.retrieveAide(id);
    }

    @PostMapping("/addAide")
    public Aide addEtudiant(@RequestBody Aide b) {
        Aide Aide = iAideService.addAide(b);
        return Aide;
    }

    @PutMapping("/UpdateAide")
    public Aide updateAide(@RequestBody Aide e) {
        Aide Aide= iAideService.updateAide(e);
        return Aide;
    }

    @DeleteMapping("/RemoveAide/{id}")
    public void removeAide(@PathVariable("id") Long id) {
        iAideService.removeAide(id);
    }
}
