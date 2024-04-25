package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Evennement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository

public interface EvennementRepository extends JpaRepository<Evennement, Long> {


    @Query(value = "SELECT * FROM evennement e WHERE DATE(e.date) = ?1", nativeQuery = true)
    List<Evennement> getEvennementByDate(Date date);

    @Query(""" 
            SELECT c.user.id, COUNT(c.id) AS commentCount
            FROM Evennement e 
            JOIN e.commentaires c
            GROUP BY c.user.id
            ORDER BY commentCount DESC
            """)
    List<Object[]> findTopFiveUsersWithMostComments();

    Optional<Evennement> findByDate(LocalDate date);
}
