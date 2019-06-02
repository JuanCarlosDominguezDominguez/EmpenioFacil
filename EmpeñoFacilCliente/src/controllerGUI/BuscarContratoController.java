/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    }

    @FXML
    void recuperarContrato(ActionEvent event) {

    }

    @FXML
    void refrendar(ActionEvent event) {

    }

    @FXML
    void regresar(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
