/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import modelo.beans.Articulo;
import modelo.beans.TipoProducto;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author YZ
 */
public class ArticuloDAOTest {
    
    public ArticuloDAOTest() {
    }

    /**
     * Test of getArticulos method, of class ArticuloDAO.
     */
    @Test
    public void testGetArticulos() {
        System.out.println("getArticulos");
        List<Articulo> result = ArticuloDAO.getArticulos();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of buscar method, of class ArticuloDAO.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        HashMap<String, String> filtros = new HashMap<>();
        filtros.put("idArticulo", "= 0");
        List<Articulo> result = ArticuloDAO.buscar(filtros);
        assertFalse( result.isEmpty());
    }

    /**
     * Test of darDeBaja method, of class ArticuloDAO.
     */
    @Test
    public void testDarDeBaja() {
        System.out.println("darDeBaja");
        Integer idArticulo = null;
        boolean expResult = false;
        boolean result = ArticuloDAO.darDeBaja(idArticulo);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTiposProducto method, of class ArticuloDAO.
     */
    @Test
    public void testGetTiposProducto() {
        System.out.println("getTiposProducto");
        List<TipoProducto> result = ArticuloDAO.getTiposProducto();
        assertFalse( result.isEmpty());
    }

    /**
     * Test of actualizarArticulo method, of class ArticuloDAO.
     */
    @Test
    public void testActualizarArticulo() {
        System.out.println("actualizarArticulo");
        Random r = new Random();
        Integer rint = r.nextInt(2000000000);
        Integer idArticulo = 0;
        Integer idCategoria = 3;
        Integer idTipoProducto = 22;
        Integer precio = 300000;
        String descripcion = "Artículo de prueba " + rint.toString();
        boolean expResult = true;
        boolean result = ArticuloDAO.actualizarArticulo(idArticulo, idCategoria, idTipoProducto, precio, descripcion);
        assertEquals(expResult, result);
    }
    
}
