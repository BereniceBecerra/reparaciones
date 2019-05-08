/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerdereparaciones;



public class Servicio {
    private int idServicio=-1;
    private int codigo;
    private String descripcion;
    private double costo;
   

    public Servicio(int idServicio, int codigo, String descripcion, double costo) {
        this.idServicio = idServicio;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.costo = costo;
        
    }

    public Servicio(int codigo, String descripcion, double costo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public Servicio() {
    }

    public int getIdServicio() {
        return idServicio;
    }

    

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCosto() {
        return costo;
    }

    
    

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }


    

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

        
}
