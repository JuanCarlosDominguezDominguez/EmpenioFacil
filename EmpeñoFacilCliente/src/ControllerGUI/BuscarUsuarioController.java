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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class BuscarUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField numeroDePersonalTxt;

    @FXML
    private TextField fechaTxt;

    @FXML
    private ChoiceBox<?> rolCbx;

    @FXML
    private TableView<?> usuariosTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void restringirFecha(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < '0' || caracter > '9') || (caracter != '/') || (fechaTxt.getText().length() >= 10)) {
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
}
