/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.beans.Prenda;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juuan
 */
public class PrendaDAO {
    public static boolean registrarPrenda(Prenda prenda) {
        SqlSession conn = null;
        try {
            //LLAVE   VALOR
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("descripcion", prenda.getDescripcion());
            parametros.put("avaluo", prenda.getAvaluo());
            parametros.put("prestamo", prenda.getPrestamo());
            parametros.put("idContrato", prenda.getIdContrato());
            parametros.put("idCategoria", prenda.getCategoria());
            
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
    
    public static List<Prenda> obtenerPrendas(Integer Contrato_idContrato) {
        List<Prenda> pagos = new ArrayList<Prenda>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            pagos = conn.selectList("Prenda.obtenerPrendas", Contrato_idContrato);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return pagos;
    }
}
