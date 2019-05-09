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
import modelo.beans.Usuario;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juuan
 */
public class UsuarioDAO {

    public static boolean registrarUsuario(String nombre, String contrasenia, String rol) {
        SqlSession conn = null;
        try {
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nombreCompleto", nombre);
            parametros.put("contrasenia", contrasenia);
            parametros.put("rol", rol);

            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Usuario.registrar", parametros);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numerofilasafectadas > 0) {
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public static boolean eliminarUsuario(int numPersonal) {
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Usuario.eliminar", numPersonal);
            conn.commit();
            if (numerofilasafectadas > 0) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn == null) {
                conn.close();
            }
        }
        return false;
    }

    public static boolean actualizarUsuario(String nombreCompleto, String contrasenia, String rol, Integer numPersonal) {
        SqlSession conn = null;
        try {
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nombreCompleto", nombreCompleto);
            parametros.put("contrasenia", contrasenia);
            parametros.put("rol", rol);
            parametros.put("numPersonal", numPersonal);

            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Usuario.actualizar", parametros);
            conn.commit();//SIEMPRE QUE SE EJECUTEN INSERT, UPDATE, DELETE
            if (numerofilasafectadas > 0) {
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public static List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            usuarios = conn.selectList("Usuario.obtenerUsuarios");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return usuarios;
    }

    public static int obtenerUsuarioParaLogin(String numPersonal, String contrasenia) {
        int existe = 0;
        SqlSession conn = null;
        try {
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("numPersonal", numPersonal);
            parametros.put("contrasenia", contrasenia);
            conn = ConexionDB.getSession();
            existe = conn.selectOne("Usuario.obtenerUsuarioParaLogin", parametros);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn == null) {
                conn.close();
            }
        }
        return existe;
    }

    public static Usuario obtenerUsuarioPorNumeroDePersonal(String numPersonal) {
        Usuario usuario = new Usuario();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            usuario = conn.selectOne("Usuario.obtenerUsuarioPorNumeroDePersonal", numPersonal);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn == null) {
                conn.close();
            }
        }
        return usuario;
    }
}
