package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
    List<Produit> findByNomproduitStartingWith(String nomproduit);
    Page<Produit> findAll(Pageable pageable);

}
