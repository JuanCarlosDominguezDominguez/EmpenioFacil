/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juuan
 */
public class CategoriaDAOTest {

    public CategoriaDAOTest() {
    }

    @Test
    //Esta si pasa sin marcar excepciones

    public void registrarCategoriaExito() {
        boolean valorEsperado = true;
        boolean valorObtenido = CategoriaDAO.registrarCategoriaPrincipal("Test");
        assertEquals("Categoria registrada", valorEsperado, valorObtenido);
    }

    @Test
    public void registrarCategoriaFracaso() {
        boolean valorEsperado = false;
        boolean valorObtenido = CategoriaDAO.registrarCategoriaPrincipal("Test eliminar");
        assertEquals("Categoria no registrada", valorEsperado, valorObtenido);
    }
    @Test
    //Esta si pasa sin marcar excepciones

    public void eliminarCategoriaExito() {
        boolean valorEsperado = true;
        boolean valorObtenido = CategoriaDAO.eliminarCategoria(34);
        assertEquals("Categoria eliminada", valorEsperado, valorObtenido);
    }
    @Test
    //Esta si pasa sin marcar excepciones

    public void eliminarCategoriaFracaso() {
        boolean valorEsperado = false;
        boolean valorObtenido = CategoriaDAO.eliminarCategoria(100);
        assertEquals("Categoria no eliminada", valorEsperado, valorObtenido);
    }
    @Test
    //Esta si pasa sin marcar excepciones

    public void actualizarCategoriaExito() {
        boolean valorEsperado = true;
        boolean valorObtenido = CategoriaDAO.actualizarCategoriaPrincipal(35, "Test actualizar");
        assertEquals("Categoria no eliminada", valorEsperado, valorObtenido);
    }
    @Test
    //Esta si pasa sin marcar excepciones

    public void actualizarCategoriaFracaso() {
        boolean valorEsperado = false;
        boolean valorObtenido = CategoriaDAO.actualizarCategoriaPrincipal(0, "Test actualizar");
        assertEquals("Categoria no eliminada", valorEsperado, valorObtenido);
    }
}
