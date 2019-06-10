/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.beans.Articulo;
import modelo.beans.Cliente;
import modelo.beans.VentaApartado;
import modelo.dao.VentaApartadoDAO;
import utileria.Dialogos;
import utileria.PriceCell;

/**
 * FXML Controller class
 *
 * @author YZ
 */
public class FormularioVentaApartadoController implements Initializable {

    @FXML
    private TableView<Articulo> tblArticulos;
    @FXML
    private TableColumn<Articulo, Integer> colIdArticulo;
    @FXML
    private TableColumn<Articulo, String> colDescripcion;
    @FXML
    private TableColumn<Articulo, Integer> colPrecio;
    @FXML
    private Label lblCliente;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblTotal;
    @FXML
    private Button btnBuscarCliente;
    @FXML
    private Button btnBuscarArticulo;
    @FXML
    private Button btnQuitar;
    @FXML
    private Button btnGuardar;

    private Integer totalVenta;

    private Cliente clienteCompra = null;

    public static final int NUEVA_VENTA = 0;
    public static final int NUEVO_APARTADO = 1;
    public static final int VER_VENTA = 2;
    public static final int VER_APARTADO = 3;

    public static int modo = 0;

    public static VentaApartado seleccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        totalVenta = 0;
        inicializarColumnas();
        tblArticulos.getItems().clear();
        lblTotal.setText("$0.00");
        switch (modo) {
            case NUEVA_VENTA:
                lblTitulo.setText("Registrar venta");
                break;
            case NUEVO_APARTADO:
                lblTitulo.setText("Registrar apartado");
                break;
            case VER_VENTA:
                lblTitulo.setText("Información de Venta");
                btnGuardar.setVisible(false);
                btnBuscarCliente.setVisible(false);
                btnBuscarArticulo.setVisible(false);
                btnQuitar.setVisible(false);
                String txtCliente = seleccion.getNombreCliente() + " (" + seleccion.getRfcCliente() + ")";
                lblCliente.setText(txtCliente);
                Integer item = seleccion.getMonto();
                String precioString = item.toString();
                String pesos = precioString.substring(0, precioString.length() - 2);
                String centavos = precioString.substring(precioString.length() - 2, precioString.length());
                lblTotal.setText("$" + pesos + "." + centavos);
                List<Articulo> articulosVenta = VentaApartadoDAO.getArticulosVenta(seleccion.getIdVentaApartado());
                llenarTabla(articulosVenta);
                break;
            case VER_APARTADO:
                lblTitulo.setText("Información de Apartado");
                break;
            default:
                break;
        }
    }
    
    private void llenarTabla(List<Articulo> articulos){
        tblArticulos.getItems().clear();
        articulos.forEach(articulo -> {
            tblArticulos.getItems().add(articulo);
        });
    }

    private void inicializarColumnas() {
        colIdArticulo.setCellValueFactory(new PropertyValueFactory<>("idArticulo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPrecio.setCellFactory(col -> {
            PriceCell<Articulo> cell = new PriceCell<>();
            return cell;
        });
    }

    @FXML
    void buscarCliente(ActionEvent event) {
        BuscarClienteController.soloBuscar = true;
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BuscarCliente.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(BuscarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BuscarClienteController controller = (BuscarClienteController) loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        Cliente clienteSeleccionado = controller.getSeleccionCliente();
        if (clienteSeleccionado != null) {
            seleccionarCliente(clienteSeleccionado);
        }
    }

    private void seleccionarCliente(Cliente clienteSeleccionado) {
        clienteCompra = clienteSeleccionado;
        String txtCliente = clienteCompra.getNombre() + " "
                + clienteCompra.getApellidoPaterno() + " "
                + clienteCompra.getApellidoMaterno() + "("
                + clienteCompra.getRfc() + ")";
        lblCliente.setText(txtCliente);
    }

    @FXML
    void buscarArticulo(ActionEvent event) {
        BuscarArticuloController.soloBuscar = true;
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BuscarArticulo.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(BuscarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BuscarArticuloController controller = (BuscarArticuloController) loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        Articulo articuloSeleccionado = controller.getSeleccionArticulo();
        if (articuloSeleccionado != null) {
            tblArticulos.getItems().add(articuloSeleccionado);
            Integer precio = articuloSeleccionado.getPrecio();
            totalVenta += precio;
            Integer item = totalVenta;
            String precioString = item.toString();
            String pesos = precioString.substring(0, precioString.length() - 2);
            String centavos = precioString.substring(precioString.length() - 2, precioString.length());
            lblTotal.setText("$" + pesos + "." + centavos);
        }
    }

    @FXML
    void quitarArticulo(ActionEvent event) {
        Articulo articuloSeleccionado = tblArticulos.getSelectionModel().getSelectedItem();
        if (articuloSeleccionado != null) {
            tblArticulos.getItems().remove(articuloSeleccionado);
            Integer precio = articuloSeleccionado.getPrecio();
            totalVenta -= precio;
            Integer item = totalVenta;
            String precioString = item.toString();
            String pesos = precioString.substring(0, precioString.length() - 2);
            String centavos = precioString.substring(precioString.length() - 2, precioString.length());
            lblTotal.setText("$" + pesos + "." + centavos);
        }
    }

    @FXML
    void guardar(ActionEvent event) {
        // TODO Cambiar info del usuario
        Integer numPersonal = 0;
        List<Articulo> articulos = tblArticulos.getItems();
        if (clienteCompra == null) {
            Dialogos.showWarning("Venta no guardada", "Seleccionar cliente");
        } else if (articulos.isEmpty()) {
            Dialogos.showWarning("Venta no guardada", "Se necesitan artículos");
        } else {
            String rfcCliente = clienteCompra.getRfc();
            Integer monto = totalVenta;
            if (VentaApartadoDAO.RegistrarVenta(monto, rfcCliente, numPersonal, articulos)) {
                Dialogos.showInformation("Venta guardada", "La venta se ha guardado exitosamente.");
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
            } else {
                Dialogos.showError("Venta no guardada", "Error al guardar la venta");
            }
        }

    }

    @FXML
    void cancelar(ActionEvent event) {

        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

}
