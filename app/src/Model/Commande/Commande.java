package Model.Commande;

import Model.Exceptions.*;
import Model.Plateaux.*;
import Model.Produits.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Date;
import java.time.Month;
import java.util.Iterator;

/**
 *
 * @author Lea Berne
 */
public class Commande {

    public Commande(String nomClient, LocalDate DateEcriture,
            LocalDate dateRecuperation, double prix, double montantPaye, String commentaires, EtatCommande etatCommande) {
        this.idCommande = compteur--;
        this.nomClient = nomClient;
        if (dateEcriture == null) {
            this.dateEcriture = Date.valueOf(LocalDate.now());
        } else {
            this.dateEcriture = Date.valueOf(DateEcriture);
        }
        if (dateRecuperation == null) {
            LocalDate date = LocalDate.of(2000, Month.JANUARY, 01);
            this.dateRecuperation = Date.valueOf(date);
        } else {
            this.dateRecuperation = Date.valueOf(dateRecuperation);
        }
        this.prix = prix;
        this.montantPaye = montantPaye;
        this.commentaires = commentaires;
        if (etatCommande == null) {
            this.etatCommande = new APreparer(this);
        } else {
            this.etatCommande = etatCommande;
        }
    }

    public Commande(int idCommande, String nomClient, Date DateEcriture,
            Date dateRecuperation, double prix, double montantPaye, String commentaires, EtatCommande etatCommande) {
        this.idCommande = idCommande;
        this.nomClient = nomClient;
        if (dateEcriture == null) {
            this.dateEcriture = Date.valueOf(LocalDate.now());
        } else {
            this.dateEcriture = DateEcriture;
        }
        if (dateRecuperation == null) {
            LocalDate date = LocalDate.of(2000, Month.JANUARY, 01);
            this.dateRecuperation = Date.valueOf(date);
        } else {
            this.dateRecuperation = dateRecuperation;
        }
        this.prix = prix;
        this.montantPaye = montantPaye;
        this.commentaires = commentaires;
        if (etatCommande == null) {
            this.etatCommande = new APreparer(this);
        } else {
            this.etatCommande = etatCommande;
        }
    }

    public int getIdCommande() {
        return this.idCommande;
    }

    public void setIdCommande(int idCommande) throws IdDejaDefiniException {
        if (this.idCommande < 0) {
            this.idCommande = idCommande;
        } else {
            throw new IdDejaDefiniException("Cette commande a deja un identifiant.");
        }
    }

