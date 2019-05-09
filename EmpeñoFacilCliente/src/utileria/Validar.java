/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utileria;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Juuan
 */
public class Validar {
    //private Pattern patronCadena = Pattern.compile("A-Za-z ");
    //private Pattern patronEntero = Pattern.compile("0-9");
    //private Pattern patronCadenaEntero = Pattern.compile("A-Za-z0-9 ");
    
    public static boolean validarCadena(String cadena){
        return cadena.matches("^[a-zA-ZáéíóúÁÉÍÓÚ ]+$");
    }
    
    public static boolean validarEntero(String entero){
        return entero.matches("^\\d+$");
    }
    
    public static boolean validarCadenaEntero(String cadena){
        return cadena.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚ ]+$");
    }
    
    public static boolean validarMoneda(String entero){
        return entero.matches("^([0-9]+)(.[0-9][0-9])?$");
    }
}
