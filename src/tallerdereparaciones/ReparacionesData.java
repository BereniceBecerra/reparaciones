/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerdereparaciones;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReparacionesData {
    private Connection con;
    private Conexion conexion;

    public ReparacionesData(Conexion conexion) {
        try {
            this.conexion = conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir y al obtener la conexion" + ex.getMessage());
        }
    }
    
    
    public void guardarReparaciones(Reparaciones reparaciones){
        try {
            
            String sql = "INSERT INTO reparaciones (idServicio, idAparato, fechaRealizacion, estado) VALUES ( ?, ? , ? , ? );";

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, reparaciones.getServicio().getIdServicio());
                ps.setInt(2, reparaciones.getAparato().getIdAparato());
                ps.setDate(3, reparaciones.getFechaRealizacion());
                ps.setString(4, reparaciones.getEstado());
                
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                
                if (rs.next()) {
                    reparaciones.setIdReparaciones(rs.getInt(1));
                } else {
                    System.out.println("No se pudo obtener el id luego de insertar una reparación");
                }
            }
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una reparación: " + ex.getMessage());
        }
    }
    
    public void borrarReparaciones(int id){
    try {
            
            String sql = "DELETE FROM reparaciones WHERE idReparaciones =?;";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
                      
            ps.executeUpdate();
        }
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar una reparación: " + ex.getMessage());
        }
        
    
    }
    
    public void actualizarReparaciones(Reparaciones reparaciones){
    
        try {
            
            String sql = "UPDATE reparaciones SET idServicio = ?, idAparato = ?, fechaRealizacion = ?, estado = ? WHERE idReparaciones = ?;";

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, reparaciones.getServicio().getIdServicio());
                ps.setInt(2, reparaciones.getAparato().getIdAparato());
                ps.setDate(3, reparaciones.getFechaRealizacion());
                ps.setString(4, reparaciones.getEstado());
                ps.setInt(5, reparaciones.getIdReparaciones());
                ps.executeUpdate();
            }
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar una reparación: " + ex.getMessage());
        }
    
    }
    
    public List<Reparaciones> obtenerReparaciones(){
        List<Reparaciones> reparaciones = new ArrayList<Reparaciones>();
        try {
            String sql = "SELECT * FROM reparaciones;";
            PreparedStatement ps = con.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery();
                Reparaciones reparacion;
                
                while(resultSet.next()){
                    reparacion = new Reparaciones();
                    reparacion.setIdReparaciones(resultSet.getInt("idReparaciones"));
                    Servicio s=buscarServicio(resultSet.getInt("idServicio"));
                    reparacion.setServicio(s);
                    Aparato a=buscarAparato(resultSet.getInt("idAparato"));
                    reparacion.setAparato(a);
                    reparacion.setFechaRealizacion(resultSet.getDate("fechaRealizacion"));
                    reparacion.setEstado(resultSet.getString("estado"));
                    
                    reparaciones.add(reparacion);
                }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las reparaciones: " + ex.getMessage()); 
        }
        return reparaciones;
    }
    
    
    public Servicio buscarServicio(int id){
        ServicioData sd=new ServicioData(conexion);
        return sd.buscarServicio(id);
    }
    
    public Aparato buscarAparato(int id){
        AparatoData ad=new AparatoData(conexion);
        return ad.buscarAparato(id);
    }
    
    public Cliente buscarCliente(int id){
        ClienteData cd=new ClienteData(conexion);
        return cd.buscarCliente(id);
    }

    public Reparaciones buscarReparaciones(int id){
    Reparaciones reparaciones=null;
    try {
            
            String sql = "SELECT * FROM reparaciones WHERE idReparaciones =?;";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
   
            ResultSet resultSet=ps.executeQuery();
            
            while(resultSet.next()){
                reparaciones = new Reparaciones();
                reparaciones.setIdReparaciones(resultSet.getInt("idReparaciones"));
                Servicio s=buscarServicio(resultSet.getInt("idServicio"));
                reparaciones.setServicio(s);
                Aparato a=buscarAparato(resultSet.getInt("idAparato"));
                reparaciones.setAparato(a);
                reparaciones.setFechaRealizacion(resultSet.getDate("fechaRealizacion"));
                reparaciones.setEstado(resultSet.getString("estado"));
                
                
            }
        }

        } catch (SQLException ex) {
            System.out.println("Error al buscar una reparación: " + ex.getMessage());
        }
        
        return reparaciones;
    }
    


    public List<Aparato> obtenerAparatosReparados(){
        List<Aparato> aparatos = new ArrayList<Aparato>();
        List<Integer> idap = new ArrayList<Integer>();
    try {
            //Busca los id de los aparatos pendientes de la base de datos
            String sql = "SELECT * FROM reparaciones;";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet resultSet=ps.executeQuery();
            while(resultSet.next()){
               if(resultSet.getString("estado").equals("pendiente")){
                    idap.add(resultSet.getInt("idAparato"));
                    
               }  
            }
            ps.close();
            //Busca los aparatos
            String sqla = "SELECT * FROM aparato;";

            PreparedStatement psa = con.prepareStatement(sqla);

            ResultSet resultSeta=psa.executeQuery();
            Aparato aparato;
            AparatoData ad;
            
            if (idap.isEmpty()){
                idap.add(0);
            }
            //Compara los id de los aparatos pendientes con los reparados y guarda los reparados
            while(resultSeta.next()){
             int a=0;
             for (Integer i:idap){
               if (resultSeta.getInt("idAparato")!=i){
                a+=1;
               }
             }
             if (a==idap.size()){
                aparato = new Aparato();
                ad = new AparatoData(conexion);
                aparato.setIdAparato(resultSeta.getInt("idAparato"));
                aparato.setNroSerie(resultSeta.getString("nroSerie"));
                aparato.setTipo(resultSeta.getString("tipo"));
                Cliente dueño=ad.buscarCliente(resultSeta.getInt("dueño"));
                aparato.setDueño(dueño);
                aparato.setFechaIngreso(resultSeta.getDate("fechaIngreso"));
                aparato.setFechaEgreso(resultSeta.getDate("fechaEgreso"));
                aparatos.add(aparato);
               }
             }      
            psa.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar aparatos reparados: " + ex.getMessage());
        }
        
        return aparatos;
        
    }
    
    public List<Aparato> obtenerAparatosPendientes(){
        List<Integer> idap = new ArrayList<Integer>();
        List<Aparato> aparatos = new ArrayList<Aparato>();
        
    try {
            //Busca los id de los aparatos pendientes de la base de datos
            String sql = "SELECT * FROM reparaciones;";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet resultSet=ps.executeQuery();
            while(resultSet.next()){
               if(resultSet.getString("estado").equals("pendiente")){
                    idap.add(resultSet.getInt("idAparato"));
                    
               }   
            }  
            ps.close();
            //Según los id, busca los aparatos pendientes
            AparatoData ad = new AparatoData(conexion);
            int indice=0;
            for(Integer i:idap){
                aparatos.add(indice, buscarAparato(i));
                indice += 1;
            }
   
        } catch (SQLException ex) {
            System.out.println("Error al buscar los aparatos pendientes: " + ex.getMessage());
        }
        
        return aparatos;
    }
    
    
