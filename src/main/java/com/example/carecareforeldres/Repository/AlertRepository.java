package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Alert;
import com.example.carecareforeldres.Entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert,Integer> {
  //  List<Alert> findByMedecinssyContaining(Medecin medecin);
    List<Alert> findByMedecinssy (Medecin medecin);


}
