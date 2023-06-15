/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View2.model;

import Model.Produits.*;
import Model.Commande.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Léa Berne
 */
public class TotalPlanificationModel extends AbstractTableModel {

    private List<Produit> produits = new ArrayList<>();
    private List<Double> quantites = new ArrayList<>();

    private String[] totalPlanificationColumn = {"Nom du produit", "Quantité"};

    public void addProduitsDeCommande(Commande commande) {
        ArrayList listeAAjouter = commande.getListeProduitsNecessaires();
        if (listeAAjouter != null && !listeAAjouter.isEmpty()) {
            Iterator iter = listeAAjouter.iterator();
            while (iter.hasNext()) {
                ArrayList elementAAjouter = (ArrayList) iter.next();
                Produit produitAAjouter = (Produit) elementAAjouter.get(0);
                double quantiteAAjouter = (double) elementAAjouter.get(1);
                if (produits.contains(produitAAjouter)) {
                    var index = produits.indexOf(produitAAjouter);
                    double quantite = this.quantites.get(index);
                    quantite += quantiteAAjouter;
                    this.produits.remove(produitAAjouter);
                    this.quantites.remove(index);

                    this.produits.add(produitAAjouter);
                    if (quantite <= 0) {
                        quantite ++;
                    }
                    this.quantites.add(quantite);
                } else {
                    if (quantiteAAjouter <= 0) {
                        quantiteAAjouter ++;
                    }
                    this.produits.add(produitAAjouter);
                    this.quantites.add(quantiteAAjouter);
                }
            }
        }
        fireTableRowsInserted(0, produits.size());
    }

    public void removeAllTotalPlanification() {
        this.produits.removeAll(produits);
        this.quantites.removeAll(quantites);
    }

    @Override
    public int getRowCount() {
        return this.produits.size();
    }

    @Override
    public int getColumnCount() {
        return totalPlanificationColumn.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                Produit produit = produits.get(rowIndex);
                return produit.getNom();
            case 1:
                double quantite = quantites.get(rowIndex);
                return quantite;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return totalPlanificationColumn[column];
    }

}
