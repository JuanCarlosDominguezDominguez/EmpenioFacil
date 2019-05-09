/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.beans.ParametrosSucursal;
import modelo.beans.Periodo;
import modelo.dao.ParametrosSucursalDAO;
import utileria.Dialogos;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author YZ
 */
public class ModificarParametrosSucursalController implements Initializable {
    
    private ParametrosSucursal parametros;
    
    @FXML
    private TextField txtInteresOrdinario;
    @FXML
    private TextField txtInteresAlmacen;
    @FXML
    private ComboBox<Periodo> cmbTipoPeriodo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarPeriodos();
        cargarParametros();
    }
    
    private void cargarPeriodos(){
        List<Periodo> periodos = ParametrosSucursalDAO.getPeriodos();
        cmbTipoPeriodo.getItems().clear();
        periodos.forEach((periodo) -> {
            cmbTipoPeriodo.getItems().add(periodo);
        });
    }
    
    private void cargarParametros(){
        parametros = ParametrosSucursalDAO.getParametros();
        if(parametros != null) {
            txtInteresOrdinario.setText(parametros.getInteresOrdinario().toString());
            txtInteresAlmacen.setText(parametros.getInteresAlmacen().toString());
            for (Periodo periodo : cmbTipoPeriodo.getItems()){
                if (periodo.getIdCategoria() == parametros.getIdTipoPeriodo()){
                    cmbTipoPeriodo.getSelectionModel().select(periodo);
                    break;
                }
            }
        }
    }
    
    @FXML
    void guardar(ActionEvent event) {
        if(validar()){
            Integer idSucursal = parametros.getIdSucursal();
            Integer interesOrdinario = Integer.parseInt(txtInteresOrdinario.getText());
            Integer interesAlmacen = Integer.parseInt(txtInteresAlmacen.getText());
            Integer idTipoPeriodo = cmbTipoPeriodo.getSelectionModel().getSelectedItem().getIdCategoria();
            if(ParametrosSucursalDAO.actualizarParametros(idSucursal, interesOrdinario, interesAlmacen, idTipoPeriodo)) {
                Dialogos.showInformation("Parámetros guardados", "Los parámetros de la sucursal se actualizaron exitosamente.");
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
            } else {
                Dialogos.showError("Prarámetros no guardados", "Error al guardar los parámetros.");
            }
        } else{
            Dialogos.showWarning("Campos inválidos.", "Verifique el contenido de los campos.");
        }
    }
    
    @FXML
    void cancelar(ActionEvent event) {
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }
    
   private boolean validar(){
       if(!Validar.validarEntero(txtInteresAlmacen.getText())) {
           return false;
       }
       if(!Validar.validarEntero(txtInteresOrdinario.getText())){
           return false;
       }
        return cmbTipoPeriodo.getSelectionModel().getSelectedIndex() >= 0;
   }
    
}
