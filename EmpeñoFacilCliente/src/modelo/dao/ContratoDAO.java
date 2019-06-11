/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.beans.Contrato;
import modelo.beans.Pago;
import modelo.beans.Prenda;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juuan
 */
public class ContratoDAO {
    public static boolean registrarContrato(Contrato contrato, List<Pago> pagos, List<Prenda> prendas) {
        SqlSession conn = null;
        try {
            //LLAVE   VALOR
            HashMap<String, Object> parametros = new HashMap<String, Object>();   
            parametros.put("Cliente_rfc", contrato.getCliente_rfc());
            parametros.put("Usuario_numPersonal", contrato.getUsuario_numPersonal());
            parametros.put("interesOrdinario", contrato.getInteresOrdinario());
            parametros.put("interesAlmacen", contrato.getInteresAlmacen());
            parametros.put("fechaInicio", contrato.getFechaInicio());
            parametros.put("fechaFin", contrato.getFechaFin());

            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Contrato.registrarContrato", parametros);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numerofilasafectadas > 0) {
                Contrato contratoAlmacenado = new Contrato();
                contratoAlmacenado = obtenerUltimoContrato();
                for (int i = 0; i < pagos.size(); i++){
                    pagos.get(i).setContrato_idContrato(contratoAlmacenado.getIdContrato());
                    if(!PagoDAO.registrarPago(pagos.get(i))){
                        return false;
                    }
                }
                for(int i = 0;i < prendas.size(); i++){
                    prendas.get(i).setIdContrato(contratoAlmacenado.getIdContrato());
                    if(!PrendaDAO.registrarPrenda(prendas.get(i))){
                        return false;
                    }
                }
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
    
    public static Contrato obtenerUltimoContrato() {
        Contrato contrato = new Contrato();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            contrato = conn.selectOne("Contrato.obtenerUltimoContrato");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return contrato;
    }
    
    public static List<Contrato> obtenerContratos() {
        List<Contrato> contratos = new ArrayList<Contrato>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            contratos = conn.selectList("Contrato.obtenerContratos");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return contratos;
    }
}
