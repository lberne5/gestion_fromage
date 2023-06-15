package Model.Plateaux;

import Model.Exceptions.IdDejaDefiniException;
import Model.Produits.Produit;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Lea Berne
 */
public abstract class Plateau {

    public Plateau(double prix, String nomPlateau) {
        this.idPlateau = compteur--;
        if (nomPlateau != null && !nomPlateau.equals("")) {
            this.nomPlateau = nomPlateau;
        } else {
            this.nomPlateau = this.getClass().getSimpleName() + " n" + idPlateau;
        }
        if (prix < 0) {
            this.prix = -1;
        } else {
            this.prix = prix;
        }
        this.listeProduits = new ArrayList<>();
    }

    public Plateau(int idPlateau, double prix, String nomPlateau) {
        this.idPlateau = idPlateau;
        if (nomPlateau != null && !nomPlateau.equals("")) {
            this.nomPlateau = nomPlateau;
        } else {
            this.nomPlateau = this.getClass().getSimpleName() + " n" + idPlateau;
        }
        this.prix = prix;
        this.listeProduits = new ArrayList<>();
    }
    public Plateau(Plateau plateau) {
        this.idPlateau = plateau.getIdPlateau()+1;
        this.nomPlateau = plateau.getNomPlateau();
        this.prix = plateau.getPrix();
        this.listeProduits = new ArrayList(plateau.getListeProduits());
    }
    public int getIdPlateau() {
        return this.idPlateau;
    }

    public void setIdPlateau(int idPlateau) throws IdDejaDefiniException {
        if (this.idPlateau < 0) {
            this.idPlateau = idPlateau;
        } else {
            throw new IdDejaDefiniException("Ce plateau a deja un identifiant.");
        }
    }

    public String getNomPlateau() {
        return this.nomPlateau;
    }

    public void setNomPlateau(String nom) {
        if (nom != null && !nom.equals("")) {
            this.nomPlateau = nom;
        } else {
            this.nomPlateau = this.getClass().getSimpleName() + " n" + Math.round(idPlateau);
        }
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public ArrayList<Produit> getListeProduits() {
        return this.listeProduits;
    }

    public void setListeProduits(ArrayList<Produit> listeProduits) {
        this.listeProduits = listeProduits;
    }

    public void ajouterProduitDansListe(Produit produit) {
        if (!this.listeProduits.contains(produit)) {
            this.listeProduits.add(produit);
            this.prix += produit.getPrix();
        }
    }

    /**
     * Cette méthode permet de supprimer un produit d'un plateau s'il s'y
     * trouvait et renvoit un booleen.
     *
     * @param produit
     * @return true si la liste contenait bien le produit, false sinon.
     */
    public boolean supprimerProduitDansListe(Produit produit) {
        Boolean supprime = this.listeProduits.remove(produit);
        if (supprime) {
            this.prix -= produit.getPrix();
        }
        return supprime;
    }

    public String listeProduitsToString() {
        if (listeProduits.isEmpty()) {
            return ("\"" + this.nomPlateau + "\" ne contient pas encore de produits.");
        }
        String res = "\"" + this.nomPlateau + "\" contient :";
        Iterator<Produit> iter = listeProduits.iterator();
        while (iter.hasNext()) {
            res += "\n-" + iter.next().toString();
        }
        return res;
    }

    public int getNombreProduits() {
        return listeProduits.size();
    }

    @Override
    public String toString() {
        String res;
        if (this.getNomPlateau().compareTo(this.getClass().getSimpleName() + " n" + Math.round(this.idPlateau)) == 0) {
            res = (this.getClass().getSimpleName() + " numero " + this.idPlateau + ". " + this.prix + " euros.");
        } else {
            res = (this.getClass().getSimpleName() + " \"" + this.nomPlateau + "\". " + this.prix + " euros.");
        }
        return res;
    }
    private int idPlateau;
    private double prix;
    private String nomPlateau;
    private static int compteur = -1;

    private ArrayList <Produit> listeProduits;
}
