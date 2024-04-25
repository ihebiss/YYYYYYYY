package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
}
