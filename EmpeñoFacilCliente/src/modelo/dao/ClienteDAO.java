/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.beans.Cliente;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

@SuppressWarnings("CallToPrintStackTrace")
/**
 *
 * @author YZ
 */
public class ClienteDAO {

    public static List<Cliente> getClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (SqlSession conn = ConexionDB.getSession()) {
            clientes = conn.selectList("Cliente.getClientes");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    public static boolean registrarCliente(String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, String curp, String numeroIdentificacion, Integer idOcupacion) {
        try (SqlSession conn = ConexionDB.getSession()) {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("nombre", nombre);
            parametros.put("apellidoPaterno", apellidoPaterno);
            parametros.put("apellidoMaterno", apellidoMaterno);
            parametros.put("rfc", rfc);
            parametros.put("curp", curp);
            parametros.put("numeroIdentificacion", numeroIdentificacion);
            parametros.put("idOcupacion", idOcupacion);
            int numeroFilasAfectadas = conn.insert("Cliente.registrar", parametros);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean actualizarCliente(String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, String curp, String numeroIdentificacion, Integer idOcupacion) {
        try (SqlSession conn = ConexionDB.getSession()) {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("nombre", nombre);
            parametros.put("apellidoPaterno", apellidoPaterno);
            parametros.put("apellidoMaterno", apellidoMaterno);
            parametros.put("rfc", rfc);
            parametros.put("curp", curp);
            parametros.put("numeroIdentificacion", numeroIdentificacion);
            parametros.put("idOcupacion", idOcupacion);
            int numeroFilasAfectadas = conn.update("Cliente.actualizar", parametros);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static List<Cliente> buscar(HashMap<String, String> filtros) {
        List<Cliente> resultado = new ArrayList<>();
        try (SqlSession conn = ConexionDB.getSession()) {
            String criterios = "";
            if (filtros != null) {
                for (Map.Entry<String, String> entry : filtros.entrySet()) {
                    String campo = entry.getKey();
                    String condicion = entry.getValue();
                    if(!criterios.isEmpty()){
                        criterios += "AND ";
                    }
                    criterios += String.format("%s %s ", campo, condicion);
                }
                System.out.println(criterios);
                resultado = conn.selectList("Cliente.busqueda", criterios);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

}
