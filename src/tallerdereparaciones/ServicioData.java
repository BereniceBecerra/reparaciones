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
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ServicioData {
     private Connection connection = null;

    public ServicioData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir y al obtener la conexion");
        }
    }
    
    
    public void guardarServicio(Servicio servicio){
     
        try {
            
            String sql = "INSERT INTO servicio (codigo, descripcion, costo) VALUES ( ? , ? , ? );";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, servicio.getCodigo());
            statement.setString(2, servicio.getDescripcion());
            statement.setDouble(3, servicio.getCosto());

            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
     
            if (rs.next()) {
                servicio.setIdServicio(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar un servicio");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un servicio: " + ex.getMessage());
        }
    }
    
    public void borrarServicio(int id){
    try {
            
            String sql = "DELETE FROM servicio WHERE idServicio =?;";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
                      
            statement.executeUpdate();
        }
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar un servicio: " + ex.getMessage());
        }
        
    
    }
    
    public void actualizarServicio(Servicio servicio){
    try {
            
            String sql = "UPDATE servicio SET codigo = ?, descripcion = ?, costo = ? WHERE idServicio = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, servicio.getCodigo());
            statement.setString(2, servicio.getDescripcion());
            statement.setDouble(3, servicio.getCosto());
            statement.setInt(4, servicio.getIdServicio());
            statement.executeUpdate();

            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar una servicio: " + ex.getMessage());
        }

    }
    
    public List<Servicio> obtenerServicios(){
        List<Servicio> servicios = new ArrayList<Servicio>();
     
        try {
            String sql = "SELECT * FROM servicio;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Servicio servicio;
            while(resultSet.next()){
                servicio = new Servicio();
                servicio.setIdServicio(resultSet.getInt("idServicio"));
                servicio.setCodigo(resultSet.getInt("codigo"));
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.setCosto(resultSet.getDouble("costo"));

                servicios.add(servicio);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los servicios: " + ex.getMessage());
        }
        
        
        return servicios;
    }
    
            
    public Servicio buscarServicio(int id){
    Servicio servicio=null;
    try {
            
            String sql = "SELECT * FROM servicio WHERE idServicio =?;";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);

            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                servicio = new Servicio();
                servicio.setIdServicio(resultSet.getInt("idServicio"));
                servicio.setCodigo(resultSet.getInt("codigo"));
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.setCosto(resultSet.getDouble("costo"));
                
            }
        }
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un servicio: " + ex.getMessage());
        }
        
        return servicio;
    }
    
    public Servicio buscarServicioPorDescripcion(String descripcion){
    Servicio servicio=null;
    try {
            
            String sql = "SELECT * FROM servicio WHERE descripcion =?;";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, descripcion);

            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                servicio = new Servicio();
                servicio.setIdServicio(resultSet.getInt("idServicio"));
                servicio.setCodigo(resultSet.getInt("codigo"));
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.setCosto(resultSet.getDouble("costo"));
                
            }
        }
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un servicio: " + ex.getMessage());
        }
        
        return servicio;
    }
    
}
