package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Maladie;
import com.example.carecareforeldres.Repository.MaladieRepository;
import com.example.carecareforeldres.Service.IServiceMaladie;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/maladie")
@CrossOrigin("*")
public class MaladieController {

    MaladieRepository maladieRepository;

    IServiceMaladie iServiceMaladie;
    @PostMapping("/add")
    public Maladie ajouterMedicament(@RequestBody Maladie med){
        Maladie p1=iServiceMaladie.add(med);
        return p1;
    }

    @GetMapping("/retrive_all_maladie")
    public List<Maladie> retrieveMaladieList(){

        return iServiceMaladie.getAll();
    }



    @PutMapping("/update_maladie")
    public Maladie updateMaladie(@RequestBody Maladie med){

        return iServiceMaladie.update(med);
    }

    @GetMapping("/retrive_maladie/{medID}")
    public Maladie retrieveMaladie(@PathVariable("medID") Integer medID){

        return maladieRepository.findById(medID).get();
    }

    @DeleteMapping("/delete_maladie/{medId}")
    public void deleteMaladie(@PathVariable("medId") Integer medId){
        iServiceMaladie.remove(medId);
    }

}
