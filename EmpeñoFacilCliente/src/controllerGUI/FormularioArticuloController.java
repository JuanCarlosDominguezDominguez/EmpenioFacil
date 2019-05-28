/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.beans.Articulo;
import modelo.beans.TipoProducto;
import modelo.dao.ArticuloDAO;
import utileria.Dialogos;
import utileria.Validar;

/**
 * FXML Controller class
 *
 * @author YZ
 */
public class FormularioArticuloController implements Initializable {

    public static Articulo articulo = null;
    
    @FXML
    private ComboBox<TipoProducto> cmbCategoria;
    @FXML
    private ComboBox<TipoProducto> cmbTipoProducto;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtPrecio;
    
    private List<TipoProducto> tiposProducto;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(articulo != null){
            tiposProducto = ArticuloDAO.getTiposProducto();
            String precioString = articulo.getPrecio().toString();
            String pesos = precioString.substring(0, precioString.length() - 2);
            String centavos = precioString.substring(precioString.length() - 2, precioString.length());
            txtPrecio.setText(pesos + "." + centavos);
            txtDescripcion.setText(articulo.getDescripcion());
            llenarCmbCategoria();
            for (TipoProducto categoria : cmbCategoria.getItems()){
                if(categoria.getIdCategoria() == articulo.getIdCategoria()) {
                    cmbCategoria.getSelectionModel().select(categoria);
                    break;
                }
            }
            llenarCmbTipoProducto(articulo.getIdCategoria());
            for (TipoProducto tipoProducto : cmbTipoProducto.getItems()) {
                if(tipoProducto.getIdCategoria() == articulo.getIdTipoProducto()) {
                    cmbTipoProducto.getSelectionModel().select(tipoProducto);
                    break;
                }
            }
        }
    }
    
    private void llenarCmbCategoria(){
        EventHandler<ActionEvent> handler = cmbCategoria.getOnAction(); //Se quita el handler para que no se ejecute cuand
        cmbCategoria.setOnAction(null);
        cmbCategoria.getItems().clear();
        TipoProducto oro = new TipoProducto();
        oro.setIdCategoria(2);
        oro.setNombre("Oro");
        TipoProducto producto = new TipoProducto();
        producto.setIdCategoria(3);
        producto.setNombre("Producto");
        cmbCategoria.getItems().add(oro);
        cmbCategoria.getItems().add(producto);
        cmbCategoria.setOnAction(handler);
    }
    
    private void llenarCmbTipoProducto(Integer idCategoria) {
        cmbTipoProducto.getItems().clear();
        tiposProducto.forEach(tipoProducto -> {
            if(tipoProducto.getCategorias_IdCategoria() == idCategoria) {
                cmbTipoProducto.getItems().add(tipoProducto);
            }
        });
        cmbTipoProducto.getSelectionModel().select(0);
    }
    
    @FXML
    void cambiarCategoria(ActionEvent event){
        llenarCmbTipoProducto(cmbCategoria.getSelectionModel().getSelectedItem().getIdCategoria());
    }
    

    @FXML
    void guardar(ActionEvent event) {
        if(validar()){
            String descripcion = txtDescripcion.getText();
            Double precioDouble = Double.parseDouble(txtPrecio.getText());
            precioDouble *= 100;
            Integer precio = precioDouble.intValue();
            Integer idCategoria = cmbCategoria.getSelectionModel().getSelectedItem().getIdCategoria();
            Integer idTipoProducto = cmbTipoProducto.getSelectionModel().getSelectedItem().getIdCategoria();
            if(ArticuloDAO.actualizarArticulo(articulo.getIdArticulo(), idCategoria, idTipoProducto, precio, descripcion)) {
                Dialogos.showInformation("Artículo guardado", "El artículo se ha guardado exitosamente.");
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
            } else {
                Dialogos.showError("Artículo no guardado", "Error al guardar el artículo");
            }
        } else {
            Dialogos.showWarning("Campos inválidos.", "Verifique el contenido de los campos.");
        }
    }
    
    @FXML
    void cancelar(ActionEvent event) {
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }
    
    private boolean validar(){
        if(cmbCategoria.getSelectionModel().getSelectedItem() == null){
            return false;
        }
        if(cmbTipoProducto.getSelectionModel().getSelectedItem() == null){
            return false;
        }
        if(!Validar.validarCadena(txtDescripcion.getText())) {
            return false;
        }
        if(!Validar.validarMoneda(txtPrecio.getText())) {
            return false;
        }
        return true;
    }
    
}
