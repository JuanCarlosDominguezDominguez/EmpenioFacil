/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.beans.Pago;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juuan
 */
public class PagoDAO {
    
    public static boolean registrarPago(Pago pago) {
        SqlSession conn = null;
        try {
            //LLAVE   VALOR
            HashMap<String, Object> parametros = new HashMap<String, Object>();            
            parametros.put("fechaInicio", pago.getFechaInicio());
            parametros.put("fechaLimite", pago.getFechaLimite());
            parametros.put("contrato_idContrato", pago.getContrato_idContrato());
            parametros.put("pago", pago.getPago());
            parametros.put("refrendo", pago.getRefrendo());
            parametros.put("finiquito", pago.getFiniquito());

            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Pago.registrarPago", parametros);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
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
    
    public static List<Pago> obtenerPagos(Integer contrato_idContrato) {
        List<Pago> pagos = new ArrayList<Pago>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            pagos = conn.selectList("Pago.obtenerPagos", contrato_idContrato);
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
