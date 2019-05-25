/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.beans;

/**
 *
 * @author YZ
 */
public class Periodo extends Categoria{
    
    public Periodo(){
        
    }
    
    @Override
    public String toString() {
        return this.getNombreCategoria();
    }
}
