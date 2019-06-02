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

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class CoventirPrendaArticuloController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private Button regresarBtn;

    @FXML
    private TableView<?> prendasTbl;

    @FXML
    private Button convertirBtn;

    @FXML
    void convertirAArticulo(ActionEvent event) {

    }

    @FXML
    void regresar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
