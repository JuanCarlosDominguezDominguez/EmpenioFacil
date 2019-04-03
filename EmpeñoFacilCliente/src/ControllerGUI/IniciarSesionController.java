/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerGUI;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class IniciarSesionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button iniciarSesionBtn;

    @FXML
    private PasswordField contraseniaTxt;

    @FXML
    private TextField numeroDePersonalTxt;

    @FXML
    void iniciarSesion(ActionEvent event) {

    }

    @FXML
    void validarContrasenia(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < 'a' || caracter > 'z') && (caracter < '0' || caracter > '9') &&
                (caracter < 'A' || caracter > 'Z')){
            event.consume();
        }
        /*String validos = "^[a-zA-Z'.\\\\s]{1,40}$";
        Pattern p = Pattern.compile(validos);
        Matcher m = p.matcher(validos);
        boolean ad = m.matches();
        char caracter = event.getCharacter().charAt(0);
        if (ad){
            event.consume();
        }*/
    }

    @FXML
    void validarNumPersonal(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < '0' || caracter > '9') && (numeroDePersonalTxt.lengthProperty() < 10) ){
            event.consume();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
