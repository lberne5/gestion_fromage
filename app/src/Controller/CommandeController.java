/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Commande.APreparer;
import Model.Commande.Archivee;
import Model.Commande.Commande;
import Model.Commande.EtatCommande;
import Model.Commande.Preparee;
import Model.Commande.Recuperee;
import Model.Exceptions.IdDejaDefiniException;
import Model.Plateaux.*;
import Model.Produits.AutreProduit;
import Model.Produits.Charcuterie;
import Model.Produits.Fromage;
import Model.Produits.Fruit;
import Model.Produits.Produit;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timon Fournier
 */
public class CommandeController {

    public static Connection connexion;

    public CommandeController(Connection conn) {
        this.connexion = conn;
    }

    public static Commande ajouter(Commande commande) throws SQLException {
        //crée la commande
        Statement stateCommande = connexion.createStatement();
        stateCommande.executeUpdate("INSERT INTO COMMANDE VALUES('" + 0/*id non pris en compte*/ + "', '" + commande.getNomClient() + "', '" + commande.getDateEcriture() + "', '" + commande.getDateRecuperation() + "', '" + commande.getPrix() + "', '" + commande.getMontantPaye() + "', '" + commande.getCommentaires() + "', '" + "A PREPARER" + "')", Statement.RETURN_GENERATED_KEYS);

        //récupère l'id de la commande
        ResultSet generatedKeys = stateCommande.getGeneratedKeys();
        generatedKeys.next();
        int commandeId = generatedKeys.getInt(1);
        try {
            commande.setIdCommande(commandeId);
        } catch (IdDejaDefiniException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Object element : commande.getListePlateaux()) {
            //associe les id de plateaux avec l'id de la commande
            Statement stateCommandePlateau = connexion.createStatement();
            stateCommandePlateau.executeUpdate("INSERT INTO COMMANDE_PLATEAU VALUES('" + commandeId + "', '" + ((Plateau) ((ArrayList) element).get(0)).getIdPlateau() + "', '" + ((ArrayList) element).get(1) + "', '" + ((ArrayList) element).get(2) + "' )");
        }

        return commande;
    }

