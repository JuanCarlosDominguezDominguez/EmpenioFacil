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
    private String rol;
    private Date fehcaIngreso;

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" + "numPersonal=" + numPersonal + ", nombreCompleto=" + nombreCompleto + ", contrasenia=" + contrasenia + ", rol=" + rol + ", fehcaIngreso=" + fehcaIngreso + '}';
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

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFehcaIngreso() {
        return fehcaIngreso;
    }

    public void setFehcaIngreso(Date fehcaIngreso) {
        this.fehcaIngreso = fehcaIngreso;
    }
    
    
}
