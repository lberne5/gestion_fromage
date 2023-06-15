/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View2.model;

import Model.Produits.Charcuterie;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author potatos
 */
public class CharcuterieTableModel extends AbstractTableModel {
    
    private List<Charcuterie> charcuterieProduits = new ArrayList<>();
    private String[] charcuterieColumn = {"Nom de la charcuterie", "Quantité / Personne", "Unite", "Prix unitaire (Euro)"};
    
    public void addCharcuterie(Charcuterie charcuterie){
        this.charcuterieProduits.add(charcuterie);
        //Ca sert a reinitialiser de la ligne zero à la ligne maximale du tableau
        fireTableRowsInserted(0, charcuterieProduits.size());
        
    }
    
    public void removeAllCharcuterie(){
        this.charcuterieProduits.removeAll(charcuterieProduits);
    }
    
    @Override
    public int getRowCount() {
        return this.charcuterieProduits.size();
    }

    @Override
    public int getColumnCount() {
        return charcuterieColumn.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Charcuterie charcuterie = charcuterieProduits.get(rowIndex);
        switch (columnIndex){
            case 0: return charcuterie.getNom();
            case 1: return charcuterie.getQuantiteParPersonnes();
            case 2: return charcuterie.getUnite();
            case 3: return charcuterie.getPrix();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return charcuterieColumn[column];
    }
    
}
