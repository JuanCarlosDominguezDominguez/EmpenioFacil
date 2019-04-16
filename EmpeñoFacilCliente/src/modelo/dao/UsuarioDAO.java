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
import modelo.beans.Usuario;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juuan
 */
public class UsuarioDAO {
    public static boolean registrarUsuario(String nombre, String contrasenia, String rol, String fechaIngreso){
        SqlSession conn = null;
        try{
            HashMap<String, Object> parametros = new HashMap<String, Object> ();
            parametros.put("nombreCompleto", nombre);
            parametros.put("contrasenia", contrasenia);
            parametros.put("rol", rol);
            parametros.put("fechaIngreso", fechaIngreso);
            
            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Usuario.registrar", parametros);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numerofilasafectadas > 0) {
                return true;
            }
        }catch(IOException ex){
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
    
    public static List<Usuario> getTodas() {
        List<Usuario> categorias = new ArrayList<Usuario>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Usuario.getTodas");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }
}
