/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View2.model;

import Model.Produits.Fruit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author potatos
 */
public class FruitTableModel extends AbstractTableModel {
    
    private List<Fruit> fruitProduits = new ArrayList<>();
    private String[] fruitColumn = {"Nom du fruit", "Quantité / Personne", "Unite", "Prix unitaire (Euro)"};
    
    public void addFruit(Fruit fruit){
        this.fruitProduits.add(fruit);
        //Ca sert a reinitialiser de la ligne zero à la ligne maximale du tableau
        fireTableRowsInserted(0, fruitProduits.size());
        
    }
    
    public void removeAllFruit(){
        this.fruitProduits.removeAll(fruitProduits);
    }
    
    @Override
    public int getRowCount() {
        return this.fruitProduits.size();
    }

    @Override
    public int getColumnCount() {
        return fruitColumn.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fruit fruit = fruitProduits.get(rowIndex);
        switch (columnIndex){
            case 0: return fruit.getNom();
            case 1: return fruit.getQuantiteParPersonnes();
            case 2: return fruit.getUnite();
            case 3: return fruit.getPrix();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return fruitColumn[column];
    }
    
}
