/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button cerrarSesionBtn;

    @FXML
    private ComboBox<String> administradorCbx;

    public void cargarAccionesAdministrador() {
        ObservableList<String> acciones = FXCollections.observableArrayList();
        acciones.add("Registrar usuario");
        acciones.add("Modificar usuario");
        acciones.add("Buscar usuario");
        acciones.add("Dar de alta usuario");
        administradorCbx = new ComboBox<String>(acciones);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarAccionesAdministrador();
    }

}
