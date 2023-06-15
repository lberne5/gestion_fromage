/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Exceptions;

/**
 *
 * @author Timon Fournier
 */
public class PrixInvalideException extends Exception {

    public PrixInvalideException() {
        super("Le prix entré n'est pas valide");
    }
    
}
