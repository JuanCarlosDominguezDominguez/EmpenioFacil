/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.beans;

import java.sql.Date;

/**
 *
 * @author Juuan
 */
public class Contrato {
    private Integer idContrato;
    private String Cliente_rfc;
    private Integer Usuario_numPersonal;
    private Integer interesOrdinario;
    private Integer interesAlmacen;
    private Date fechaInicio;
    private Date fechaFin;

    public Contrato() {
    }

    public Contrato(Integer idContrato, String Cliente_rfc, Integer Usuario_numPersonal, Integer interesOrdinario, Integer interesAlmacen, Date fechaInicio, Date fechaFin) {
        this.idContrato = idContrato;
        this.Cliente_rfc = Cliente_rfc;
        this.Usuario_numPersonal = Usuario_numPersonal;
        this.interesOrdinario = interesOrdinario;
        this.interesAlmacen = interesAlmacen;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Contrato{" + "idContrato=" + idContrato + ", Cliente_rfc=" + Cliente_rfc + ", Usuario_numPersonal=" + Usuario_numPersonal + ", interesOrdinario=" + interesOrdinario + ", interesAlmacen=" + interesAlmacen + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getCliente_rfc() {
        return Cliente_rfc;
    }

    public void setCliente_rfc(String Cliente_rfc) {
        this.Cliente_rfc = Cliente_rfc;
    }

    public Integer getUsuario_numPersonal() {
        return Usuario_numPersonal;
    }

    public void setUsuario_numPersonal(Integer Usuario_numPersonal) {
        this.Usuario_numPersonal = Usuario_numPersonal;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
}
