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
public class TipoProducto extends Categoria{
    public TipoProducto() {
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
