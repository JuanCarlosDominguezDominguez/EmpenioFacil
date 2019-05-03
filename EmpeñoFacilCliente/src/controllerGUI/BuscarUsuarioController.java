/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.geometry.Point2D;
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
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.ListAdapter;
import modelo.beans.Categoria;
import modelo.beans.Usuario;
import modelo.dao.CategoriaDAO;
import modelo.dao.UsuarioDAO;

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

    @FXML
    void buscarUsuarios(ActionEvent event) {
        
    }

    @FXML
    void darDeBajaUsuario(ActionEvent event) {
        if(listaUsuariosTb.getSelectionModel().getSelectedIndex() >= 0){
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar el elemento seleccionado?", "Confirmación", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(opcion == 0){
                int identificador = listaUsuariosTb.getSelectionModel().getSelectedItem().getNumPersonal();
                System.out.println(identificador);
                if(UsuarioDAO.eliminarUsuario(identificador)){
                    JOptionPane.showMessageDialog(null, "El Usuario seleccionado se ha eliminado correctamente.");
                }else{
                   JOptionPane.showMessageDialog(null, "El Usuario seleccionado no se pudo eliminar.");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debes seleccionar el elemento que deseas eliminar.");
        }
        //System.out.println(listaUsuariosTb.getSelectionModel().getSelectedItem().getNombreCompleto());
    }

    @FXML
    void modificarUsuario(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/RegistrarUsuario.fxml"));
            Stage escenario = new Stage();
            Scene scene = new Scene(root);
            escenario.setScene(scene);
            escenario.show();
            //RegistrarUsuarioController.setTipo("modificar");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void nuevoUsuario(ActionEvent event) {
        //System.out.println("La fecha es: " + fechaTxt.getValue());
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/RegistrarUsuario.fxml"));
            Stage escenario = new Stage();
            Scene scene = new Scene(root);
            escenario.setScene(scene);
            escenario.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private List<Categoria> categorias;

    public void cargarRoles() {
        categorias = new ArrayList<Categoria>();
        categorias = CategoriaDAO.buscarCategoriasRoles();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < categorias.size(); i++) {
            acciones.add(categorias.get(i).getNombre());
        }
        rolCbx.setItems(acciones);
    }

    public void cargarTabla() {
        colNumPersonal.setCellValueFactory(new PropertyValueFactory<>("numPersonal"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));

        List<Usuario> usuarios = new ArrayList<>();
        usuarios = UsuarioDAO.getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            listaUsuariosTb.getItems().addAll(usuarios.get(i));
        }
    }

    public void iniciarCalendario() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yy/MM/dd");
        fechaTxt.setConverter(new LocalDateStringConverter(formato, null));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRoles();
        cargarTabla();
        iniciarCalendario();
    }

}
