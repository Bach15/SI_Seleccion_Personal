/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.database;

import Model.Area;
import Model.Puesto;
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
public class PuestoDB {
    private connectDB _db;
    
    public PuestoDB(){
        _db = new connectDB();
    }
    
    public void crearPuesto(Puesto puesto){
        String query = "INSERT INTO puesto(id_area, nombre, descripcion) VALUES(?,?,?)"
                + "RETURNING id_puesto;";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, puesto.getId_area());
            pstmt.setString(2, puesto.getNombre());
            pstmt.setString(3, puesto.getDescripcion());
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                puesto.setId_puesto(rs.getInt(1));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
    }

    public List<Puesto> obtenerPuesto(){
        List<Puesto> listPuesto = new ArrayList<>();
        String query = "SELECT * FROM puesto";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()){
                Puesto puesto = new Puesto();
                puesto.setId_puesto(rs.getInt("id_puesto"));
                puesto.setId_area(rs.getInt("id_area"));
                puesto.setNombre(rs.getString("nombre"));
                puesto.setDescripcion(rs.getString("descripcion"));
                listPuesto.add(puesto);
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return listPuesto;
    }
    
    public List<Puesto> obtenerPuestoxArea(int codArea){
        List<Puesto> listPuesto = new ArrayList<>();
        String query = "SELECT * FROM puesto WHERE id_area = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, codArea);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Puesto puesto = new Puesto();
                puesto.setId_puesto(rs.getInt("id_puesto"));
                puesto.setId_area(rs.getInt("id_area"));
                puesto.setNombre(rs.getString("nombre"));
                puesto.setDescripcion(rs.getString("descripcion"));
                listPuesto.add(puesto);
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return listPuesto;
    }
    
    public Puesto obtenerPuestoxId(int codigo){
        Puesto puesto = null;
        String query = "SELECT * FROM puesto WHERE id_puesto = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, codigo);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                puesto = new Puesto();
                puesto.setId_puesto(rs.getInt("id_puesto"));
                puesto.setId_area(rs.getInt("id_area"));
                puesto.setNombre(rs.getString("nombre"));
                puesto.setDescripcion(rs.getString("descripcion"));
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return puesto;
    }
    
    public Puesto obtenerPuestoxNombre(String nombre){
        Puesto puesto = null;
        String query = "SELECT * FROM puesto WHERE nombre LIKE ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, nombre);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                puesto = new Puesto();
                puesto.setId_puesto(rs.getInt("id_puesto"));
                puesto.setId_area(rs.getInt("id_area"));
                puesto.setNombre(rs.getString("nombre"));
                puesto.setDescripcion(rs.getString("descripcion"));
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return puesto;
    }
}
