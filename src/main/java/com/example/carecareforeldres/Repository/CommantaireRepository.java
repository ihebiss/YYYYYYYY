package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CommantaireRepository extends JpaRepository<Commentaire, Long> {

    List<Commentaire> findByEvennementId(Long evennementId);

}
