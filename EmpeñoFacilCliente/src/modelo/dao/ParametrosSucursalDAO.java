/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.HashMap;
import modelo.beans.ParametrosSucursal;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

@SuppressWarnings("CallToPrintStackTrace")
/**
 *
 * @author YZ
 */
public class ParametrosSucursalDAO {
    
    public static ParametrosSucursal getParametros(){
        ParametrosSucursal parametros = null;
        try (SqlSession conn = ConexionDB.getSession()) {
            parametros = conn.selectOne("ParametrosSucursal.getParametros");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return parametros;
    }
    
    public static boolean actualizarParametros(Integer idSucursal, Integer fondo, Integer interesOrdinario, Integer interesAlmacen, Integer idTipoPeriodo){
        try (SqlSession conn = ConexionDB.getSession()) {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("idSucursal", idSucursal);
            parametros.put("fondo", fondo);
            parametros.put("interesOrdinario", interesOrdinario);
            parametros.put("interesAlmacen", interesAlmacen);
            parametros.put("idTipoPeriodo", idTipoPeriodo);
            int numeroFilasAfectadas = conn.update("ParametrosSucursal.actualizar", parametros);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
