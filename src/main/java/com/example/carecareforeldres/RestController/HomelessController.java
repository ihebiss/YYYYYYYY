package com.example.carecareforeldres.RestController;


import com.example.carecareforeldres.Entity.Homeless;
import com.example.carecareforeldres.Service.IHomelessService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Homeless")
public class HomelessController {
    IHomelessService iHomelessService;
    @GetMapping("/allHomeless")
    public List<Homeless> getHomeless() {
        List<Homeless> listHomelesss = iHomelessService.retrieveAllHomeless();
        return listHomelesss;
    }

    @GetMapping("/Homeless/{id}")
    public Homeless retrieveHomeless(@PathVariable("id") Long id) {
        return iHomelessService.retrieveHomeless(id);
    }

    @PostMapping("/addHomeless")
    public Homeless addHomeless(@RequestBody Homeless b) {
        Homeless Homeless = iHomelessService.addHomeless(b);
        return Homeless;
    }

    @PutMapping("/UpdateHomeless")
    public Homeless updateHomeless(@RequestBody Homeless e) {
        Homeless Homeless = iHomelessService.updateHomeless(e);
        return Homeless;
    }

    @DeleteMapping("/RemoveHomeless/{id}")
    public void removeHomeless(@PathVariable("id") Long id) {
        iHomelessService.removeHomeless(id);
    }


    @PutMapping("/affect/{idHomeless}/{idSheletr}")
    public ResponseEntity<?>  assignHomlessToShelterrrr(@PathVariable("idHomeless") Long idHomeless,@PathVariable("idSheletr") Long idSheletr) {
        return iHomelessService.connectHomelessToShelter(idHomeless,idSheletr);
    }

}

