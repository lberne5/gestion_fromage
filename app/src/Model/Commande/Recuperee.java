package Model.Commande;

import Model.Commande.*;

/**
 *
 * @author Lea Berne
 */
public class Recuperee implements EtatCommande {

    public Recuperee(Commande commande) {
        this.commande = commande;
    }

    @Override
    public void etatSuivant() {
        this.commande.setEtatCommande(new Archivee(this.commande));
    }

    @Override
    public EtatCommande getEtat() {
        return this;
    }

    @Override
    public String toString() {
        return "Recuperee.";
    }
    private final Commande commande;

}
