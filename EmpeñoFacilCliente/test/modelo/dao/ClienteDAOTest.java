/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import modelo.beans.Cliente;
import modelo.beans.Ocupacion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author YZ
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getClientes method, of class ClienteDAO.
     */
    @Test
    public void testGetClientes() {
        System.out.println("getClientes");
        List<Cliente> result = ClienteDAO.getClientes();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of registrarCliente method, of class ClienteDAO.
     */
    @Test
    public void testRegistrarCliente() {
        System.out.println("registrarCliente");
        String nombre = "Cliente";
        String apellidoPaterno = "Prueba";
        String apellidoMaterno = "Unitaria";
        Random r = new Random();
        Integer rint = r.nextInt(2000000000);
        String rfc = rint.toString();
        String curp = "CURP00000000AAA";
        String numeroIdentificacion = "122AAA";
        Integer idOcupacion = 20;
        boolean expResult = true;
        boolean result = ClienteDAO.registrarCliente(nombre, apellidoPaterno, apellidoMaterno, rfc, curp, numeroIdentificacion, idOcupacion);
        assertEquals(expResult, result);
    }

    /**
     * Test of actualizarCliente method, of class ClienteDAO.
     */
    @Test
    public void testActualizarCliente() {
        System.out.println("actualizarCliente");
        Random r = new Random();
        Integer rint = r.nextInt(2000000000);
        String nombre = "Cliente no registrado";
        String apellidoPaterno = " ";
        String apellidoMaterno = " ";
        String rfc = "0000000000000";
        String curp = "000000000000000000";
        String numeroIdentificacion = rint.toString();
        Integer idOcupacion = 20;
        boolean expResult = true;
        boolean result = ClienteDAO.actualizarCliente(nombre, apellidoPaterno, apellidoMaterno, rfc, curp, numeroIdentificacion, idOcupacion);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscar method, of class ClienteDAO.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        HashMap<String, String> filtros = new HashMap<>();
        filtros.put("rfc", "= '0000000000000'");
        List<Cliente> result = ClienteDAO.buscar(filtros);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getOcupaciones method, of class ClienteDAO.
     */
    @Test
    public void testGetOcupaciones() {
        System.out.println("getOcupaciones");
        List<Ocupacion> result = ClienteDAO.getOcupaciones();
        assertFalse(result.isEmpty());
    }
    
}
