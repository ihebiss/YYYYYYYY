package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Maladie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaladieRepository extends JpaRepository<Maladie,Integer> {
    Optional<Maladie> findByNom(String nomMaladie);

}
