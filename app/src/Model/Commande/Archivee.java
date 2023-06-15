package Model.Commande;

import Model.Commande.*;
import Model.Exceptions.CommandeDejaArchiveeException;

/**
 *
 * @author Lea Berne
 */
public class Archivee implements EtatCommande {

    public Archivee(Commande commande) {
        this.commande = commande;
    }

    @Override
    public void etatSuivant() throws CommandeDejaArchiveeException {
        throw new CommandeDejaArchiveeException("La commande est deja archivee.");
    }

    @Override
    public EtatCommande getEtat() {
        return this;
    }

    @Override
    public String toString() {
        return "Archivee.";
    }

    private final Commande commande;

}
