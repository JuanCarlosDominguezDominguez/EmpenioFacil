/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerGUI;

import java.net.URL;
import java.util.ResourceBundle;
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
public class RegistrarUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField nombreUsuarioTxt;

    @FXML
    private PasswordField contraseniaTxt;

    @FXML
    private Button guardarBtn;

    @FXML
    private Button cancelarBtn;
    
    @FXML
    void restringirContrasenia(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < 'a' || caracter > 'z') && (caracter < '0' || caracter > '9')
                && (caracter < 'A' || caracter > 'Z') && (contraseniaTxt.getText().length() >= 45)) {
            event.consume();
        }
    }

    @FXML
    void restringirNombre(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < 'a' || caracter > 'z') && (caracter < 'A' || caracter > 'Z') 
                && (nombreUsuarioTxt.getText().length() >= 5)) {
            event.consume();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
