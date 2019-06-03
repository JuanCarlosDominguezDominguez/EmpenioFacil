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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class RegistrarContratoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buscarBtn;

    @FXML
    private TextField nombreClienteTxt;

    @FXML
    private Button registrarClienteTxt;

    @FXML
    private TableView<?> prendasTbl;

    @FXML
    private Button agregarPrendaBtn;

    @FXML
    private Button actualizarPrendaBtn;

    @FXML
    private Button eliminarPrendaBtn;

    @FXML
    private Button tomarFotoBtn;

    @FXML
    private TableView<?> pagosTbl;

    @FXML
    private Button guardarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Label interesOrdinarioTXT;

    @FXML
    private Label interesAlmacenTxt;

    @FXML
    private Label periodoTxt;

    @FXML
    private Label fechaInicioTxt;

    @FXML
    private Label fechaFinTxt;

    @FXML
    void actualizarPrenda(ActionEvent event) {

    }

    @FXML
    void agregarPrenda(ActionEvent event) {

    }

    @FXML
    void buscarCliente(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void eliminarPrenda(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {

    }

    @FXML
    void registrarCliente(ActionEvent event) {

    }

    @FXML
    void restringirNombreCliente(KeyEvent event) {
        if (nombreClienteTxt.getText().length() >= 30) {
            event.consume();
        }
    }

    @FXML
    void tomarFotoBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
