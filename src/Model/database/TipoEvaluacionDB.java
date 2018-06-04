/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.database;

import Model.TipoEvaluacion;
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
public class TipoEvaluacionDB {
    private connectDB _db;
    
    public TipoEvaluacionDB(){
        _db = new connectDB();
    }
    
    public void crearTipoEvaluacion(TipoEvaluacion tipo){
        String query = "INSERT INTO tipoevaluacion(nombre,descripcion) VALUES(?,?)"
                + "RETURNING id_tipoevaluacion;";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, tipo.getNombre());
            pstmt.setString(2, tipo.getDescripcion());
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                tipo.setId_tipoEvaluacion(rs.getInt(1));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
    }
    
    public List<TipoEvaluacion> obtenerTipoEvaluacion(){
        List<TipoEvaluacion> listTipoEvaluacion = new ArrayList<>();
        String query = "SELECT * FROM tipoevaluacion";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()){
                TipoEvaluacion tipo = new TipoEvaluacion();
                tipo.setId_tipoEvaluacion(rs.getInt("id_tipoevaluacion"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setDescripcion(rs.getString("descripcion"));
                listTipoEvaluacion.add(tipo);
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return listTipoEvaluacion;
    }
    
    public TipoEvaluacion obtenerTipoEvaluacionxId(int codigo){
        TipoEvaluacion tipo = null;
        String query = "SELECT * FROM tipoevaluacion WHERE id_tipoevaluacion = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, codigo);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                tipo = new TipoEvaluacion();
                tipo.setId_tipoEvaluacion(rs.getInt("id_tipoevaluacion"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setDescripcion(rs.getString("descripcion"));
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return tipo;
    }
    
    
    
}
