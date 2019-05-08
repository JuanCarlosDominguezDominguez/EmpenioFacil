/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.HashMap;
import java.util.List;
import modelo.beans.*;
import modelo.dao.*;

/**
 *
 * @author Juuan
 */
public class NewMain {

    public static void main(String[] args) {
        //ClienteDAO.registrarCliente("Jonathan", "Vélez", "Álvarez", "XXXX000000AAA", "XXXX000000XXXXXX00", "9999K", 18);
        //ClienteDAO.actualizarCliente("Jonita", "Vélez", "Álvarez", "XXXX000000AAA", "XXXX000000XXXXXX00", "9999K", 18);
        //List<Cliente> clientes = ClienteDAO.getClientes();
//        HashMap<String, String> filtros = new HashMap();
//        filtros.put("apellidoPaterno", "LIKE '%Za%'");
//        List<Cliente> clientes = ClienteDAO.buscar(filtros);
        //clientes.forEach((c) -> {
        //    System.out.println(c);
        //});
        //ParametrosSucursal parametros = ParametrosSucursalDAO.getParametros();
        //System.out.println(parametros);
        //ParametrosSucursalDAO.actualizarParametros(parametros.getIdSucursal(), parametros.getFondo(), parametros.getInteresOrdinario(), 2, parametros.getIdTipoPeriodo());
        //parametros = ParametrosSucursalDAO.getParametros();
        //System.out.println(parametros);
        System.out.println("El tamaño es de: " + UsuarioDAO.getUsuarios().size());
    }

}
