package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface ReponseRepository extends JpaRepository<Reponse, Long> {
    List<Reponse> findByCommantaires_Id(Long commentaireId);

}