    public String getNomClient() {
        return this.nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public LocalDate getDateEcriture() {
        return this.dateEcriture.toLocalDate();
    }

    public LocalDate getDateRecuperation() {
        return this.dateRecuperation.toLocalDate();
    }

    public void setDateRecuperation(LocalDate dateRecuperation) throws DateRecupPlusTotQueEcritureException {
        if (dateRecuperation.isAfter(this.dateEcriture.toLocalDate())) {
            this.dateRecuperation = Date.valueOf(dateRecuperation);
        } else {
            throw new DateRecupPlusTotQueEcritureException("Date ecriture: " + this.dateEcriture.toString()
                    + ". Date recuperation definie" + dateRecuperation.toString());
        }
    }

    public void setDateRecuperation(Date dateRecuperation) throws DateRecupPlusTotQueEcritureException {
        if (dateRecuperation.toLocalDate().isAfter(this.dateEcriture.toLocalDate())) {
            this.dateRecuperation = dateRecuperation;
        } else {
            throw new DateRecupPlusTotQueEcritureException("Date ecriture: " + this.dateEcriture.toString()
                    + ". Date recuperation définie" + dateRecuperation.toString());
        }

    }

    public double getPrix() {
        return this.prix;
    }

    /**
     * Si le prix passé en paramètre est inférieur à 0, alors
     *
     * @param prix
     */
    public void setPrix(double prix) {
        if (prix < 0) {
            this.prix = -1;
        } else {
            this.prix = prix;

        }
    }

    public double getMontantPaye() {
        return this.montantPaye;
    }

    /**
     * Cette méthode ne vérifie pas si le montant payé par le client est
     * supérieur au prix de la commande, car le client voulait que le prix soit
     * indicatif et ne va probablement pas rentrer le prix final à chaque fois.
     *
     * Si le montant passé en paramètre est négatif, la méthode ne fait rien.
     *
     * @param montantPaye
     * @throws Model.Exceptions.CommandeDejaArchiveeException
     */
    public void setMontantPaye(double montantPaye) throws CommandeDejaArchiveeException {
        if (montantPaye > 0) {
            this.montantPaye = montantPaye;
            if (this.montantPaye >= prix && this.etatCommande.toString().equals("Recuperee.")) {
                this.etatSuivant();
            }
        }
    }

    /**
     * Cette méthode a été pensée au cas où nous aurions besoin d'ajouter une
     * somme au montant payé par le client (au lieu de devoir utiliser le
     * setter).
     *
     * @param payement
     * @throws Model.Exceptions.CommandeDejaArchiveeException
     */
    public void addPayement(double payement) throws CommandeDejaArchiveeException {
        this.montantPaye += payement;
        if (this.montantPaye >= this.prix && this.etatCommande.toString().equals("Recuperee.")) {
            this.etatSuivant();
        }
    }

    public String getCommentaires() {
        return this.commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public EtatCommande getEtatCommande() {
        return this.etatCommande;
    }

    public void setEtatCommande(EtatCommande etatCommande) {
        this.etatCommande = etatCommande;
    }

    public void etatSuivant() throws CommandeDejaArchiveeException {
        try {
            this.etatCommande.etatSuivant();
        } catch (CommandeDejaArchiveeException e) {
        }
    }

    public ArrayList getListePlateaux() {
        return this.listePlateaux;
    }

    /**
     * Méthode qui ajoute à listePlateau une ArrayList contenant: le plateau à
     * l'index 0, la quantite à l'index 1, et le nombre de personnes à l'index
     * 2.
     *
     * Elle ajoute également le prix du plateau (à hauteur du nombre de 
     * personnes et de la quantité ajoutée) au prix de la commande.
     *
     * @param plateau
     * @param quantite
     * @param nbPersonnes
     */
    public void ajouterACommande(Plateau plateau, int quantite, int nbPersonnes) {
        ArrayList nouvelleLigne = new ArrayList();
        nouvelleLigne.add(plateau);
        nouvelleLigne.add(quantite);
        nouvelleLigne.add(nbPersonnes);
        this.listePlateaux.add(nouvelleLigne);
        this.prix += plateau.getPrix() * quantite * nbPersonnes;
    }

    /**
     * Méthode qui permet de supprimer un plateau de la listePlateau de la commande. 
     * Si le plateau a bien été supprimé, alors on enlève également la quantité et le nombre de personnes
     * au prix de la commande. 
     * @param plateau
     * @return boolean : true si le plateau était dans la liste et qu'il a bien été supprimé, 
     * false sinon.
     */
    public boolean supprimerPlateauDeCommande(Plateau plateau) {
        if (listePlateaux.isEmpty()) {
            return false;
        }
        Iterator iter = listePlateaux.iterator();
        while (iter.hasNext()) {
            ArrayList listeActuelle = (ArrayList) iter.next();
            Plateau plateauActuel = (Plateau)  listeActuelle.get(0);
            if (plateauActuel.equals(plateau)) {
                double prix = plateauActuel.getPrix();
                int quantite = (int) listeActuelle.get(1);
                int nbPersonnes = (int) listeActuelle.get(2);
                if(this.listePlateaux.remove(listeActuelle)){
                    this.prix -= prix * quantite *nbPersonnes;
                    return true;
                } 
                else{
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Méthode qui permet de savoir combien de plateaux il y a dans la commande. 
     * @return int
     */
    public int getNbPlateauxCommande() {
        var compteur = 0;
        if (this.listePlateaux.isEmpty()){
            return compteur;
        }else{
            Iterator iter = listePlateaux.iterator();
            while(iter.hasNext()){
                 ArrayList listeActuelle = (ArrayList) iter.next();
                int quantite = (int)  listeActuelle.get(1);
                compteur += quantite; 
            }
            return compteur; 
        }
    }

    //<editor-fold defaultstate="collapsed" desc="PRIVATE HELPER METHODS">
    /*
        Cette méthode sert pour faire des calculs intermédiaires pour la méthode publique 
        getProduitsNecessaires(). 
        Elle récupère la listePlateau de la commande. 
        Elle renvoit une liste qui contient : 
            - des listes contenant: 
                    * des listes de produits 
                    * leur nombre de personnes du plateau pour la commande 
                    * la quantité de ce plateau dans la commande.
     */
    private ArrayList getListeProduitsFromListePlateaux() {
        ArrayList listeProduitsQuantite = new ArrayList();
        Iterator iterPlateau = this.listePlateaux.iterator();
        while (iterPlateau.hasNext()) {
            var listeActuelle = iterPlateau.next();

            Plateau plateauActuel = (Plateau) ((ArrayList) listeActuelle).get(0);
            int quantitePlateauActuel = (int) ((ArrayList) listeActuelle).get(1);
            int nbPersonnesPlateauActuel = (int) ((ArrayList) listeActuelle).get(2);

            ArrayList produitsPlateauActuel = plateauActuel.getListeProduits();
            ArrayList recapPlateau = new ArrayList();
            recapPlateau.add(produitsPlateauActuel);
            recapPlateau.add(quantitePlateauActuel);
            recapPlateau.add(nbPersonnesPlateauActuel);
            listeProduitsQuantite.add(recapPlateau);
        }
        return listeProduitsQuantite;
    }

    /*
        Cette méthode sert pour faire des calculs intermédiaires pour la méthode publique 
        getProduitsNecessaires(). 
        Elle prends en paramètre une liste de produits, une quantite (qui provient de la 
        quantite de plateaux de la liste) et un nombre de personnes. 
        Elle retourne une liste de listes contenants un produit et une quantite de ce produit. 
     */
    private ArrayList getListeQuantiteParProduit(ArrayList listeProduits, int quantite, int nbPersonnes) {
        ArrayList quantiteNecessaire = new ArrayList();
        Iterator iter = listeProduits.iterator();
        while (iter.hasNext()) {
            ArrayList liste = new ArrayList();
            Produit produit = (Produit) iter.next();
            if (produit instanceof Fromage) {
                Fromage fromage = (Fromage) produit;
                double quantitePP = fromage.getQuantiteParPersonnes();
                liste.add(fromage);
                liste.add((double)(quantitePP * quantite * nbPersonnes));
            } else if (produit instanceof Fruit) {
                Fruit fruit = (Fruit) produit;
                double quantitePP = fruit.getQuantiteParPersonnes();
                liste.add(fruit);
                liste.add((double)(quantitePP * quantite * nbPersonnes));
            } else if (produit instanceof Charcuterie) {
                Charcuterie charcuterie = (Charcuterie) produit;
                double quantitePP = charcuterie.getQuantiteParPersonnes();
                liste.add(charcuterie);
                liste.add((double)(quantitePP * quantite * nbPersonnes));
            } else {
                liste.add(produit);
                liste.add((double)quantite * nbPersonnes);
            }
            quantiteNecessaire.add(liste);
        }
        return quantiteNecessaire;
    }

    /*
        Cette méthode sert pour faire des calculs intermédiaires pour la méthode publique 
        getProduitsNecessaires(). 
        Elle prends en paramètre une liste de produits (accompagnés de leur quantite) qui peuvent se répèter et retourne une liste 
        où chaque produit ne se trouve qu'une fois.
     */
    private ArrayList getListeProduitsUniques(ArrayList liste) {
        Iterator iter = liste.iterator();
        ArrayList listeProduits = new ArrayList();
        ArrayList listeUnique = new ArrayList();
        while (iter.hasNext()) {
            ArrayList elementAAjouter = new ArrayList();
            ArrayList elementActuel = (ArrayList) iter.next();
            Produit produitActuel = (Produit) elementActuel.get(0);
            double quantite = (double) elementActuel.get(1);
            if (listeProduits.isEmpty() || !listeProduits.contains(produitActuel)) {
                listeProduits.add(produitActuel);
                elementAAjouter.add(produitActuel);
                elementAAjouter.add(quantite);
                listeUnique.add(elementAAjouter);
            } else if (listeProduits.contains(produitActuel)) {
                this.ajouterQuantiteProduitAIndex(listeUnique, produitActuel, quantite);
            }
        }
        return listeUnique;
    }

    /*
        Cette méthode sert pour faire une opération intermédiaires pour la méthode privée 
        getListeProduitsUniques(). 
    
        Elle permet d'ajouter la quantite d'un produit à la liste de produit unique. 
     */
    private void ajouterQuantiteProduitAIndex(ArrayList liste, Produit produit, double quantite) {
        ArrayList elementAAjouter = new ArrayList();
        Iterator iter = liste.iterator();
        while (iter.hasNext()) {
            ArrayList elementActuel = (ArrayList) iter.next();
            Produit produitActuel = (Produit) elementActuel.get(0);
            double quantiteActuelle = (double) elementActuel.get(1);
            if (produitActuel.toString().equals(produit.toString())) {
                elementAAjouter.add(produit);
                elementAAjouter.add(quantiteActuelle + quantite);
                iter.remove();
            }
        }
        liste.add(elementAAjouter);
    }
// </editor-fold>

    /**
     * Methode qui renvoie une ArrayList contenant des ArrayLists décrivant les
     * produits uniques présents dans les plateaux de la commande accompagnés de
     * leur quantité totale.
     *
     * @return ArrayList
     */
    public ArrayList getListeProduitsNecessaires() {
        //initialisation d'une liste contenant des listes de produits nécessaires (qui peuvent se répéter) associés une quantite
        ArrayList produitsPresqueTries = new ArrayList();

        //récupération de la liste contenant les listes de plateaux associés à une quantité et un nb de personnes
        ArrayList produitsFromListePlateau = this.getListeProduitsFromListePlateaux();
        //vérification que la liste n'est pas vide si elle l'est on renvoit une liste vide
        if (produitsFromListePlateau.isEmpty()) {
            return null;
        } else {
            Iterator iter = produitsFromListePlateau.iterator();
            //on itéère sur la liste de plateaux
            while (iter.hasNext()) {
                //on récupère les valeurs de l'indice actuel dans des variables intermédiaires
                ArrayList listeProduitQuantiteActuelle = (ArrayList) iter.next();
                if (!listeProduitQuantiteActuelle.isEmpty()) {
                    ArrayList produits = (ArrayList) listeProduitQuantiteActuelle.get(0);
                    int quantite = (int) listeProduitQuantiteActuelle.get(1);
                    int nbPersonnes = (int) listeProduitQuantiteActuelle.get(2);
                    if (!produits.isEmpty()) {
                        //on transforme ce qui est contenu à l'indice actuel (une liste de produits, une quantite, un nbpersonnes) 
                        //en liste de produits avec la quantité nécessaire 
                        ArrayList quantiteNecessaire = this.getListeQuantiteParProduit(produits, quantite, nbPersonnes);
                        Iterator iterQtNec = quantiteNecessaire.iterator();
                        while (iterQtNec.hasNext()) {
                            ArrayList elmtAAjouter = (ArrayList) iterQtNec.next();
                            produitsPresqueTries.add(elmtAAjouter);
                        }
                    }
                }
            }
            //on vérifie que la liste de produits presque tries ne soit pas vide si elle l'est, on renvoit une liste vide
            if (produitsPresqueTries.isEmpty()) {
                return null;
            } // à cette étape, la liste produitsPresqueTries contient listes de produits nécessaires 
            // (qui peuvent se répéter) associés une quantite
            else {
                //on appelle la méthode qui  et on renvoit la liste
                return (this.getListeProduitsUniques(produitsPresqueTries));

            }
        }
    }

    /**
     * Cette méthode permet d'afficher le contenu de listePlateaux ( = les
     * plateaux contenus dans la commande , avec leur quantité et le nombre de
     * personnes).
     *
     * @return
     */
    public String contenuListeToString() {
        if (listePlateaux.isEmpty()) {
            return ("Ne contient pas encore de plateaux.");
        }
        String res = "Contient :";
        Iterator iter = listePlateaux.iterator();
        while (iter.hasNext()) {
            //listePlateau est une ArrayList 2D ! 
            res += "\n*";
            ArrayList ligneActuelle = (ArrayList) iter.next();
            Plateau plateauActuel = (Plateau) ligneActuelle.get(0);
            res += ligneActuelle.get(1) + " \"" + plateauActuel.getNomPlateau()
                    + "\" pour " + ligneActuelle.get(2) + " personnes. " + plateauActuel.listeProduitsToString();
        }
        return res;
    }

    /**
     * Redefinition de la méthode toString() pour la commande. Affiche la date
     * de prise de commande, date de recuperation, et le contenu de la commande
     * (qui est géré via la méthode contenuListeToString)
     *
     * @return String
     */
    @Override
    public String toString() {

        String res = "Commande du " + this.dateEcriture.toString() + " de Monsieur / Madame " + this.nomClient + ". ";
//        if (this.dateRecuperation.toString().equals("2000-01-01")) {
//            res += "Date de recuperation non renseignee.";
//        } else {
//            res += "Date de recuperation : " + this.dateRecuperation.toString() + ".\n";
//        }
        res += this.etatCommande.toString();
//        res += "\n" + contenuListeToString();
//        if (this.commentaires != null && this.commentaires.compareTo("") != 0) {
//            res += "\nCommentaires : " + this.commentaires;
//        }
        return res;
    }

    private int idCommande;
    private static int compteur = -1;

    private String nomClient;

    private Date dateEcriture;
    private Date dateRecuperation;
    private double prix;
    private double montantPaye;
    private String commentaires;

    private EtatCommande etatCommande;

    /* ATTENTION: listePlateau est une ArrayList qui contient d'autres ArrayList. 
    Ces dernières contiennent, chacunes, un plateau (index 0 ), une quantite (1), un nombre de personnes (2)*/
    private ArrayList<ArrayList> listePlateaux = new ArrayList();

}
