/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.database;

import Model.Area;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Carlos
 */
public class AreaDB {
    private connectDB _db;
    
    public AreaDB(){
        _db = new connectDB();
    }
    
    public void crearArea(Area area){
        String query = "INSERT INTO area(nombre, descripcion) VALUES(?,?)"
                + "RETURNING id_area;";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, area.getNombre());
            pstmt.setString(2, area.getDescripcion());
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                area.setId_area(rs.getInt(1));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
    }
    
    public List<Area> obtenerArea(){
        List<Area> listAreas = new ArrayList<>();
        String query = "SELECT * FROM area";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()){
                Area area = new Area();
                area.setId_area(rs.getInt("id_area"));
                area.setNombre(rs.getString("nombre"));
                area.setDescripcion(rs.getString("descripcion"));
                listAreas.add(area);
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return listAreas;
    }
    
    public Area obtenerAreaxId(int codigo){
        Area area = null;
        String query = "SELECT * FROM area WHERE id_area = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, codigo);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                area = new Area();
                area.setId_area(rs.getInt("id_area"));
                area.setNombre(rs.getString("nombre"));
                area.setDescripcion(rs.getString("descripcion"));
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return area;
    }
    
}
