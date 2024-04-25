package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Homeless;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomelessRepository extends JpaRepository<Homeless,Long> {
    Optional<?> findHomelessByUser(Integer user);
}
