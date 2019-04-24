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
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import modelo.dao.UsuarioDAO;

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
    private TextField numPersonalTxt;

    @FXML
    private ComboBox<?> rolCbx;

    @FXML
    private DatePicker fechaTxt;

    @FXML
    private Button buscarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private TableView<?> listaUsuariosTb;

    @FXML
    void restringirNumPersonal(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < '0' || caracter > '9') || (numPersonalTxt.getText().length() >= 10)) {
            event.consume();
        }
    }

    /*@FXML
    void buscarUsuarios(ActionEvent event) {
        if(numPersonalTxt.getText() != null && rolCbx.getValue() != null && fechaTxt.getValue() != null){
            UsuarioDAO.busquedaGeneral(filtros);
        }
    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
