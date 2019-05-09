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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.beans.Cliente;
import modelo.beans.Ocupacion;
import modelo.dao.ClienteDAO;
import utileria.Dialogos;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author YZ
 */
public class FormularioClienteController implements Initializable {

    public static Cliente clienteSeleccionado = null;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidoMaterno;
    @FXML
    private TextField txtApellidoPaterno;
    @FXML
    private TextField txtRfc;
    @FXML
    private TextField txtCurp;
    @FXML
    private TextField txtNumeroIdentificacion;
    @FXML
    private ComboBox<Ocupacion> cmbOcupacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarOcupaciones();
        if (clienteSeleccionado != null) {
            cargarCliente();
        }
    }

    private void cargarOcupaciones() {
        List<Ocupacion> ocupaciones = ClienteDAO.getOcupaciones();
        cmbOcupacion.getItems().clear();
        ocupaciones.forEach((ocupacion) -> {
            cmbOcupacion.getItems().add(ocupacion);
        });
    }
    
    private void cargarCliente(){
        txtNombre.setText(clienteSeleccionado.getNombre());
        txtApellidoPaterno.setText(clienteSeleccionado.getApellidoPaterno());
        txtApellidoMaterno.setText(clienteSeleccionado.getApellidoMaterno());
        txtRfc.setText(clienteSeleccionado.getRfc());
        txtRfc.setEditable(false);
        txtCurp.setText(clienteSeleccionado.getCurp());
        txtNumeroIdentificacion.setText(clienteSeleccionado.getNumeroIdentificacion());
        for(Ocupacion ocupacion : cmbOcupacion.getItems()){
            if(ocupacion.getIdCategoria() == clienteSeleccionado.getIdOcupacion()) {
                cmbOcupacion.getSelectionModel().select(ocupacion);
                break;
            }
        }
    }

    @FXML
    void guardar(ActionEvent event) {
        if (validar()) {
            String nombre = txtNombre.getText();
            String apellidoPaterno = txtApellidoPaterno.getText();
            String apellidoMaterno = txtApellidoMaterno.getText();
            String rfc = txtRfc.getText();
            String curp = txtCurp.getText();
            String numeroIdentificacion = txtNumeroIdentificacion.getText();
            Integer idOcupacion = cmbOcupacion.getSelectionModel().getSelectedItem().getIdCategoria();
            boolean exito;
            if (clienteSeleccionado == null) {
                exito = ClienteDAO.registrarCliente(nombre, apellidoPaterno, apellidoMaterno, rfc, curp, numeroIdentificacion, idOcupacion);
            } else {
                exito = ClienteDAO.actualizarCliente(nombre, apellidoPaterno, apellidoMaterno, rfc, curp, numeroIdentificacion, idOcupacion);
            }
            if (exito) {
                Dialogos.showInformation("Cliente guardado", "El cliente se ha guardado exitosamente.");
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
            } else {
                Dialogos.showError("Cliente no guardado", "Error al guardar el cliente");
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
        if (!Validar.validarCadena(txtNombre.getText())) {
            return false;
        }
        if (!Validar.validarCadena(txtApellidoMaterno.getText())) {
            return false;
        }
        if (!Validar.validarCadena(txtApellidoPaterno.getText())) {
            return false;
        }
        if (!Validar.validarCadenaEntero(txtRfc.getText())) {
            return false;
        }
        if (!Validar.validarCadenaEntero(txtCurp.getText())) {
            return false;
        }
        if (!Validar.validarCadenaEntero(txtNumeroIdentificacion.getText())) {
            return false;
        }
        return cmbOcupacion.getSelectionModel().getSelectedIndex() >= 0;
    }

}
