/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.beans;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author YZ
 */
public class VentaApartado {
    
    private Integer idVentaApartado;
    
    private Integer monto;
    
    private Date fecha;
    
    private String rfcCliente;
    
    private String nombreCliente;
    
    private Integer numPersonal;
    
    private Integer idTipoVenta;
    
    public VentaApartado(){
        
    }

    public Integer getIdVentaApartado() {
        return idVentaApartado;
    }

    public void setIdVentaApartado(Integer idVentaApartado) {
        this.idVentaApartado = idVentaApartado;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Date getFecha() {
         Date d = new Date(fecha.getTime()) {
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString() {
                return dateFormatter.format(this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
        };
        return d;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRfcCliente() {
        return rfcCliente;
    }

    public void setRfcCliente(String rfcCliente) {
        this.rfcCliente = rfcCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(Integer numPersonal) {
        this.numPersonal = numPersonal;
    }

    public Integer getIdTipoVenta() {
        return idTipoVenta;
    }

    public void setIdTipoVenta(Integer idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }
    
    
    
}
