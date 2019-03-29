/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerGUI;

import GUI.NewFXMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class IniciarSesionController implements Initializable {

    
    @FXML
    private Button iniciarSesionBtn;

    @FXML
    private PasswordField contraseniaTxt;

    @FXML
    private TextField numeroDePersonalTxt;

    
    
    private int numPersonal = Integer.parseInt(numeroDePersonalTxt.getText());
    private String contrasenia = contraseniaTxt.getText();
    
    
    @FXML
    void iniciarSesion() {
        
    }
    
    public void validarCampos (){
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml") );
            Scene panatlla = new Scene(root);
            Stage ventana = new Stage();
            ventana.setScene(panatlla);
            ventana.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void validarContrasenia(KeyEvent event) {
        String caracter = event.getCharacter();
        /*if (caracter < '0' || caracter >'9' ){
            event.consume();
        }*/
    }

    @FXML
    void validarNumPersonal(KeyEvent event) {
        if (numeroDePersonalTxt.lengthProperty().getValue() > 10){
            numeroDePersonalTxt.setText(numeroDePersonalTxt.getText().substring(0, 10));
        }
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
}
