/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import Controller.*;
import Controller.Exceptions.*;
import Model.Commande.*;
import Model.Exceptions.*;
import Model.Plateaux.*;
import Model.Produits.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import Model.Util.Unite;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author Timon Fournier
 */
public class TestController {
    public static void main(String Args[]) throws IdDejaDefiniException, CommandeDejaArchiveeException, DateRecupPlusTotQueEcritureException, NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException{
        try {
            
            //initialisation de la connexion
            System.out.println("\ninitialisation de la connexion ...");
            Connection conn = ConnexionBDD.initConnexion();
            
            //instanciation des controllers
            System.out.println("\ninstanciation des controllers ...");
            AutreController ac = new AutreController(conn);
            CharcuterieController cc = new CharcuterieController(conn);
            CommandeController coc = new CommandeController(conn);
            FromageController fc = new FromageController(conn);
            FruitController frc = new FruitController(conn);
            PlateauController pc = new PlateauController(conn);
            Controller c = new Controller();
            
            //instanciation des objets
            System.out.println("\ninstanciation des objets ...");
            AutreProduit testAutre = new AutreProduit("testGeneral", 0, -1);
            Charcuterie testCharcuterie  = new Charcuterie("testGeneral", 0, 0, Unite.GRAMMES, -1);
            Fromage testFromage = new Fromage("testGeneral", 0, 0, Unite.GRAMMES, -1);
            Fruit testFruit = new Fruit("testGeneral", 0, 0, Unite.GRAMMES, -1);
            Plateau testPlateauPerso = new PlateauPerso(0, "testGeneral");
            Plateau testPlateauStandard = new PlateauStandard(0, "testGeneral");
            Commande testCommande = new Commande( "testGeneral", LocalDate.now(),LocalDate.of(2050, Month.MARCH, 1), 0, 0, "testGeneral", null);
            
            //ajout des objets en BDD
            System.out.println("\najout des objets en BDD : ");
            AutreProduit autre = (AutreProduit)c.ajouterAutre(testAutre.getNom(), testAutre.getPrix());
            Charcuterie charcuterie = (Charcuterie)c.ajouterCharcuterie(testCharcuterie.getNom(), testCharcuterie.getPrix(), testCharcuterie.getQuantiteParPersonnes(), testCharcuterie.getUnite().toString());
            Fromage fromage = (Fromage)c.ajouterFromage(testFromage.getNom(), testFromage.getPrix(), testFromage.getQuantiteParPersonnes(), testFromage.getUnite().toString());
            Fruit fruit = (Fruit)c.ajouterFruit(testFruit.getNom(), testFruit.getPrix(), testFruit.getQuantiteParPersonnes(), testFruit.getUnite().toString());
            testPlateauPerso.ajouterProduitDansListe((Produit)charcuterie);//attention, il faut ajouter les produits au plateau avant de l'ajouter a la base de données mais apres avoir ajouté les produits à la bdd
            testPlateauPerso.ajouterProduitDansListe((Produit)fromage);
            testPlateauStandard.ajouterProduitDansListe((Produit)fruit);
            testPlateauStandard.ajouterProduitDansListe((Produit)autre);
            PlateauPerso plateauPerso = (PlateauPerso)c.ajouterPlateauPerso(testPlateauPerso.getPrix(), testPlateauPerso.getNomPlateau(), testPlateauPerso.getListeProduits());
            PlateauStandard plateauStandard = (PlateauStandard)c.ajouterPlateauStandard(testPlateauStandard.getPrix(), testPlateauStandard.getNomPlateau(), testPlateauStandard.getListeProduits());
            testCommande.ajouterACommande(plateauPerso, 1, 1);
            testCommande.ajouterACommande(plateauStandard, 1, 1); 
            Commande commande = (Commande)c.ajouterCommande(testCommande.getNomClient(), testCommande.getDateRecuperation(), testCommande.getPrix(), testCommande.getMontantPaye(), testCommande.getCommentaires(), testCommande.getListePlateaux());
            System.out.println(autre);
            System.out.println(charcuterie);
            System.out.println(fromage);
            System.out.println(fruit);
            
            //affichage des listes d'objets de la BDD
            System.out.println("\naffichage des listes d'objets de la BDD : ");
            System.out.println(c.toutRecupererAutre());
            System.out.println(c.toutRecupererCharcuterie());
            System.out.println(c.toutRecupererFromage());
            System.out.println(c.toutRecupererFruit());
            System.out.println(c.toutRecupererPlateauPerso());
            System.out.println(c.toutRecupererPlateauStandard()); 
            System.out.println(c.toutRecupererCommande());
            
            //modification des objets
            System.out.println("\nmodification des objets ... ");
            autre.setNom("testModifie");
            autre.setPrix(3);
            charcuterie.setNom("testModifie");
            charcuterie.setPrix(3);
            charcuterie.setQuantiteParPersonnes(3);
            charcuterie.setUnite(Unite.PIECE);
            commande.setNomClient("testModifie");
            commande.setDateRecuperation(LocalDate.of(2050, Month.MARCH, 10));
            commande.setEtatCommande(new Preparee(commande));
            commande.setPrix(3);
            commande.setMontantPaye(3);
            fromage.setNom("testModifie");
            fromage.setPrix(3);
            fromage.setQuantiteParPersonnes(3);
            fromage.setUnite(Unite.PIECE);
            fruit.setNom("testModifie");
            fruit.setPrix(3);
            fruit.setQuantiteParPersonnes(3);
            fruit.setUnite(Unite.PIECE);
            plateauPerso.setNomPlateau("testModifie");
            plateauStandard.setPrix(3);
            
            //enregistrement des modifications des objets en BDD
            System.out.println("\nmodification d'un objet en BDD ... ");
            c.modifierAutre(autre, autre.getNom(), autre.getPrix());
            c.modifierCharcuterie(charcuterie, charcuterie.getNom(), charcuterie.getPrix(), charcuterie.getQuantiteParPersonnes(), charcuterie.getUnite());
            ArrayList plateauAAjouterACommande = new ArrayList();//creation de l'ArrayList d'ArrayList de plateaux a ajouter a la commande
            plateauAAjouterACommande.add(new PlateauPerso(4, "testAjoute"));
            plateauAAjouterACommande.add(4);
            plateauAAjouterACommande.add(4);
            ArrayList<ArrayList> plateauxAAjouterACommande = new ArrayList<ArrayList>();
            plateauxAAjouterACommande.add(plateauAAjouterACommande);
            c.modifierCommande(commande, commande.getNomClient(), commande.getDateRecuperation(), commande.getPrix(), commande.getMontantPaye(), commande.getCommentaires(), plateauxAAjouterACommande);
            c.modifierFromage(fromage, fromage.getNom(), fromage.getPrix(), fromage.getQuantiteParPersonnes(), fromage.getUnite());
            c.modifierFruit(fruit, fruit.getNom(), fruit.getPrix(), fruit.getQuantiteParPersonnes(), fruit.getUnite());
            ArrayList produitsAAjouterAuPlateauPerso = new ArrayList();//creation de l'ArrayList d'ArrayList de produits a ajouter au plateau perso
            produitsAAjouterAuPlateauPerso.add(new Fromage("testAjouter", 4, -1));
            c.modifierPlateauPerso(plateauPerso, plateauPerso.getPrix(), plateauPerso.getNomPlateau(), produitsAAjouterAuPlateauPerso);
            ArrayList produitsAAjouterAuPlateauStandard = new ArrayList();//creation de l'ArrayList d'ArrayList de produits a ajouter au plateau standard
            produitsAAjouterAuPlateauStandard.add(new Fruit("testAjouter", 4, -1));
            c.modifierPlateauStandard(plateauStandard, plateauStandard.getPrix(), plateauStandard.getNomPlateau(), produitsAAjouterAuPlateauStandard);
            
            //afichage d'un objet de la BDD
            System.out.println("\naffichage d'un objet de la BDD : ");
            System.out.println(c.recupererAutre(autre.getIdProduit()));
            System.out.println(c.recupererCharcuterie(charcuterie.getIdProduit()));
            System.out.println(c.recupererFromage(fromage.getIdProduit()));
            System.out.println(c.recupererFruit(fruit.getIdProduit()));
            System.out.println(c.recupererPlateau(plateauPerso.getIdPlateau()));
            System.out.println(c.recupererPlateau(plateauStandard.getIdPlateau()));
            System.out.println(c.recupererCommande(commande.getIdCommande()));
            
            //suppression des objets en BDD
            //la suppression en fonctionnne pas encore pour les plateaux et les commandes
            System.out.println("\nsuppression des objets en BDD ...");
//            c.supprimer(autre);
//            c.supprimer(charcuterie);
//            c.supprimer(fromage);
//            c.supprimer(fruit);
//            c.supprimer(plateauPerso);
//            c.supprimer(plateauStandard);
//            c.supprimer(commande);
            
        } catch (SQLException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, "probleme avec la BDD", ex);
        }
    }
}
