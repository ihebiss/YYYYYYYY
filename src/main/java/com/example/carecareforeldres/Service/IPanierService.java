package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Commande;
import com.example.carecareforeldres.Entity.ModePay;
import com.example.carecareforeldres.Entity.Produit;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface IPanierService {
    Produit addProduitToPanier(Long idProduit, Long idPanier);
    void mettreAJourQuantiteProduit(Long idProduit, Long idPanier, int quantite);
    public boolean removeProduitFromPanier(Long idProduit, Long idPanier);
    float calculerMontantTotalPanier(Long idPanier);
    List<Produit> getProduitsDansPanier(Long idPanier);
    boolean checkProduitExistence(Long idPanier, Long idProduit);
    void mettreAJourMontantTotalPanier(Long idPanier, float montantTotal);
    int calculerNombreProduits(Long idPanier);
    void mettreAJourNombreProduitsPanier(Long idPanier);
    boolean isPanierVide(Long idPanier);
    public Commande ajouterCommande(Integer id,Long idPanier, float montantTotal, ModePay modePay);
    void supprimerCommande(Long idCommande);

    public ByteArrayOutputStream generateCommandePdf(Commande commande) throws DocumentException;
}
