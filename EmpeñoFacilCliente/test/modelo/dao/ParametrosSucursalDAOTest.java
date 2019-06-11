/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.beans.ParametrosSucursal;
import modelo.beans.Periodo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author YZ
 */
public class ParametrosSucursalDAOTest {
    
    public ParametrosSucursalDAOTest() {
    }

    /**
     * Test of getParametros method, of class ParametrosSucursalDAO.
     */
    @Test
    public void testGetParametros() {
        System.out.println("getParametros");
        ParametrosSucursal result = ParametrosSucursalDAO.getParametros();
        assertNotNull(result);
    }

    /**
     * Test of actualizarParametros method, of class ParametrosSucursalDAO.
     */
    @Test
    public void testActualizarParametros() {
        System.out.println("actualizarParametros");
        ParametrosSucursal p = ParametrosSucursalDAO.getParametros();
        Integer idSucursal = p.getIdSucursal();
        Integer interesOrdinario = p.getInteresOrdinario()==8?7:8;
        Integer interesAlmacen = 5;
        Integer idTipoPeriodo = 14;
        boolean expResult = true;
        boolean result = ParametrosSucursalDAO.actualizarParametros(idSucursal, interesOrdinario, interesAlmacen, idTipoPeriodo);
        assertEquals(expResult, result);
    }

    /**
     * Test of registrarGasto method, of class ParametrosSucursalDAO.
     */
    @Test
    public void testRegistrarGasto() throws Exception {
        System.out.println("registrarGasto");
        int monto = 50000;
        boolean expResult = true;
        boolean result = ParametrosSucursalDAO.registrarGasto(monto);
        assertEquals(expResult, result);
    }

    /**
     * Test of registrarAumentoFondos method, of class ParametrosSucursalDAO.
     */
    @Test
    public void testRegistrarAumentoFondos() {
        System.out.println("registrarAumentoFondos");
        int monto = 50000;
        boolean expResult = true;
        boolean result = ParametrosSucursalDAO.registrarAumentoFondos(monto);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPeriodos method, of class ParametrosSucursalDAO.
     */
    @Test
    public void testGetPeriodos() {
        System.out.println("getPeriodos");
        List<Periodo> result = ParametrosSucursalDAO.getPeriodos();
        assertFalse(result.isEmpty());
    }
    
}
