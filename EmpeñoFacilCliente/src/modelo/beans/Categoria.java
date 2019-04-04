/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.beans;

/**
 *
 * @author Juuan
 */
public class Categoria {
    private int idCategoria;
    private String nombre;
    private int categoriaIdCategoria;

    public Categoria() {
    }

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nombre=" + nombre + ", categoriaIdCategoria=" + categoriaIdCategoria + '}';
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoriaIdCategoria() {
        return categoriaIdCategoria;
    }

    public void setCategoriaIdCategoria(int categoriaIdCategoria) {
        this.categoriaIdCategoria = categoriaIdCategoria;
    }
    
    
}
