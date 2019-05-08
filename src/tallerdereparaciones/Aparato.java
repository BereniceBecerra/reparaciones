/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerdereparaciones;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Aparato {

    private int idAparato=-1;
    private String nroSerie;
    private String tipo;
    private Cliente dueño;
    private Date fechaIngreso;
    private Date fechaEgreso;

    public Aparato(int idAparato, String nroSerie, String tipo, Cliente dueño, Date fechaIngreso, Date fechaEgreso) {
        this.idAparato = idAparato;
        this.nroSerie = nroSerie;
        this.tipo = tipo;
        this.dueño = dueño;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }

    public Aparato(String nroSerie, String tipo, Cliente dueño, Date fechaIngreso, Date fechaEgreso) {
        this.nroSerie = nroSerie;
        this.tipo = tipo;
        this.dueño = dueño;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }

    public Aparato() {
    }

    public int getIdAparato() {
        return idAparato;
    }

    
    public String getNroSerie() {
        return nroSerie;
    }

    public String getTipo() {
        return tipo;
    }

    public Cliente getDueño() {
        return dueño;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setIdAparato(int idAparato) {
        this.idAparato = idAparato;
    }
    
    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDueño(Cliente dueño) {
        this.dueño = dueño;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }


    
@Override
    public String toString(){
    
        return idAparato+"-"+nroSerie;
    }


}
