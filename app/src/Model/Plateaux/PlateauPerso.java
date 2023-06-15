package Model.Plateaux;

/**
 *
 * @author Lea Berne
 */
public class PlateauPerso extends Plateau {
    public PlateauPerso(double prix, String nomPlateau) {
        super(prix, nomPlateau);
    }
    public PlateauPerso(int idPlateau, double prix, String nomPlateau){
        super(idPlateau, prix, nomPlateau);
    }
    /**
     * Constructeur spécifique à la classe PlateauPerso qui crée un PlateauPerso à partir d'un PlateauStandard existant.
     * Appelle le constructeur de la classe abstraite Plateau qui initialise tous les attributs de la classe.
     * @param plateau
     */
    public PlateauPerso(PlateauStandard plateau){
        super( plateau);
    }
    
}
