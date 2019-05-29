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
import modelo.beans.Ocupacion;
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
        } finally {
            return clientes;
        }
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
                    if (!criterios.isEmpty()) {
                        criterios += "AND ";
                    }
                    criterios += String.format("%s %s ", campo, condicion);
                }
                resultado = conn.selectList("Cliente.busqueda", criterios);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return resultado;
        }
    }

    public static boolean enviarAListaNegra(String rfc) throws IllegalStateException {
        try (SqlSession conn = ConexionDB.getSession()) {
            boolean estaEnListaNegra = conn.selectOne("Cliente.estaEnListaNegra", rfc);
            if (estaEnListaNegra) {
                throw new IllegalStateException("El cliente ya está en la lista negra.");
            }
            int numeroFilasAfectadas = conn.update("Cliente.enviarAListaNegra", rfc);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (IOException ex) {
            System.out.println("ex");
            ex.printStackTrace();
        }
        return false;

    }

    public static boolean sacarDeListaNegra(String rfc) throws IllegalStateException {
        try (SqlSession conn = ConexionDB.getSession()) {
            boolean estaEnListaNegra = conn.selectOne("Cliente.estaEnListaNegra", rfc);
            if (!estaEnListaNegra) {
                throw new IllegalStateException("El cliente no está en la lista negra.");
            }
            int numeroFilasAfectadas = conn.update("Cliente.sacarDeListaNegra", rfc);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static List<Ocupacion> getOcupaciones() {
        List<Ocupacion> ocupaciones = new ArrayList<>();
        try (SqlSession conn = ConexionDB.getSession()) {
            ocupaciones = conn.selectList("Cliente.getOcupaciones");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return ocupaciones;
        }
    }

}
