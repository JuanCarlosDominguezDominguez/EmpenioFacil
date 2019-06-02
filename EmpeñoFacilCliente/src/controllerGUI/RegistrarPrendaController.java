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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class RegistrarPrendaController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private Button guardarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private ComboBox<?> categoriasCbx;

    @FXML
    private TextField prestamoTxt;

    @FXML
    private TextField avaluoTxt;

    @FXML
    private TextArea descripcionTxt;

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {

    }

    @FXML
    void restrigirDescripcion(KeyEvent event) {

    }

    @FXML
    void restringirAvaluo(KeyEvent event) {

    }

    @FXML
    void restringirPrestamo(KeyEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
