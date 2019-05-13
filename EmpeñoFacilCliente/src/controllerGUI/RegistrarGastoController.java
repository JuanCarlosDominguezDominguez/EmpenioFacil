/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RegistrarGastoController implements Initializable {

    @FXML
    private TextField txtMontoGasto;
    @FXML
    private TextField txtMontoAumento;
    @FXML
    private Label lblFondosActuales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarFondo();
    }
    
    private void cargarFondo(){
        ParametrosSucursal parametros = ParametrosSucursalDAO.getParametros();
        if (parametros != null) {
            Double monto = Double.parseDouble(parametros.getFondo().toString());
            monto /= 100;
            lblFondosActuales.setText(monto.toString());
        }
    }

    @FXML
    void guardarGasto(ActionEvent event) {
        if (validar(txtMontoGasto)) {
            String strMonto = txtMontoGasto.getText();
            Integer monto;
            if (strMonto.contains(".")) {
                Double dblMonto = Double.parseDouble(strMonto) * 100;
                monto = dblMonto.intValue();
            } else {
                monto = Integer.parseInt(strMonto) * 100;
            }
            try {
                if (ParametrosSucursalDAO.registrarGasto(monto)) {
                    cargarFondo();
                    Dialogos.showInformation("Gasto registrado", "El gasto se registró exitosamente.");
                } else {
                    Dialogos.showError("Gasto no registrado", "Error al registrar el gasto.");
                }
            } catch (Exception ex) {
                Dialogos.showError("Error", "No hay suficiente dinero.");
            }
        } else {
            Dialogos.showWarning("Campos inválidos.", "Verifique el contenido de los campos.");
        }
    }
    
    @FXML
    void guardarAumento(ActionEvent event) {
        if (validar(txtMontoAumento)) {
            String strMonto = txtMontoAumento.getText();
            Integer monto;
            if (strMonto.contains(".")) {
                Double dblMonto = Double.parseDouble(strMonto) * 100;
                monto = dblMonto.intValue();
            } else {
                monto = Integer.parseInt(strMonto) * 100;
            }
            if (ParametrosSucursalDAO.registrarAumentoFondos(monto)) {
                cargarFondo();
                Dialogos.showInformation("Aumento registrado", "El aumento se registró exitosamente.");
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

    private boolean validar(TextField txt) {
        return Validar.validarMoneda(txt.getText());
    }

}
