/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.dao.UsuarioDAO;


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
    private Button guardarBtn;
    
    @FXML
    private ComboBox<String> rolCbx;

    @FXML
    private Button cancelarBtn;

    @FXML
    private TextField nombreTxt;

    @FXML
    private PasswordField contraseniaTxt;

    @FXML
    void restringirContrasenia(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < 'a' || caracter > 'z') && (caracter < '0' || caracter > '9')
                && (caracter < 'A' || caracter > 'Z') || (contraseniaTxt.getText().length() >= 45)) {
            event.consume();
        }
    }

    @FXML
    void restringirNombre(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < 'a' || caracter > 'z') && (caracter < 'A' || caracter > 'Z') || caracter == 32
                || (nombreTxt.getText().length() >= 60)) {
            event.consume();
        }
    }
    
    public boolean validarCampos(){
        boolean validos = false;
        String nombre = nombreTxt.getText();
        String contrasenia = contraseniaTxt.getText();
        String rol = rolCbx.getValue();
        if (nombre != null && contrasenia != null){
            validos = true;
        }
        return validos;
    }

    @FXML
    void cancelar(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/Principal.fxml"));
            Stage escenario = new Stage();
            Scene scene = new Scene(root);
            escenario.setScene(scene);
            escenario.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void guardar(ActionEvent event) {
        if (validarCampos()){
            if(UsuarioDAO.registrarUsuario(nombreTxt.getText(), contraseniaTxt.getText() , rolCbx.getValue())){
                JOptionPane.showMessageDialog(null, "Usuario guardado exitosamente.");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo registrar al usuario");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Los datos del usuario son incorrectos.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
