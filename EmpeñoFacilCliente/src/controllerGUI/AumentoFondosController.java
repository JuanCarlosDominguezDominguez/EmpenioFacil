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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.beans.ParametrosSucursal;
import modelo.dao.ParametrosSucursalDAO;
import utileria.Dialogos;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author YZ
 */
public class AumentoFondosController implements Initializable {

    @FXML
    private TextField txtMonto;
    @FXML
    private Label lblFondosActuales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ParametrosSucursal parametros = ParametrosSucursalDAO.getParametros();
        if (parametros != null) {
            Double monto = Double.parseDouble(parametros.getFondo().toString());
            monto /= 100;
            lblFondosActuales.setText(monto.toString());
        }
    }

    @FXML
    void guardarAumento(ActionEvent event) {
        if (validar()) {
            String strMonto = txtMonto.getText();
            Integer monto;
            if (strMonto.contains(".")) {
                Double dblMonto = Double.parseDouble(strMonto) * 100;
                monto = dblMonto.intValue();
            } else {
                monto = Integer.parseInt(strMonto) * 100;
            }
            if (ParametrosSucursalDAO.registrarAumentoFondos(monto)) {
                Dialogos.showInformation("Aumento registrado", "El aumento se registró exitosamente.");
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
            } else {
                Dialogos.showError("Aumento no registrado", "Error al registrar el aumento.");
            }
        } else {
            Dialogos.showWarning("Campos inválidos.", "Verifique el contenido de los campos.");
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    private boolean validar() {
        return Validar.validarMoneda(txtMonto.getText());
    }
}
