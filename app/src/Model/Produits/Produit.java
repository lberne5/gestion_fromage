package Model.Produits;

import Model.Exceptions.IdDejaDefiniException;

/**
 * Classe abstraite qui est implémentée dans Fromage, Fruit, Charcuterie et AutreProduit. 
 * @author Lea Berne
 */
public abstract class Produit {

    public Produit(String nom, double prix, int idProduit) {
        if (!nom.equals("")) {
            this.nom = nom;
        } else {
            this.nom = "Indefini";
        }
        if (prix > 0) {
            this.prix = prix;
        } else {
            this.prix = -1;
        }
        if (idProduit > -1) {
            this.idProduit = idProduit;
        } else {
            this.idProduit = --compteur;
        }
    }

    public Produit(String nom, double prix) {
        if (!nom.equals("")) {
            this.nom = nom;
        } else {
            this.nom = "Indefini";
        }
        if (prix > 0) {
            this.prix = prix;
        } else {
            this.prix = -1;
        }
        this.idProduit = compteur --;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String value) {
        this.nom = value;
    }

    public int getIdProduit() {
        return this.idProduit;
    }

    public void setIdProduit(int id) throws IdDejaDefiniException {
        if (this.idProduit < 0) {
            this.idProduit = id;
        } else {
            throw new IdDejaDefiniException("Ce produit a deja un identifiant.");
        }
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double value) {
        this.prix = value;
    }
    
    public int getCompteur(){
        return compteur;
    }

    @Override
    public String toString() {
        String res = (this.nom + " (" + this.getClass().getSimpleName() + ") : " + this.prix + "euros par personne.");

        return res;
    }
    private String nom;
    private int idProduit;
    private double prix;
    private static int compteur = -1;
}
