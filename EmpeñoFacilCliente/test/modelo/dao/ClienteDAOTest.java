/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.HashMap;
import java.util.List;
import modelo.beans.Cliente;
import modelo.beans.Ocupacion;
import org.junit.After;
import org.junit.AfterClass;
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
        List<Cliente> expResult = null;
        List<Cliente> result = ClienteDAO.getClientes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarCliente method, of class ClienteDAO.
     */
    @Test
    public void testRegistrarCliente() {
        System.out.println("registrarCliente");
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        String rfc = "";
        String curp = "";
        String numeroIdentificacion = "";
        Integer idOcupacion = null;
        boolean expResult = false;
        boolean result = ClienteDAO.registrarCliente(nombre, apellidoPaterno, apellidoMaterno, rfc, curp, numeroIdentificacion, idOcupacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarCliente method, of class ClienteDAO.
     */
    @Test
    public void testActualizarCliente() {
        System.out.println("actualizarCliente");
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        String rfc = "";
        String curp = "";
        String numeroIdentificacion = "";
        Integer idOcupacion = null;
        boolean expResult = false;
        boolean result = ClienteDAO.actualizarCliente(nombre, apellidoPaterno, apellidoMaterno, rfc, curp, numeroIdentificacion, idOcupacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class ClienteDAO.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        HashMap<String, String> filtros = null;
        List<Cliente> expResult = null;
        List<Cliente> result = ClienteDAO.buscar(filtros);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOcupaciones method, of class ClienteDAO.
     */
    @Test
    public void testGetOcupaciones() {
        System.out.println("getOcupaciones");
        List<Ocupacion> expResult = null;
        List<Ocupacion> result = ClienteDAO.getOcupaciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
