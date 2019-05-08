/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerdereparaciones;



public class Cliente {
    private int idCliente = -1;
    private String nombreC;
    private int dni;
    private String domicilio;
    private String celular;

    
    public Cliente(int idCliente, String nombreC, int dni, String domicilio, String celular) {
        this.idCliente = idCliente;
        this.nombreC = nombreC;
        this.dni = dni;
        this.domicilio = domicilio;
        this.celular = celular;
    }

    public Cliente(String nombreC, int dni, String domicilio, String celular) {
        this.nombreC = nombreC;
        this.dni = dni;
        this.domicilio = domicilio;
        this.celular = celular;
    }

    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombreC() {
        return nombreC;
    }

    public int getDni() {
        return dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getCelular() {
        return celular;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String toString(){
        return idCliente+"-"+nombreC;
    }
}
