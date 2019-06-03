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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import modelo.beans.Categoria;
import modelo.dao.CategoriaDAO;
import modelo.dao.PrendaDAO;
import utileria.Dialogos;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class RegistrarPrendaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button guardarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private ComboBox<String> categoriasCbx;

    @FXML
    private TextField prestamoTxt;

    @FXML
    private TextField avaluoTxt;

    @FXML
    private TextArea descripcionTxt;

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {
        if(validarCampos()){
            int idCategoria = CategoriaDAO.obtenerCategoriaNombre(categoriasCbx.getValue()).getIdCategoria();
            PrendaDAO.registrarPrenda(descripcionTxt.getText(), Integer.parseInt(avaluoTxt.getText()),
                    Integer.parseInt(prestamoTxt.getText()), idCategoria , 1);
            if(PrendaDAO.registrarPrenda(descripcionTxt.getText(), Integer.parseInt(avaluoTxt.getText()),
                    Integer.parseInt(prestamoTxt.getText()), idCategoria , 1)){
                Dialogos.showInformation("Registro exitoso", "La prenda se ha registrado exitosamente.");
            }else{
                Dialogos.showError("Error de Registro", "Ocurrio un error al registra la prenda");
            }
        }else{
            Dialogos.showWarning("Error", "No puede haber campos nulos");
        }
    }

    public boolean validarCampos() {
        String prestamo = prestamoTxt.getText();
        String avaluo = avaluoTxt.getText();
        String descripcion = descripcionTxt.getText();
        String categoria = categoriasCbx.getValue();
        if(!prestamo.equals(null)|| !avaluo.equals(null) || !descripcion.equals(null) || !categoria.equals(null)){
            if(Validar.validarMoneda(avaluo) && Validar.validarMoneda(prestamo) && Validar.validarCadenaEntero(categoria)
                    && Validar.validarCadenaEntero(descripcion)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @FXML
    void restrigirDescripcion(KeyEvent event) {
        if (descripcionTxt.getText().length() >= 120) {
            event.consume();
        }
    }

    @FXML
    void restringirAvaluo(KeyEvent event) {
        if (avaluoTxt.getText().length() >= 11) {
            event.consume();
        }
    }

    @FXML
    void restringirPrestamo(KeyEvent event) {
        if (prestamoTxt.getText().length() >= 11) {
            event.consume();
        }
    }

    public void cargarCategorias() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        categorias = CategoriaDAO.obtenerCategoriasPrendas();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getCategorias_IdCategoria() == 0) {
                acciones.add(categorias.get(i).getNombre());
            }
        }
        categoriasCbx.setItems(acciones);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCategorias();
    }

}
