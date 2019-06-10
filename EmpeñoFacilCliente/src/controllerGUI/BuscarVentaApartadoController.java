/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.beans.Articulo;
import modelo.beans.VentaApartado;
import modelo.dao.VentaApartadoDAO;
import utileria.Dialogos;
import utileria.LocalDateStringConverter;
import utileria.PriceCell;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author YZ
 */
public class BuscarVentaApartadoController implements Initializable {

    @FXML
    private TextField txtIdVenta;
    @FXML
    private TextField txtRfcCliente;
    @FXML
    private DatePicker txtFechaIngreso;
    @FXML
    private TableView<VentaApartado> tblVentaApartado;
    @FXML
    private TableColumn<VentaApartado, Integer> colIdVenta;
    @FXML
    private TableColumn<VentaApartado, String> colRfcCliente;
    @FXML
    private TableColumn<VentaApartado, String> colNombreCliente;
    @FXML
    private TableColumn<VentaApartado, Date> colFecha;
    @FXML
    private TableColumn<VentaApartado, Integer> colTotal;
    @FXML
    private ComboBox<TipoVenta> cmbTipoVenta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarColumnas();
        inicializarTabla();
        inicializarComboBox();
        txtFechaIngreso.setConverter(new LocalDateStringConverter("dd/MM/yyyy"));
    }

    private void inicializarColumnas() {
        colIdVenta.setCellValueFactory(new PropertyValueFactory<>("idVentaApartado"));
        colRfcCliente.setCellValueFactory(new PropertyValueFactory<>("rfcCliente"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colTotal.setCellFactory(col -> {
            PriceCell<VentaApartado> cell = new PriceCell<>();
            return cell;
        });
    }

    private void inicializarTabla() {
        HashMap<String, String> filtros = new HashMap<>();
        filtros.put("tipoVenta", "= 11");
        List<VentaApartado> ventas = VentaApartadoDAO.buscar(filtros);
        llenarTabla(ventas);
    }
    
    private void inicializarComboBox(){
        cmbTipoVenta.getItems().clear();
        cmbTipoVenta.getItems().add(new TipoVenta(11, "Venta"));
        cmbTipoVenta.getItems().add(new TipoVenta(12, "Apartado"));
        cmbTipoVenta.getSelectionModel().select(0);
    }

    private void llenarTabla(List<VentaApartado> ventasApartados) {
        tblVentaApartado.getItems().clear();
        ventasApartados.forEach(ventaApartado -> {
            tblVentaApartado.getItems().add(ventaApartado);
        });
    }

    @FXML
    void buscar(ActionEvent event) {
        String tipoVenta = cmbTipoVenta.getSelectionModel().getSelectedItem().getIdTipoVenta().toString();
        String idVenta = txtIdVenta.getText();
        String rfc = txtRfcCliente.getText();
        LocalDate fechaSeleccionada = txtFechaIngreso.getValue();
        HashMap<String, String> filtros = new HashMap<>();
        filtros.put("tipoVenta", "= "+tipoVenta);
        if (Validar.validarCadenaEntero(rfc)) {
            filtros.put("Cliente_rfc", "LIKE '" + rfc + "%'");
        }
        if(Validar.validarEntero(idVenta)){
            filtros.put("idVentaApartado", "= " + idVenta);
        }
        if (fechaSeleccionada != null) {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            String fechaIngreso = dateFormatter.format(fechaSeleccionada);
            filtros.put("fecha", "= '" + fechaIngreso + "'");
        }
        if (filtros.size() > 1) {
            List<VentaApartado> ventasApartados = VentaApartadoDAO.buscar(filtros);
            llenarTabla(ventasApartados);
        } else {
            Dialogos.showWarning("Buscar Ventas/Apartados", "Introduzca al menos un criterio de búsqueda apropiadamente.");
        }
    }

    @FXML
    void restablecer(ActionEvent event) {
        inicializarTabla();
        txtIdVenta.setText("");
        txtRfcCliente.setText("");
        txtFechaIngreso.setValue(null);
        cmbTipoVenta.getSelectionModel().select(0);
    }

    @FXML
    void ver(ActionEvent event) {
        VentaApartado seleccion = tblVentaApartado.getSelectionModel().getSelectedItem();
        if(seleccion != null){
            if(seleccion.getIdTipoVenta() == 11){ //Si es venta
                FormularioVentaApartadoController.modo = FormularioVentaApartadoController.VER_VENTA;
                FormularioVentaApartadoController.seleccion = seleccion;
                mostrarFormulario();
            }
        }
    }

    @FXML
    void nuevaVenta(ActionEvent event) {
        mostrarFormulario();
    }

    @FXML
    void nuevoApartado(ActionEvent event) {
        
    }

    public void mostrarFormulario() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FormularioVentaApartado.fxml"));
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
    
    class TipoVenta{
        private final Integer idTipoVenta;
        private final String tipoVenta;
        
        public Integer getIdTipoVenta(){
            return idTipoVenta;
        }
        
        public TipoVenta(Integer idTipoVenta, String tipoVenta){
            this.idTipoVenta = idTipoVenta;
            this.tipoVenta = tipoVenta;
        }
        
        @Override
        public String toString(){
            return tipoVenta;
        }
    }

}
