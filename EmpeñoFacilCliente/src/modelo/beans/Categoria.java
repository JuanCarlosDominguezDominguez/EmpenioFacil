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
    private int Categorias_IdCategoria;
    private String nombreCategoriaPadre;

    public Categoria() {
    }

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nombre=" + nombre + ", Categorias_IdCategoria=" + Categorias_IdCategoria + '}';
    }

    public String getNombreCategoriaPadre() {
        return nombreCategoriaPadre;
    }

    public void setNombreCategoriaPadre(String nombreCategoriaPadre) {
        this.nombreCategoriaPadre = nombreCategoriaPadre;
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

    public int getCategorias_IdCategoria() {
        return Categorias_IdCategoria;
    }

    public void setCategorias_IdCategoria(int Categorias_IdCategoria) {
        this.Categorias_IdCategoria = Categorias_IdCategoria;
    }
    
    
}
