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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.beans.Categoria;
import modelo.beans.Usuario;
import modelo.dao.CategoriaDAO;
import modelo.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class RegistrarUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String nombre;
    private String contrasenia;
    private String rol;
    private static String tipo;
    private List<Categoria> roles;

    @FXML
    private Button guardarBtn;

    @FXML
    private ComboBox<String> rolCbx;

    @FXML
    private Button cancelarBtn;

    @FXML
    private TextField nombreTxt;

    @FXML
    private PasswordField contraseniaTxt;

    private Usuario usuario;

    @FXML
    void restringirCintrasenia(KeyEvent event) {
        if (contraseniaTxt.getText().length() >= 45) {
            event.consume();
        }
    }

    @FXML
    void restringirNombre(KeyEvent event) {
        if (nombreTxt.getText().length() >= 60) {
            event.consume();
        }
    }

    public boolean validarCampos() {
        nombre = nombreTxt.getText();
        contrasenia = contraseniaTxt.getText();
        rol = rolCbx.getValue();
        if (nombre != null && contrasenia != null && rol != null
                && nombre.trim().length() > 0 && contrasenia.trim().length() > 0) {
            return true;
        }
        return false;
    }

    @FXML
    void cancelar(ActionEvent event) {
        Parent root;
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void guardar(ActionEvent event) {
        nombre = nombreTxt.getText();
        contrasenia = contraseniaTxt.getText();
        rol = rolCbx.getValue();
        int idrol = 0;
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getNombre().equals(rol)) {
                idrol = roles.get(i).getIdCategoria();
            }
        }
        if (validarCampos()) {
            if (tipo.equals("nuevo")) {
                if (UsuarioDAO.registrarUsuario(nombre, contrasenia, Integer.toString(idrol))) {
                    JOptionPane.showMessageDialog(null, "Usuario guardado exitosamente.");
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar al usuario");
                }
            } else {
                if (tipo.equals("modificar")) {
                    if (UsuarioDAO.actualizarUsuario(nombre, contrasenia, Integer.toString(idrol), (Integer)usuario.getNumPersonal())) {
                        JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar al usuario");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los datos del usuario son incorrectos.");
        }
    }

    public void cargarRoles() {
        roles = new ArrayList<Categoria>();
        roles = CategoriaDAO.obtenerTodosLosRoles();
        ObservableList<String> acciones = FXCollections.observableArrayList();
        for (int i = 0; i < roles.size(); i++) {
            acciones.add(roles.get(i).getNombre());
        }
        rolCbx.setItems(acciones);
    }

    @FXML
    public void obtenerDatos(Usuario usuario, String tipo) {
        if (usuario == null) {
            this.tipo = tipo;
        } else {
            this.usuario = usuario;
            this.tipo = tipo;
            this.nombreTxt.setText(usuario.getNombreCompleto());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRoles();
        System.out.println("el tipo es: " + tipo);
    }

}
