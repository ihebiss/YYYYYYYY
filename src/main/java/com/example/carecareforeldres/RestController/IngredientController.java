package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.DTO.IngredientAvecMaladieDTO;
import com.example.carecareforeldres.Entity.Ingredient;
import com.example.carecareforeldres.Repository.IngredientRepository;
import com.example.carecareforeldres.Service.CustomerService;
import com.example.carecareforeldres.Service.IServiceFood;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingredient")
@CrossOrigin("*")

public class IngredientController {

    IServiceFood ServiceFood;
    IngredientRepository foodReository;

    CustomerService customerService;
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        customerService.saveCustomersToDataBase(file);
        return "redirect:/";
    }


    @PostMapping("/add")
    public Ingredient ajouterFood(@RequestBody Ingredient food1){
        Ingredient p1=ServiceFood.addFood(food1);
        return p1;
    }
    @PostMapping("/addDTO")
    public Ingredient ajouterDTO(@RequestBody IngredientAvecMaladieDTO food1){
        Ingredient p1=ServiceFood.addIngredientDTO(food1);
        return p1;
    }
    @GetMapping("/retrive_all_ingredient")
    public List<Ingredient> retrieveFoodList(){

        return ServiceFood.getAll();
    }

    @GetMapping("/retrive_food/{foodId}")
    public Ingredient retrieveFood(@PathVariable("foodId") Integer idf){

        return foodReository.findById(idf).get();
    }

    @PutMapping("/update_food")
    public Ingredient updateFood(@RequestBody Ingredient food){

        return ServiceFood.update(food);
    }

    @DeleteMapping("/delete_food/{foodId}")
    public void deleteFood(@PathVariable("foodId") Integer foodId){
        ServiceFood.remove(foodId);
    }

    @PostMapping("/addIngredientWithMaladie")
    public Ingredient addPlatWithIngredients(@RequestBody IngredientAvecMaladieDTO platDTO) {
        return ServiceFood.addIngredientDTO(platDTO);
    }


    ///////////////////////////////////////////////////////////////////////////////////////















}