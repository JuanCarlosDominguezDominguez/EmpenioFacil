/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.beans.Ocupacion;
import modelo.beans.ParametrosSucursal;
import modelo.beans.Periodo;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

@SuppressWarnings("CallToPrintStackTrace")
/**
 *
 * @author YZ
 */
public class ParametrosSucursalDAO {

    public static ParametrosSucursal getParametros() {
        ParametrosSucursal parametros = null;
        try (SqlSession conn = ConexionDB.getSession()) {
            parametros = conn.selectOne("ParametrosSucursal.getParametros");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return parametros;
    }

    public static boolean actualizarParametros(Integer idSucursal, Integer interesOrdinario, Integer interesAlmacen, Integer idTipoPeriodo) {
        try (SqlSession conn = ConexionDB.getSession()) {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("idSucursal", idSucursal);
            parametros.put("interesOrdinario", interesOrdinario);
            parametros.put("interesAlmacen", interesAlmacen);
            parametros.put("idTipoPeriodo", idTipoPeriodo);
            int numeroFilasAfectadas = conn.update("ParametrosSucursal.actualizarParametros", parametros);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numeroFilasAfectadas > 0) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean registrarGasto(int monto) throws Exception {
        ParametrosSucursal parametrosSucursal = ParametrosSucursalDAO.getParametros();
        if (parametrosSucursal != null) {
            if (parametrosSucursal.getFondo() - monto < 0) {
                throw new Exception("No hay dinero suficiente.");
            }
            try (SqlSession conn = ConexionDB.getSession()) {
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("idSucursal", parametrosSucursal.getIdSucursal());
                parametros.put("monto", 0 - monto);
                int numeroFilasAfectadas = conn.update("ParametrosSucursal.actualizarFondo", parametros);
                ParametrosSucursal p = ParametrosSucursalDAO.getParametros();
                System.out.println("Fondo: " + p.getFondo());
                conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
                if (numeroFilasAfectadas > 0) {
                    return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public static boolean registrarAumentoFondos(int monto) {
        ParametrosSucursal parametrosSucursal = ParametrosSucursalDAO.getParametros();
        if (parametrosSucursal != null) {
            try (SqlSession conn = ConexionDB.getSession()) {
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("idSucursal", parametrosSucursal.getIdSucursal());
                parametros.put("monto", monto);
                int numeroFilasAfectadas = conn.update("ParametrosSucursal.actualizarFondo", parametros);
                conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
                if (numeroFilasAfectadas > 0) {
                    return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public static List<Periodo> getPeriodos() {
        List<Periodo> periodos = new ArrayList<>();
        try (SqlSession conn = ConexionDB.getSession()) {
            periodos = conn.selectList("ParametrosSucursal.getPeriodos");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return periodos;
    }
}
