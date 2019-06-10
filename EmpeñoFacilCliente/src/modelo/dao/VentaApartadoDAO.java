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
import modelo.beans.Articulo;
import modelo.beans.VentaApartado;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

@SuppressWarnings("CallToPrintStackTrace")
/**
 *
 * @author YZ
 */
public class VentaApartadoDAO {

    public static boolean RegistrarVenta(Integer monto, String rfcCliente, Integer numPersonal, List<Articulo> articulos) {
        try (SqlSession conn = ConexionDB.getSession()) {
            HashMap<String, Object> parametrosVenta = new HashMap<>();
            int numFilasAfectadas;
            parametrosVenta.put("id", 0);
            parametrosVenta.put("monto", monto);
            parametrosVenta.put("rfc", rfcCliente);
            parametrosVenta.put("numPersonal", numPersonal);
            numFilasAfectadas = conn.insert("VentaApartado.registrarVenta", parametrosVenta);
            if (numFilasAfectadas < 1) {
                conn.rollback();
                return false;
            }
            Integer idVenta = (Integer) parametrosVenta.get("id");
            HashMap<String, Object> parametrosTicket;
            for (Articulo articulo : articulos) {
                parametrosTicket = new HashMap<>();
                parametrosTicket.put("idVentaApartado", idVenta);
                parametrosTicket.put("idArticulo", articulo.getIdArticulo());
                numFilasAfectadas = conn.insert("VentaApartado.agregarTicket", parametrosTicket);
                if (numFilasAfectadas < 1) {
                    conn.rollback();
                    return false;
                }
                numFilasAfectadas = conn.update("Articulo.marcarVendido", articulo.getIdArticulo());
                if (numFilasAfectadas < 1) {
                    conn.rollback();
                    return false;
                }
            }
            conn.commit();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static List<VentaApartado> buscar(HashMap<String, String> filtros) {
        List<VentaApartado> resultado = new ArrayList<>();
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
                resultado = conn.selectList("VentaApartado.buscarVenta", criterios);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return resultado;
        }
    }
    
    public static List<Articulo> getArticulosVenta(Integer idVenta) {
        List<Articulo> articulos = new ArrayList<>();
        try (SqlSession conn = ConexionDB.getSession()) {
            articulos = conn.selectList("VentaApartado.getArticulosVenta", idVenta);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return articulos;
        }
    }

}
