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
public class Pago {
    private Integer idPago;
    private Date fechaInicio;
    private Date fechaLimite;
    private Integer contrato_idContrato;
    private Integer pago;
    private Integer refrendo;
    private Integer finiquito;
    
    private Integer prestamo;

    public Pago() {
    }

    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", fechaInicio=" + fechaInicio + ", fechaLimite=" + fechaLimite + ", contrato_idContrato=" + contrato_idContrato + ", pago=" + pago + ", refrendo=" + refrendo + ", finiquito=" + finiquito + ", prestamo=" + prestamo + '}';
    }
    
    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Integer getContrato_idContrato() {
        return contrato_idContrato;
    }

    public void setContrato_idContrato(Integer contrato_idContrato) {
        this.contrato_idContrato = contrato_idContrato;
    }

    public Integer getPago() {
        return pago;
    }

    public void setPago(Integer pago) {
        this.pago = pago;
    }

    public Integer getRefrendo() {
        return refrendo;
    }

    public void setRefrendo(Integer refrendo) {
        this.refrendo = refrendo;
    }

    public Integer getFiniquito() {
        return finiquito;
    }

    public void setFiniquito(Integer finiquito) {
        this.finiquito = finiquito;
    }

    public Integer getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Integer prestamo) {
        this.prestamo = prestamo;
    }
    
    
}
