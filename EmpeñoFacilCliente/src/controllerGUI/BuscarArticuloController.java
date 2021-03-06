/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.beans.Articulo;
import modelo.beans.TipoProducto;
import modelo.dao.ArticuloDAO;
import utileria.Dialogos;
import utileria.PriceCell;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author YZ
 */
public class BuscarArticuloController implements Initializable {

    @FXML
    private TableView<Articulo> tblArticulos;
    @FXML
    private TableColumn<Articulo, Integer> colIdArticulo;
    @FXML
    private TableColumn<Articulo, String> colCategoria;
    @FXML
    private TableColumn<Articulo, String> colDescripcion;
    @FXML
    private TableColumn<Articulo, Integer> colPrecio;
    @FXML
    private TableColumn<Articulo, String> colTipoProducto;
    @FXML
    private TableColumn<Articulo, Boolean> colDeBaja;
    @FXML
    private TableColumn<Articulo, Boolean> colVendido;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtIdArticulo;
    @FXML
    private ComboBox<TipoProducto> cmbTipoProducto;
    @FXML
    private Button btnBaja;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnSeleccionar;

    public static boolean soloBuscar = false;

    private boolean soloNoVendidos = false;

    private Articulo seleccionArticulo = null;

    public Articulo getSeleccionArticulo() {
        return seleccionArticulo;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        soloNoVendidos = soloBuscar;
        llenarComboBox();
        inicializarColumnas();
        inicializarTabla();
        if (soloBuscar) {
            btnModificar.setVisible(false);
            btnBaja.setVisible(false);
            btnSeleccionar.setVisible(true);
        } else {
            btnModificar.setVisible(true);
            btnBaja.setVisible(true);
            btnSeleccionar.setVisible(false);
        }

        soloBuscar = false;
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

        //PrincipalController pc = (PrincipalController) loader.getController();
        //pc.obtenerUsuario(usuario);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    private void llenarComboBox() {
        List<TipoProducto> tiposProducto = ArticuloDAO.getTiposProducto();
        cmbTipoProducto.getItems().clear();
        tiposProducto.forEach(tipoProducto -> {
            cmbTipoProducto.getItems().add(tipoProducto);
        });
    }

    private void inicializarColumnas() {
        colIdArticulo.setCellValueFactory(new PropertyValueFactory<>("idArticulo"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPrecio.setCellFactory(col -> {
            PriceCell<Articulo> cell = new PriceCell<>();
            return cell;
        });
        colTipoProducto.setCellValueFactory(new PropertyValueFactory<>("nombreTipoProducto"));
        colDeBaja.setCellValueFactory(new PropertyValueFactory<>("deBaja"));
        colDeBaja.setCellFactory(col -> {
            CheckBoxTableCell<Articulo, Boolean> cell = new CheckBoxTableCell<>(index -> {
                BooleanProperty deBaja = new SimpleBooleanProperty(tblArticulos.getItems().get(index).getDeBaja());
                return deBaja;
            });
            return cell;
        });
        colVendido.setCellValueFactory(new PropertyValueFactory<>("vendido"));
        colVendido.setCellFactory(col -> {
            CheckBoxTableCell<Articulo, Boolean> cell = new CheckBoxTableCell<>(index -> {
                BooleanProperty vendido = new SimpleBooleanProperty(tblArticulos.getItems().get(index).getVendido());
                return vendido;
            });
            return cell;
        });
    }

    private void inicializarTabla() {
        List<Articulo> articulos;
        if (soloNoVendidos) {
            HashMap<String, String> filtros = new HashMap<>();
            filtros.put("vendido", "= 0");
            filtros.put("deBaja", "= 0");
            articulos = ArticuloDAO.buscar(filtros);
        } else {
            articulos = ArticuloDAO.getArticulos();
        }
        llenarTabla(articulos);
    }

    private void llenarTabla(List<Articulo> articulos) {
        tblArticulos.getItems().clear();
        articulos.forEach(articulo -> {
            tblArticulos.getItems().add(articulo);
        });
    }

    @FXML
    void buscar(ActionEvent event) {
        String idArticulo = txtIdArticulo.getText();
        TipoProducto tipoProducto = cmbTipoProducto.getSelectionModel().getSelectedItem();
        HashMap<String, String> filtros = new HashMap<>();
        if (Validar.validarEntero(idArticulo)) {
            filtros.put("idArticulo", "= " + idArticulo);
        }
        if (tipoProducto != null) {
            filtros.put("tipoProducto", "= " + tipoProducto.getIdCategoria());
        }
        if (filtros.size() > 0) {
            if (soloNoVendidos) {
                filtros.put("vendido", "= 0");
                filtros.put("deBaja", "= 0");
            }
            List<Articulo> articulos = ArticuloDAO.buscar(filtros);
            llenarTabla(articulos);
        } else {
            Dialogos.showWarning("Buscar Artículo", "Introduzca al menos un criterio de búsqueda");
        }
    }

    @FXML
    void restablecer(ActionEvent event) {
        inicializarTabla();
        txtIdArticulo.setText("");
        cmbTipoProducto.getSelectionModel().clearSelection();
    }

    @FXML
    void darDeBaja(ActionEvent event) {
        Articulo articuloSeleccionado = tblArticulos.getSelectionModel().getSelectedItem();
        if (articuloSeleccionado != null) {
            String respuesta = Dialogos.showConfirm("Dar de baja artículo", "¿Desea dar de baja el artículo seleccionado? Esta operación no se puede deshacer.", Dialogos.SI, Dialogos.NO);
            if (respuesta.equals(Dialogos.SI)) {
                if (ArticuloDAO.darDeBaja(articuloSeleccionado.getIdArticulo())) {
                    Dialogos.showInformation("Dar de baja artículo", "El artículo fue dado de baja exitosamente.");
                    restablecer(null);
                } else {
                    Dialogos.showError("Dar de baja artículo", "El artículo no se pudo dar de baja.\n(Verifique que el artículo no esté ya dado de baja) ");
                }
            }
        } else {
            Dialogos.showWarning("Dar de baja artículo", "Debe seleccionar un artículo de la tabla");
        }

    }

    @FXML
    void seleccionar(ActionEvent event) {
        Articulo articuloSeleccionado = tblArticulos.getSelectionModel().getSelectedItem();
        if (articuloSeleccionado == null) {
            Dialogos.showWarning("Seleccionar artículo", "Debe seleccionar un artículo de la tabla");
        } else {
            seleccionArticulo = articuloSeleccionado;
            ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
        }
    }

    @FXML
    void modificar(ActionEvent event) {
        Articulo articuloSeleccionado = tblArticulos.getSelectionModel().getSelectedItem();
        if (articuloSeleccionado == null) {
            Dialogos.showWarning("Modificar artículo", "Debe seleccionar un artículo de la tabla");
        } else {
            FormularioArticuloController.articulo = articuloSeleccionado;
            mostrarFormulario();
        }
    }

    public void mostrarFormulario() {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FormularioArticulo.fxml"));
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

}
