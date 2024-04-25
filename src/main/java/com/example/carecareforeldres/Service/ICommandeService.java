package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Commande;

import java.util.Date;
import java.util.List;

public interface ICommandeService {

    List<Commande> getCommandesByDate(Date date);

    List<Commande> getCommandesByMonth(int month);
    List<Commande> getCommandesByYear(int year);

    int getTotalCommandesByDate(Date date);
    int getTotalCommandesByMonth(int month);
    List<Commande> retrieveAllCommandes();
    int getTotalCommandesByYear(int year);

   void changerStatutCommande(Long idCommande);
    List<Commande> getCommandesByUserId(Integer userId);
    List<Commande> getCommandesEnAttente();
    public int countCommandesEnAttente();
    public int countCommandesExpediees();
}
