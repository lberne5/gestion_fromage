/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Exceptions.IdDejaDefiniException;
import Model.Produits.Fromage;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timon Fournier
 */
public class FromageController {
    
    public static Connection connexion;
    
    public FromageController(Connection conn) {
        FromageController.connexion = conn;
    }
    
    public static Fromage ajouter(Fromage fromage) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("INSERT INTO PRODUIT VALUES('" + 0/*id non pris en compte*/ + "', '" + fromage.getNom() + "', '"  + fromage.getUnite().toString().toUpperCase() + "', '" + fromage.getQuantiteParPersonnes() + "', '" + "FROMAGE" + "', '" + fromage.getPrix() + "' )", Statement.RETURN_GENERATED_KEYS); 
        ResultSet generatedKeys = state.getGeneratedKeys();
        generatedKeys.next();
        try {
            fromage.setIdProduit(generatedKeys.getInt(1));
        } catch (IdDejaDefiniException ex) {
            Logger.getLogger(FromageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fromage;
    }
    
    public static ArrayList<Fromage> toutRecuperer() throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT ID, NOM, QUANTITE_PAR_PERSONNE, UNITE, PRIX, QUANTITE_PAR_PERSONNE FROM PRODUIT WHERE TYPE = 'FROMAGE';");
        
        ArrayList<Fromage> retour = new ArrayList<Fromage>();
        while(result.next())
            retour.add(new Fromage(result.getString("nom"), result.getDouble("prix"), result.getDouble("quantite_par_personne"), result.getString("unite"), result.getInt("id")));
        return retour;
    }
    
    public static Fromage recuperer(int id) throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM PRODUIT WHERE ID = " + id + " AND TYPE = 'FROMAGE'");
        if(result.next())
            return new Fromage(result.getString("nom"), result.getDouble("prix"), result.getDouble("quantite_par_personne"), result.getString("unite"), id);
        else
            return null;
    }
    
    public static void modifier(Fromage nouveauFromage) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("UPDATE PRODUIT SET NOM = '" + nouveauFromage.getNom() + "', UNITE = '" + nouveauFromage.getUnite().toString().toUpperCase() + "', QUANTITE_PAR_PERSONNE = '" + nouveauFromage.getQuantiteParPersonnes() + "', PRIX = '" + nouveauFromage.getPrix() + "' WHERE ID = " + nouveauFromage.getIdProduit());
    }
    
    public static void supprimer(Fromage fromage) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("DELETE FROM PRODUIT WHERE ID = " + fromage.getIdProduit());
        state.executeUpdate("DELETE FROM PLATEAU_PRODUIT WHERE PRODUIT = " + fromage.getIdProduit());
    }
    
}
