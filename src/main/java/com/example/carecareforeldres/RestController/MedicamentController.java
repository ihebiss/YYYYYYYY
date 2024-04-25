package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Entity.Medicament;
import com.example.carecareforeldres.Repository.MedicamentRepository;
import com.example.carecareforeldres.Service.IServiceMedicament;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/medicament")
@CrossOrigin("*")
public class MedicamentController {

    IServiceMedicament iServiceMedicament;
    MedicamentRepository medicamentRepository;

    @PostMapping("/add")
    public Medicament ajouterMedicament(@RequestBody Medicament med){
        Medicament p1=iServiceMedicament.addMed(med);
        return p1;
    }

    @GetMapping("/retrive_all_medicament")
    public List<Medicament> retrieveMedicamentList(){

        return iServiceMedicament.getAllMed();
    }

    @GetMapping("/retrive_medicament/{medID}")
    public Medicament retrieveMedicament(@PathVariable("medID") Integer medID){

        return medicamentRepository.findById(medID).get();
    }

    @PutMapping("/update_medicament")
    public Medicament updateMedicament(@RequestBody Medicament med){

        return iServiceMedicament.update(med);
    }

    @DeleteMapping("/delete_medicament/{medId}")
    public void deleteMedicament(@PathVariable("medId") Integer medId){
        iServiceMedicament.remove(medId);
    }

}
