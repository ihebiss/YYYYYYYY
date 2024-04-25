package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin,Integer> {

    Optional<?> findMedecinByUser(Integer user);
}
