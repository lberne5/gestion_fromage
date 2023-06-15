/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Model.Commande.*;
import Model.Exceptions.CommandeDejaArchiveeException;
import Model.Exceptions.DateRecupPlusTotQueEcritureException;
import Model.Exceptions.*;
import Model.Plateaux.*;
import Model.Produits.*;
import Model.Util.*;
import java.time.LocalDate;

/**
 *
 * @author etulyon1
 */
public class TestModel {

    public static void main(String[] args) throws DateRecupPlusTotQueEcritureException, CommandeDejaArchiveeException {

        PlateauStandard unPS = new PlateauStandard(4, 20, "Truc");
        System.out.println(unPS);
        System.out.println();

        PlateauPerso unAutrePlateau = new PlateauPerso(unPS);
        unAutrePlateau.setPrix(40);
        Fromage fromageBidon = new Fromage("Un fromage bidon", 1, 1);
        unAutrePlateau.ajouterProduitDansListe(fromageBidon);
        unAutrePlateau.supprimerProduitDansListe(fromageBidon);
        System.out.println(unAutrePlateau);

        System.out.println();

        PlateauPerso lePlateauDeValou = new PlateauPerso(5, 20, "Le Plateau de Valou");
        Fromage bonFrometon = new Fromage("Bon frometon", 1.5, 2);
        Fromage fromageDeJulesAndre = new Fromage("Fromage ok-tier", 2, 5);
        fromageDeJulesAndre.setQuantiteParPersonnes(100);
        fromageDeJulesAndre.setUnite(Unite.GRAMMES);

        System.out.println("Essai de suppression bidon. Le produit se trouve dans le plateau? " + unAutrePlateau.supprimerProduitDansListe(fromageBidon) + "\n");

        unAutrePlateau.ajouterProduitDansListe(bonFrometon);
        System.out.println("Prix du plateau de Valou avant ajout : " + lePlateauDeValou.getPrix() + " euros");
        System.out.println();

        lePlateauDeValou.ajouterProduitDansListe(bonFrometon);
        lePlateauDeValou.ajouterProduitDansListe(fromageDeJulesAndre);
        lePlateauDeValou.supprimerProduitDansListe(fromageBidon);

        System.out.println(lePlateauDeValou);
        System.out.println();

        System.out.println();
        Commande uneCommande = new Commande(1, "Valou", null, null, 50, 50, "", null);
        System.out.println("Avant Ajout : \n" + uneCommande + "\n");
        uneCommande.ajouterACommande(lePlateauDeValou, 1, 4);
        uneCommande.ajouterACommande(unAutrePlateau, 2, 4);
        uneCommande.setDateRecuperation(LocalDate.of(2023, 9, 05));
        System.out.println("Apres Ajout : \n" + uneCommande + "\n");
        uneCommande.supprimerPlateauDeCommande(unAutrePlateau);
        System.out.println("Apres Suppression : \n" + uneCommande + "\n");

        System.out.println("Essai de suppression bidon. Le plateau se trouve dans la commande? " + uneCommande.supprimerPlateauDeCommande(unPS) + "\n");

        uneCommande.etatSuivant();
        System.out.println("\nEtat Suivant de la commande : " + uneCommande.getEtatCommande());

        uneCommande.setPrix(30);
        uneCommande.setMontantPaye(30);
        System.out.println("Prix : " + uneCommande.getPrix() + " montantPaye : " + uneCommande.getMontantPaye());
        uneCommande.etatSuivant();
        System.out.println("Etat Suivant de la commande : " + uneCommande.getEtatCommande());
        uneCommande.etatSuivant();
        uneCommande.etatSuivant();

        bonFrometon.setUnite(Unite.PIECE);
        bonFrometon.setQuantiteParPersonnes(1);
        uneCommande.ajouterACommande(unAutrePlateau, 2, 4);

        System.out.println(uneCommande + "\n\n\nNombre de plateaux dans la commande : " + uneCommande.getNbPlateauxCommande());

        System.out.println("\n\n" + uneCommande.getListeProduitsNecessaires().toString());

        System.out.println("\nFin des tests. \n\n");

    }
}
