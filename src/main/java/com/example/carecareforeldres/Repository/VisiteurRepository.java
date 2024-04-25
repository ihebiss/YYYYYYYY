package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Visiteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisiteurRepository extends JpaRepository<Visiteur,Integer> {
    Optional<?> findVisiteurByUser(Integer user);
}
