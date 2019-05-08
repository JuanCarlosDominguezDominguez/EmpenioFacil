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
public class ParametrosSucursal {
    
    private Integer idSucursal;
    private Integer fondo;
    private Integer interesOrdinario;
    private Integer interesAlmacen;
    private Integer idTipoPeriodo;
    private String tipoPeriodo;

    public ParametrosSucursal() {
    }

    @Override
    public String toString() {
        return "ParametrosSucursal{" + "idSucursal=" + idSucursal + ", fondo=" + fondo + ", interesOrdinario=" + interesOrdinario + ", interesAlmacen=" + interesAlmacen + ", idTipoPeriodo=" + idTipoPeriodo + ", tipoPeriodo=" + tipoPeriodo + '}';
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Integer getFondo() {
        return fondo;
    }

    public void setFondo(Integer fondo) {
        this.fondo = fondo;
    }

    public Integer getInteresOrdinario() {
        return interesOrdinario;
    }

    public void setInteresOrdinario(Integer interesOrdinario) {
        this.interesOrdinario = interesOrdinario;
    }

    public Integer getInteresAlmacen() {
        return interesAlmacen;
    }

    public void setInteresAlmacen(Integer interesAlmacen) {
        this.interesAlmacen = interesAlmacen;
    }

    public Integer getIdTipoPeriodo() {
        return idTipoPeriodo;
    }

    public void setIdTipoPeriodo(Integer idTipoPeriodo) {
        this.idTipoPeriodo = idTipoPeriodo;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    
    
    
    
}
