/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import modelo.dao.CategoriaDAO;
import modelo.dao.UsuarioDAO;

/**
 *
 * @author Juuan
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(CategoriaDAO.buscarCategoriasRoles().size());
        
    }
    
}
