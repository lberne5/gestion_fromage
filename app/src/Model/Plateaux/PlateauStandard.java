package Model.Plateaux;

/**
 *
 * @author Lea Berne
 */
public class PlateauStandard extends Plateau {
    
    public PlateauStandard(double prix, String nomPlateau) {
        super(prix, nomPlateau);
    }
    public PlateauStandard(int idPlateau, double prix, String nomPlateau) {
        super(idPlateau, prix, nomPlateau);
    }
    public PlateauStandard(Plateau plateau) {
        super(plateau);
    }
}
