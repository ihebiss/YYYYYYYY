package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Optional<?> findPatientByUser(Integer user);
    @Query("SELECT YEAR(CURRENT_DATE()) - YEAR(p.datedeNais) FROM Patient p WHERE p.idpatient = :patientId")
    Integer calculatePatientAgeById(@Param("patientId") Integer patientId);

}
