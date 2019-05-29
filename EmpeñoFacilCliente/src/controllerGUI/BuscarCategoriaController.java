/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.beans.Categoria;
import modelo.beans.Usuario;
import modelo.dao.CategoriaDAO;
import utileria.Dialogos;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class BuscarCategoriaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button regresarBtn;

    @FXML
    public TableView<Categoria> categoriasTbl;

    @FXML
    public TableColumn<Categoria, String> categoriaPrincipalCol;

    @FXML
    public TableColumn<Categoria, String> subcategoriaCol;

    @FXML
    private ComboBox<String> categoriasPrincipalesCbx;

    @FXML
    private Button buscarBtn;

    @FXML
    private Button nuevaBtn;

    @FXML
    private Button modificarBtn;

    @FXML
    private TextField nombretxt;

    @FXML
    private Button eliminarBtn;

    private Usuario usuario;

    @FXML
    void restringirNombre(KeyEvent event) {
        if (nombretxt.getText().length() >= 30) {
            event.consume();
        }
    }

    @FXML
    public void obtenerUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    void buscarCategorias(ActionEvent event) {
        String nombre = nombretxt.getText();
        int idCategoria = 0;
        if(!categoriasPrincipalesCbx.getValue().equals(null)){
            idCategoria = CategoriaDAO.obtenerCategoriaNombre(categoriasPrincipalesCbx.getValue()).getIdCategoria();
        }
        
        HashMap<String, String> filtros = new HashMap<>();
        if(idCategoria > 0){
            filtros.put("Categorias_idCategoria", " = " + Integer.toString(idCategoria));
        }
        if(Validar.validarCadenaEntero(nombre)){
            filtros.put("nombre", "LIKE '" + nombre + "%'");
        }
        if(filtros.size() > 0){
            List<Categoria> categorias = CategoriaDAO.busquedaGenerica(filtros);
            cargarTabla(categorias);
        }else{
            Dialogos.showWarning("Buscar Categoria", "Introduzca al menos un criterio de búsqueda");
        }
    }

    @FXML
    void eliminarCategoria(ActionEvent event) {
        if (categoriasTbl.getSelectionModel().getSelectedIndex() >= 0) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar el elemento seleccionado?", "Confirmación",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (opcion == 0) {
                int identificador = categoriasTbl.getSelectionModel().getSelectedItem().getIdCategoria();
                if (CategoriaDAO.eliminarCategoria(identificador)) {
                    cargarCategoriasPrincipales();
                    inicializarTabla();
                    JOptionPane.showMessageDialog(null, "La Categoria seleccionada se ha eliminado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "La Categoria seleccionada no se pudo eliminar.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar el elemento que deseas eliminar.");
        }
    }

    @FXML
    void modificarCategoria(ActionEvent event) throws IOException {
        Categoria c = new Categoria();
        if (categoriasTbl.getSelectionModel().getSelectedIndex() >= 0) {
            c = CategoriaDAO.obtenerCategoriaNombre(categoriasTbl.getSelectionModel().getSelectedItem().getNombre());
            
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/gui/AgregarCategoria.fxml").openStream());

            AgregarCategoriaController acc = (AgregarCategoriaController) loader.getController();

            acc.obtenerDatos(c, false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            cargarCategoriasPrincipales();
            inicializarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar el elemento que deseas modificar.");
        }
    }

    @FXML
    void nuevaCategoria(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/gui/AgregarCategoria.fxml").openStream());

        AgregarCategoriaController acc = (AgregarCategoriaController) loader.getController();

        acc.obtenerDatos(null, true);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        cargarCategoriasPrincipales();
        inicializarTabla();
    }

    private List<Categoria> categoriasPrincipales;
    private List<Categoria> subcategorias;

    public void cargarCategoriasPrincipales() {
        categoriasPrincipales = new ArrayList<Categoria>();
        categoriasPrincipales = CategoriaDAO.obtenerTodasLasCategorias();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < categoriasPrincipales.size(); i++) {
            if(categoriasPrincipales.get(i).getCategorias_IdCategoria() == 0){
                acciones.add(categoriasPrincipales.get(i).getNombre());
            }
        }
        categoriasPrincipalesCbx.setItems(acciones);

    }

    private void inicializarComunas() {
        categoriaPrincipalCol.setCellValueFactory(new PropertyValueFactory<>("nombreCategoriaPadre"));
        subcategoriaCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    private void inicializarTabla() {
        List<Categoria> categorias = CategoriaDAO.obtenerNombresCategorias();
        cargarTabla(categorias);
    }

    @FXML
    public void cargarTabla(List<Categoria> categorias) {
        categoriasTbl.getItems().clear();
        for (int i = 0; i < categorias.size(); i++) {
            //if (categorias.get(i).getCategorias_IdCategoria() == 0) {
            categoriasTbl.getItems().add(categorias.get(i));
            //}
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCategoriasPrincipales();
        inicializarComunas();
        inicializarTabla();
    }

}
