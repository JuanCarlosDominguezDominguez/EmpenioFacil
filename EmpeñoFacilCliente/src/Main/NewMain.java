/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.CategoriaDAO;
import modelo.dao.UsuarioDAO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author Juuan
 */
public class NewMain {
    public static void main(String[] args) {
        System.out.println(CategoriaDAO.buscarCategoriasRoles().size());
}

}
