/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.beans.Categoria;
import modelo.beans.Usuario;
import modelo.dao.CategoriaDAO;

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
    private Button eliminarBtn;

    @FXML
    private ComboBox<String> subcategoriasCbx;
    
    private Usuario usuario;

    @FXML
    public void obtenerUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    void buscarCategorias(ActionEvent event) {

    }

    @FXML
    void eliminarCategoria(ActionEvent event) {
        if (categoriasTbl.getSelectionModel().getSelectedIndex() >= 0) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar el elemento seleccionado?", "Confirmación",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (opcion == 0) {
                int identificador = categoriasTbl.getSelectionModel().getSelectedItem().getIdCategoria();
                if (CategoriaDAO.eliminarCategoria(identificador)) {
                    categoriasTbl.getItems().clear();
                    cargarTabla();
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
            c = categoriasTbl.getSelectionModel().getSelectedItem();

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/gui/AgregarCategoria.fxml").openStream());

            AgregarCategoriaController acc = (AgregarCategoriaController) loader.getController();

            acc.obtenerDatos(c, "modificar");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
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

        acc.obtenerDatos(null, "nuevo");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    private List<Categoria> categoriasPrincipales;
    private List<Categoria> subcategorias;

    public void cargarCategoriasPrincipales() {
        categoriasPrincipales = new ArrayList<Categoria>();
        categoriasPrincipales = CategoriaDAO.obtenerCategoriasPrincipalesPrendas();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < categoriasPrincipales.size(); i++) {
            acciones.add(categoriasPrincipales.get(i).getNombre());
        }
        categoriasPrincipalesCbx.setItems(acciones);

    }

    public void cargarSubCategorias() {
        subcategorias = new ArrayList<Categoria>();
        subcategorias = CategoriaDAO.obtenerSubCategoriasPrendas();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < subcategorias.size(); i++) {
            acciones.add(subcategorias.get(i).getNombre());
        }
        subcategoriasCbx.setItems(acciones);
    }

    @FXML
    public void cargarTabla() {
        categoriaPrincipalCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        //subcategoriaCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        List<Categoria> categorias = new ArrayList<>();
        categorias = CategoriaDAO.buscarTodasLasCategoriasDePrendas();

        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getCategorias_IdCategoria() == 0) {
                System.out.println(categorias.get(i).toString());
                categoriasTbl.getItems().add(categorias.get(i));
            }
        }
        System.out.println("El tamaño 1 es: " + categoriasTbl.getItems().size());
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getCategorias_IdCategoria() != 0) {
                System.out.println(categorias.get(i).toString());
                categoriasTbl.getItems().add(categorias.get(i));
            }
        }
        System.out.println("El tamaño 2 es: " + categoriasTbl.getItems().size());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCategoriasPrincipales();
        cargarSubCategorias();
        cargarTabla();
    }

}
