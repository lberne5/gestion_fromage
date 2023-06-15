/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Exceptions.IdDejaDefiniException;
import Model.Produits.AutreProduit;
import Model.Produits.Produit;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timon Fournier
 */
public class AutreController {
    
    
    public static Connection connexion;
    
    
    public AutreController(Connection conn) throws SQLException {
        connexion = conn;
    }
    
    public static AutreProduit ajouter(AutreProduit produit) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("INSERT INTO PRODUIT (NOM, UNITE, TYPE, PRIX) VALUES('" + produit.getNom() + "', '" + "INDEFINIE" + "', '" + "AUTRE"  + "', "  + produit.getPrix() + ")", Statement.RETURN_GENERATED_KEYS); 
        //retourne l'id
        ResultSet generatedKeys = state.getGeneratedKeys();
        generatedKeys.next();
        try {
            produit.setIdProduit(generatedKeys.getInt(1));
        } catch (IdDejaDefiniException ex) {
            Logger.getLogger(AutreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produit;
    }
    
    public static ArrayList<AutreProduit> toutRecuperer() throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT NOM, PRIX, ID FROM PRODUIT WHERE TYPE = 'AUTRE';");
        
        ArrayList<AutreProduit> retour = new ArrayList<AutreProduit>();
        while(result.next())
            retour.add(new AutreProduit(result.getString("nom"), result.getDouble("prix"), result.getInt("id")));
        return retour;
    }
    
    public static AutreProduit recuperer(int id) throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT NOM, PRIX FROM PRODUIT WHERE ID = " + id + " AND TYPE = 'AUTRE'");
        if(result.next())
            return new AutreProduit(result.getString("nom"), result.getDouble("prix"), id);
        else 
            return null;
    }
    
    //on prend en parametre le produit qui a été modifié grâce aux setters
    public static void modifier(AutreProduit nouveauProduit) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("UPDATE PRODUIT SET NOM = '" + nouveauProduit.getNom() + "', PRIX = '" + nouveauProduit.getPrix() + "' WHERE ID = " + nouveauProduit.getIdProduit());
    }
    
    public static void supprimer(AutreProduit produit) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("DELETE FROM PRODUIT WHERE ID = " + produit.getIdProduit());
        state.executeUpdate("DELETE FROM PLATEAU_PRODUIT WHERE PRODUIT = " + produit.getIdProduit());
    }
}
