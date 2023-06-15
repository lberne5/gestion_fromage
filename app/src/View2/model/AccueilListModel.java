package View2.model;


import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import Model.Commande.Commande;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Timon Fournier
 */
public class AccueilListModel extends AbstractListModel {
    
    private List<Commande> listeCommandes = new ArrayList<Commande>();
    
    public void addCommande(Commande commande){
        this.listeCommandes.add(commande);
        this.fireContentsChanged(this, 0, listeCommandes.size());
    }
        
    public void removeAllCommandes(){
        this.listeCommandes.removeAll(listeCommandes);
    }
    
    @Override
    public int getSize() {
        return listeCommandes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return listeCommandes.get(index).getNomClient();
    }
    
}
