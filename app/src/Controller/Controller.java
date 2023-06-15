/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Exceptions.NomTropLongException;
import Controller.Exceptions.ErreurInconnueException;
import Controller.Exceptions.QuantiteParPersonneInvalideException;
import Controller.Exceptions.PrixInvalideException;
import Model.Commande.APreparer;
import Model.Commande.Commande;
import Model.Exceptions.CommandeDejaArchiveeException;
import Model.Exceptions.DateRecupPlusTotQueEcritureException;
import Model.Plateaux.Plateau;
import Model.Plateaux.PlateauPerso;
import Model.Plateaux.PlateauStandard;
import Model.Produits.AutreProduit;
import Model.Produits.Charcuterie;
import Model.Produits.Fromage;
import Model.Produits.Fruit;
import Model.Produits.Produit;
import Model.Util.Unite;
import View2.App;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timon Fournier
 */
public class Controller implements IApp{
    
    public Controller() {}

    @Override    
    public AutreProduit ajouterAutre(String nom, double prix) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
            return AutreController.ajouter(new AutreProduit(nom, prix, -1));
        } catch (SQLException ex) {
            gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public Charcuterie ajouterCharcuterie(String nom, double prix, double quantiteParPersonne, String unite) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
            return CharcuterieController.ajouter(new Charcuterie(nom, prix, quantiteParPersonne, unite, -1));
        } catch (SQLException ex) {
            gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public Commande ajouterCommande(String client, LocalDate dateRecuperation, double prix, double montantPaye, String commentaires, ArrayList<ArrayList> plateau_quantite_nbpersonnesList) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
        Commande commande = new Commande(client, LocalDate.now(), dateRecuperation, prix, montantPaye, commentaires, new APreparer(null));
        if(plateau_quantite_nbpersonnesList != null)
            for(ArrayList plateauQuantiteNbpersonnes : plateau_quantite_nbpersonnesList){
                commande.ajouterACommande((Plateau)plateauQuantiteNbpersonnes.get(0), (int)plateauQuantiteNbpersonnes.get(1), (int)plateauQuantiteNbpersonnes.get(1));//comme dans le modele on utilise des ArrayList d'ArrayList
            }
        return CommandeController.ajouter(commande);
        } catch (SQLException ex) {
            gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public Fromage ajouterFromage(String nom, double prix, double quantiteParPersonne, String unite) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException{
        try {
            return FromageController.ajouter(new Fromage(nom, prix, quantiteParPersonne, unite, -1));
        } catch (SQLException ex) {
            gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public Fruit ajouterFruit(String nom, double prix, double quantiteParPersonne, String unite) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException{
        try {
            return FruitController.ajouter(new Fruit(nom, prix, quantiteParPersonne, unite, -1));
        } catch (SQLException ex) {
            gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public Plateau ajouterPlateauPerso(double prix, String nom, ArrayList<Produit> listeProduits/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
            PlateauPerso plateau = new PlateauPerso(prix, nom);
            plateau.setListeProduits(listeProduits);
            return PlateauController.ajouter(plateau);
        } catch (SQLException ex) {
            gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public Plateau ajouterPlateauStandard(double prix, String nom, ArrayList<Produit> listeProduits/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
            PlateauStandard plateau = new PlateauStandard(prix, nom);
            plateau.setListeProduits(listeProduits);
            return PlateauController.ajouter(plateau);
        } catch (SQLException ex) {
            gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public ArrayList toutRecupererAutre() {
        try {
            return AutreController.toutRecuperer();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public ArrayList toutRecupererCommande() {
        try {
            return CommandeController.toutRecuperer();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
    @Override
    public ArrayList toutRecupererCharcuterie() {
        try {
            return CharcuterieController.toutRecuperer();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public ArrayList toutRecupererFruit() {
        try {
            return FruitController.toutRecuperer();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public ArrayList toutRecupererFromage() {
        try {
            return FromageController.toutRecuperer();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public ArrayList toutRecupererPlateauStandard() {
        try {
            return PlateauController.toutRecupererStandard();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public ArrayList toutRecupererPlateauPerso() {
        try {
            return PlateauController.toutRecupererPerso();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
    
    @Override
    public AutreProduit recupererAutre(int id) {
        try {
            return AutreController.recuperer(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public Commande recupererCommande(int id) {
        try {
            return CommandeController.recuperer(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
    @Override
    public Charcuterie recupererCharcuterie(int id) {
        try {
            return CharcuterieController.recuperer(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public Fruit recupererFruit(int id) {
        try {
            return FruitController.recuperer(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public Fromage recupererFromage(int id) {
        try {
            return FromageController.recuperer(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public Plateau recupererPlateau(int id) {
        try {
            return PlateauController.recuperer(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    @Override
    public AutreProduit modifierAutre(AutreProduit produit, String nom, double prix) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
            produit.setNom(nom);
            produit.setPrix(prix);
            AutreController.modifier(produit);
            return produit;
        } catch (SQLException ex) {
            this.gestionExceptionSQL(ex);
            return  null;
        }
    }
    
    @Override
    public Charcuterie modifierCharcuterie(Charcuterie charcuterie, String nom, double prix, double quantiteParPersonne, Unite unite) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException  {
        try {
            charcuterie.setNom(nom);
            charcuterie.setPrix(prix);
            charcuterie.setQuantiteParPersonnes(quantiteParPersonne);
            charcuterie.setUnite(unite);
            CharcuterieController.modifier(charcuterie);
            return charcuterie;
        } catch (SQLException ex) {
            this.gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public Commande modifierCommande(Commande commande, String client, LocalDate dateRecuperation, double prix, double montantPaye, String commentaires, ArrayList<ArrayList> plateauxAAjouter/*ArrayList d'ArrayList avec en 0: plateau, en 1: quantite, en 2: nbPersonnes représentant un plateau a ajouter a la commande*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException, CommandeDejaArchiveeException, DateRecupPlusTotQueEcritureException {
        try {
            commande.setNomClient(client);
            commande.setDateRecuperation(dateRecuperation);
            commande.setPrix(prix);
            commande.setMontantPaye(montantPaye);
            commande.setCommentaires(commentaires);
            for(ArrayList plateau : plateauxAAjouter) {
                commande.ajouterACommande((Plateau)plateau.get(0), (int)plateau.get(1), (int)plateau.get(2));
            }
            CommandeController.modifier(commande);
            return commande;
        } catch (SQLException ex) {
            this.gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public Fromage modifierFromage(Fromage fromage, String nom, double prix, double quantiteParPersonne, Unite unite) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
            fromage.setNom(nom);
            fromage.setPrix(prix);
            fromage.setQuantiteParPersonnes(quantiteParPersonne);
            fromage.setUnite(unite);
            FromageController.modifier(fromage);        
            return fromage;
        } catch (SQLException ex) {
            this.gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public Fruit modifierFruit(Fruit fruit, String nom, double prix, double quantiteParPersonne, Unite unite) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
            fruit.setNom(nom);
            fruit.setPrix(prix);
            fruit.setQuantiteParPersonnes(quantiteParPersonne);
            fruit.setUnite(unite);
            FruitController.modifier(fruit);        
            return fruit;
        } catch (SQLException ex) {
            this.gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public PlateauPerso modifierPlateauPerso(PlateauPerso plateau, double prix, String nom, ArrayList<Produit> produitsAAjouter) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
            plateau.setPrix(prix);
            plateau.setNomPlateau(nom);
            for(Produit produit : produitsAAjouter) {
                plateau.ajouterProduitDansListe(produit);
            }
            PlateauController.modifier(plateau);
            return plateau;
        } catch (SQLException ex) {
            this.gestionExceptionSQL(ex);
            return null;
        }
    }
    
    @Override
    public PlateauStandard modifierPlateauStandard(PlateauStandard plateau, double prix, String nom, ArrayList<Produit> produitsAAjouter) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        try {
            plateau.setPrix(prix);
            plateau.setNomPlateau(nom);
            for(Produit produit : produitsAAjouter) {
                plateau.ajouterProduitDansListe(produit);
            }
            PlateauController.modifier(plateau);
            return plateau;
        } catch (SQLException ex) {
            this.gestionExceptionSQL(ex);
            return null;
        }
    }
    
    
    @Override
    public void supprimer(Object objet)  {
        try{
            switch (objet.getClass().getSimpleName()) {
                case "AutreProduit":
                    AutreController.supprimer((AutreProduit)objet);
                    break;
                case "Charcuterie":
                    CharcuterieController.supprimer((Charcuterie)objet);
                    break;
                case "Commande":
                    CommandeController.supprimer((Commande)objet);
                    break;
                case "Fromage":
                    FromageController.supprimer((Fromage)objet);
                    break;
                case "Fruit":
                    FruitController.supprimer((Fruit)objet);
                    break;
                case "PlateauPerso":
                    PlateauController.supprimer((Plateau)objet);
                    break;
                case "PlateauStandard":
                    PlateauController.supprimer((Plateau)objet);
                    break;
            }
        } catch (SQLException ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private void gestionExceptionSQL(SQLException ex) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException {
        if(ex.getMessage().equals("Data truncation: Data too long for column 'nom' at row 1"))
            throw new NomTropLongException();
        else if(ex.getMessage().equals("Data truncated for column 'quantite_par_personne' at row 1"))
            throw new QuantiteParPersonneInvalideException();
        else if(ex.getMessage().equals("Data truncated for column 'prix' at row 1"))
            throw new PrixInvalideException();
        else
            throw new ErreurInconnueException(ex.getMessage());  
    }
    
    
    public static void main(String args[]) throws SQLException  {
//        FlatGrayIJTheme.setup();
        FlatCyanLightIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            Connection conn = ConnexionBDD.initConnexion();
            AutreController ac = new AutreController(conn);
            CharcuterieController cc = new CharcuterieController(conn);
            CommandeController coc = new CommandeController(conn);
            FromageController fc = new FromageController(conn);
            FruitController frc = new FruitController(conn);
            PlateauController pc = new PlateauController(conn);
            Controller c = new Controller();
            public void run() {
                new App(c).setVisible(true);
            }
        });
    }
    
    
}