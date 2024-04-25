package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Commande;
import com.example.carecareforeldres.Entity.StatutsCom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Integer> {

    @Query("SELECT c FROM Commande c WHERE c.date_commande = :date")
    List<Commande> findByDateCommande(@Param("date") Date date);
    @Query("SELECT c FROM Commande c WHERE MONTH(c.date_commande) = :month")
    List<Commande> findByMonth(@Param("month") int month);

    @Query("SELECT c FROM Commande c WHERE YEAR(c.date_commande) = :year")
    List<Commande> findByYear(@Param("year") int year);
    @Query("SELECT COUNT(c) FROM Commande c WHERE MONTH(c.date_commande) = :month")
    int countByMonth(@Param("month") int month);

    @Query("SELECT COUNT(c) FROM Commande c WHERE YEAR(c.date_commande) = :year")
    int countByYear(@Param("year") int year);

    @Query("SELECT c FROM Commande c WHERE c.user.id = :userId")
    List<Commande> findByUserId(Integer userId);
    @Query("SELECT COUNT(c) FROM Commande c WHERE c.statut = 'EN_ATTENTE'")
    int countCommandesEnAttente();

    @Query("SELECT COUNT(c) FROM Commande c WHERE c.statut = 'EXPEDIEE'")
    int countCommandesExpediees();
    List<Commande> findByStatut(StatutsCom statutsCom);
}
