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


public class ClienteData {
    private Connection con;

    public ClienteData(Conexion conexion) {
        try {
            con = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir y al obtener la conexion" + ex.getMessage());
        }
    }
    
    
    public void guardarCliente(Cliente cliente){
        try {
            
            String sql = "INSERT INTO cliente (nombreC, dni, domicilio, celular) VALUES ( ? , ? , ? , ? );";

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cliente.getNombreC());
                ps.setInt(2, cliente.getDni());
                ps.setString(3, cliente.getDomicilio());
                ps.setString(4, cliente.getCelular());
                
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                } else {
                    System.out.println("No se pudo obtener el id luego de insertar un cliente");
                }
            }
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un cliente: " + ex.getMessage());
        }
    }
    
    public void borrarCliente(int id){
    try {
            
            String sql = "DELETE FROM cliente WHERE idCliente =?;";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
                      
            ps.executeUpdate();
        }
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar un cliente: " + ex.getMessage());
        }
    
    }
    
    public void actualizarCliente(Cliente cliente){
    
        try {
            
            String sql = "UPDATE cliente SET nombreC = ?, dni = ? , "
                    + "domicilio =?, celular =? WHERE idCliente = ?;";

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cliente.getNombreC());
                ps.setInt(2, cliente.getDni());
                ps.setString(3, cliente.getDomicilio());
                ps.setString(4, cliente.getCelular());
                ps.setInt(5, cliente.getIdCliente());
                ps.executeUpdate();
            }
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar un cliente: " + ex.getMessage());
        }
    
    }
    
    public List<Cliente> obtenerClientes(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            String sql = "SELECT * FROM cliente;";
            PreparedStatement ps = con.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery();
                Cliente cliente;
                
                while(resultSet.next()){
                    cliente = new Cliente();
                    cliente.setIdCliente(resultSet.getInt("idCliente"));
                    cliente.setNombreC(resultSet.getString("nombreC"));
                    cliente.setDni(resultSet.getInt("dni"));
                    cliente.setDomicilio(resultSet.getString("domicilio")); 
                    cliente.setCelular(resultSet.getString("celular"));
                    clientes.add(cliente);
                }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los clientes: " + ex.getMessage()); 
        }
        return clientes;
    }
    
    
    
    public Cliente buscarCliente(int id){
    Cliente cliente=null;
    try {
            
            String sql = "SELECT * FROM cliente WHERE idCliente =?;";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            
            
            ResultSet resultSet=ps.executeQuery();
            
            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                cliente.setNombreC(resultSet.getString("nombreC"));
                cliente.setDni(resultSet.getInt("dni"));
                cliente.setDomicilio(resultSet.getString("domicilio"));
                cliente.setCelular(resultSet.getString("celular"));
                
                
            }
        }
            
            
            
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un cliente: " + ex.getMessage());
        }
        
        return cliente;
    }
    
}
