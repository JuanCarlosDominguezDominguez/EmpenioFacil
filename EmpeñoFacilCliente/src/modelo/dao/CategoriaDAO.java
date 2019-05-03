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
import modelo.beans.Categoria;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juuan
 */
public class CategoriaDAO {
    public static boolean registrarCategoria(Integer idCategoria, String nombre) {
        SqlSession conn = null;
        try {
            //LLAVE   VALOR
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nombre", nombre);
            parametros.put("idCategoria", idCategoria);

            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Categoria.registrar", parametros);
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
    
    public static List<Categoria> buscarCategoriasRoles(){
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try{
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.buscarCategoriasRoles");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }
    
    public static List<Categoria> buscarCategoriasPrendas(){
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try{
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.buscarCategoriasPrendas");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }
    
    public static List<Categoria> buscarCategoriasPrendasSecundarias(){
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try{
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.buscarCategoriasPrendasSecundarias");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }
    
    public static List<Categoria> buscarCategoriasVentaApartado(){
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try{
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.buscarCategoriasVentaApartado");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }
    public static List<Categoria> buscarCategoriasPeriodos(){
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try{
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.buscarCategoriasPeriodos");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }
    public static List<Categoria> buscarCategoriasOcupacion(){
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try{
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.buscarCategoriasOcupacion");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }
    
    public static List<Categoria> busquedaGeneral(HashMap<String, String> filtros) {
        List<Categoria> resultado = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            String criterios = "";

            if (filtros != null) {
                for (Map.Entry<String, String> entry : filtros.entrySet()) {
                    String campo = entry.getKey();
                    String valor = entry.getKey();

                    criterios += (criterios.isEmpty())
                            ? String.format("%s = '%s'", campo, valor)
                            : String.format("AND %s = '%s'", campo, valor);
                }

                conn = ConexionDB.getSession();
                resultado = conn.selectList("Categoria.busquedaGeneral", criterios);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn == null) {
                conn.close();
            }
        }
        return resultado;
    }
}
