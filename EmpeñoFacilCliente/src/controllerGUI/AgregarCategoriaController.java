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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.beans.Categoria;
import modelo.beans.Usuario;
import modelo.dao.CategoriaDAO;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class AgregarCategoriaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Categoria categoriaSelecionada;
    private String tipo;
    private List<Categoria> categorias = new ArrayList<>();

    private String nombre;
    private String categoria;
    @FXML
    private Button guardarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private ComboBox<String> categoriaCbx;

    @FXML
    private TextField nombreCategoriaTxt;

    @FXML
    void restringirNombre(KeyEvent event) {
        if (nombreCategoriaTxt.getText().length() >= 30) {
            event.consume();
        }
    }

    @FXML
    void obtenerDatos(Categoria categoria, String tipo) {
        if (categoria == null) {
            this.tipo = tipo;
        } else {
            this.categoriaSelecionada = categoria;
            this.tipo = tipo;
            this.nombreCategoriaTxt.setText(categoriaSelecionada.getNombre());
        }
    }

    public boolean validarCampos() {
        nombre = nombreCategoriaTxt.getText();
        categoria = categoriaCbx.getValue();
        if (nombre != null && nombre.trim().length() > 0) {
            if (Validar.validarCadenaEntero(nombre)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void cancelar(ActionEvent event) {
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void guardar(ActionEvent event) {
        nombre = nombreCategoriaTxt.getText();
        categoria = categoriaCbx.getValue();
        int idCategoria = 0;
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getNombre().equalsIgnoreCase(categoria)) {
                idCategoria = categorias.get(i).getIdCategoria();
            }
        }
        if (validarCampos()) {
            if (tipo.equals("nuevo")) {
                if (CategoriaDAO.registrarCategoria(idCategoria, nombre)) {
                    JOptionPane.showMessageDialog(null, "Categoria guardada exitosamente.");
                    ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar la categoria");
                }
            } else {
                if (tipo.equals("modificar")) {
                    if (CategoriaDAO.actualizarCategoria(categoriaSelecionada.getIdCategoria(), nombre)) {
                        JOptionPane.showMessageDialog(null, "Categoria actualizada exitosamente.");
                        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar la categoria");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los datos de la categoria son incorrectos.");
        }
    }

    public boolean existe() {
        List<Categoria> categoriasSecundarias = CategoriaDAO.obtenerSubCategoriasPrendas();
        for (int i = 0; i < categoriasSecundarias.size(); i++) {
            if (nombreCategoriaTxt.getText().equals(categoriasSecundarias.get(i).getNombre())) {
                return true;
            }
        }
        return false;
    }

    public void cargarCategoriasPrincipales() {
        categorias = CategoriaDAO.obtenerCategoriasPrincipalesPrendas();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < categorias.size(); i++) {
            acciones.add(categorias.get(i).getNombre());
        }
        categoriaCbx.setItems(acciones);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCategoriasPrincipales();
    }
}
