/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.beans.Articulo;
import modelo.beans.VentaApartado;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author YZ
 */
public class VentaApartadoDAOTest {
    
    private int idVenta = 0;
    
    public VentaApartadoDAOTest() {
    }

    /**
     * Test of RegistrarVenta method, of class VentaApartadoDAO.
     */
    @Test
    public void testRegistrarVenta() {
        System.out.println("RegistrarVenta");
        Integer monto = 300000;
        String rfcCliente = "0000000000000";
        Integer numPersonal = 0;
        Articulo a = new Articulo();
        a.setIdArticulo(0);
        List<Articulo> articulos = new ArrayList<>();
        articulos.add(a);
        boolean expResult = true;
        boolean result = VentaApartadoDAO.RegistrarVenta(monto, rfcCliente, numPersonal, articulos);
        assertEquals(expResult, result);
    }

    /**
     * Test of buscar method, of class VentaApartadoDAO.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        HashMap<String, String> filtros = new HashMap<>();
        filtros.put("Usuario_numPersonal", "= 0");
        List<VentaApartado> result = VentaApartadoDAO.buscar(filtros);
        idVenta = result.get(0).getIdVentaApartado();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getArticulosVenta method, of class VentaApartadoDAO.
     */
    @Test
    public void testGetArticulosVenta() {
        System.out.println("getArticulosVenta");
        List<Articulo> result = VentaApartadoDAO.getArticulosVenta(idVenta);
        assertTrue(result.isEmpty());
    }
    
    @Test
    public void testRegistrarApartado() {
        Integer monto = 300000;
        String rfcCliente = "0000000000000";
        Integer numPersonal = 0;
        Articulo a = new Articulo();
        a.setIdArticulo(0);
        List<Articulo> articulos = new ArrayList<>();
        articulos.add(a);
        Integer montoAdelanto = 30000;
        boolean expResult = true;
        boolean result = VentaApartadoDAO.RegistrarApartado(monto, rfcCliente, numPersonal, articulos, montoAdelanto);
        assertEquals(expResult, result);
    }

    /**
     * Test of marcarFiniquito method, of class VentaApartadoDAO.
     */
    @Test
    public void testMarcarFiniquito() {
        System.out.println("marcarFiniquito");
        Integer idVentaApartado = null;
        Integer restante = null;
        boolean expResult = false;
        boolean result = VentaApartadoDAO.marcarFiniquito(idVentaApartado, restante);
        assertEquals(expResult, result);
    }

    /**
     * Test of RegistrarApartado method, of class VentaApartadoDAO.
     */
    
    
}
