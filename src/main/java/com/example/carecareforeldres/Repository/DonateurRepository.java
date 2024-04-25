package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Donateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonateurRepository extends JpaRepository<Donateur,Long> {
    Optional<?> findDonateurByUser(Integer user);
}

