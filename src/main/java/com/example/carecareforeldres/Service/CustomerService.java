package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ingredient;
import com.example.carecareforeldres.Repository.CustomerRepository;
import com.example.carecareforeldres.Repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    EscelUploadService ExcelUploadService;
    IngredientRepository ingredientRepository;
    public  void  saveCustomersToDataBase(MultipartFile file){
        if (ExcelUploadService.isValidExcelFile(file)) {
            try {
                List<Ingredient> customers = ExcelUploadService.getCustomersDataFromExcel(file.getInputStream());
                 ingredientRepository.saveAll(customers);
                System.out.println("Données des clients enregistrées avec succès.");
            } catch (IOException e) {
                throw new IllegalArgumentException("Le fichier n'est pas un fichier Excel valide.");
            }
        }
    }
    public List<Ingredient> getCustomers(){
        return ingredientRepository.findAll();
    }
}
