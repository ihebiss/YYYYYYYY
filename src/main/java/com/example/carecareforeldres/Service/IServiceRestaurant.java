package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.DTO.RestaurantDTO;
import com.example.carecareforeldres.Entity.Restaurant;

import java.util.List;

public interface IServiceRestaurant {
    Restaurant addRes(Restaurant res);

    List<Restaurant> getAll();

    void remove(int idf);

    Restaurant update(Restaurant res);

    RestaurantDTO updateRestaurant(Integer id, RestaurantDTO restaurantDTO);
}
