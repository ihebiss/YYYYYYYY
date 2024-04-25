package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Medicament;
import com.example.carecareforeldres.Repository.MedicamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicamentService  implements IServiceMedicament{
 MedicamentRepository medicamentRepository;

    @Override
    public Medicament addMed(Medicament med) {return medicamentRepository.save(med);}
    @Override
    public List<Medicament> getAllMed(){return medicamentRepository.findAll();}

    @Override
    public void remove(int idf) {
        medicamentRepository.deleteById(idf);}

    @Override
    public Medicament update(Medicament med) {

        return medicamentRepository.save(med);
    }

}
