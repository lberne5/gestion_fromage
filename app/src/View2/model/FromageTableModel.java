/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View2.model;

import Model.Produits.Fromage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author potatos
 */
public class FromageTableModel extends AbstractTableModel {
    
    private List<Fromage> fromageProduits = new ArrayList<>();
    private String[] fromageColumn = {"Nom du fromage", "Quantité / Personne", "Unite", "Prix unitaire (Euro)"};
    
    public void addFromage(Fromage fromage){
        this.fromageProduits.add(fromage);
        //Ca sert a reinitialiser de la ligne zero à la ligne maximale du tableau
        fireTableRowsInserted(0, fromageProduits.size());
        
    }
    
    public void removeAllFromages(){
        this.fromageProduits.removeAll(fromageProduits);
    }
    
    @Override
    public int getRowCount() {
        return this.fromageProduits.size();
    }

    @Override
    public int getColumnCount() {
        return fromageColumn.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fromage fromage = fromageProduits.get(rowIndex);
        switch (columnIndex){
            case 0: return fromage.getNom();
            case 1: return fromage.getQuantiteParPersonnes();
            case 2: return fromage.getUnite();
            case 3: return fromage.getPrix();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return fromageColumn[column];
    }
    
}
