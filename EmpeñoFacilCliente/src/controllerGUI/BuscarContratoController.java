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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.beans.Usuario;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class BuscarContratoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buscarBtn;

    @FXML
    private TextField folioTxt;

    @FXML
    private Button nuevoBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Button recuperarBtn;

    @FXML
    private Button finiquitarBtn;

    @FXML
    private Button refrendarBtn;

    @FXML
    private TableView<?> contratosTbl;

    @FXML
    private Button regresarBtn;
    
    private Usuario usuario;

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void cancelarContrato(ActionEvent event) {

    }

    @FXML
    void finiquitar(ActionEvent event) {

    }

    @FXML
    void nuevo(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResource("/gui/RegistrarContrato.fxml").openStream());
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        RegistrarContratoController rcc = (RegistrarContratoController) loader.getController();
        rcc.obtenerUsuario(usuario);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void recuperarContrato(ActionEvent event) {

    }

    @FXML
    void refrendar(ActionEvent event) {

    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResource("/gui/Principal.fxml").openStream());
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrincipalController pc = (PrincipalController) loader.getController();
        pc.obtenerUsuario(usuario);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void restringirFolio(KeyEvent event) {
        if (folioTxt.getText().length() >= 11) {
            event.consume();
        }
    }
    
    public void obtenerUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
