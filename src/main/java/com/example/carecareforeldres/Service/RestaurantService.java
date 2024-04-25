package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.RestaurantDTO;
import com.example.carecareforeldres.Entity.Etablissement;
import com.example.carecareforeldres.Entity.Restaurant;
import com.example.carecareforeldres.Repository.EtablissementRepository;
import com.example.carecareforeldres.Repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService implements IServiceRestaurant{

    RestaurantRepository restaurantRepository;
EtablissementRepository etablissementRepository;
    @Override
    public Restaurant addRes(Restaurant res) {return restaurantRepository.save(res);}
    @Override
    public List<Restaurant> getAll(){return restaurantRepository.findAll();}

    @Override
    public void remove(int idf) {
        Restaurant restaurant=restaurantRepository.findById(idf).get();
        restaurant.setEtablissement(null);
        restaurant.setCuisiniers(null);
        restaurantRepository.save(restaurant);
        restaurantRepository.deleteById(idf);}

    @Override
    public Restaurant update(Restaurant res) {
        return restaurantRepository.save(res);
    }
    @Override
    public RestaurantDTO updateRestaurant(Integer id, RestaurantDTO restaurantDTO) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            // Mettre à jour les champs avec les valeurs du DTO
            if (restaurantDTO.getNomResto() != null) {
                restaurant.setNomResto(restaurantDTO.getNomResto());
            }
            if (restaurantDTO.getAddress() != null) {
                restaurant.setAddress(restaurantDTO.getAddress());
            }
            if (restaurantDTO.getImage() != null) {
                restaurant.setImage(restaurantDTO.getImage());
            }
            if (restaurantDTO.getTel() != null) {
                restaurant.setTel(restaurantDTO.getTel());
            }
            if (restaurantDTO.getStatus_ouv() != null) {
                restaurant.setStatus_ouv(restaurantDTO.getStatus_ouv());
            }
            if (restaurantDTO.getIdEtab() != null) {
                Etablissement etablissement=etablissementRepository.findById(restaurantDTO.getIdEtab()).get();
                restaurant.setEtablissement(etablissement);
            }

            // Enregistrer les modifications dans la base de données
            restaurantRepository.save(restaurant);

            // Retourner le DTO mis à jour
            return restaurantDTO;
        } else {
            // Si aucun restaurant avec l'ID donné n'est trouvé, retourner null ou jeter une exception selon le cas d'utilisation
            return null;
        }
    }
}