    public static ArrayList<Commande> toutRecuperer() throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM COMMANDE");
        ArrayList<Commande> retour = new ArrayList<Commande>();
        while (result.next()) {
            Commande commandeARetourner = new Commande(result.getInt("id"), result.getString("client"), result.getDate("date_commande"), result.getDate("date_recuperation"), result.getDouble("prix"), result.getDouble("montant_paye"), result.getString("commentaire"), null/*on ne peut pas convertir un String en EtatCommande*/);
            if (result.getString("etat").equals("A PREPARER")) {
                commandeARetourner.setEtatCommande(new APreparer(commandeARetourner));
            } else if (result.getString("etat").equals("PREPAREE")) {
                commandeARetourner.setEtatCommande(new Preparee(commandeARetourner));
            } else if (result.getString("etat").equals("RECUPEREE")) {
                commandeARetourner.setEtatCommande(new Recuperee(commandeARetourner));
            } else {
                commandeARetourner.setEtatCommande(new Archivee(commandeARetourner));
            }
            Plateau plateauAAJouter = null;

            //pour chaque commmande, on lui ajoute tous ses plateaux avant de la mettre dans l'ArrayList de retour
            Statement statePlateaux = connexion.createStatement();
            ResultSet resultPlateaux = statePlateaux.executeQuery("SELECT STANDARD, NOM, ID, PRIX, QUANTITE, NOMBRE_PERSONNE FROM COMMANDE_PLATEAU, PLATEAU WHERE COMMANDE_PLATEAU.PLATEAU = PLATEAU.ID AND COMMANDE =" + result.getInt("id") + ";");
            while (resultPlateaux.next()) {
                if (resultPlateaux.getBoolean("standard")) {
                    plateauAAJouter = new PlateauStandard(resultPlateaux.getInt("id"), resultPlateaux.getDouble("prix"), resultPlateaux.getString("nom"));
                    //pour chaque plateau, on lui ajoute tout ses produits
                    ArrayList<Produit> listeProduitDePlateau = PlateauController.recupererProduitsDuPlateau(resultPlateaux.getInt("id"));
                    for (Produit element : listeProduitDePlateau) {
                        plateauAAJouter.ajouterProduitDansListe(element);
                    }
                    commandeARetourner.ajouterACommande(plateauAAJouter,
                            resultPlateaux.getInt("quantite"),
                            resultPlateaux.getInt("nombre_personne"));
                } else {
                    plateauAAJouter = new PlateauPerso(resultPlateaux.getInt("id"), resultPlateaux.getDouble("prix"), resultPlateaux.getString("nom"));
                    //pour chaque plateau, on lui ajoute tout ses produits
                    ArrayList<Produit> listeProduitDePlateau = PlateauController.recupererProduitsDuPlateau(resultPlateaux.getInt("id"));
                    for (Produit element : listeProduitDePlateau) {
                        plateauAAJouter.ajouterProduitDansListe(element);
                    }
                    commandeARetourner.ajouterACommande(plateauAAJouter,
                            resultPlateaux.getInt("quantite"),
                            resultPlateaux.getInt("nombre_personne"));
                }
            }
            retour.add(commandeARetourner);
        }
        return retour;
    }

    public static Commande recuperer(int id) throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM COMMANDE WHERE ID = " + id);
        Commande commandeARetourner;
        EtatCommande etat = null;
        if (result.next()) {
            //on cree la commande
            switch (result.getString("etat")) {
                case "A PREPARER":
                    etat = new APreparer(null);
                    break;
                case "PREPAREE":
                    etat = new Preparee(null);
                    break;
                case "RECUPEREE":
                    etat = new Recuperee(null);
                    break;
                case "ARCHIVEE":
                    etat = new Archivee(null);
                    break;
            }
            commandeARetourner = new Commande(id, result.getString("client"), result.getDate("date_commande"), result.getDate("date_recuperation"), result.getDouble("prix"), result.getDouble("montant_paye"), result.getString("commentaire"), etat);
            //on ajoute les plateaux a la commande
            Statement statePlateaux = connexion.createStatement();
            ResultSet resultPlateaux = statePlateaux.executeQuery("SELECT STANDARD, NOM, ID, PRIX, QUANTITE, NOMBRE_PERSONNE FROM COMMANDE_PLATEAU, PLATEAU WHERE COMMANDE_PLATEAU.PLATEAU = PLATEAU.ID AND COMMANDE =" + result.getInt("id") + ";");
            Plateau plateauAAjouter;
            while (resultPlateaux.next()) {
                if (resultPlateaux.getBoolean("standard")) {
                    plateauAAjouter = new PlateauStandard(resultPlateaux.getInt("id"), resultPlateaux.getDouble("prix"), resultPlateaux.getString("nom"));
                    //pour chaque plateau, on lui ajoute tout ses produits
                    ArrayList<Produit> listeProduitDePlateau = PlateauController.recupererProduitsDuPlateau(resultPlateaux.getInt("id"));
                    for (Produit element : listeProduitDePlateau) {
                        plateauAAjouter.ajouterProduitDansListe(element);
                    }
                    commandeARetourner.ajouterACommande(plateauAAjouter,
                            resultPlateaux.getInt("quantite"),
                            resultPlateaux.getInt("nombre_personne"));
                } else {
                    plateauAAjouter = new PlateauPerso(resultPlateaux.getInt("id"), resultPlateaux.getDouble("prix"), resultPlateaux.getString("nom"));
                    //pour chaque plateau, on lui ajoute tout ses produits
                    ArrayList<Produit> listeProduitDePlateau = PlateauController.recupererProduitsDuPlateau(resultPlateaux.getInt("id"));
                    for (Produit element : listeProduitDePlateau) {
                        plateauAAjouter.ajouterProduitDansListe(element);
                    }
                    commandeARetourner.ajouterACommande(plateauAAjouter,
                            resultPlateaux.getInt("quantite"),
                            resultPlateaux.getInt("nombre_personne"));
                }
            }
            return commandeARetourner;
        } else {
            return null;
        }
    }

    public static void modifier(Commande nouvelleCommande) throws SQLException {
        Statement state = connexion.createStatement();
        String etat;
        switch (nouvelleCommande.getEtatCommande().toString()) {
            case "APreparer.":
                etat = "A PREPARER";
                break;
            case "Preparee.":
                etat = "PREPAREE";
                break;
            case "Recuperee.":
                etat = "RECUPEREE";
                break;
            case "Archivee.":
                etat = "ARCHIVEE";
                break;
            default:
                etat = "A PREPARER";
        }
        state.executeUpdate("UPDATE COMMANDE SET CLIENT = '" + nouvelleCommande.getNomClient() + "', DATE_RECUPERATION = '" + nouvelleCommande.getDateRecuperation() + "', PRIX = '" + nouvelleCommande.getPrix() + "', MONTANT_PAYE = '" + nouvelleCommande.getMontantPaye() + "', COMMENTAIRE = '" + nouvelleCommande.getCommentaires() + "', ETAT = '" + etat + "' WHERE ID = " + nouvelleCommande.getIdCommande());
    }

    public static void supprimer(Commande commande) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("DELETE FROM COMMANDE WHERE ID = " + commande.getIdCommande());
        supprimerPlateauxDeLaCommande(commande.getIdCommande());
    }

    public static void supprimerPlateauxDeLaCommande(int idCommande) throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet resultPlateaux = state.executeQuery("SELECT PLATEAU FROM COMMANDE_PLATEAU WHERE COMMANDE = " + idCommande);
        System.out.println("commande supprimée : " + idCommande);
        while (resultPlateaux.next()) {
            System.out.println("plateau supprimé" + resultPlateaux.getInt("plateau"));
            Statement stateSuppr = connexion.createStatement();
            stateSuppr.executeUpdate("DELETE FROM PLATEAU WHERE ID = " + resultPlateaux.getInt("plateau"));
            PlateauController.supprimerProduitsDuPlateau(resultPlateaux.getInt("plateau"));
        }
        System.out.println("commande_plateau supprimé : " + idCommande);
        state.executeUpdate("DELETE FROM COMMANDE_PLATEAU WHERE COMMANDE = " + idCommande);
    }

}
