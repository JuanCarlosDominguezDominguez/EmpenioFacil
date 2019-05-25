/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.beans.Articulo;
import modelo.beans.TipoProducto;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

@SuppressWarnings("CallToPrintStackTrace")
/**
 *
 * @author YZ
 */
public class ArticuloDAO {
    
    public static List<Articulo> getArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        try (SqlSession conn = ConexionDB.getSession()) {
            articulos = conn.selectList("Articulo.getArticulos");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return articulos;
        }
    }
    
    public static List<Articulo> buscar(HashMap<String, String> filtros) {
        List<Articulo> articulos = new ArrayList<>();
        try (SqlSession conn = ConexionDB.getSession()) {
            String criterios = "";
            if(filtros != null) {
                for (Map.Entry<String, String> entry : filtros.entrySet()) {
                    String campo = entry.getKey();
                    String condicion = entry.getValue();
                    if (!criterios.isEmpty()) {
                        criterios += "AND ";
                    }
                    criterios += String.format("%s %s ", campo, condicion);
                }
                articulos = conn.selectList("Articulo.busqueda", criterios);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return articulos;
        }
    }
    
    public static boolean darDeBaja(Integer idArticulo){
        try (SqlSession conn = ConexionDB.getSession()) {
            int numeroFilasAfectadas = conn.update("Articulo.darDeBaja", idArticulo);
            conn.commit();
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static List<TipoProducto> getTiposProducto(){
        List<TipoProducto> tiposProducto = new ArrayList<>();
        try (SqlSession conn = ConexionDB.getSession()) {
            tiposProducto = conn.selectList("Articulo.getTiposProducto");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return tiposProducto;
        }
    }
    
}