public double obtenerCostoTotalPorAparato(int id){
    double costoTotal = 0;
        try {
            String sql = "SELECT idServicio FROM reparaciones WHERE idAparato = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            
            List<Servicio> s = new ArrayList<>();
            while(resultSet.next()){
                 s.add(buscarServicio(resultSet.getInt("idServicio")));
            }
            for (Servicio x:s){
                costoTotal += x.getCosto();
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener el costo total del aparato: " + ex.getMessage());
        }
        return costoTotal;
    }

 public List<Aparato> buscarAparatosPorFechaIngreso(Date fecha){
        List<Aparato> aparatos = new ArrayList<Aparato>();
        
    try {
            
            String sql = "SELECT * FROM aparato WHERE fechaIngreso =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) fecha);

            ResultSet resultSet=ps.executeQuery();
            Aparato aparato;
            while(resultSet.next()){
                aparato = new Aparato();
                aparato.setIdAparato(resultSet.getInt("idAparato"));
                aparato.setNroSerie(resultSet.getString("nroSerie"));
                aparato.setTipo(resultSet.getString("tipo"));
                Cliente dueño=buscarCliente(resultSet.getInt("dueño"));
                aparato.setDueño(dueño);
                aparato.setFechaIngreso(resultSet.getDate("fechaIngreso"));
                aparato.setFechaEgreso(resultSet.getDate("fechaEgreso"));
                aparatos.add(aparato);
                
            }      
           ps.close(); 
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un aparato: " + ex.getMessage());
        }
        
        return aparatos;
    }
    
    public List<Aparato> buscarAparatosPorDueño(int id){
        List<Aparato> aparatos = new ArrayList<Aparato>();
        
    try {
            
            String sql = "SELECT * FROM aparato WHERE dueño =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet=ps.executeQuery();
            Aparato aparato;
            while(resultSet.next()){
                aparato = new Aparato();
                aparato.setIdAparato(resultSet.getInt("idAparato"));
                aparato.setNroSerie(resultSet.getString("nroSerie"));
                aparato.setTipo(resultSet.getString("tipo"));
                Cliente dueño=buscarCliente(resultSet.getInt("dueño"));
                aparato.setDueño(dueño);
                aparato.setFechaIngreso(resultSet.getDate("fechaIngreso"));
                aparato.setFechaEgreso(resultSet.getDate("fechaEgreso"));
                aparatos.add(aparato);
                
            }      
            ps.close();
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un aparato: " + ex.getMessage());
        }
        
        return aparatos;
    }
    
        public List<Aparato> buscarAparatosPorServicio(int id){
        List<Reparaciones> reparac = new ArrayList<Reparaciones>();
        List<Aparato> aparatos =new ArrayList<Aparato>();
        
    try {
            
            String sql = "SELECT * FROM reparaciones WHERE idServicio =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet=ps.executeQuery();
            Reparaciones reparaciones;
            while(resultSet.next()){
                reparaciones = new Reparaciones();
                reparaciones.setIdReparaciones(resultSet.getInt("idReparaciones"));
                Servicio s=buscarServicio(resultSet.getInt("idServicio"));
                reparaciones.setServicio(s);
                Aparato a=buscarAparato(resultSet.getInt("idAparato"));
                reparaciones.setAparato(a);
                reparaciones.setFechaRealizacion(resultSet.getDate("fechaRealizacion"));
                reparaciones.setEstado(resultSet.getString("estado"));
                reparac.add(reparaciones);
            }
            List<Integer> i=new ArrayList<Integer>();
            for(Reparaciones x:reparac){
                if (x.getServicio().getIdServicio()== id){
                        i.add(x.getAparato().getIdAparato()); 
                }
            }
            
            int indice = 0;
            for(Integer x:i){
            aparatos.add(indice, buscarAparato(x));
            indice += 1;
            }
                
                  
            ps.close();
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un aparato: " + ex.getMessage());
        }
        
        return aparatos;
    }
        
    
        
    public void borrarReparacionDeUnAparato(int idServicio,int idAparato){
    
        try {
            
            String sql = "DELETE FROM reparaciones WHERE idServicio =? and idAparato =?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idServicio);
            ps.setInt(2, idAparato); 
           
            
            ps.executeUpdate();
            
            
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al al borrar una reparación: " + ex.getMessage());
        }
    }
        
        public List<Aparato> buscarAparatosPorServicioSegunListaDeAparatosPendientes(int id, List<Aparato> la){
        List<Reparaciones> reparac = new ArrayList<Reparaciones>();
        List<Aparato> aparatos =new ArrayList<Aparato>();
        
    try {
            
            String sql = "SELECT * FROM reparaciones WHERE idServicio =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet=ps.executeQuery();
            Reparaciones reparaciones;
            while(resultSet.next()){
                reparaciones = new Reparaciones();
                reparaciones.setIdReparaciones(resultSet.getInt("idReparaciones"));
                Servicio s=buscarServicio(resultSet.getInt("idServicio"));
                reparaciones.setServicio(s);
                Aparato a=buscarAparato(resultSet.getInt("idAparato"));
                reparaciones.setAparato(a);
                reparaciones.setFechaRealizacion(resultSet.getDate("fechaRealizacion"));
                reparaciones.setEstado(resultSet.getString("estado"));
                reparac.add(reparaciones);
            }
            List<Integer> i=new ArrayList<Integer>();
            for(Reparaciones x:reparac){
                if (x.getServicio().getIdServicio()== id){
                        i.add(x.getAparato().getIdAparato());
                }
            }
            int c=0;
            for (int indice=0; indice<la.size(); indice++){
                for(Integer x:i){
                    if (x==la.get(indice).getIdAparato()){
                        aparatos.add(c, la.get(indice));
                        c++;
                    }
                }
            }
                
                  
            ps.close();
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un aparato: " + ex.getMessage());
        }
        
        return aparatos;
    }
}