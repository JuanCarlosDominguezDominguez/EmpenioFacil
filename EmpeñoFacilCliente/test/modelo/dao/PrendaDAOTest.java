/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.beans.Prenda;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juuan
 */
public class PrendaDAOTest {
    
    public PrendaDAOTest() {
    }

    @Test
    public void registrarPrendaExito() {
        Prenda prenda = new Prenda();
        prenda.setAvaluo(1000);
        prenda.setCategoria(2);
        prenda.setDescripcion("Registar prenda Test");
        prenda.setIdContrato(1);
        prenda.setPrestamo(800);
        boolean valorEsperado = true;
        boolean valorObtenido = PrendaDAO.registrarPrenda(prenda);
        assertEquals("Prenda registrada", valorEsperado, valorObtenido);
    }
    
    @Test
    public void registrarPrendaFracaso() {
        Prenda prenda = new Prenda();
        boolean valorEsperado = false;
        boolean valorObtenido = PrendaDAO.registrarPrenda(prenda);
        assertEquals("Prenda no registrada", valorEsperado, valorObtenido);
    }
    
}
