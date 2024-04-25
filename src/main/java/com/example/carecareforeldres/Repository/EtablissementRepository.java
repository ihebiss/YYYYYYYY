package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtablissementRepository extends JpaRepository<Etablissement,Long> {
    List<Etablissement> findByRestaurantIsNull();

}
