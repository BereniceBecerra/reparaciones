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
public class AparatoData {
    private Connection connection = null;
    private Conexion conexion;

    public AparatoData(Conexion conexion) {
        try {
            this.conexion=conexion;
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir y al obtener la conexion" + ex.getMessage());
        }
    }
    
    
    public void guardarAparato(Aparato aparato){
        try {
            
            String sql = "INSERT INTO aparato (nroSerie, tipo, dueño, fechaIngreso, fechaEgreso) VALUES ( ? , ? , ? , ? , ? );";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, aparato.getNroSerie());
            statement.setString(2, aparato.getTipo());
            statement.setInt(3, aparato.getDueño().getIdCliente());
            statement.setDate(4, (java.sql.Date) aparato.getFechaIngreso());
            statement.setDate(5, (java.sql.Date) aparato.getFechaEgreso());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                aparato.setIdAparato(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar un aparato");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un aparato: " + ex.getMessage());
        }
    }
    
    public void borrarAparato (int id){
        
    try {
            String sql = "DELETE FROM aparato WHERE idAparato =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);

            statement.executeUpdate();
    
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar un aparato: " + ex.getMessage());
        }
  
    }

    public void actualizarAparato(Aparato aparato){
    try {
            
            String sql = "UPDATE aparato SET nroSerie = ?,  tipo = ?, dueño = ?, fechaIngreso = ?, fechaEgreso = ? WHERE idAparato = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, aparato.getNroSerie());
            statement.setString(2, aparato.getTipo());
            statement.setInt(3, aparato.getDueño().getIdCliente());
            statement.setDate(4, (java.sql.Date) aparato.getFechaIngreso());
            statement.setDate(5, (java.sql.Date) aparato.getFechaEgreso());
            statement.setInt(6, aparato.getIdAparato());
            
            statement.executeUpdate();

            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar un aparato: " + ex.getMessage());
        }
   
    }
    
    public List<Aparato> obtenerAparatos(){
        List<Aparato> aparatos = new ArrayList<Aparato>();

        try {
            String sql = "SELECT * FROM aparato;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Aparato aparato;
            while(resultSet.next()){
                aparato = new Aparato();
                aparato.setIdAparato(resultSet.getInt("idAparato"));
                aparato.setNroSerie(resultSet.getString("nroSerie"));
                aparato.setTipo(resultSet.getString("tipo"));
                Cliente d=buscarCliente(resultSet.getInt("dueño"));
                aparato.setDueño(d);
                aparato.setFechaIngreso(resultSet.getDate("fechaIngreso"));
                aparato.setFechaEgreso(resultSet.getDate("fechaEgreso"));

                aparatos.add(aparato);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los aparatos: " + ex.getMessage());
        }

        return aparatos;
    }
    
     public Cliente buscarCliente(int id){
        ClienteData cd=new ClienteData(conexion);
        return cd.buscarCliente(id);
    }
    
    public Aparato buscarAparato(int id){
    
        Aparato aparato=null;
    try {
            
            String sql = "SELECT * FROM aparato WHERE idAparato =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
 
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                aparato = new Aparato();
                aparato.setIdAparato(resultSet.getInt("idAparato"));
                aparato.setNroSerie(resultSet.getString("nroSerie"));
                aparato.setTipo(resultSet.getString("tipo"));
                Cliente dueño=buscarCliente(resultSet.getInt("dueño"));
                aparato.setDueño(dueño);
                aparato.setFechaIngreso(resultSet.getDate("fechaIngreso"));
                aparato.setFechaEgreso(resultSet.getDate("fechaEgreso"));
     
            }      
            statement.close(); 
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un aparato: " + ex.getMessage());
        }
        
        return aparato;
    }
    
    public Aparato buscarAparatoPorTipo(String tipo){
    
        Aparato aparato=null;
    try {
            
            String sql = "SELECT * FROM aparato WHERE tipo =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tipo);
 
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                aparato = new Aparato();
                aparato.setIdAparato(resultSet.getInt("idAparato"));
                aparato.setNroSerie(resultSet.getString("nroSerie"));
                aparato.setTipo(resultSet.getString("tipo"));
                Cliente dueño=buscarCliente(resultSet.getInt("dueño"));
                aparato.setDueño(dueño);
                aparato.setFechaIngreso(resultSet.getDate("fechaIngreso"));
                aparato.setFechaEgreso(resultSet.getDate("fechaEgreso"));
     
            }      
            statement.close(); 
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un aparato: " + ex.getMessage());
        }
        
        return aparato;
    }

}