package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Cuisinier;
import com.example.carecareforeldres.Entity.TypeBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuisinierRepository extends JpaRepository<Cuisinier,Integer> {
    Optional<?> findCuisinierByUser(Integer user);

    List<Cuisinier> findTop2ByOrderByScoreDesc();

    Integer countByTypeBadge(TypeBadge typeBadge);
}
