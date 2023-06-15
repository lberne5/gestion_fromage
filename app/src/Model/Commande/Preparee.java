package Model.Commande;

import Model.Commande.*;

/**
 *
 * @author Lea Berne
 */
public class Preparee implements EtatCommande {

    public Preparee(Commande commande) {
        this.commande = commande;
    }

    @Override
    public void etatSuivant() {
        if (this.commande.getMontantPaye() >= this.commande.getPrix()) {
            this.commande.setEtatCommande(new Archivee(this.commande));
        } else {
            this.commande.setEtatCommande(new Recuperee(this.commande));
        }
    }

    @Override
    public EtatCommande getEtat() {
        return this;
    }

    @Override
    public String toString() {
        return "Preparee.";
    }

    private final Commande commande;
}
