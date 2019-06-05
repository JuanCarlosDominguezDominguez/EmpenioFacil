/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.beans.ParametrosSucursal;
import modelo.beans.Prenda;
import modelo.beans.Usuario;
import modelo.dao.ParametrosSucursalDAO;
import modelo.dao.PrendaDAO;
import utileria.Dialogos;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class RegistrarContratoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buscarBtn;

    @FXML
    private TextField nombreClienteTxt;

    @FXML
    private Button registrarClienteTxt;

    @FXML
    private TableView<Prenda> prendasTbl;

    @FXML
    private TableColumn<Prenda, String> colNumPrenda;

    @FXML
    private TableColumn<Prenda, String> colCategoria;

    @FXML
    private TableColumn<Prenda, String> colAvaluo;

    @FXML
    private TableColumn<Prenda, String> colPrestamo;

    @FXML
    private Button agregarPrendaBtn;

    @FXML
    private Button actualizarPrendaBtn;

    @FXML
    private Button eliminarPrendaBtn;

    @FXML
    private Button tomarFotoBtn;

    @FXML
    private TableView<?> pagosTbl;

    @FXML
    private TableColumn<?, ?> colNumPago;

    @FXML
    private TableColumn<?, ?> colMonto;

    @FXML
    private TableColumn<?, ?> colRefrendar;

    @FXML
    private TableColumn<?, ?> colFiniquitar;

    @FXML
    private Button guardarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Label interesOrdinarioTXT;

    @FXML
    private Label interesAlmacenTxt;

    @FXML
    private Label periodoTxt;

    @FXML
    private Label fechaInicioTxt;

    @FXML
    private Label fechaFinTxt;
    
    @FXML
    private ScrollBar scrollBar;


    private Usuario usuario;

    private static List<Prenda> prendas = new ArrayList<Prenda>();

    public static void agregarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }

    public static void insertarPrenda(Prenda prenda, int posicion) {
        prendas.remove(posicion);
        prendas.add(posicion, prenda);   
    }

    @FXML
    void actualizarPrenda(ActionEvent event) throws IOException {
        if (prendasTbl.getSelectionModel().getSelectedIndex() >= 0) {
            int numPrenda = prendasTbl.getSelectionModel().getSelectedIndex();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/gui/RegistrarPrenda.fxml").openStream());

            RegistrarPrendaController rpc = (RegistrarPrendaController) loader.getController();
            Prenda prenda = prendasTbl.getSelectionModel().getSelectedItem();
            rpc.cargarDatos(false, prenda, numPrenda);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            System.out.println(prendas.size());
            inicialiazarTablaPrendas();
            cargarTablaPrendas(prendas);
        } else {
            Dialogos.showWarning("Error", "Debes seleccionar una prenda");
        }
    }

    @FXML
    void agregarPrenda(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/gui/RegistrarPrenda.fxml").openStream());

        RegistrarPrendaController rpc = (RegistrarPrendaController) loader.getController();
        Prenda prenda = new Prenda();
        rpc.cargarDatos(true, prenda, 0);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        inicialiazarTablaPrendas();
    }

    @FXML
    void buscarCliente(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResource("/gui/BuscarCliente.fxml").openStream());
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        inicialiazarTablaPrendas();
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = loader.load(getClass().getResource("/gui/BuscarContrato.fxml").openStream());
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        BuscarContratoController bcc = (BuscarContratoController) loader.getController();
        bcc.obtenerUsuario(usuario);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void eliminarPrenda(ActionEvent event) {
        if (prendasTbl.getSelectionModel().getSelectedIndex() >= 0) {
            int numPrenda = prendasTbl.getSelectionModel().getSelectedIndex();
            prendas.remove(numPrenda);
            cargarTablaPrendas(prendas);
        } else {
            Dialogos.showWarning("Error", "Debes seleccionar una prenda");
        }
    }

    @FXML
    void guardar(ActionEvent event) {

    }

    @FXML
    void registrarCliente(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FormularioCliente.fxml"));
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
    }

    @FXML
    void restringirNombreCliente(KeyEvent event) {
        if (nombreClienteTxt.getText().length() >= 30) {
            event.consume();
        }
    }

    @FXML
    void tomarFotoBtn(ActionEvent event) {

    }

    private void inicializarComunasPrendas() {
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));
        colAvaluo.setCellValueFactory(new PropertyValueFactory<>("avaluo"));
        colPrestamo.setCellValueFactory(new PropertyValueFactory<>("prestamo"));
    }

    private void inicialiazarTablaPrendas() {
        List<Prenda> prendas = this.prendas;
        cargarTablaPrendas(prendas);
    }

    public void cargarTablaPrendas(List<Prenda> prendas) {
        prendasTbl.getItems().clear();
        for (int i = 0; i < prendas.size(); i++) {
            prendasTbl.getItems().addAll(prendas.get(i));
        }
    }

    public void cargarParametros() {
        ParametrosSucursal parametros = new ParametrosSucursal();
        parametros = ParametrosSucursalDAO.getParametros();
        interesOrdinarioTXT.setText(Integer.toString(parametros.getInteresOrdinario()));
        interesAlmacenTxt.setText(Integer.toString(parametros.getInteresAlmacen()));
        periodoTxt.setText(parametros.getTipoPeriodo());

        Calendar c = Calendar.getInstance();
        fechaInicioTxt.setText(c.get(Calendar.DAY_OF_MONTH) + " / " + c.get(Calendar.MONTH)
                + " / " + c.get(Calendar.YEAR));
    }

    public void obtenerUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarComunasPrendas();
        inicialiazarTablaPrendas();
        cargarParametros();
    }

}
