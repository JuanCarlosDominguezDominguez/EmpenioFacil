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
public class Articulo {
    
    private Integer idArticulo;
    private Integer idPrenda;
    private Integer idCategoria;
    private String nombreCategoria;
    private String descripcion;
    private Integer precio;
    private Integer idComercializacion;
    private Integer idTipoProducto;
    private String nombreTipoProducto;
    private Boolean deBaja;
    private Boolean vendido;

    public Articulo() {
    }

    @Override
    public String toString() {
        return "Articulo{" + "idArticulo=" + idArticulo + ", prenda_idPrenda=" + idPrenda + ", idCategoria=" + idCategoria + ", nombreCategoria=" + nombreCategoria + ", descripcion=" + descripcion + ", precio=" + precio + ", comercializacion_idComercializacion=" + idComercializacion + ", idTipoProducto=" + idTipoProducto + ", nombreTipoProducto=" + nombreTipoProducto + ", deBaja=" + deBaja + ", vendido=" + vendido + '}';
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(Integer idPrenda) {
        this.idPrenda = idPrenda;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getIdComercializacion() {
        return idComercializacion;
    }

    public void setIdComercializacion(Integer idComercializacion) {
        this.idComercializacion = idComercializacion;
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }

    public Boolean getDeBaja() {
        return deBaja;
    }

    public void setDeBaja(Boolean deBaja) {
        this.deBaja = deBaja;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }
    
    
            
}
