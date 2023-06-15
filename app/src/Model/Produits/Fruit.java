package Model.Produits;

import Model.Util.Quantifiable;
import Model.Util.Unite;

/**
 *
 * @author Lea Berne
 */
public class Fruit extends Produit implements Quantifiable {

    public Fruit(String nom, double prix, int idProduit) {
        super(nom, prix, idProduit);
        this.unite = Unite.INDEFINIE;
        this.quantiteParPersonnes = 0;
    }

    public Fruit(String nom, double prix, double quantiteParPersonnes, String unite, int idProduit) {
        super(nom, prix, idProduit);
        if (unite != null) {
            if ((unite.compareToIgnoreCase("grammes") == 0) || (unite.compareToIgnoreCase("gramme") == 0) || (unite.compareToIgnoreCase("g") == 0)) {
                this.unite = Unite.GRAMMES;
            } else if ((unite.compareToIgnoreCase("piece") == 0) || (unite.compareToIgnoreCase("pieces") == 0)) {
                this.unite = Unite.PIECE;
            } else {
                this.unite = Unite.INDEFINIE;
            }
        } else {
            this.unite = Unite.INDEFINIE;
        }
        if (quantiteParPersonnes < 0) {
            this.quantiteParPersonnes = 0;
        } else {
            this.quantiteParPersonnes = quantiteParPersonnes;
        }
    }

    public Fruit(String nom, double prix, double quantiteParPersonnes, Unite unite, int idProduit) {
        super(nom, prix, idProduit);
        if (unite != null) {
            this.unite = unite;
        } else {
            this.unite = Unite.INDEFINIE;
        }
        if (quantiteParPersonnes < 0) {
            this.quantiteParPersonnes = 0;
        } else {
            this.quantiteParPersonnes = quantiteParPersonnes;
        }
    }

    @Override
    public double getQuantiteParPersonnes() {
        return this.quantiteParPersonnes;
    }

    @Override
    public void setQuantiteParPersonnes(double quantite) {
        this.quantiteParPersonnes = quantite;
    }

    @Override
    public Unite getUnite() {
        return this.unite;
    }

    @Override
    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    @Override
    public String toString() {
        String res = (this.getNom() + " (Fruit) : " + this.getPrix() + " euros par personnes");
        if ((this.quantiteParPersonnes != 0) && (this.unite != unite.INDEFINIE)) {
            res += " (" + this.quantiteParPersonnes + " " + this.unite.toString().toLowerCase();
            if (this.quantiteParPersonnes > 1){
                res += "s";
            }
             res += ").";
        }
        return res;
    }
    
    private double quantiteParPersonnes;
    private Unite unite;

}
