package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.*;
import com.example.carecareforeldres.Repository.CommandeRepository;
import com.example.carecareforeldres.Repository.PanierRepository;
import com.example.carecareforeldres.Repository.ProduitRepository;
import com.example.carecareforeldres.Repository.UserRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;
@Service
@Slf4j
@AllArgsConstructor
public class PanierService implements IPanierService {
 CommandeRepository commandeRepository;
 PanierRepository panierRepository;
 ProduitRepository produitRepository;
UserRepository userRepository;

 public ByteArrayOutputStream generateCommandePdf(Commande commande) throws DocumentException {
  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  Document document = new Document();
  PdfWriter.getInstance(document, outputStream);
  document.open();

  Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

  // Ajouter les détails de la commande au document PDF
  addCommandeDetailsToPdf(document, font, commande);

  document.close();
  return outputStream;
 }

 private void addCommandeDetailsToPdf(Document document, Font font, Commande commande) throws DocumentException {
  // Ajouter chaque détail de la commande comme paragraphe dans le document PDF

  // Ajouter d'autres détails de la commande ici...

  // Exemple:
   addParagraph(document, "Montant total: " + commande.getMontantTotal(), font);
   addParagraph(document, "Date de commande: " + commande.getDate_commande(), font);
   addParagraph(document, "Mode de paiement: " + commande.getModePay(), font);
  // Ajoutez d'autres détails selon vos besoins
 }

 private void addParagraph(Document document, String text, Font font) throws DocumentException {
  Paragraph paragraph = new Paragraph(text, font);
  document.add(paragraph);
 }

 @Override
 public Produit addProduitToPanier(Long idProduit, Long idPanier) {
  Produit produit = produitRepository.findById(idProduit).orElse(null);
  Panier panier = panierRepository.findById(idPanier).orElse(null);
  if (produit != null && panier != null) {
   panier.getProduits().add(produit);
   panierRepository.save(panier);
   return produit;
  }
  return null;
 }

 @Override
 public void mettreAJourQuantiteProduit(Long idProduit, Long idPanier, int quantite) {

  Produit produit = produitRepository.findById(idProduit).orElse(null);
  Panier panier = panierRepository.findById(idPanier).orElse(null);
  if (produit != null && panier != null && quantite > 0) {
   panier.getProduitsQuantite().put(produit, quantite);
   panierRepository.save(panier);
  }
 }

 @Override
 public boolean removeProduitFromPanier(Long idProduit, Long idPanier) {
  Produit produit = produitRepository.findById(idProduit).orElse(null);
  Panier panier = panierRepository.findById(idPanier).orElse(null);
  if (produit != null && panier != null) {
   panier.getProduits().remove(produit);
   panierRepository.save(panier);
   return true; // Suppression réussie
  }
  return false; // Produit non trouvé ou panier non trouvé
 }

 @Override
 public float calculerMontantTotalPanier(Long idPanier) {
  Panier panier = panierRepository.findById(idPanier).orElse(null);
  if (panier != null) {
   float montantTotal = 0;
   for (Produit produit : panier.getProduits()) {
    montantTotal += produit.getPrix();
   }
   return montantTotal;
  }
  return 0;
 }

 @Override
 public List<Produit> getProduitsDansPanier(Long idPanier) {
  Panier panier = panierRepository.findById(idPanier).orElse(null);
  if (panier != null) {
   return new ArrayList<>(panier.getProduits());
  }
  return Collections.emptyList();
 }

 @Override
 public boolean checkProduitExistence(Long idPanier, Long idProduit) {
  Panier panier = panierRepository.findById(idPanier).orElse(null);


  Produit produit = panier.getProduits().stream()
          .filter(p -> p.getIdProduit().equals(idProduit))
          .findFirst()
          .orElse(null);

  return produit != null;
 }



 @Override
 public void mettreAJourMontantTotalPanier(Long idPanier, float montantTotal) {
  Panier panier = panierRepository.findById(idPanier).orElse(null);
  if (panier != null) {
   // Calculez le montant total à partir des produits du panier
   float montantTotalCalcule = 0;
   for (Produit produit : panier.getProduits()) {
    montantTotalCalcule += produit.getPrix();
   }
   // Mettez à jour le montant total avec la valeur calculée
   panier.setPrix_total(montantTotalCalcule);
   panierRepository.save(panier);
  }}

 @Override
 public int calculerNombreProduits(Long idPanier) {
  Panier panier = panierRepository.findById(idPanier).orElse(null);


  return panier.getProduits().size();
 }

 @Override
 public void mettreAJourNombreProduitsPanier(Long idPanier) {

  Optional<Panier> panierOptional = panierRepository.findById(idPanier);
  if (panierOptional.isPresent()) {
   Panier panier = panierOptional.get();
   int nombreProduits = panier.getProduits().size();
   panier.setNombreTotalProduits(nombreProduits);
   panierRepository.save(panier);
  }
 }

 @Override
 public boolean isPanierVide(Long idPanier) {
  Optional<Panier> panierOptional = panierRepository.findById(idPanier);
  if (panierOptional.isPresent()) {
   Panier panier = panierOptional.get();
   return panier.getProduits().isEmpty();
  }
  return true; // Panier vide si l'ID de panier n'est pas trouvé
 }

 @Override
 public Commande ajouterCommande(Integer id,Long idPanier, float montantTotal, ModePay modePay) {
  Panier panier = panierRepository.findById(idPanier).get();
  User utilisateur = userRepository.findById(id).get();
  Commande commande = new Commande();
  commande.setUser(utilisateur);
  commande.setPanier(panier);
  commande.setStatut(StatutsCom.EN_ATTENTE);
  commande.setDate_commande(new Date());
  commande.setMontantTotal(montantTotal);
  commande.setModePay(modePay);

  Commande savedCommande = commandeRepository.save(commande);

  // Vider le panier
  panier.getProduits().clear(); // Supprimez tous les produits du panier
  panierRepository.save(panier);
  return savedCommande; // Enregistrez les modifications apportées au panier
 }


 @Override
 public void supprimerCommande(Long idCommande) {
  int id = Math.toIntExact(idCommande);
  commandeRepository.deleteById(id);
 }






}
