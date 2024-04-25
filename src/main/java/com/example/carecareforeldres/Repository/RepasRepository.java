package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Repas;
import com.example.carecareforeldres.Entity.TypeRepas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

@EnableJpaRepositories
public interface RepasRepository extends JpaRepository<Repas,Integer> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Repas r WHERE r.patient.idpatient = :patientId AND r.typeRepas = :typeRepas AND r.dateRepas = :date")
    boolean existsByPatientIdAndTypeRepasAndDate(@Param("patientId") Integer patientId, @Param("typeRepas") TypeRepas typeRepas, @Param("date") LocalDate date);


}
