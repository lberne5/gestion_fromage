/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View2.model;

import Model.Commande.Commande;
import Model.Produits.Produit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Timon Fournier
 */
public class AccueilTableModel extends AbstractTableModel {
    
    private List<Commande> listeCommandes = new ArrayList<Commande>();
    private List<ArrayList> listeProduits = new ArrayList<ArrayList>();
    private String[] colonnes = {"Commandes à préparer pour demain", "Produits nécessaires"};
    
    public void addCommande(Commande commande){
        this.listeCommandes.add(commande);
        //Ca sert a reinitialiser de la ligne zero à la ligne maximale du tableau
        fireTableRowsInserted(0, listeCommandes.size());
    }
    
    //affiche dans la colonne de gauche les produits de la commande selectionnee
    public void showCommande(int rowIndex){
        Commande commandeSelectionnee = listeCommandes.get(rowIndex);
        this.listeProduits = commandeSelectionnee.getListeProduitsNecessaires();
        fireTableDataChanged();
    }
    
    public void removeAllCommandes(){
        this.listeCommandes.removeAll(listeCommandes);
    }
    
    @Override
    public int getRowCount() {
        return this.listeCommandes.size();
    }

    @Override
    public int getColumnCount() {
        return colonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Commande commande = listeCommandes.get(rowIndex);
        switch (columnIndex){
            case 0: return commande.getNomClient();
            case 1: 
                if(listeProduits != null && listeProduits.size() > rowIndex)
                    return listeProduits.get(rowIndex).get(1) + "x " + listeProduits.get(rowIndex).get(0);
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return colonnes[column];
    }
    
}
