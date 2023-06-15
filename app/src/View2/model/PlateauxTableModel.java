/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View2.model;

import Model.Plateaux.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Léa Berne
 */
public class PlateauxTableModel extends AbstractTableModel {
    
    private List<Plateau> plateaux = new ArrayList<>();
    private String[] plateauxColonnes = {"Nom du plateau", "Nombre de produits", "Prix indicatif (Euros/Personnes)"};
    
    public void addPlateau(Plateau plateau){
        this.plateaux.add(plateau);
        fireTableRowsInserted(0, plateaux.size());
        
    }
    
    public void removeAllPlateaux(){
        this.plateaux.removeAll(plateaux);
    }
    
    @Override
    public int getRowCount() {
        return this.plateaux.size();
    }

    @Override
    public int getColumnCount() {
        return plateauxColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Plateau plateau = plateaux.get(rowIndex);
        switch (columnIndex){
            case 0: return plateau.getNomPlateau();
            case 1: return plateau.getNombreProduits();
            case 2 : return plateau.getPrix();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return plateauxColonnes[column];
    }
    
}
