/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.beans.Categoria;
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
    private TableView<Categoria> categoriasTbl;
    
    @FXML
    private TableColumn<Categoria, String> categoriaPrincipalCol;

    @FXML
    private TableColumn<Categoria, String> subcategoriaCol;

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

    @FXML
    void buscarCategorias(ActionEvent event) {

    }

    @FXML
    void eliminarCategoria(ActionEvent event) {

    }

    @FXML
    void modificarCategoria(ActionEvent event) {

    }

    @FXML
    void nuevaCategoria(ActionEvent event) {

    }
    
    private List<Categoria> categoriasPrincipales;
    private List<Categoria> subcategorias;
    
    public void cargarCategoriasPrincipales(){
        categoriasPrincipales = new ArrayList<Categoria>();
        categoriasPrincipales = CategoriaDAO.buscarCategoriasPrendas();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < categoriasPrincipales.size(); i++) {
            acciones.add(categoriasPrincipales.get(i).getNombre());
        }
        categoriasPrincipalesCbx.setItems(acciones);
        
    }
    
    public void cargarSubCategorias(){
        subcategorias = new ArrayList<Categoria>();
        subcategorias = CategoriaDAO.buscarCategoriasPrendasSecundarias();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < subcategorias.size(); i++) {
            acciones.add(subcategorias.get(i).getNombre());
        }
        subcategoriasCbx.setItems(acciones);
    }
    
    public void cargarTabla(){
        categoriaPrincipalCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        subcategoriaCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        List<Categoria> categorias = new ArrayList<>();
        categorias = CategoriaDAO.buscarCategoriasPrendas();
        for(int i = 0; i < categorias.size(); i ++){
            categoriasTbl.getItems().addAll(categorias.get(i));
        }
        categorias = CategoriaDAO.buscarCategoriasPrendasSecundarias();
        for(int i = 0; i < categorias.size(); i ++){
            categoriasTbl.getItems().addAll(categorias.get(i));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCategoriasPrincipales();
        cargarSubCategorias();
        cargarTabla();
    }    
    
}
