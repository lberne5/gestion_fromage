/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Model.Util;

/**
 * Enumération qui sert pour savoir en quelle unité les produits quantifiables de 
 * l'application (les Fromages, Charcuteries et Fruits) sont comptés. 
 * @author Lea Berne
 */
public enum Unite {
    PIECE ("piece"),
    GRAMMES ("gramme"),
    INDEFINIE ("indefinie");
    private final String nom;
    private Unite(String nom){
        this.nom = nom;
    }
    
    /**
     *
     * @return String : le nom de l'unite
     */
    @Override
    public String toString(){
        return this.nom;
    }
}
