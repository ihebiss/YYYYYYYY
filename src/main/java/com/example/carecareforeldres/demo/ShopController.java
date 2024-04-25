package com.example.carecareforeldres.demo;

import com.example.carecareforeldres.Entity.Commande;
import com.example.carecareforeldres.Entity.ModePay;
import com.example.carecareforeldres.Entity.Produit;

import com.example.carecareforeldres.Service.ICommandeService;
import com.example.carecareforeldres.Service.IPanierService;
import com.example.carecareforeldres.Service.IProduitService;
import com.example.carecareforeldres.Service.IUserService;
import com.itextpdf.text.DocumentException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/Shop")
@CrossOrigin("*")
public class ShopController {

IProduitService iProduitService;
IPanierService iPanierService;
ICommandeService iCommandeService;
IUserService iUserService;

    @GetMapping("/count/en-attente")
    public int countCommandesEnAttente() {
        return iCommandeService.countCommandesEnAttente();
    }

    @GetMapping("/count/expediees")
    public int countCommandesExpediees() {
        return iCommandeService.countCommandesExpediees();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Produit>> searchProduitsByNom(@RequestParam String nom) {
        List<Produit> produits = iProduitService.searchProduitsByNom(nom);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }
    @PostMapping("/{id}/favoris/{idProduit}")
    public ResponseEntity<Object> addProduitToFavoris(@PathVariable Integer id, @PathVariable Long idProduit) {


            Map<String, String> response = new HashMap<>();
        try {
            iProduitService.addProduitToFavoris(id, idProduit);
            response.put("message", "Produit ajouté aux favoris avec succès.");
            return ResponseEntity.ok(response);

        } catch (EntityNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (IllegalArgumentException e) {
            response.put("error", "Le produit est déjà dans les favoris de l'utilisateur.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            response.put("error", "Erreur lors de l'ajout du produit aux favoris.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @DeleteMapping("/{id}/defavoris/{idProduit}")
    public ResponseEntity<Object> removeProduitFromFavoris(@PathVariable Integer id, @PathVariable Long idProduit) {
        try {
            iProduitService.removeProduitFromFavoris(id, idProduit);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Produit retire des favoris avec succès.");
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}/favoris")
    public ResponseEntity<List<Produit>> getProduitsFavorisByUserId(@PathVariable Integer id) {
        try {
            List<Produit> produitsFavoris = iProduitService.getProduitsFavorisByUserId(id);
            return ResponseEntity.ok(produitsFavoris);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/add-produit")
    public Produit addProduit(@RequestBody Produit p) {
        Produit produit = iProduitService.addProduit(p);
        return produit;
    }

    @DeleteMapping("/remove-produit/{idProduit}")
    public void removeproduit(@PathVariable("idProduit") Long idProduit) {
        iProduitService.removeProduit(idProduit);
    }

    @PutMapping("/update-produit")
    public Produit updateProduit(@RequestBody Produit p) {
        Produit produit= iProduitService.updateProduit(p);
        return produit;
    }


    @GetMapping("/retrieve-all-Produits")
    public List<Produit> getProduits() {
        List<Produit> listProduits = iProduitService.retrieveAllProduits();
        return listProduits;
    }


    @GetMapping("/retrieve-produit/{idProduit}")
    public Produit retrieveProduit(@PathVariable("idProduit") Long idProduit) {
        return iProduitService.retrieveProduit(idProduit);
    }




    @PostMapping("/{idPanier}/add-produitaupanier/{idProduit}")
    public ResponseEntity<Produit> addProduitToPanier(@PathVariable Long idPanier, @PathVariable Long idProduit) {
        Produit produit = iPanierService.addProduitToPanier(idProduit, idPanier);
        if (produit != null) {
            return ResponseEntity.ok(produit);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{idPanier}/remove-produit/{idProduit}")
    public ResponseEntity<?> removeProduitFromPanier(@PathVariable Long idPanier, @PathVariable Long idProduit) {
        boolean isRemoved = iPanierService.removeProduitFromPanier(idProduit, idPanier);
        if (isRemoved) {
            return ResponseEntity.noContent().build(); // Suppression réussie, pas de contenu à renvoyer
        } else {
            return ResponseEntity.notFound().build(); // Produit non trouvé ou erreur lors de la suppression
        }
    }

    @GetMapping("/{idPanier}/total")
    public ResponseEntity<Float> calculerMontantTotalPanier(@PathVariable Long idPanier) {
        float montantTotal = iPanierService.calculerMontantTotalPanier(idPanier);
        return ResponseEntity.ok(montantTotal);
    }

    @PutMapping("/{idPanier}/update-total/{montantTotal}")
    public ResponseEntity<?> mettreAJourMontantTotalPanier(@PathVariable Long idPanier, @PathVariable float montantTotal) {
        iPanierService.mettreAJourMontantTotalPanier(idPanier, montantTotal);
        return ResponseEntity.ok("Montant total du panier mis à jour avec succès !");
    }
    @PutMapping("/{idPanier}/update-quantity/{idProduit}/{quantite}")
    public ResponseEntity<?> mettreAJourQuantiteProduit(@PathVariable Long idPanier, @PathVariable Long idProduit, @PathVariable int quantite) {
        iPanierService.mettreAJourQuantiteProduit(idProduit, idPanier, quantite);
        return ResponseEntity.ok("Quantité du produit mise à jour avec succès !");
    }
    @GetMapping("/{idPanier}/produits")
    public ResponseEntity<List<Produit>> getProduitsDansPanier(@PathVariable Long idPanier) {
        List<Produit> produits =  iPanierService.getProduitsDansPanier(idPanier);
        return ResponseEntity.ok().body(produits);
    }

    @GetMapping("/{idPanier}/produit-existe/{idProduit}")
    public ResponseEntity<Boolean> checkProduitExistence(@PathVariable Long idPanier, @PathVariable Long idProduit) {
        boolean existe = iPanierService.checkProduitExistence(idPanier, idProduit);
        return ResponseEntity.ok(existe);
    }
    @GetMapping("/{idPanier}/nombre-produits")
    public ResponseEntity<Integer> calculerNombreProduits(@PathVariable Long idPanier) {
        int nombreProduits = iPanierService.calculerNombreProduits(idPanier);
        return ResponseEntity.ok(nombreProduits);
    }
    @PutMapping("/{idPanier}/update-nombre-produits")
    public ResponseEntity<String> mettreAJourNombreProduitsPanier(@PathVariable Long idPanier) {
       iPanierService.mettreAJourNombreProduitsPanier(idPanier);
        return ResponseEntity.ok("Nombre de produits dans le panier mis à jour avec succès.");
    }
    @GetMapping("/{idPanier}/is-empty")
    public ResponseEntity<Boolean> isPanierVide(@PathVariable Long idPanier) {
        boolean isVide = iPanierService.isPanierVide(idPanier);
        return ResponseEntity.ok(isVide);
    }


    @DeleteMapping("/commandes/{idCommande}")
    public ResponseEntity<?> supprimerCommande(@PathVariable Long idCommande) {
        iPanierService.supprimerCommande(idCommande);
        return ResponseEntity.ok("Commande supprimée avec succès.");
    }


    @GetMapping("/sorted-by-price")
    public ResponseEntity<List<Produit>> getAllProductsSortedByPrice(@RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        List<Produit> sortedProducts = iProduitService.getAllProductsSortedByPrice(sortOrder);
        return ResponseEntity.ok(sortedProducts);
    }
    @GetMapping("/date")
    public List<Commande> getCommandesByDate(@RequestParam String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(date);
        return iCommandeService.getCommandesByDate(parsedDate);
    }

    @GetMapping("/mois")
    public ResponseEntity<List<Commande>> getCommandesByMonth(@RequestParam("month") int month) {
        List<Commande> commandes = iCommandeService.getCommandesByMonth(month);
        return ResponseEntity.ok(commandes);
    }

    @GetMapping("/annee")
    public ResponseEntity<List<Commande>> getCommandesByYear(@RequestParam("year") int year) {
        List<Commande> commandes = iCommandeService.getCommandesByYear(year);
        return ResponseEntity.ok(commandes);
    }
    @GetMapping("/total")
    public int getTotalCommandesByDate(@RequestParam String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(date);
        return iCommandeService.getTotalCommandesByDate(parsedDate);
    }
    @GetMapping("/total/mois")
    public int getTotalCommandesByMonth(@RequestParam int month) {
        return iCommandeService.getTotalCommandesByMonth(month);
    }

    @GetMapping("/total/annee")
    public int getTotalCommandesByYear(@RequestParam int year) {
        return iCommandeService.getTotalCommandesByYear(year);
    }
    @GetMapping("/en-attente")
    public ResponseEntity<List<Commande>> getCommandesEnAttente() {
        List<Commande> commandesEnAttente = iCommandeService.getCommandesEnAttente();
        return ResponseEntity.ok(commandesEnAttente);
    }
    @GetMapping("{userId}/commandes")
    public ResponseEntity<List<Commande>> getCommandesByUserId(@PathVariable Integer userId) {
        List<Commande> commandes = iCommandeService.getCommandesByUserId(userId);
        return ResponseEntity.ok(commandes);
    }
    @GetMapping("/retrieve-all-Commandes")
    public List<Commande> getCommandes() {
        return iCommandeService.retrieveAllCommandes();
    }
    @PutMapping("/{idCommande}/changer-statut")
    public ResponseEntity<Object> changerStatutCommande(@PathVariable Long idCommande) {
        iCommandeService.changerStatutCommande(idCommande);
        Map<String, String> response = new HashMap<>();
        response.put("message", " La commande est validee avec succès.");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{id}/{idPanier}/ajouter-commande")
    public ResponseEntity<byte[]> ajouterCommande(@PathVariable Integer id,@PathVariable Long idPanier, @RequestBody Map<String, Object> requestBody) throws IOException, DocumentException {
        float montantTotal = Float.parseFloat(requestBody.get("montantTotal").toString());
        ModePay modePay = ModePay.valueOf(requestBody.get("modePay").toString());
        Commande commande = iPanierService.ajouterCommande(id,idPanier, montantTotal, modePay);

        // Générer le PDF pour la commande créée
        ByteArrayOutputStream pdfStream = iPanierService.generateCommandePdf(commande);

        // Préparer les en-têtes de la réponse
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=commande_details.pdf"); // Indique au navigateur de télécharger le fichier

        // Renvoyer le PDF en réponse à la demande
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfStream.toByteArray());
    }
}
