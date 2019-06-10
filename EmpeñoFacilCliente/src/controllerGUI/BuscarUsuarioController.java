/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.ListAdapter;
import modelo.beans.Categoria;
import modelo.beans.Usuario;
import modelo.dao.CategoriaDAO;
import modelo.dao.UsuarioDAO;
import utileria.Dialogos;
import utileria.Validar;

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
    private ComboBox<String> rolCbx;

    @FXML
    private DatePicker fechaTxt;

    @FXML
    private Button buscarBtn;

    @FXML
    private TableView<Usuario> listaUsuariosTb;

    @FXML
    private Button nuevobtn;

    @FXML
    private Button modificarbtn;

    @FXML
    private Button dardebajabtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private TableColumn<Usuario, String> colNumPersonal;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colRol;

    @FXML
    private TableColumn<Usuario, Date> colFechaIngreso;

    private Usuario usuario;

    @FXML
    void restringirNumeroDePersonal(KeyEvent event) {
        if (numPersonalTxt.getText().length() >= 11) {
            event.consume();
        }
    }

    @FXML
    public void obtenerUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    void buscarUsuarios(ActionEvent event) {
        String numeroDePersonal = numPersonalTxt.getText();
        String rolSeleccionado = "";
        if(rolCbx.getValue() != null){
            rolSeleccionado = Integer.toString(CategoriaDAO.obtenerRolPorNombre(rolCbx.getValue()).getIdCategoria());
        }
        LocalDate fechaSeleccionada = fechaTxt.getValue();
        HashMap<String, String> filtros = new HashMap<>();
        if (Validar.validarCadenaEntero(numeroDePersonal)) {
            filtros.put("numPersonal", "LIKE '" + numeroDePersonal + "%'");
        }
        if (Validar.validarCadenaEntero(rolSeleccionado)) {
            filtros.put("rol", "LIKE '" + rolSeleccionado + "%'");
        }
        if (fechaSeleccionada != null) {
            filtros.put("fechaIngreso", "= '" + fechaSeleccionada + "'");
        }
        if (filtros.size() > 0) {
            List<Usuario> usuarios = UsuarioDAO.busquedaGenerica(filtros);
            cargarTabla(usuarios);
        } else {
            Dialogos.showWarning("Buscar Cliente", "Introduzca al menos un criterio de búsqueda");
        }
    }

    @FXML
    void darDeBajaUsuario(ActionEvent event) {
        if (listaUsuariosTb.getSelectionModel().getSelectedIndex() >= 0) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar el elemento seleccionado?", "Confirmación",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (opcion == 0) {
                int identificador = listaUsuariosTb.getSelectionModel().getSelectedItem().getNumPersonal();
                if (UsuarioDAO.eliminarUsuario(identificador)) {
                    inicialiazarTabla();
                    JOptionPane.showMessageDialog(null, "El Usuario seleccionado se ha eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "El Usuario seleccionado no se pudo eliminar.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar el elemento que deseas eliminar.");
        }
    }

    @FXML
    void modificarUsuario(ActionEvent event) {
        Usuario u = new Usuario();
        if (listaUsuariosTb.getSelectionModel().getSelectedIndex() >= 0) {
            u = listaUsuariosTb.getSelectionModel().getSelectedItem();

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = null;
            try {
                root = loader.load(getClass().getResource("/gui/RegistrarUsuario.fxml").openStream());
            } catch (IOException ex) {
                Logger.getLogger(BuscarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }

            RegistrarUsuarioController ruc = (RegistrarUsuarioController) loader.getController();

            ruc.obtenerDatos(u, "modificar");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            inicialiazarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar el elemento que deseas modificar.");
        }
    }

    @FXML
    void nuevoUsuario(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResource("/gui/RegistrarUsuario.fxml").openStream());
        } catch (IOException ex) {
            Logger.getLogger(BuscarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        RegistrarUsuarioController ruc = (RegistrarUsuarioController) loader.getController();

        ruc.obtenerDatos(null, "nuevo");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        inicialiazarTabla();
    }

    private List<Categoria> categorias;

    public void cargarRoles() {
        categorias = new ArrayList<Categoria>();
        categorias = CategoriaDAO.obtenerTodosLosRoles();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < categorias.size(); i++) {
            acciones.add(categorias.get(i).getNombre());
        }
        rolCbx.setItems(acciones);
    }

    private void inicializarComunas() {
        colNumPersonal.setCellValueFactory(new PropertyValueFactory<>("numPersonal"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("nombreRol"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
    }

    private void inicialiazarTabla() {
        List<Usuario> usuarios = UsuarioDAO.obtenerUsuarios();
        cargarTabla(usuarios);
    }

    public void cargarTabla(List<Usuario> usuarios) {
        listaUsuariosTb.getItems().clear();
        for (int i = 0; i < usuarios.size(); i++) {
            listaUsuariosTb.getItems().addAll(usuarios.get(i));
            System.out.println(listaUsuariosTb.getColumns().get(1).getId());
        }
    }

    public void iniciarCalendario() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yy/MM/dd");
        fechaTxt.setConverter(new LocalDateStringConverter(formato, null));
    }

    @FXML
    void regresar(ActionEvent event) {
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
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarComunas();
        inicialiazarTabla();
        iniciarCalendario();
        cargarRoles();
    }
}
