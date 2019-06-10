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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.beans.Categoria;
import modelo.dataBase.ConexionDB;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juuan
 */
public class CategoriaDAO {

    public static boolean registrarCategoriaPrincipal(String nombre) {
        SqlSession conn = null;
        try {
            //LLAVE   VALOR
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nombre", nombre);

            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Categoria.registrarPrincipal", parametros);
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

    public static boolean registrarSubCategoria(Integer Categorias_idCategoria, String nombre) {
        SqlSession conn = null;
        try {
            //LLAVE   VALOR
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nombre", nombre);
            parametros.put("Categorias_idCategoria", Categorias_idCategoria);

            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Categoria.registrarSubCategoria", parametros);
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

    public static boolean eliminarCategoria(int idCategoria) {
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Categoria.eliminar", idCategoria);
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

    public static boolean actualizarCategoriaPrincipal(Integer idCategoria, String nombre) {
        SqlSession conn = null;
        try {
            //LLAVE   VALOR
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nombre", nombre);
            parametros.put("idCategoria", idCategoria);

            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Categoria.actualizarPrincipal", parametros);
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

    public static boolean actualizarSubCategoria(Integer idCategoria, String nombre, Integer Categorias_idCategoria) {
        SqlSession conn = null;
        try {
            //LLAVE   VALOR
            HashMap<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nombre", nombre);
            parametros.put("idCategoria", idCategoria);
            parametros.put("Categorias_idCategoria", Categorias_idCategoria);

            conn = ConexionDB.getSession();
            int numerofilasafectadas = 0;
            numerofilasafectadas = conn.insert("Categoria.actualizarSubCategoria", parametros);
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

    //BUSCAR TODAS LAS CATEGORIAS 
    public static List<Categoria> obtenerTodasLasCategorias() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerTodasLasCategorias");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    //BUSCAR CATEGORIAS POR ROL
    public static List<Categoria> obtenerTodosLosRoles() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerTodosLosRoles");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    public static Categoria obtenerCategoriaPorID(String idCategoria) {
        Categoria categoria = new Categoria();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categoria = conn.selectOne("Categoria.obtenerCategoriaPorID", idCategoria);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categoria;
    }

    public static Categoria obtenerRolPorNombre(String nombre) {
        Categoria categoria = new Categoria();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categoria = conn.selectOne("Categoria.obtenerRolPorNombre", nombre);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categoria;
    }

    //BUSCAR CATEGORIAS POR PRENDAS
    public static List<Categoria> obtenerCategoriasPrincipalesPrendas() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerCategoriasPrincipalesPrendas");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    public static List<Categoria> obtenerCategoriasPrendas() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerCategoriasPrendas");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    public static List<Categoria> obtenerSubCategoriasPrendas() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerSubCategoriasPrendas");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    public static List<Categoria> buscarTodasLasCategoriasDePrendas() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.buscarTodasLasCategoriasDePrendas");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    //BUSCAR CATEGORIAS DE VENTA O APARTADO
    public static List<Categoria> obtenerCategoriasVentaApartado() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerCategoriasVentaApartado");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    //BUSCAR CATEGORIAS DE PERIODOS
    public static List<Categoria> obtenerCategoriasPeriodos() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerCategoriasPeriodos");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    //BUSCAR CATEGORIAS DE OCUPACIONES
    public static List<Categoria> obtenerCategoriasOcupacion() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerCategoriasOcupacion");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    //BUSCAR CATEGORIA DE FORMA GENERAL
    public static List<Categoria> busquedaGenerica(HashMap<String, String> filtros) {
        List<Categoria> categorias = new ArrayList<>();
        SqlSession conn = null;
        String criterios = "";
        try {
            if (filtros != null) {
                for (Map.Entry<String, String> entry : filtros.entrySet()) {
                    String campo = entry.getKey();
                    String condicion = entry.getValue();
                    if (!criterios.isEmpty()) {
                        criterios += "AND ";
                    }
                    criterios += String.format("%s %s ", campo, condicion);
                }
                conn = ConexionDB.getSession();
                categorias = conn.selectList("Categoria.busquedaGenerica", criterios);
            }
        } catch (IOException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn == null) {
                conn.close();
            }
        }
        return categorias;
    }

    public static Categoria obtenerCategoriaNombre(String nombre) {
        Categoria categoria = new Categoria();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categoria = conn.selectOne("Categoria.obtenerCategoriaNombre", nombre);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categoria;
    }

    public static List<Categoria> obtenerCategoriasPrincipales() {
        List<Categoria> categorias = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerCategoriasPrincipales");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return categorias;
    }

    public static List<Categoria> obtenerNombresCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = ConexionDB.getSession();
            categorias = conn.selectList("Categoria.obtenerNombresCategorias");
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
