/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javax.swing.JOptionPane;
import modelo.beans.Cliente;
import modelo.dao.ClienteDAO;
import utileria.Dialogos;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author YZ
 */
public class BuscarClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colApellidoPaterno;
    @FXML
    private TableColumn<Cliente, String> colApellidoMaterno;
    @FXML
    private TableColumn<Cliente, String> colRfc;
    @FXML
    private TableColumn<Cliente, String> colCurp;
    @FXML
    private TableColumn<Cliente, String> colNumeroIdentificacion;
    @FXML
    private TableColumn<Cliente, String> colNombreOcupacion;
    @FXML
    private TableColumn<Cliente, Date> colFechaIngreso;
    @FXML
    private TextField txtRfc;
    @FXML
    private TextField txtNumeroIdentificacion;
    @FXML
    private DatePicker txtFechaIngreso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarColumnas();
        inicializarTabla();
    }

    private void inicializarColumnas() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colRfc.setCellValueFactory(new PropertyValueFactory<>("rfc"));
        colCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        colNumeroIdentificacion.setCellValueFactory(new PropertyValueFactory<>("numeroIdentificacion"));
        colNombreOcupacion.setCellValueFactory(new PropertyValueFactory<>("nombreOcupacion"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
    }

    private void inicializarTabla() {
        List<Cliente> clientes = ClienteDAO.getClientes();
        llenarTabla(clientes);
    }

    private void llenarTabla(List<Cliente> clientes) {
        tblClientes.getItems().clear();
        clientes.forEach((cliente) -> {
            tblClientes.getItems().add(cliente);
        });
    }

    @FXML
    void buscar(ActionEvent event) {
        String rfc = txtRfc.getText();
        String numeroIdentificacion = txtNumeroIdentificacion.getText();
        LocalDate fechaSeleccionada = txtFechaIngreso.getValue();
        HashMap<String, String> filtros = new HashMap<>();
        if (Validar.validarCadenaEntero(rfc)) {
            filtros.put("rfc", "LIKE '" + rfc + "%'");
        }
        if (Validar.validarCadenaEntero(numeroIdentificacion)) {
            filtros.put("numeroIdentificacion", "LIKE '" + numeroIdentificacion + "%'");
        }
        if (fechaSeleccionada != null) {
            //TODO: Abstraer la creación de cadenas de fecha a una utilería
            String dia = Integer.toString(fechaSeleccionada.getDayOfMonth());
            String mes = Integer.toString(fechaSeleccionada.getMonthValue());
            String anio = Integer.toString(fechaSeleccionada.getYear());
            if (dia.length() < 2) {
                dia = "0" + dia;
            }
            if (mes.length() < 2) {
                mes = "0" + mes;
            }
            String fechaIngreso = anio + "-" + mes + "-" + dia;
            filtros.put("fechaIngreso", "= '" + fechaIngreso + "'");
        }
        if (filtros.size() > 0) {
            List<Cliente> clientes = ClienteDAO.buscar(filtros);
            llenarTabla(clientes);
        } else {
            Dialogos.showWarning("Buscar Cliente", "Introduzca al menos un criterio de búsqueda");
        }
    }

    @FXML
    void restablecer(ActionEvent event) {
        inicializarTabla();
        txtRfc.setText("");
        txtNumeroIdentificacion.setText("");
        txtFechaIngreso.setValue(null);
    }

    @FXML
    void nuevo(ActionEvent event) {
        FormularioClienteController.clienteSeleccionado = null;
        mostrarFormulario();
    }

    @FXML
    void modificar(ActionEvent event) {
        Cliente clienteSeleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if(clienteSeleccionado == null){
            Dialogos.showWarning("Modificar cliente", "Debe seleccionar un cliente de la tabla");
        } else {
            FormularioClienteController.clienteSeleccionado = clienteSeleccionado;
            mostrarFormulario();
        }
    }
    
    public void mostrarFormulario () {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FormularioCliente.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(BuscarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //RegistrarUsuarioController ruc = (RegistrarUsuarioController) loader.getController();Scene scene = new Scene(root);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        inicializarTabla();
    }
}
