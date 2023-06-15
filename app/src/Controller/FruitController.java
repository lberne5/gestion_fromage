/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Exceptions.IdDejaDefiniException;
import Model.Produits.Fruit;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timon Fournier
 */
public class FruitController {
    
    public static Connection connexion;
    
    public FruitController(Connection conn) {
        this.connexion = conn;
    }
    
    public static Fruit ajouter(Fruit fruit) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("INSERT INTO PRODUIT VALUES('" + 0/*id non pris en compte*/ + "', '" + fruit.getNom() +  "', '" + fruit.getUnite().toString().toUpperCase() + "', '" + fruit.getQuantiteParPersonnes() + "', '" + "FRUIT" + "', '" + fruit.getPrix() + "' )", Statement.RETURN_GENERATED_KEYS); 
        ResultSet generatedKeys = state.getGeneratedKeys();
        generatedKeys.next();
        try {
            fruit.setIdProduit(generatedKeys.getInt(1));
        } catch (IdDejaDefiniException ex) {
            Logger.getLogger(FruitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fruit;
    }
    
    public static ArrayList<Fruit> toutRecuperer() throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT ID, NOM, PRIX, QUANTITE_PAR_PERSONNE, UNITE FROM PRODUIT WHERE TYPE = 'FRUIT';");
        
        ArrayList<Fruit> retour = new ArrayList<Fruit>();
        while(result.next())
            retour.add(new Fruit(result.getString("nom"), result.getDouble("prix"), result.getDouble("quantite_par_personne"), result.getString("unite"), result.getInt("id")));
        return retour;
    }
    
    public static Fruit recuperer(int id) throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM PRODUIT WHERE ID = " + id + " AND TYPE = 'FRUIT'");
        if(result.next())
            return new Fruit(result.getString("nom"), result.getDouble("prix"), result.getDouble("quantite_par_personne"), result.getString("unite"), id);
        else
            return null;
    }
    
    public static void modifier(Fruit nouveauFruit) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("UPDATE PRODUIT SET NOM = '" + nouveauFruit.getNom() + "', UNITE = '" + nouveauFruit.getUnite().toString().toUpperCase() + "', QUANTITE_PAR_PERSONNE = '" + nouveauFruit.getQuantiteParPersonnes() + "', PRIX = '" + nouveauFruit.getPrix() + "' WHERE ID = " + nouveauFruit.getIdProduit());
    }
    
    public static void supprimer(Fruit fruit) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("DELETE FROM PRODUIT WHERE ID = " + fruit.getIdProduit());
        state.executeUpdate("DELETE FROM PLATEAU_PRODUIT WHERE PRODUIT = " + fruit.getIdProduit());
    }
}
