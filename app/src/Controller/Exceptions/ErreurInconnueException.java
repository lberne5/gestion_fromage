/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Exceptions;

/**
 *
 * @author Timon Fournier
 */
public class ErreurInconnueException extends Exception {

    public ErreurInconnueException() {
        super("Erreur inconnue");
    }
    
    public ErreurInconnueException(String message) {
        super(message);
    }
    
}
