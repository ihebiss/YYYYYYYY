package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Counge;
import com.example.carecareforeldres.Entity.Cuisinier;
import com.example.carecareforeldres.Entity.EtatCounger;
import com.example.carecareforeldres.Entity.TypeCounger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CoungeRepository extends JpaRepository<Counge,Integer> {

    @Query("SELECT COUNT(c) FROM Counge c WHERE c.cuisinierC = :cuisinier AND YEAR(c.dateDebut) = YEAR(:currentDate)")
    int countCongesByCuisinierAndYear(Cuisinier cuisinier, LocalDate currentDate);

    List<Counge> findByEtatCounger(EtatCounger etatCounger);

    @Query("SELECT COUNT(c) FROM Counge c WHERE c.cuisinierC = :cuisinier AND YEAR(c.dateDebut) = YEAR(:currentDate) AND c.typeCounger = :typeC ")
    int countCongesByCuisinierAndYear(Cuisinier cuisinier, LocalDate currentDate, TypeCounger typeC);


    List<Counge> findCoungeByCuisinierC(Cuisinier cuisinier);
}
