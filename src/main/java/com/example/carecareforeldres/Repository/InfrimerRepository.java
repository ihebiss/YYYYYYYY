package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Infermier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfrimerRepository extends JpaRepository<Infermier,Integer> {
    Optional<?> findInfermierByUser(Integer user);
}
