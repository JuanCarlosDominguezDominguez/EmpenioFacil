/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.HashMap;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juuan
 */
public class PrendaDAO {
    public static boolean registrarPrenda(String descripcion, Integer avaluo, Integer prestamo, Integer idCategoria, Integer idContrato) {
        SqlSession conn = null;
        try {
            //LLAVE   VALOR
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("descripcion", descripcion);
            parametros.put("avaluo", avaluo);
            parametros.put("prestamo", prestamo);
            parametros.put("idContrato", idContrato);
            parametros.put("idCategoria", idCategoria);
            //#{descripcion}, #{avaluo}, #{prestamo}, #{idContrato}, #{nombreCategoria}
            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Prenda.registrar", parametros);
            conn.commit();
            if (numerofilasafectadas > 0) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
}
