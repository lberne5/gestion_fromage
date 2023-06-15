/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Exceptions;

/**
 *
 * @author Timon Fournier
 */
public class QuantiteParPersonneInvalideException extends Exception {

    public QuantiteParPersonneInvalideException() {
        super("La qauntité par personne entrée n'est pas valide");
    }
    
}
