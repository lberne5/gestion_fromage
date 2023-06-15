/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;

import Controller.Exceptions.ErreurInconnueException;
import Controller.Exceptions.NomTropLongException;
import Controller.Exceptions.PrixInvalideException;
import Controller.Exceptions.QuantiteParPersonneInvalideException;
import Model.Commande.Commande;
import Model.Exceptions.*;
import Model.Plateaux.Plateau;
import Model.Plateaux.PlateauPerso;
import Model.Plateaux.PlateauStandard;
import Model.Produits.AutreProduit;
import Model.Produits.Charcuterie;
import Model.Produits.Fromage;
import Model.Produits.Fruit;
import Model.Produits.Produit;
import Model.Util.Unite;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Timon Fournier
 */
public interface IApp {
    //exceptions a changer
    
    public AutreProduit ajouterAutre(String nom, double prix) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;
    
    public Charcuterie ajouterCharcuterie(String nom, double prix, double quantiteParPersonne, String unite) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException  ;
    
    //pour ajouter une commande il faut déjà avoir ajouté ses plateaux avant
    public Commande ajouterCommande(String client, LocalDate dateRecuperation, double prix, double montantPaye, String commentaires, ArrayList<ArrayList> idQuantiteNbpersonnesPlateaux/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;
    
    public Fromage ajouterFromage(String nom, double prix, double quantiteParPersonne, String unite) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;
    
    public Fruit ajouterFruit(String nom, double prix, double quantiteParPersonne, String unite) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;
        
    //pour ajouter un plateau il faut déjà avoir ajouté ses produits avant
    public Plateau ajouterPlateauPerso(double prix, String nom, ArrayList<Produit> listeProduits/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException;
    
    public Plateau ajouterPlateauStandard(double prix, String nom, ArrayList<Produit> listeProduits/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;
    
    /**
     * Supprimer l'objet de la base de données
     * @param objet l'objet à ajouter en base de données
     * @throws SQLException en cas de probleme lors de la requete SQL
     */
    public void supprimer(Object objet);
    
    
    public AutreProduit modifierAutre(AutreProduit produit, String nom, double prix) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;
    
    public Charcuterie modifierCharcuterie(Charcuterie charcuterie, String nom, double prix, double quantiteParPersonne, Unite unite/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;
    
    public Commande modifierCommande(Commande commande, String client, LocalDate dateRecuperation, double prix, double montantPaye, String commentaires, ArrayList<ArrayList> plateauxAAjouter/*a voir*//*ArrayList d'ArrayList avec en 0: plateau, en 1: quantite, en 2: nbPersonnes représentant un plateau a ajouter a la commande*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException, DateRecupPlusTotQueEcritureException, CommandeDejaArchiveeException ;
    
    public Fromage modifierFromage(Fromage fromage, String nom, double prix, double quantiteParPersonne, Unite unite/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException  ;

    public Fruit modifierFruit(Fruit fruit, String nom, double prix, double quantiteParPersonne, Unite unite/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;
    
    public PlateauPerso modifierPlateauPerso(PlateauPerso plateau, double prix, String nom, ArrayList<Produit> produitsAAjouter/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;
    
    public PlateauStandard modifierPlateauStandard(PlateauStandard plateau, double prix, String nom, ArrayList<Produit> produitsAAjouter/*a voir*/) throws NomTropLongException, QuantiteParPersonneInvalideException, PrixInvalideException, ErreurInconnueException ;


    public AutreProduit recupererAutre(int id);
            
    public Commande recupererCommande(int id);
    
    public Charcuterie recupererCharcuterie(int id);
    
    public Fruit recupererFruit(int id);
    
    public Fromage recupererFromage(int id);
    
    public Plateau recupererPlateau(int id) ;
    
    
    public ArrayList toutRecupererAutre() ;
    
    public ArrayList toutRecupererCommande() ;
    
    public ArrayList toutRecupererCharcuterie() ;
    
    public ArrayList toutRecupererFromage() ;
    
    public ArrayList toutRecupererFruit() ;
    
    public ArrayList toutRecupererPlateauPerso() ;
    
    public ArrayList toutRecupererPlateauStandard() ;
    
}
