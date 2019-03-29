/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ControllerGUI.IniciarSesionController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Juuan
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
            /* try {
            Parent root = FXMLLoader.load(clase.getResource(nombreFXML), recurso);
            Stage escenario = new Stage();
            Scene scene = new Scene(root);
            escenario.setScene(scene);
            escenario.setTitle(titulo);
            escenario.show();
            */
 
    }
    
    public static void iniciar(){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        IniciarSesionController i = new IniciarSesionController();
        i.validarCampos();
    }
    
}
