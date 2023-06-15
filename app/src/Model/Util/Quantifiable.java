/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Util;

/**
 * Interface pour les produits quantifiables de l'application 
 * (les Fromages, Charcuteries et Fruits)
 * @author Lea Berne
 */
public interface Quantifiable {
    double getQuantiteParPersonnes();
    void setQuantiteParPersonnes(double quantite);
    Unite getUnite();
    void setUnite(Unite unite);   
}
