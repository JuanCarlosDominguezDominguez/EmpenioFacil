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
public class Usuario {
    private int numPersonal;
    private String nombreCompleto;
    private String contrasenia;
    private String idRol;
    private String nombreRol;
    private Date fechaIngreso;

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" + "numPersonal=" + numPersonal + ", nombreCompleto=" + nombreCompleto + ", contrasenia=" + contrasenia + ", idRol=" + idRol + ", nombreRol=" + nombreRol+", fehcaIngreso=" + fechaIngreso + '}';
    }

    public int getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(int numPersonal) {
        this.numPersonal = numPersonal;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    
}
