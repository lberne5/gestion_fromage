package Model.Commande;

import Model.Exceptions.CommandeDejaArchiveeException;

/**
 *
 * @author Lea Berne
 */
public interface EtatCommande {

    public void etatSuivant() throws CommandeDejaArchiveeException;
    public EtatCommande getEtat();
    
}
