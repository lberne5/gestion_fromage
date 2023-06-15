/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timon Fournier
 */
public class ConnexionBDD {
    
    public static Connection initConnexion() throws SQLException{ 
        String url = "jdbc:mysql://root@127.0.0.1:3306/bdd_fromages";
        return DriverManager.getConnection(url); //retourne la connexion
    }
    
    public static void fermeConnexion(Connection connexion) throws SQLException {
        connexion.close();
    }
}
