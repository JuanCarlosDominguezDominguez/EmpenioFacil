/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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
import javax.swing.JOptionPane;
import modelo.beans.Usuario;
import modelo.dao.UsuarioDAO;

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
        if (validarCampos()) {
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
        } else {
            JOptionPane.showMessageDialog(null, "Los datos del usuario son incorrectos.");
        }
    }

    public boolean validarCampos() {
        boolean validos = false;
        String contrasenia = contraseniaTxt.getText();
        String numPersonal = numeroDePersonalTxt.getText();
        Usuario usuario = new Usuario();
        usuario = UsuarioDAO.buscarUsuarioParaLogin(numPersonal, contrasenia);
        if (contrasenia != null && numPersonal != null) {
            if (contrasenia == usuario.getContrasenia() && numPersonal == Integer.toString(usuario.getNumPersonal())) {
                validos = true;
            }
        }
        return validos;
    }

    @FXML
    void restringirContrasenia(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < 'a' || caracter > 'z') && (caracter < '0' || caracter > '9')
                && (caracter < 'A' || caracter > 'Z') || (contraseniaTxt.getText().length() >= 45)) {
            event.consume();
        }
    }

    @FXML
    void restringirNumPersonal(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < '0' || caracter > '9') || (numeroDePersonalTxt.getText().length() >= 10)) {
            event.consume();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
