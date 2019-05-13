/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.beans.Categoria;
import modelo.beans.Usuario;
import modelo.dao.CategoriaDAO;

/**
 * FXML Controller class
 *
 * @author Juuan
 */
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button cerrarSesionBtn;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu administradorMenu;

    @FXML
    private MenuItem adminUsuariosMenuItem;

    @FXML
    private MenuItem modificarParametrosMenuItem;

    @FXML
    private MenuItem aumentarFondosMenuItem;

    @FXML
    private Menu gerenteMenu;

    @FXML
    private MenuItem registrarGastosMenuItem;

    @FXML
    private Menu usuarioMenu;

    @FXML
    private MenuItem clientesMenuItem;

    @FXML
    private Menu bodegueroMenu;

    @FXML
    private Label txtnombre;

    @FXML
    private Label txtNumPersonal;

    @FXML
    private Label txtRol;

    private Usuario usuario;

    @FXML
    void administrarUsuarios(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BuscarUsuario.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        BuscarUsuarioController buc = (BuscarUsuarioController) loader.getController();

        buc.obtenerUsuario(usuario);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void clientes(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BuscarCliente.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //BuscarUsuarioController buc = (BuscarUsuarioController) loader.getController();
        //buc.obtenerUsuario(usuario);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void modificarParametros(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModificarParametrosSucursal.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //BuscarUsuarioController buc = (BuscarUsuarioController) loader.getController();
        //buc.obtenerUsuario(usuario);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void registrarGastos(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/RegistrarGasto.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //BuscarUsuarioController buc = (BuscarUsuarioController) loader.getController();
        //buc.obtenerUsuario(usuario);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void aumentarFondos(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AumentoFondos.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //BuscarUsuarioController buc = (BuscarUsuarioController) loader.getController();
        //buc.obtenerUsuario(usuario);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/IniciarSesion.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage escenario = new Stage();
        Scene scene = new Scene(root);
        escenario.setScene(scene);
        escenario.show();
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void obtenerUsuario(Usuario usuario) {
        this.usuario = usuario;
        txtNumPersonal.setText(Integer.toString(usuario.getNumPersonal()));
        Categoria c = CategoriaDAO.obtenerRolPorID(usuario.getIdRol());
        txtRol.setText(c.getNombre());
        txtnombre.setText(usuario.getNombreCompleto());
        identificarUsuario(txtRol.getText());
    }

    @FXML
    void iniciarlizarMenus() {
        administradorMenu.setVisible(false);
        gerenteMenu.setVisible(false);
        usuarioMenu.setVisible(false);
        bodegueroMenu.setVisible(false);
    }

    @FXML
    void identificarUsuario(String rol) {
        switch (rol) {
            case "Administrador":
                administradorMenu.setVisible(true);
                gerenteMenu.setVisible(true);
                usuarioMenu.setVisible(true);
                bodegueroMenu.setVisible(true);
                break;
            case "Cajero":
                usuarioMenu.setVisible(true);
                break;
            case "Gerente":
                gerenteMenu.setVisible(true);
                usuarioMenu.setVisible(true);
                bodegueroMenu.setVisible(true);
                break;
            case "Bodeguero":
                bodegueroMenu.setVisible(true);
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarlizarMenus();
    }

}
