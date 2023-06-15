/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Exceptions.IdDejaDefiniException;
import Model.Produits.Charcuterie;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timon Fournier
 */
public class CharcuterieController {
    
    public static Connection connexion;
    
    public CharcuterieController(Connection conn) {
        this.connexion = conn;
    }
    
    public static Charcuterie ajouter(Charcuterie charcuterie) throws SQLException{
        Statement state = connexion.createStatement();
        state.executeUpdate("INSERT INTO PRODUIT VALUES('" + 0/*id non pris en compte*/ + "', '" + charcuterie.getNom() + "', '" + charcuterie.getUnite().toString().toUpperCase() + "', '" + charcuterie.getQuantiteParPersonnes() + "', '" + "CHARCUTERIE" + "', '" + charcuterie.getPrix() + "')", Statement.RETURN_GENERATED_KEYS); //il faudrait catch java.sql.SQLIntegrityConstraintViolationException pour savoir si la clé primaire n'existe pas déjà ;
        ResultSet generatedKeys = state.getGeneratedKeys();
        generatedKeys.next();
        try {
            charcuterie.setIdProduit(generatedKeys.getInt(1));
        } catch (IdDejaDefiniException ex) {
            Logger.getLogger(CharcuterieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return charcuterie;
    }
    
    public static ArrayList<Charcuterie> toutRecuperer() throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT NOM, PRIX, QUANTITE_PAR_PERSONNE, UNITE, ID FROM PRODUIT WHERE TYPE = 'CHARCUTERIE';");
        
        ArrayList<Charcuterie> retour = new ArrayList<Charcuterie>();
        while(result.next())
            retour.add(new Charcuterie(result.getString("nom"), result.getDouble("prix"), result.getDouble("quantite_par_personne"), result.getString("unite"), result.getInt( "id")));
        return retour;
    }
    
    public static Charcuterie recuperer(int id) throws SQLException {
        Statement state = connexion.createStatement();
        ResultSet result = state.executeQuery("SELECT * FROM PRODUIT WHERE ID = " + id + " AND TYPE = 'CHARCUTERIE'");
        if(result.next())
            return new Charcuterie(result.getString("nom"), result.getDouble("prix"), result.getDouble("quantite_par_personne"), result.getString("unite"), id);
        else
            return null;
    }
    
    public static void modifier(Charcuterie nouvelleCharcuterie) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("UPDATE PRODUIT SET NOM = '" + nouvelleCharcuterie.getNom() + "', UNITE = '" + nouvelleCharcuterie.getUnite().toString().toUpperCase() + "', QUANTITE_PAR_PERSONNE = '" + nouvelleCharcuterie.getQuantiteParPersonnes() + "', PRIX = '" + nouvelleCharcuterie.getPrix() + "' WHERE ID = " + nouvelleCharcuterie.getIdProduit());
    }
    
    public static void supprimer(Charcuterie charcuterie) throws SQLException {
        Statement state = connexion.createStatement();
        state.executeUpdate("DELETE FROM PRODUIT WHERE ID = " + charcuterie.getIdProduit());
        state.executeUpdate("DELETE FROM PLATEAU_PRODUIT WHERE PRODUIT = " + charcuterie.getIdProduit());
    }
}
