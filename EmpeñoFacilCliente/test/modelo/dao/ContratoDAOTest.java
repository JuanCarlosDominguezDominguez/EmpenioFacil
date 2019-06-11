/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.beans.Contrato;
import modelo.beans.Pago;
import modelo.beans.Prenda;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juuan
 */
public class ContratoDAOTest {
    
    public ContratoDAOTest() {
    }

    @Test
    public void registrarContratoExito() {
        Prenda prenda = new Prenda();
        List<Prenda> prendas = new ArrayList<Prenda>();
        prenda.setAvaluo(1000);
        prenda.setCategoria(2);
        prenda.setDescripcion("Registar prenda Test");
        prenda.setIdContrato(1);
        prenda.setPrestamo(800);
        prendas.add(prenda);
        
        LocalDate fecha = LocalDate.now();
        Pago pago = new Pago();
        List<Pago> pagos = new ArrayList<Pago>();        
        pago.setFechaInicio(java.sql.Date.valueOf(fecha));
        pago.setFechaLimite(java.sql.Date.valueOf(fecha.plusDays(30)));
        pago.setFiniquito(1100);
        pago.setPago(1100);
        pago.setRefrendo(100);
        
        Contrato contrato = new Contrato();
        contrato.setUsuario_numPersonal(3);
        contrato.setCliente_rfc("JCDD961206");
        contrato.setFechaInicio(java.sql.Date.valueOf(fecha));
        contrato.setInteresAlmacen(Integer.MIN_VALUE);
        contrato.setInteresOrdinario(Integer.BYTES);
        contrato.setFechaFin(java.sql.Date.valueOf(fecha.plusDays(30)));
        
        boolean valorEsperado = true;
        boolean valorObtenido = ContratoDAO.registrarContrato(contrato, pagos, prendas);
        assertEquals("Contrato registrado", valorEsperado, valorObtenido);
    }
    
    @Test
    public void registrarContratoFracaso() {
        Prenda prenda = new Prenda();
        List<Prenda> prendas = new ArrayList<Prenda>();
        prenda.setAvaluo(1000);
        prenda.setCategoria(2);
        prenda.setDescripcion("Registar prenda Test");
        prenda.setIdContrato(1);
        prenda.setPrestamo(800);
        prendas.add(prenda);
        
        LocalDate fecha = LocalDate.now();
        Pago pago = new Pago();
        List<Pago> pagos = new ArrayList<Pago>();        
        pago.setFechaInicio(java.sql.Date.valueOf(fecha));
        pago.setFechaLimite(java.sql.Date.valueOf(fecha.plusDays(30)));
        pago.setFiniquito(1100);
        pago.setPago(1100);
        pago.setRefrendo(100);
        
        Contrato contrato = new Contrato();
        contrato.setUsuario_numPersonal(0);
        contrato.setCliente_rfc("JCDD961206");
        contrato.setFechaInicio(java.sql.Date.valueOf(fecha));
        contrato.setInteresAlmacen(Integer.MIN_VALUE);
        contrato.setInteresOrdinario(Integer.BYTES);
        contrato.setFechaFin(java.sql.Date.valueOf(fecha.plusDays(30)));
        
         boolean valorEsperado = false;
        boolean valorObtenido = ContratoDAO.registrarContrato(contrato, pagos, prendas);
        assertEquals("Contrato registrado", valorEsperado, valorObtenido);
    }
    
}
