/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.beans.Articulo;
import modelo.beans.Cliente;
import modelo.beans.Contrato;
import modelo.beans.Pago;
import modelo.beans.ParametrosSucursal;
import modelo.beans.Prenda;
import modelo.beans.Usuario;
import modelo.dao.ContratoDAO;
import modelo.dao.ParametrosSucursalDAO;
import modelo.dao.PrendaDAO;
import utileria.Dialogos;
import utileria.PriceCell;

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
    private TableView<Prenda> prendasTbl;

    @FXML
    private TableColumn<Prenda, String> colNumPrenda;

    @FXML
    private TableColumn<Prenda, String> colCategoria;

    @FXML
    private TableColumn<Prenda, String> colDescripcion;

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
    private TableView<Pago> pagosTbl;

    @FXML
    private TableColumn<Pago, String> colFecha;

    @FXML
    private TableColumn<Pago, Integer> colInteres;

    @FXML
    private TableColumn<Pago, Integer> colPago;

    @FXML
    private TableColumn<Pago, Integer> colRefrendar;

    @FXML
    private TableColumn<Pago, Integer> colFiniquitar;

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
    private Label nombreClienteTxt;

    @FXML
    private Label fechaFinTxt;

    @FXML
    private Label totalTxt;

    private Usuario usuario;

    private Cliente cliente;

    private LocalDate fechaInicio = LocalDate.now();

    private LocalDate fechaFin = fechaInicio.plusDays(30);

    private static List<Prenda> prendas = new ArrayList<Prenda>();

    private static List<Pago> pagos = new ArrayList<Pago>();

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
            calcularTotal();
            pagos.clear();
            calcularPagos();
        } else {
            Dialogos.showWarning("Error", "Debes seleccionar una prenda");
        }
    }

    @FXML
    void agregarPrenda(ActionEvent event) throws IOException {
        if (nombreClienteTxt.getText().equals("")) {
            Dialogos.showWarning("No hay Cliente", "Debe agregar a un cliente al contrato.");
        } else {
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
            calcularTotal();
            pagos.clear();
            calcularPagos();
        }
        
    }

    public void obtenerCliente(Cliente cliente) {
        this.cliente = cliente;
        nombreClienteTxt.setText(cliente.getNombre() + " " + cliente.getApellidoMaterno() + " "
                + cliente.getApellidoMaterno());
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
            calcularTotal();
            pagos.clear();
            calcularPagos();
        } else {
            Dialogos.showWarning("Error", "Debes seleccionar una prenda");
        }
    }

    @FXML
    void guardar(ActionEvent event) {
        Contrato contrato = new Contrato();
        contrato.setCliente_rfc(cliente.getRfc());
        contrato.setFechaFin(java.sql.Date.valueOf(fechaFin));
        contrato.setFechaInicio(java.sql.Date.valueOf(fechaInicio));
        contrato.setInteresAlmacen(Integer.parseInt(interesAlmacenTxt.getText()));
        contrato.setInteresOrdinario(Integer.parseInt(interesOrdinarioTXT.getText()));
        contrato.setUsuario_numPersonal(3);

        if (ContratoDAO.registrarContrato(contrato, pagos, prendas)) {
            Dialogos.showInformation("Registro exitoso", "Contrato almacenado exitosamente");
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
        } else {
            Dialogos.showInformation("Error de Registro", "No se pudo almacenar el contrato.");
        }
    }

    @FXML
    void buscarCliente(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BuscarCliente.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        BuscarClienteController bcc = (BuscarClienteController) loader.getController();
        bcc.esRegistrarContrato(true);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void tomarFotoBtn(ActionEvent event) {

    }

    private void inicializarColumnasPrendas() {
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));
        colAvaluo.setCellValueFactory(new PropertyValueFactory<>("avaluo"));
        colPrestamo.setCellValueFactory(new PropertyValueFactory<>("prestamo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
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

    private void inicializarColumnasPagos() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));
        colInteres.setCellValueFactory(new PropertyValueFactory<>("refrendo"));
        colInteres.setCellFactory(col -> {
            PriceCell<Pago> cell = new PriceCell<>();
            return cell;
        });
        colPago.setCellValueFactory(new PropertyValueFactory<>("pago"));
        colPago.setCellFactory(col -> {
            PriceCell<Pago> cell = new PriceCell<>();
            return cell;
        });
        colRefrendar.setCellValueFactory(new PropertyValueFactory<>("refrendo"));
        colRefrendar.setCellFactory(col -> {
            PriceCell<Pago> cell = new PriceCell<>();
            return cell;
        });
        colFiniquitar.setCellValueFactory(new PropertyValueFactory<>("finiquito"));
        colFiniquitar.setCellFactory(col -> {
            PriceCell<Pago> cell = new PriceCell<>();
            return cell;
        });
    }

    private void inicializarTablaPagos() {
        List<Pago> pagos = this.pagos;
        cargarTablaPagos(pagos);
    }

    public void cargarTablaPagos(List<Pago> pagos) {
        pagosTbl.getItems().clear();
        for (int i = 0; i < pagos.size(); i++) {
            pagosTbl.getItems().addAll(pagos.get(i));
        }
    }

    public void calcularPagos() {
        String periodo = periodoTxt.getText();
        Double total = Double.parseDouble(totalTxt.getText());
        Double intereses = Double.parseDouble(interesOrdinarioTXT.getText()) + Double.parseDouble(interesAlmacenTxt.getText());
        intereses /= 100;
        switch (periodo) {
            case "Semanal":
                //primer pago
                Pago pago1 = new Pago();
                pago1.setFechaInicio(java.sql.Date.valueOf(fechaInicio));
                pago1.setFechaLimite(java.sql.Date.valueOf(fechaInicio.plusDays(8)));
                Double finiquito = (total + total * (intereses / 4));
                pago1.setFiniquito(finiquito.intValue() * 100);
                pago1.setPrestamo(total.intValue());
                Double refrendo = ((total * intereses) / 4) * 100;
                pago1.setRefrendo(refrendo.intValue());
                pago1.setPago((finiquito.intValue() * 100) / 4);
                pagos.add(pago1);
                //segundo pago
                Pago pago2 = new Pago();
                pago2.setFechaInicio(java.sql.Date.valueOf(fechaInicio.plusDays(8)));
                pago2.setFechaLimite(java.sql.Date.valueOf(fechaInicio.plusDays(15)));
                finiquito = total + total * (intereses / 4) * 2;
                pago2.setFiniquito(finiquito.intValue() * 100);
                pago2.setPrestamo(total.intValue());
                refrendo = (((total * intereses) / 4) * 100) * 2;
                pago2.setRefrendo(refrendo.intValue());
                pago2.setPago((finiquito.intValue() * 100) / 4);
                pagos.add(pago2);
                //tercero pago
                Pago pago3 = new Pago();
                pago3.setFechaInicio(java.sql.Date.valueOf(fechaInicio.plusDays(15)));
                pago3.setFechaLimite(java.sql.Date.valueOf(fechaInicio.plusDays(22)));
                finiquito = total + total * (intereses / 4) * 3;
                pago3.setFiniquito(finiquito.intValue() * 100);
                pago3.setPrestamo(total.intValue());
                refrendo = (((total * intereses) / 4) * 100) * 3;
                pago3.setRefrendo(refrendo.intValue());
                pago3.setPago((finiquito.intValue() * 100) / 4);
                pagos.add(pago3);
                //primer pago
                Pago pago4 = new Pago();
                pago4.setFechaInicio(java.sql.Date.valueOf(fechaInicio.plusDays(22)));
                pago4.setFechaLimite(java.sql.Date.valueOf(fechaInicio.plusDays(30)));
                finiquito = (total + total * intereses);
                pago4.setFiniquito(finiquito.intValue() * 100);
                pago4.setPrestamo(total.intValue());
                refrendo = (total * intereses) * 100;
                pago4.setRefrendo(refrendo.intValue());
                pago4.setPago((finiquito.intValue() * 100) / 4);
                pagos.add(pago4);
                cargarTablaPagos(pagos);
                break;
            case "Quincenal":
                //primer pago
                Pago pago5 = new Pago();
                pago5.setFechaInicio(java.sql.Date.valueOf(fechaInicio));
                pago5.setFechaLimite(java.sql.Date.valueOf(fechaInicio.plusDays(15)));
                finiquito = (total + total * (intereses / 2));
                pago5.setFiniquito(finiquito.intValue() * 100);
                pago5.setPrestamo(total.intValue());
                refrendo = ((total * intereses) / 2) * 100;
                pago5.setRefrendo(refrendo.intValue());
                pago5.setPago((finiquito.intValue() * 100) / 2);
                pagos.add(pago5);
                //segundo pago
                Pago pago6 = new Pago();
                pago6.setFechaInicio(java.sql.Date.valueOf(fechaInicio.plusDays(15)));
                pago6.setFechaLimite(java.sql.Date.valueOf(fechaInicio.plusDays(30)));
                finiquito = (total + total * intereses);
                pago6.setFiniquito(finiquito.intValue() * 100);
                pago6.setPrestamo(total.intValue());
                refrendo = ((total * intereses)) * 100;
                pago6.setRefrendo(refrendo.intValue());
                pago6.setPago((finiquito.intValue() * 100) / 2);
                pagos.add(pago6);
                cargarTablaPagos(pagos);
                break;
            case "Mensual":
                Pago pago7 = new Pago();
                pago7.setFechaInicio(java.sql.Date.valueOf(fechaInicio));
                pago7.setFechaLimite(java.sql.Date.valueOf(fechaInicio.plusDays(30)));
                finiquito = (total + total * intereses);
                pago7.setFiniquito(finiquito.intValue() * 100);
                pago7.setPrestamo(total.intValue());
                refrendo = ((total * intereses)) * 100;
                pago7.setRefrendo(refrendo.intValue());
                pago7.setPago(finiquito.intValue() * 100);
                pagos.add(pago7);
                cargarTablaPagos(pagos);
                break;
        }
    }

    public void calcularTotal() {
        int suma = 0;
        for (int i = 0; i < prendas.size(); i++) {
            suma = suma + prendas.get(i).getPrestamo();
        }
        totalTxt.setText(Integer.toString(suma));
    }

    public void cargarParametros() {
        ParametrosSucursal parametros = new ParametrosSucursal();
        parametros = ParametrosSucursalDAO.getParametros();
        interesOrdinarioTXT.setText(Integer.toString(parametros.getInteresOrdinario()));
        interesAlmacenTxt.setText(Integer.toString(parametros.getInteresAlmacen()));
        periodoTxt.setText(parametros.getTipoPeriodo());

        fechaInicioTxt.setText(fechaInicio.getDayOfMonth() + "/" + fechaInicio.getMonthValue() + "/" + fechaInicio.getYear());
        fechaFinTxt.setText(fechaFin.getDayOfMonth() + "/" + fechaFin.getMonthValue() + "/" + fechaFin.getYear());
    }

    public void obtenerUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarColumnasPrendas();
        inicialiazarTablaPrendas();
        inicializarColumnasPagos();
        inicializarTablaPagos();
        cargarParametros();
        
    }

}
