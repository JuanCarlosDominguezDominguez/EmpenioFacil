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
    private Label lblAdelanto;
    @FXML
    private Label lblMontoAdelanto;
    @FXML
    private Button btnBuscarCliente;
    @FXML
    private Button btnBuscarArticulo;
    @FXML
    private Button btnQuitar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnFiniquitar;

    private Integer totalVenta;

    private Integer montoAdelanto;

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
                lblAdelanto.setVisible(false);
                lblMontoAdelanto.setVisible(false);
                btnFiniquitar.setVisible(false);
                break;
            case NUEVO_APARTADO:
                montoAdelanto = 0;
                btnFiniquitar.setVisible(false);
                lblTitulo.setText("Registrar apartado");
                break;
            case VER_VENTA:
                btnFiniquitar.setVisible(false);
                lblAdelanto.setVisible(false);
                lblMontoAdelanto.setVisible(false);
                lblTitulo.setText("Información de Venta");
                btnGuardar.setVisible(false);
                btnBuscarCliente.setVisible(false);
                btnBuscarArticulo.setVisible(false);
                btnQuitar.setVisible(false);
                String txtCliente = seleccion.getNombreCliente() + " (" + seleccion.getRfcCliente() + ")";
                lblCliente.setText(txtCliente);
                totalVenta = seleccion.getMonto();
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
                btnGuardar.setVisible(false);
                btnBuscarCliente.setVisible(false);
                btnBuscarArticulo.setVisible(false);
                btnQuitar.setVisible(false);
                String txtCliente2 = seleccion.getNombreCliente() + " (" + seleccion.getRfcCliente() + ")";
                lblCliente.setText(txtCliente2);
                totalVenta = seleccion.getMonto();
                Integer item2 = seleccion.getMonto();
                String precioString2 = item2.toString();
                String pesos2 = precioString2.substring(0, precioString2.length() - 2);
                String centavos2 = precioString2.substring(precioString2.length() - 2, precioString2.length());
                lblTotal.setText("$" + pesos2 + "." + centavos2);
                List<Articulo> articulosVenta2 = VentaApartadoDAO.getArticulosVenta(seleccion.getIdVentaApartado());
                llenarTabla(articulosVenta2);
                Integer montoAdelanto3 = totalVenta / 10;
                Integer item3 = montoAdelanto3;
                String precioString3 = item3.toString();
                String pesos3 = precioString3.substring(0, precioString3.length() - 2);
                String centavos3 = precioString3.substring(precioString3.length() - 2, precioString3.length());
                lblMontoAdelanto.setText("$" + pesos3 + "." + centavos3);
                break;
            default:
                break;
        }
    }

    private void llenarTabla(List<Articulo> articulos) {
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
            if (modo == NUEVO_APARTADO) {
                montoAdelanto = totalVenta / 10;
                item = montoAdelanto;
                precioString = item.toString();
                pesos = precioString.substring(0, precioString.length() - 2);
                centavos = precioString.substring(precioString.length() - 2, precioString.length());
                lblMontoAdelanto.setText("$" + pesos + "." + centavos);
            }
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
        if (modo == NUEVA_VENTA){
            guardarVenta(event);
        } else {
            guardarApartado(event);
        }
    }

    private void guardarVenta(ActionEvent event) {
        // TODO Cambiar info del usuario
        Integer numPersonal = PrincipalController.numPersonal;
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

    private void guardarApartado(ActionEvent event) {
        // TODO Cambiar info del usuario
        Integer numPersonal = PrincipalController.numPersonal;
        List<Articulo> articulos = tblArticulos.getItems();
        if (clienteCompra == null) {
            Dialogos.showWarning("Apartado no guardado", "Seleccionar cliente");
        } else if (articulos.isEmpty()) {
            Dialogos.showWarning("Apartado no guardado", "Se necesitan artículos");
        } else {
            String rfcCliente = clienteCompra.getRfc();
            Integer monto = totalVenta;
            if (VentaApartadoDAO.RegistrarApartado(monto, rfcCliente, numPersonal, articulos, montoAdelanto)) {

                Dialogos.showInformation("Apartado guardado", "El apartado se ha guardado exitosamente.");
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
            } else {
                Dialogos.showError("Apartado no guardado", "Error al guardar el apartado");
            }
        }
    }

    @FXML
    void cancelar(ActionEvent event) {

        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void finiquitar(ActionEvent event) {
        Integer restante = totalVenta - montoAdelanto;
        String precioString = restante.toString();
        String pesos = precioString.substring(0, precioString.length() - 2);
        String centavos = precioString.substring(precioString.length() - 2, precioString.length());
        String strRestante = "$" + pesos + "." + centavos;
        if (Dialogos.showConfirm("Finiquitar apartado", "¿Está seguro de que desea finiquitar apartado pagando " + strRestante + "?", Dialogos.SI, Dialogos.NO).equals(Dialogos.SI)) {
            boolean exito = VentaApartadoDAO.marcarFiniquito(seleccion.getIdVentaApartado(), restante);
            if(exito){
                Dialogos.showInformation("Apartado finiquitado", "El apartado se ha finiquitado exitosamente.");
            } else {
                Dialogos.showError("Apartado no finiquitado", "Error al finiquitar el apartado");
            }
            ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
        }
    }
}
