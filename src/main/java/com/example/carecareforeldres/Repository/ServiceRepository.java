package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Service;
import com.example.carecareforeldres.Entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service,Long> {
    List<Service> findByShelter(Shelter shelter);

}

