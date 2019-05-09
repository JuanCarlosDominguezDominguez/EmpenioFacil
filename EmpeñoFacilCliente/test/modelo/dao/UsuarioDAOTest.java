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
public class UsuarioDAOTest {

    public UsuarioDAOTest() {
    }

    @Test
    public void resgistrarUsuarioExito() {
        boolean valorEsperado = true;
        boolean valorObtenido = UsuarioDAO.registrarUsuario("Test", "Test", "8");
        assertEquals("Usuario registrado", valorEsperado, valorObtenido);
    }

    @Test
    public void resgistrarUsuarioFracaso() {
        boolean valorEsperado = true;
        boolean valorObtenido = UsuarioDAO.registrarUsuario("Test", "Test", "8");
        assertEquals("Usuario no registrado", valorEsperado, valorObtenido);
    }
    
    @Test
    public void eliminarUsuarioExito(){
        boolean valorEsperado = true;
        boolean valorObtenido = UsuarioDAO.eliminarUsuario(8);
        assertEquals("Usuario eliminado", valorEsperado, valorObtenido);
    }
    
    @Test
    public void eliminarUsuarioFracaso(){
        boolean valorEsperado = false;
        boolean valorObtenido = UsuarioDAO.eliminarUsuario(100);
        assertEquals("Usuario no eliminado", valorEsperado, valorObtenido);
    }
    public void actualizarUsuarioExito(){
        //boolean valorEsperado = true;
        //boolean valorObtenido = UsuarioDAO.actualizarUsuario();
        //assertEquals("Usuario actualizado", valorEsperado, valorObtenido);
    }
    
    public void actualizarUsuarioFracasp(){
        //boolean valorEsperado = false;
        //boolean valorObtenido = UsuarioDAO.actualizarUsuario();
        //assertEquals("Usuario no actualizado", valorEsperado, valorObtenido);
    }
}
