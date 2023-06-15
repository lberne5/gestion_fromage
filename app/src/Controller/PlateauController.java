/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Exceptions.IdDejaDefiniException;
import Model.Plateaux.*;
import Model.Produits.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timon Fournier
 */
public class PlateauController {
    
    public static Connection connexion;
    
    public PlateauController(Connection conn) {
       this.connexion = conn;
    }
    
    public static Plateau ajouter(Plateau plateau) throws SQLException {
        //crée le plateau
        Statement statePlateau = connexion.createStatement();
        statePlateau.executeUpdate("INSERT INTO PLATEAU (STANDARD, NOM, PRIX) VALUES('" + ((plateau.getClass() == PlateauStandard.class)?1:0/*conversion en int*/) + "', '" + plateau.getNomPlateau() + "', '" + plateau.getPrix() + "' )", Statement.RETURN_GENERATED_KEYS);
        
        //récupère l'id du plateau
        ResultSet generatedKeys = statePlateau.getGeneratedKeys();
        generatedKeys.next();
        int plateauId = generatedKeys.getInt(1);
        try {
            plateau.setIdPlateau(plateauId);
        } catch (IdDejaDefiniException ex) {
            Logger.getLogger(PlateauController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        if(plateau.getListeProduits() != null) {
        //associe l'id du plateau avec les id de produits de la liste
            for (Produit element : plateau.getListeProduits()) {
                Statement statePlateauProduit = connexion.createStatement();
                statePlateauProduit.executeUpdate("INSERT INTO PLATEAU_PRODUIT VALUES('" + plateauId + "', '" + element.getIdProduit() + "', '" + 1 + "' )");
            }
        }
        return plateau;
    }
    
    public static ArrayList<PlateauPerso> toutRecupererPerso() throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT ID, PRIX, STANDARD, NOM FROM PLATEAU;");
        
        ArrayList<PlateauPerso> retour = new ArrayList<PlateauPerso>();
        PlateauPerso plateauPersoARetourner;
        while(result.next())
        {
            if(!result.getBoolean("standard"))
            {
                plateauPersoARetourner = new PlateauPerso(result.getInt("id"), result.getDouble("prix"), result.getString("nom"));
                ArrayList<Produit> listeProduitDePlateau = recupererProduitsDuPlateau(result.getInt("id"));
                for(Produit element : listeProduitDePlateau)
                {
                    plateauPersoARetourner.ajouterProduitDansListe(element);
                }
                retour.add(plateauPersoARetourner);
            }
        }
        return retour;
    }
    
    public static ArrayList<PlateauStandard> toutRecupererStandard() throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT ID, PRIX, STANDARD, NOM FROM PLATEAU;");
        
        ArrayList<PlateauStandard> retour = new ArrayList<PlateauStandard>();
        PlateauStandard plateauStandardARetourner;
        while(result.next())
        {
            if(result.getBoolean("standard"))
            {
                plateauStandardARetourner = new PlateauStandard(result.getInt("id"), result.getDouble("prix"), result.getString("nom"));
                ArrayList<Produit> listeProduitDePlateau = recupererProduitsDuPlateau(result.getInt("id"));
                for(Produit element : listeProduitDePlateau)
                {
                    plateauStandardARetourner.ajouterProduitDansListe(element);
                }
                retour.add(plateauStandardARetourner);
            }
        }
        return retour;
    }
    
    public static Plateau recuperer(int id) throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM PLATEAU WHERE ID = " + id);
        Plateau plateauARetourner;
        if(result.next()) {
            //on cree le plateau
            if(result.getBoolean("standard"))
                plateauARetourner = new PlateauStandard(id, result.getDouble("prix"), result.getString("nom"));
            else
                plateauARetourner =  new PlateauPerso(id, result.getDouble("prix"), result.getString("nom"));
            //on ajoute les produits au plateau
            ArrayList<Produit> listeProduitDePlateau = recupererProduitsDuPlateau(result.getInt("id"));
                for(Produit element : listeProduitDePlateau)
                {
                    plateauARetourner.ajouterProduitDansListe(element);
                }
                return plateauARetourner;
        } 
        else
            return null;
    }
    
    public static ArrayList<Produit> recupererProduitsDuPlateau(int idPlateau) throws SQLException{
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT ID, NOM, UNITE, QUANTITE_PAR_PERSONNE, TYPE, QUANTITE, PRIX FROM PLATEAU_PRODUIT, PRODUIT WHERE PLATEAU_PRODUIT.PRODUIT = PRODUIT.ID AND PLATEAU_PRODUIT.PLATEAU = " + idPlateau + ";");
        
        ArrayList retour = new ArrayList<Produit>();
        while(result.next())
            switch(result.getString("type")){
                case "AUTRE" :
                    retour.add(new AutreProduit(result.getString("nom"), result.getDouble("prix"), result.getInt("id")));
                    break;
                case "CHARCUTERIE" : 
                    retour.add(new Charcuterie(result.getString("nom"), result.getDouble("prix"), result.getDouble("quantite_par_personne"), result.getString("unite"), result.getInt("prix")));
                    break;
                case "FROMAGE" :
                    retour.add(new Fromage(result.getString("nom"), result.getDouble("prix"), result.getDouble("quantite_par_personne"), result.getString("unite"), result.getInt("id")));
                    break;
                case "FRUIT" :
                    retour.add(new Fruit(result.getString("nom"), result.getDouble("prix"), result.getInt("id")));
                    break;
            }
        return retour;
    }
    
    public static void supprimer(Plateau plateau) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("DELETE FROM PLATEAU WHERE ID = " + plateau.getIdPlateau());
        state.executeUpdate("DELETE FROM COMMANDE_PLATEAU WHERE PLATEAU = " + plateau.getIdPlateau());
        supprimerProduitsDuPlateau(plateau.getIdPlateau());
    }
    
    public static void modifier(Plateau nouveauPlateau) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("UPDATE PLATEAU SET NOM = '" + nouveauPlateau.getNomPlateau() + "', PRIX = " + nouveauPlateau.getPrix() + " WHERE ID = " + nouveauPlateau.getIdPlateau());
    }
    
    public static void supprimerProduitsDuPlateau(int idPlateau) throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet resultProduits = state.executeQuery("SELECT PRODUIT FROM PLATEAU_PRODUIT WHERE PLATEAU = " + idPlateau);
        while(resultProduits.next()) {
            Statement stateSuppr = connexion.createStatement();
            stateSuppr.executeUpdate("DELETE FROM PRODUIT WHERE ID = " + resultProduits.getInt("produit"));
        }
        state.executeUpdate("DELETE FROM PLATEAU_PRODUIT WHERE PLATEAU = " + idPlateau);
    }
}
