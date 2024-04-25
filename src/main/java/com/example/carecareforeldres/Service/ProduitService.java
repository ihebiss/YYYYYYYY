package com.example.carecareforeldres.Service;


import com.example.carecareforeldres.Entity.Produit;
import com.example.carecareforeldres.Entity.User;
import com.example.carecareforeldres.Repository.CommandeRepository;
import com.example.carecareforeldres.Repository.ProduitRepository;
import com.example.carecareforeldres.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ProduitService implements IProduitService {

    ProduitRepository produitRepository;
    CommandeRepository commandeRepository;
    UserRepository userRepository;
    @PersistenceContext



    private EntityManager entityManager;






    @Override
    public List<Produit> searchProduitsByNom(String nomproduit) {
        return produitRepository.findByNomproduitStartingWith(nomproduit);
    }


    @Override
    public Produit addProduit(Produit p) {
        return produitRepository.save(p);
    }

    @Override
    public void removeProduit(Long idProduit) {
        produitRepository.deleteById(idProduit);
    }

    @Override
    public Produit updateProduit(Produit p) {
        return produitRepository.save(p);
    }

    @Override
    public List<Produit> retrieveAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public void addProduitToFavoris(Integer id, Long idProduit) {
        Optional<User> optionalUtilisateur = userRepository.findById(id);
        if (optionalUtilisateur.isPresent()) {
            User utilisateur = optionalUtilisateur.get();
            Produit produit = retrieveProduit(idProduit); // Assurez-vous que cette méthode est correctement implémentée

            // Vérifier si le produit est déjà dans les favoris de l'utilisateur
            if (utilisateur.getProduitsFavoris().contains(produit)) {
                throw new IllegalArgumentException("Le produit est déjà dans les favoris de l'utilisateur.");
            }

            utilisateur.getProduitsFavoris().add(produit);
            userRepository.save(utilisateur);
            log.info("Le produit avec l'ID {} a été ajouté aux favoris de l'utilisateur avec l'ID {}.", idProduit, id);
        } else {
            throw new EntityNotFoundException("Aucun utilisateur trouvé avec l'identifiant : " + id);
        }
    }
    @Override
    public void removeProduitFromFavoris(Integer id, Long idProduit) {
        Optional<User> optionalUtilisateur = userRepository.findById(id);
        if (optionalUtilisateur.isPresent()) {
            User utilisateur = optionalUtilisateur.get();
            Produit produit = retrieveProduit(idProduit); // Assurez-vous que cette méthode est correctement implémentée

            utilisateur.getProduitsFavoris().remove(produit);
            userRepository.save(utilisateur);
            log.info("Le produit avec l'ID {} a été retiré des favoris de l'utilisateur avec l'ID {}.", idProduit, id);
        } else {
            throw new EntityNotFoundException("Aucun utilisateur trouvé avec l'identifiant : " + id);
        }
    }




    @Override
    public List<Produit> getProduitsFavorisByUserId(Integer id) {
        Optional<User> optionalUtilisateur = userRepository.findById(id);
        if (optionalUtilisateur.isPresent()) {
            User utilisateur = optionalUtilisateur.get();
            return utilisateur.getProduitsFavoris();
        } else {
            throw new EntityNotFoundException("Aucun utilisateur trouvé avec l'identifiant : " + id);
        }
    }
    @Override
    public Produit retrieveProduit(Long idProduit) {
        Optional<Produit> optionalProduit = produitRepository.findById(idProduit);
        if (optionalProduit.isPresent()) {
            return optionalProduit.get();
        } else {
            // Gérer le cas où aucun shop correspondant n'est trouvé
            // Par exemple, vous pouvez renvoyer null ou lever une exception spécifique
            throw new EntityNotFoundException("Aucun shop trouvé avec l'identifiant : " + idProduit);
        }
    }

    @Override
    public List<Produit> getAllProduits() {
        return null;
    }

    @Override
    public List<Produit> getAllProductsSortedByPrice(String sortOrder) {
        List<Produit> produits = produitRepository.findAll();

        // Trier les produits par prix
        if ("asc".equalsIgnoreCase(sortOrder)) {
            produits.sort(Comparator.comparing(Produit::getPrix));
        } else if ("desc".equalsIgnoreCase(sortOrder)) {
            produits.sort(Comparator.comparing(Produit::getPrix).reversed());
        }

        return produits;
    }
    }

