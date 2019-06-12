/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Modality;
import modelo.beans.Cliente;
import modelo.beans.Usuario;
import modelo.dao.ClienteDAO;
import utileria.Dialogos;
import utileria.LocalDateStringConverter;
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
    private Button btnRegresar;
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
    private TableColumn<Cliente, Boolean> colEnListaNegra;
    @FXML
    private TextField txtRfc;
    @FXML
    private TextField txtNumeroIdentificacion;
    @FXML
    private DatePicker txtFechaIngreso;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnSeleccionar;

    private Usuario usuario;

    private Cliente seleccionCliente = null;

    public static boolean soloBuscar = false;

    private boolean esRegistrarContrato;

    public Cliente getSeleccionCliente() {
        return seleccionCliente;
    }

    @FXML
    void regresar(ActionEvent event) {
        if (esRegistrarContrato) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = null;
            try {
                root = loader.load(getClass().getResource("/gui/RegistrarContrato.fxml").openStream());
            } catch (IOException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            RegistrarContratoController rcc = (RegistrarContratoController) loader.getController();
            rcc.obtenerUsuario(usuario);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Stage principal = (Stage) btnRegresar.getScene().getWindow();
            principal.close();
        } else {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = null;
            try {
                root = loader.load(getClass().getResource("/gui/Principal.fxml").openStream());
            } catch (IOException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            PrincipalController pc = (PrincipalController) loader.getController();
            pc.obtenerUsuario(usuario);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Stage principal = (Stage) btnRegresar.getScene().getWindow();
            principal.close();
        }
    }

    public void obtenerUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    void enviarCliente(ActionEvent event) {
        Cliente clienteSeleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado == null) {
            Dialogos.showWarning("Modificar cliente", "Debe seleccionar un cliente de la tabla");
        } else {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = null;
            try {
                root = loader.load(getClass().getResource("/gui/RegistrarContrato.fxml").openStream());
            } catch (IOException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            RegistrarContratoController bcc = (RegistrarContratoController) loader.getController();
            bcc.obtenerCliente(clienteSeleccionado);
            bcc.obtenerUsuario(usuario);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarColumnas();
        inicializarTabla();
        txtFechaIngreso.setConverter(new LocalDateStringConverter("dd/MM/yyyy"));
        if (soloBuscar) {
            btnModificar.setVisible(false);
            btnNuevo.setVisible(true);
            btnSeleccionar.setVisible(true);
        } else {
            btnModificar.setVisible(true);
            btnNuevo.setVisible(true);
            btnSeleccionar.setVisible(false);
        }
        soloBuscar = false;
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
        colEnListaNegra.setCellValueFactory(new PropertyValueFactory<>("enListaNegra"));
        colEnListaNegra.setCellFactory(col -> {
            CheckBoxTableCell<Cliente, Boolean> cell = new CheckBoxTableCell<>(index -> {
                BooleanProperty active = new SimpleBooleanProperty(tblClientes.getItems().get(index).getEnListaNegra());
                return active;
            });
            return cell;
        });
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
    void seleccionar(ActionEvent event) {
        Cliente clienteSeleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado == null) {
            Dialogos.showWarning("Seleccionar cliente", "Debe seleccionar un cliente de la tabla");
        } else {
            seleccionCliente = clienteSeleccionado;
            ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
        }
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
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            String fechaIngreso = dateFormatter.format(fechaSeleccionada);
            filtros.put("fechaIngreso", "= '" + fechaIngreso + "'");
        }
        if (filtros.size() > 0) {
            List<Cliente> clientes = ClienteDAO.buscar(filtros);
            llenarTabla(clientes);
        } else {
            Dialogos.showWarning("Buscar Cliente", "Introduzca al menos un criterio de búsqueda apropiadamente.");
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
        if (clienteSeleccionado == null) {
            Dialogos.showWarning("Modificar cliente", "Debe seleccionar un cliente de la tabla");
        } else {
            FormularioClienteController.clienteSeleccionado = clienteSeleccionado;
            mostrarFormulario();
        }
    }

    public void mostrarFormulario() {
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

    public void esRegistrarContrato(boolean esRegistrarContrato) {
        if (esRegistrarContrato) {
            btnNuevo.setVisible(false);
            btnModificar.setVisible(false);
            btnAceptar.setVisible(true);
            this.esRegistrarContrato = esRegistrarContrato;
        }
    }
}
