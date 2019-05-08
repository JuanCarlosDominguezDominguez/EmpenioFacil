/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerGUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Juuan
 */
public class IniciarSesionControllerTest {
    
    public IniciarSesionControllerTest() {
    }

    /**
     * Test of iniciarSesion method, of class IniciarSesionController.
     */
    @Test
    public void testIniciarSesion() throws Exception {
        System.out.println("iniciarSesion");
        ActionEvent event = null;
        IniciarSesionController instance = new IniciarSesionController();
        instance.iniciarSesion(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
