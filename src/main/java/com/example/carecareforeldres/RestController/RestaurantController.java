package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.DTO.RestaurantDTO;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Restaurant;
import com.example.carecareforeldres.Repository.EtablissementRepository;
import com.example.carecareforeldres.Repository.RestaurantRepository;
import com.example.carecareforeldres.Service.IServiceRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class RestaurantController {
    IServiceRestaurant iServiceRestaurant;
    RestaurantRepository restaurantRepository;
    EtablissementRepository etablissementRepository;

   // @PostMapping("/add")
    //public Restaurant ajouterRestaurant(@RequestBody Restaurant res){
      //  Restaurant p1=iServiceRestaurant.addRes(res);
       // return p1;
    //}
   @PostMapping("/add")
   public ResponseEntity<?> ajouterRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
       Restaurant restaurant = new Restaurant();
       Etablissement etablissement=etablissementRepository.findById(restaurantDTO.getIdEtab()).get();
       restaurant.setNomResto(restaurantDTO.getNomResto());
       restaurant.setAddress(restaurantDTO.getAddress());
       restaurant.setImage(restaurantDTO.getImage());
       restaurant.setTel(restaurantDTO.getTel());
       restaurant.setEtablissement(etablissement);

        restaurantRepository.save(restaurant);
       return new ResponseEntity<>("Restaurant ajouté avec succès", HttpStatus.CREATED);
   }
    @PutMapping("/updateResto/{id}")
    public ResponseEntity<?> modifierRestaurant(@PathVariable("id") Integer id,@RequestBody RestaurantDTO restaurantDTO) {
        Logger.getLogger("////////").warning("restaurant.getIdRestaurant().toString()");

       Restaurant restaurant =restaurantRepository.findById(id).get();
        Etablissement etablissement=etablissementRepository.findById(restaurantDTO.getIdEtab()).get();
        restaurant.setNomResto(restaurantDTO.getNomResto());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setImage(restaurantDTO.getImage());
        restaurant.setTel(restaurantDTO.getTel());
        restaurant.setEtablissement(etablissement);

        restaurantRepository.save(restaurant);
        Logger.getLogger("////////").warning(restaurant.getIdRestaurant().toString());
        return new ResponseEntity<>("Restaurant modifié avec succès", HttpStatus.CREATED);
    }
    @GetMapping("/retrive_all_restaurant")
    public List<Restaurant> retrieveFoodList(){

        return iServiceRestaurant.getAll();
    }
    @GetMapping("/retrive_all_etablissementNull")
    public List<Etablissement> retrieveEtablissement(){

        return etablissementRepository.findByRestaurantIsNull();
    }
    @GetMapping("/retrive_restaurant/{resId}")
    public RestaurantDTO retrieveFood(@PathVariable("resId") Integer resId){
        RestaurantDTO restaurantDTO= new RestaurantDTO();
        Restaurant restaurant=restaurantRepository.findById(resId).get();
        restaurantDTO.setIdRestaurant(restaurant.getIdRestaurant());
        restaurantDTO.setNomResto(restaurant.getNomResto());
        restaurantDTO.setTel(restaurant.getTel());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setImage(restaurant.getImage());
        restaurantDTO.setStatus_ouv(restaurant.getStatus_ouv());
        restaurantDTO.setIdEtab(restaurant.getEtablissement().getIdEtab());
        restaurantDTO.setNomEtab(restaurant.getEtablissement().getNomEtab());
        return restaurantDTO;
    }



    @PutMapping("/update_restaurant")
    public Restaurant updateResto(@RequestBody Restaurant restaurant){

        return iServiceRestaurant.update(restaurant);
    }
    @PutMapping("/update_restaurant/{id}")
    public RestaurantDTO updateRestaurantDTO(@RequestBody RestaurantDTO restaurant,
                                       @PathVariable Integer id){

        return iServiceRestaurant.updateRestaurant(id,restaurant);
    }


        @DeleteMapping("/delete_restaurant/{foodId}")
    public void deleteFood(@PathVariable("foodId") Integer foodId){
        iServiceRestaurant.remove(foodId);
    }

}
