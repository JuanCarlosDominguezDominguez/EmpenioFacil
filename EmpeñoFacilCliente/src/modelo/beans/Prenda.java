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
public class Prenda {
    
    private String descripcion;
    private Integer avaluo;
    private Integer prestamo;
    private Integer idPrenda;
    private Integer idContrato;
    private Integer categoria;
    private String nombreCategoria;

    public Prenda() {
    }

    public Prenda(String descripcion, Integer avaluo, Integer prestamo, Integer idPrenda, Integer idContrato, Integer categoria, String nombreCategoria) {
        this.descripcion = descripcion;
        this.avaluo = avaluo;
        this.prestamo = prestamo;
        this.idPrenda = idPrenda;
        this.idContrato = idContrato;
        this.categoria = categoria;
        this.nombreCategoria = nombreCategoria;
    }
    
    public Prenda(String descripcion, Integer avaluo, Integer prestamo, Integer idContrato, Integer categoria) {
        this.descripcion = descripcion;
        this.avaluo = avaluo;
        this.prestamo = prestamo;
        this.idContrato = idContrato;
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getAvaluo() {
        return avaluo;
    }

    public void setAvaluo(Integer avaluo) {
        this.avaluo = avaluo;
    }

    public Integer getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Integer prestamo) {
        this.prestamo = prestamo;
    }

    public Integer getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(Integer idPrenda) {
        this.idPrenda = idPrenda;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return "Prenda{" + "descripcion=" + descripcion + ", avaluo=" + avaluo + ", prestamo=" + prestamo + ", idPrenda=" + idPrenda + ", idContrato=" + idContrato + ", categoria=" + categoria + ", nombreCategoria=" + nombreCategoria + '}';
    }
    
}
