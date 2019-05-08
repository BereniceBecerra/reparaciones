/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerdereparaciones;

import java.sql.Date;

/**
 *
 * @author SilZitoS
 */
public class Reparaciones {
    private int idReparaciones;
    private Servicio servicio;
    private Aparato aparato;
    private Date fechaRealizacion;
    private String estado;

    public Reparaciones(int idReparaciones, Servicio servicio, Aparato aparato, Date fechaRealizacion, String estado) {
        this.idReparaciones = idReparaciones;
        this.servicio = servicio;
        this.aparato = aparato;
        this.fechaRealizacion = fechaRealizacion;
        this.estado = estado;
    }

    public Reparaciones(Servicio servicio, Aparato aparato, Date fechaRealizacion, String estado) {
        this.servicio = servicio;
        this.aparato = aparato;
        this.fechaRealizacion = fechaRealizacion;
        this.estado = estado;
    }

    public Reparaciones() {
    }

    public int getIdReparaciones() {
        return idReparaciones;
    }

    public void setIdReparaciones(int idReparaciones) {
        this.idReparaciones = idReparaciones;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Aparato getAparato() {
        return aparato;
    }

    public void setAparato(Aparato aparato) {
        this.aparato = aparato;
    }

    
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
}
