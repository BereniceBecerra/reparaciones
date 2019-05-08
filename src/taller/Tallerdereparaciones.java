/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tallerdereparaciones.Cliente;
import tallerdereparaciones.ClienteData;
import tallerdereparaciones.Conexion;
import tallerdereparaciones.Servicio;
import tallerdereparaciones.ServicioData;
import tallerdereparaciones.Aparato;
import tallerdereparaciones.AparatoData;
import tallerdereparaciones.Reparaciones;
import tallerdereparaciones.ReparacionesData;

/**
 *
 * @author SilZitoS
 */
public class Tallerdereparaciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Conexion conectar = new Conexion();
            Cliente client = new Cliente("Sosa Javier", 30857389, "Pringles 285", "266-4746098");
            ClienteData cd = new ClienteData(conectar);

            //CLIENTE
            
            //GUARDAR CLIENTE
            cd.guardarCliente(client);
            System.out.println("El cliente fue ingresado en la base de datos");
            System.out.println(" ");

            //BORRAR CLIENTE POR ID
            cd.borrarCliente(13);
            System.out.println("El cliente fue eliminado de la base de datos");
            System.out.println(" ");
            
            //ACTUALIZAR CLIENTE
            Cliente c = new Cliente(9, "Abella Raul", 10837467, "Balcarce 1099", "266-4385930");
            cd.actualizarCliente(c);
            System.out.println("El cliente fue actualizado en la base de datos");
            
            //OBTENER CLIENTES
            List<Cliente> lista = new ArrayList<Cliente>();
            lista = cd.obtenerClientes();
            System.out.println("Esta es la lista de clientes: ");
            System.out.println(" ");
            if (!lista.isEmpty()){
            for (Cliente x : lista) {
                System.out.println("Nombre: " + x.getNombreC());
                System.out.println("DNI: " + x.getDni());
                System.out.println("Domicilio: " + x.getDomicilio());
                System.out.println("Celular: " + x.getCelular());
                System.out.println(" ");
            }}else {System.out.println("La lista de clientes está vacía");}

            //BUSCAR CLIENTE POR ID
            client = cd.buscarCliente(17);
            if (client != null ){
            System.out.println("El cliente que está buscando es: ");
            System.out.println(" ");
            System.out.println("Nombre: " + client.getNombreC());
            System.out.println("DNI: " + client.getDni());
            System.out.println("Domicilio: " + client.getDomicilio());
            System.out.println("Celular: " + client.getCelular());
            System.out.println(" ");}else{System.out.println("La persona que está buscando no existe como cliente");}

            
            

            Servicio serv = new Servicio(9847, "Cambio de táctil", 890.59);
            ServicioData sd = new ServicioData(conectar);

            
            //SERVICIO
            
            
            //GUARDAR SERVICIO
            sd.guardarServicio(serv);
            System.out.println("El servicio fue ingresado en la base de datos");

            //BORRAR SERVICIO
            sd.borrarServicio(3);
            System.out.println("El servicio fue eliminado de la base de datos");

            //ACTUALIZAR SERVICIO
            Servicio s = new Servicio(2, 3, "Limpieza", 769.8);
            sd.actualizarServicio(s);
            System.out.println("El servicio fue actualizado en la base de datos");
           
            //OBTENER SERVICIOS
            List<Servicio> lis = sd.obtenerServicios();
            System.out.println("Esta es la lista de servicios: ");
            if (!lis.isEmpty()){
            for (Servicio x : lis) {
                System.out.println("Código del servicio: " + x.getCodigo() + "      Descripción: " + x.getDescripcion() + "     Costo: " + x.getCosto());
            }}else {System.out.println("La lista de servicios está vacía");}
           
            //BUSCAR SERVICIOS POR ID
            serv = sd.buscarServicio(1);
            if (serv != null ){
            System.out.println("El servicio que está buscando es : ");
            System.out.println(" ");
            System.out.println("Código: " + serv.getCodigo());
            System.out.println("Descripción: " + serv.getDescripcion());
            System.out.println("Costo: " + serv.getCosto());
            System.out.println(" ");}else{System.out.println("El servicio que está buscando no existe");}
            
            

            //cd.guardarCliente(client);
            Aparato ap = new Aparato("D-8473576-9", "IPod", client, Date.valueOf(LocalDate.of(2019, 04, 01)), Date.valueOf(LocalDate.of(2019, 04, 19)));
            AparatoData ad = new AparatoData(conectar);

           
           //APARATO
           
           
            //GUARDAR APARATO
            ad.guardarAparato(ap);
            System.out.println("El aparato fue ingresado en la base de datos");
            System.out.println(" ");
           
           //BORRAR APARATO
           ad.borrarAparato(7);
           System.out.println("El aparato fue eliminado de la base de datos");
 
           //ACTUALIZAR APARATO
           Aparato apa = new Aparato(3, "D-29487-0", "Tablet", client, Date.valueOf(LocalDate.of(2019, 03, 23)), Date.valueOf(LocalDate.of(2019, 04, 01)));
           ad.actualizarAparato(apa);
           System.out.println("El aparato fue actualizado en la base de datos");
           
           //OBTENER APARATOS
            System.out.println("Estos son los aparatos: ");
            System.out.println(" ");
            List<Aparato> lta = ad.obtenerAparatos();
            if (!lta.isEmpty()){
            for (Aparato x : lta) {
                System.out.println("Aparato : " + x.getIdAparato() + "      Nro de serie: " + x.getNroSerie());
                System.out.println("Tipo: " + x.getTipo() + "       Dueño: " + x.getDueño());
                System.out.println("Fecha de ingreso: " + x.getFechaIngreso() + "       Fecha de egreso: " + x.getFechaEgreso());
                System.out.println(" ");
            }}else {System.out.println("La lista de aparatos está vacía");}

           //BUSCAR APARATO POR ID
           ap = ad.buscarAparato(8);
           if (ap != null ){
            System.out.println("El aparato que está buscando es : ");
            System.out.println(" ");
            System.out.println("Aparato: " + ap.getIdAparato());
            System.out.println("Nro de Serie: " + ap.getNroSerie());
            System.out.println("Tipo: " + ap.getTipo());
            System.out.println("Dueño: " + ap.getDueño());
            System.out.println("Fecha de ingreso: " + ap.getFechaIngreso());
            System.out.println("Fecha de egreso: " + ap.getFechaEgreso());
            System.out.println(" ");} else{System.out.println("El aparato que está buscando no existe");}

           
          
          Reparaciones rep = new Reparaciones(serv, ap, Date.valueOf(LocalDate.of(2019, 04, 18)), "pendiente");
          ReparacionesData rd = new ReparacionesData(conectar);
          
          //REPARACIONES
          
          //GUARDAR REPARACIONES
          rd.guardarReparaciones(rep);
          System.out.println("La reparación fue ingresada en la base de datos");
          System.out.println(" ");
          
          //BORRAR REPARACIONES
          rd.borrarReparaciones(1);
          System.out.println("La reparación fue eliminada de la base de datos");
          System.out.println(" ");
          
          //ACTUALIZAR REPARACIONES
          Reparaciones repar = new Reparaciones(2, serv, ap, Date.valueOf(LocalDate.of(2019, 04, 17)), "pendiente");
          rd.actualizarReparaciones(repar);
          System.out.println("La reparación fue actualizada en la base de datos");
          
          //OBTENER REPARACIONES
          System.out.println("Estos son las reparaciones: ");
          System.out.println(" ");
            List<Reparaciones> lst = rd.obtenerReparaciones();
            if (!lst.isEmpty()){
              for (Reparaciones x : lst) {
                System.out.println("Reparaciones : " + x.getIdReparaciones() + "  Servicio: " + x.getServicio().getIdServicio());
                System.out.println("Aparato: " + x.getAparato().getIdAparato() + " Fecha de realización: " + x.getFechaRealizacion());
                if (x.getEstado().equals("pendiente")){
                    System.out.println("La reparación está pendiente");
                }else{System.out.println("La reparación está lista");}
              }
            }else {System.out.println("La lista de aparatos está vacía");}
          
          //BUSCAR REPARACIONES POR ID
          rep = rd.buscarReparaciones(2);
          if (rep != null ){
            System.out.println("La reparación que está buscando es : ");
            System.out.println(" ");
            System.out.println("Reparación: " + rep.getIdReparaciones());
            System.out.println("Servicio: " + rep.getServicio().getIdServicio());
            System.out.println("Aparato: " + rep.getAparato().getIdAparato());
            System.out.println("Fecha de realización: " + rep.getFechaRealizacion());
            if (rep.getEstado().equals("pendiente")){
                    System.out.println("La reparación está pendiente");
                }else{System.out.println("La reparación está lista");}
            System.out.println(" ");} else{System.out.println("La reparación que está buscando no existe");}
      
          
        
          //OTROS
          
          //OBTENER APARATOS REPARADOS
          List<Aparato> l = rd.obtenerAparatosReparados();
            System.out.println("Esta es la lista de aparatos reparados: ");
            System.out.println(" ");
            if (!l.isEmpty()){
            for (Aparato x : l) {
                System.out.println("Nro de Serie: " + x.getNroSerie() + "       Tipo: " + x.getTipo() + "       Dueño: " + x.getDueño());
                System.out.println("Fecha Ingreso: " + x.getFechaIngreso() + "      Fecha Egreso: " + x.getFechaEgreso());
                System.out.println(" ");
            }}else {System.out.println("No existen aparatos reparados");}
          
          
          //OBTENER APARATOS PENDIENTES
          List<Aparato> listap = rd.obtenerAparatosPendientes();
          System.out.println("Los aparatos que están pendientes son: ");
          if (!listap.isEmpty()){
            for(Aparato x:listap){
                System.out.println(" ");
                System.out.println("Aparato: " + x.getIdAparato());
                System.out.println("Nro de Serie: " + x.getNroSerie());
                System.out.println("Tipo: " + x.getTipo());
                System.out.println("Dueño: " + x.getDueño());
                System.out.println("Fecha de ingreso: " + x.getFechaIngreso());
                System.out.println("Fecha de egreso: " + x.getFechaEgreso());
            }
          }else {System.out.println("No existen aparatos pendientes");}
          
          //COSTO TOTAL DE LOS APARATOS
          System.out.println(" ");
          System.out.println("El costo total del aparato es: " + rd.obtenerCostoTotalPorAparato(11));
          
          
          //BUSCAR APARATOS POR DUEÑO
          List<Aparato> listapa = rd.buscarAparatosPorDueño(20);
          System.out.println("Los aparatos que está buscando son: ");
          if (!listapa.isEmpty()){
            for(Aparato x:listapa){
                System.out.println(" ");
                System.out.println("Aparato: " + x.getIdAparato());
                System.out.println("Nro de Serie: " + x.getNroSerie());
                System.out.println("Tipo: " + x.getTipo());
                System.out.println("Dueño: " + x.getDueño());
                System.out.println("Fecha de ingreso: " + x.getFechaIngreso());
                System.out.println("Fecha de egreso: " + x.getFechaEgreso());
            }
          }else {System.out.println("No existe aparato por el dueño ingresado");}
          
          //BUSCAR APARATOS POR SERVICIO
          List<Aparato> listapar = rd.buscarAparatosPorServicio(2);
          System.out.println("Los aparatos que está buscando son: ");
          if (!listapar.isEmpty()){
            for(Aparato x:listapar){
                System.out.println(" ");
                System.out.println("Aparato: " + x.getIdAparato());
                System.out.println("Nro de Serie: " + x.getNroSerie());
                System.out.println("Tipo: " + x.getTipo());
                System.out.println("Dueño: " + x.getDueño());
                System.out.println("Fecha de ingreso: " + x.getFechaIngreso());
                System.out.println("Fecha de egreso: " + x.getFechaEgreso());
            }
          }else {System.out.println("No existe aparato por el servicio ingresado");}
          
          //BUSCAR APARATOS POR FECHA DE INGRESO
          List<Aparato> listapara = rd.buscarAparatosPorFechaIngreso(Date.valueOf(LocalDate.of(2019, 04, 14)));
          System.out.println("Los aparatos que está buscando son: ");
          if (!listapara.isEmpty()){
            for(Aparato x:listapara){
                System.out.println(" ");
                System.out.println("Aparato: " + x.getIdAparato());
                System.out.println("Nro de Serie: " + x.getNroSerie());
                System.out.println("Tipo: " + x.getTipo());
                System.out.println("Dueño: " + x.getDueño());
                System.out.println("Fecha de ingreso: " + x.getFechaIngreso());
                System.out.println("Fecha de egreso: " + x.getFechaEgreso());
            }
          }else {System.out.println("No existe aparato por la fecha ingresada");}
         
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tallerdereparaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
