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
    
    public void crearPerfil(Perfil perfil){
        if(perfil.getNombre().equals("Estudios")){
            String query = "INSERT INTO estudios(id_perfil,opcion) VALUES(?,?);";
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, perfil.getId_perfil());
                pstmt.setString(2, perfil.getOpciones());
                
                ResultSet rs = pstmt.executeQuery();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        if(perfil.getNombre().equals("Dominio del idioma")){
            String query = "INSERT INTO idioma(id_perfil,opcion) VALUES(?,?);";
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, perfil.getId_perfil());
                pstmt.setString(2, perfil.getOpciones());
                
                ResultSet rs = pstmt.executeQuery();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        if(perfil.getNombre().equals("Manejo de Software")){
            String query = "INSERT INTO softwares(id_perfil,tipo) VALUES(?,?);";
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, perfil.getId_perfil());
                pstmt.setString(2, perfil.getCampos());
                
                ResultSet rs = pstmt.executeQuery();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        if(perfil.getNombre().equals("Competencias")){
            String query = "INSERT INTO competencias(id_perfil,tipo) VALUES(?,?);";
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, perfil.getId_perfil());
                pstmt.setString(2, perfil.getCampos());
                
                ResultSet rs = pstmt.executeQuery();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        _db.closeConnection();
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
    
    public List<Perfil> obtenerOpcionesxPerfil(Perfil perfil){
        List<Perfil> listOpciones = new ArrayList<>();
        if(perfil.getNombre().equals("Estudios")){
            String query = "SELECT * FROM estudios";
            
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Perfil perfilOp = new Perfil();
                    perfilOp.setId_opcion(rs.getInt("id_estudios"));
                    perfilOp.setOpciones(rs.getString("opcion"));
                    listOpciones.add(perfilOp);
                }
                
            }  catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }
        else if(perfil.getNombre().equals("Dominio del idioma")){
            String query = "SELECT * FROM idioma";
            
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Perfil perfilOp = new Perfil();
                    perfilOp.setId_opcion(rs.getInt("id_idioma"));
                    perfilOp.setOpciones(rs.getString("opcion"));
                    listOpciones.add(perfilOp);
                }
                
            }  catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }    
        _db.closeConnection();
        return listOpciones;
    }
    
    public List<String> llenarComboxTipo(String tipo){
        List<String> listOpciones = new ArrayList<>();
        if(tipo.equals("Estudios")){
            String query = "SELECT * FROM estudios";
            
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Perfil perfilOp = new Perfil();
                    perfilOp.setId_opcion(rs.getInt("id_estudios"));
                    perfilOp.setOpciones(rs.getString("opcion"));
                    listOpciones.add(perfilOp.getOpciones());
                }
                
            }  catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }
        else if(tipo.equals("Dominio del idioma")){
            String query = "SELECT * FROM idioma";
            
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Perfil perfilOp = new Perfil();
                    perfilOp.setId_opcion(rs.getInt("id_idioma"));
                    perfilOp.setOpciones(rs.getString("opcion"));
                    listOpciones.add(perfilOp.getOpciones());
                }
                
            }  catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }    
        _db.closeConnection();
        return listOpciones;
    }
    
    public List<Perfil> llenarGrillaxTipo(String tipo){
        List<Perfil> listCampos = new ArrayList<>();
        if(tipo.equals("Manejo de Software")){
            String query = "SELECT * FROM softwares";
            
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Perfil perfilOp = new Perfil();
                    perfilOp.setId_opcion(rs.getInt("id_software"));
                    perfilOp.setCampos(rs.getString("tipo"));
                    listCampos.add(perfilOp);
                }
                
            }  catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }
        else if(tipo.equals("Competencias")){
            String query = "SELECT * FROM competencias";
            
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Perfil perfilOp = new Perfil();
                    perfilOp.setId_opcion(rs.getInt("id_competencia"));
                    perfilOp.setCampos(rs.getString("tipo"));
                    listCampos.add(perfilOp);
                }
                
            }  catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }    
        _db.closeConnection();
        return listCampos;
    }
    
    public List<Perfil> obtenerCamposxPerfil(Perfil perfil){
        List<Perfil> listCampos = new ArrayList<>();
        if(perfil.getNombre().equals("Manejo de Software")){
            String query = "SELECT * FROM softwares";
            
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Perfil perfilCampos = new Perfil();
                    perfilCampos.setId_opcion(rs.getInt("id_software"));
                    perfilCampos.setCampos(rs.getString("tipo"));
                    listCampos.add(perfilCampos);
                }
                
            }  catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }
        else if(perfil.getNombre().equals("Competencias")){
            String query = "SELECT * FROM competencias";
            
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Perfil perfilCampos = new Perfil();
                    perfilCampos.setId_opcion(rs.getInt("id_competencia"));
                    perfilCampos.setCampos(rs.getString("tipo"));
                    listCampos.add(perfilCampos);
                }
                
            }  catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }    
        _db.closeConnection();
        return listCampos;
    }
}
