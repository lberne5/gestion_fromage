/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View2.model;

import Model.Produits.AutreProduit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author potatos
 */
public class AutreTableModel extends AbstractTableModel {
    
    private List<AutreProduit> autreProduits = new ArrayList<>();
    private String[] autreColumn = {"Nom du nouvel élément", "Prix unitaire (Euro)"};
    
    public void addAutre(AutreProduit autre){
        this.autreProduits.add(autre);
        //Ca sert a reinitialiser de la ligne zero à la ligne maximale du tableau
        fireTableRowsInserted(0, autreProduits.size());
        
    }
    
    public void removeAllAutre(){
        this.autreProduits.removeAll(autreProduits);
    }
    
    @Override
    public int getRowCount() {
        return this.autreProduits.size();
    }

    @Override
    public int getColumnCount() {
        return autreColumn.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AutreProduit autre = autreProduits.get(rowIndex);
        switch (columnIndex){
            case 0: return autre.getNom();
            case 1: return autre.getPrix();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return autreColumn[column];
    }
    
}
