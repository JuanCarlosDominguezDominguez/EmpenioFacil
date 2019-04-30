/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import modelo.dao.CategoriaDAO;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class AgregarCategoriaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button guardarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    private ComboBox<String> categoriaCbx;

    @FXML
    private TextField nombreCategoriaTxt;

    @FXML
    void restringirNombre(KeyEvent event) {
        char caracter = event.getCharacter().charAt(0);
        if ((caracter < 'a' || caracter > 'z') && (caracter < 'A' || caracter > 'Z')
                || (nombreCategoriaTxt.getText().length() >= 30)) {
            event.consume();
        }
    }

    public boolean validarCampos() {
        boolean validos = false;
        if (nombreCategoriaTxt.getText() != null && categoriaCbx.getValue() != null) {
            validos = true;
        }
        return validos;
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {
        if(validarCampos()){
            List categorias = CategoriaDAO.buscarCategoriasPrendas();
            boolean existe = false;
            for (int i = 0; i < categorias.size(); i++){
                if (nombreCategoriaTxt.getText() == categorias.get(i)){
                    existe = true;
                }
            }
            if(!existe){
                CategoriaDAO.registrarCategoria(Integer.BYTES, nombreCategoriaTxt.getText());
                JOptionPane.showMessageDialog(null, "Categoria guardada exitosamente.");
            }else{
                JOptionPane.showMessageDialog(null, "El nombre de la categoria ya existe.");
            }
        }else
            JOptionPane.showMessageDialog(null, "El nombre de la categoria es incorrecto.");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
