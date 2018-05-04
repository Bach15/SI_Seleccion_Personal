/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.database;

import Model.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class PerfilDB {
    private connectDB _db;
    
    public PerfilDB(){
        _db = new connectDB();
    }
    
    public List<Perfil> obtenerPerfil(){
        List<Perfil> listPerfil = new ArrayList<>();
        String query = "SELECT * FROM perfil";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()){
                Perfil perfil = new Perfil();
                perfil.setId_perfil(rs.getInt("id_perfil"));
                perfil.setNombre(rs.getString("nombre"));
                perfil.setDescripcion(rs.getString("descripcion"));
                listPerfil.add(perfil);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return listPerfil;
    }  
        
    public Perfil obtenerPerfilxNombre(String nombre){
        Perfil perfil = null;
        String query = "SELECT * FROM perfil WHERE nombre LIKE ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, nombre);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                perfil = new Perfil();
                perfil.setId_perfil(rs.getInt("id_perfil"));
                perfil.setNombre(rs.getString("nombre"));
                perfil.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return perfil;
    } 
}
