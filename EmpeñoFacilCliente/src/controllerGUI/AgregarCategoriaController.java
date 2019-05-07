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
import javax.swing.JOptionPane;
import modelo.beans.Categoria;
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
    void obtenerDatos(Categoria categoria, String tipo) {
        if (categoria.equals(null)) {
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
        if (nombre != null && categoria != null && nombre.trim().length() > 0) {
            if (Validar.validarCadena(nombre)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void cancelar(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void guardar(ActionEvent event) {
        if (validarCampos()) {
            if (!existe()) {
                int idCategoria = 0;
                for (int i = 0; i < categorias.size(); i++) {
                    if (categorias.get(i).getNombre().equals(categoriaCbx.getValue())) {
                        idCategoria = categorias.get(i).getIdCategoria();
                    }
                }
                CategoriaDAO.registrarCategoria(idCategoria, nombreCategoriaTxt.getText());

                JOptionPane.showMessageDialog(null, "Categoria guardada exitosamente.");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                JOptionPane.showMessageDialog(null, "El nombre de la categoria ya existe.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El nombre de la categoria es incorrecto o un campo esta vacio.");
        }
    }

    public boolean existe() {
        List<Categoria> categoriasSecundarias = CategoriaDAO.buscarCategoriasPrendasSecundarias();
        for (int i = 0; i < categoriasSecundarias.size(); i++) {
            if (nombreCategoriaTxt.getText().equals(categoriasSecundarias.get(i).getNombre())) {
                return true;
            }
        }
        return false;
    }

    private List<Categoria> categorias = new ArrayList<>();

    public void cargarCategoriasPrincipales() {
        categorias = CategoriaDAO.buscarCategoriasPrendas();
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
