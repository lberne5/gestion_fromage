package Model.Commande;

import Model.Commande.*;

/**
 *
 * @author Lea Berne
 */
public class APreparer implements EtatCommande {
    public APreparer(Commande commande){
        this.commande = commande; 
    }
    
    @Override
    public EtatCommande getEtat(){
        return this;
    }

    @Override
    public void etatSuivant() {
        this.commande.setEtatCommande(new Preparee(this.commande));
    }
    @Override
    public String toString(){
        return "A preparer.";
    }
    private final Commande commande; 

}
