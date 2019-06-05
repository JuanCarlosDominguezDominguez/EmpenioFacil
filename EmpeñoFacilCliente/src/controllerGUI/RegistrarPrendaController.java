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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.beans.Categoria;
import modelo.beans.Prenda;
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
    private Button nuevaCategoriaBtn;

    @FXML
    private ComboBox<String> categoriasCbx;

    @FXML
    private TextField prestamoTxt;

    @FXML
    private TextField avaluoTxt;

    @FXML
    private TextArea descripcionTxt;

    private boolean esNuevo;
    
    private int posicion = 0;

    @FXML
    void agregarCategoria(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/gui/AgregarCategoria.fxml").openStream());

        AgregarCategoriaController acc = (AgregarCategoriaController) loader.getController();

        acc.obtenerDatos(null, true);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        cargarCategorias();
    }

    public void cargarDatos(boolean esNuevo, Prenda prenda, int posicion) {
        this.esNuevo = esNuevo;
        if (!esNuevo) {
            descripcionTxt.setText(prenda.getDescripcion());
            avaluoTxt.setText(Integer.toString(prenda.getAvaluo()));
            prestamoTxt.setText(Integer.toString(prenda.getPrestamo()));
            categoriasCbx.setValue(prenda.getNombreCategoria());
            this.posicion = posicion;
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void guardar(ActionEvent event) {
        if (validarCampos()) {
            if (esNuevo) {
                int idCategoria = CategoriaDAO.obtenerCategoriaNombre(categoriasCbx.getValue()).getIdCategoria();
                Prenda prenda = new Prenda(descripcionTxt.getText(), Integer.parseInt(avaluoTxt.getText()),
                        Integer.parseInt(prestamoTxt.getText()), idCategoria, 1);
                prenda.setNombreCategoria(CategoriaDAO.obtenerCategoriaPorID(Integer.toString(idCategoria)).getNombre());
                RegistrarContratoController.agregarPrenda(prenda);
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
            }else{
                int idCategoria = CategoriaDAO.obtenerCategoriaNombre(categoriasCbx.getValue()).getIdCategoria();
                Prenda prenda = new Prenda(descripcionTxt.getText(), Integer.parseInt(avaluoTxt.getText()),
                        Integer.parseInt(prestamoTxt.getText()), idCategoria, 1);
                prenda.setNombreCategoria(CategoriaDAO.obtenerCategoriaPorID(Integer.toString(idCategoria)).getNombre());
                RegistrarContratoController.insertarPrenda(prenda, posicion);
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
            }
        } else {
            Dialogos.showWarning("Error", "No puede haber campos nulos o erroneos");
        }
    }

    public boolean validarCampos() {
        String prestamo = prestamoTxt.getText();
        String avaluo = avaluoTxt.getText();
        String descripcion = descripcionTxt.getText();
        String categoria = categoriasCbx.getValue();
        if (prestamo.equals("") || avaluo.equals("") || descripcion.equals("") || categoria == null) {
            return false;
        } else {
            if (Validar.validarMoneda(avaluo) && Validar.validarMoneda(prestamo) && Validar.validarCadenaEntero(descripcion)) {
                return true;
            } else {
                return false;
            }
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
            acciones.add(categorias.get(i).getNombre());
        }
        categoriasCbx.setItems(acciones);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCategorias();
    }

}
