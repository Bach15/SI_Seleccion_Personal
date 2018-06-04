/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.database;

import Model.Usuario;
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
public class UsuarioDB {
    private connectDB _db;
    
    public UsuarioDB(){
        _db = new connectDB();
    }
    
    public void crearUsuario(Usuario usuario){
        String query = "INSERT INTO persona(nombres, apellido_paterno, "
                + "apellido_materno, dni, sexo, fecha_nacimiento, correo,"
                + "direccion, telefono) VALUES (?,?,?,?,?,?,?,?,?)"
                + "RETURNING id_persona;";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellidoPa());
            pstmt.setString(3, usuario.getApellidoMa());
            pstmt.setInt(4, usuario.getDni());
            pstmt.setInt(5, usuario.getSexo());
            pstmt.setDate(6, usuario.getFechaNac());
            pstmt.setString(7, usuario.getCorreo());
            pstmt.setString(8, usuario.getDireccion());
            pstmt.setString(9, usuario.getTelefono());
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                usuario.setId_persona(rs.getInt(1));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        String query2 = "INSERT INTO usuario(id_persona, tipo_usuario, nombre_usuario,contrasena) "
                + "VALUES (?,?,?,?)"
                + "RETURNING id_usuario;";
        try(PreparedStatement pstmt2 = conn.prepareStatement(query2)){
            pstmt2.setInt(1, usuario.getId_persona());
            pstmt2.setInt(2, usuario.getTipo_Usuario());
            if(usuario.getTipo_Usuario()== 0)
                pstmt2.setString(3, usuario.getNombre_usuario());
            else if(usuario.getTipo_Usuario()== 1){
                pstmt2.setString(3, String.valueOf(usuario.getDni()));
                usuario.setPuntaje(0.0);
            }
            pstmt2.setString(4, "Novatronic2018");
            
            ResultSet rs2 = pstmt2.executeQuery();
            if(rs2.next())
                usuario.setId_usuario(rs2.getInt(1));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
    }
    
    public List<Usuario> obtenerUsuario(){
        List<Usuario> listUsuario = new ArrayList<>();
        String query = "SELECT * FROM persona";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId_persona(rs.getInt("id_persona"));
                usuario.setNombre(rs.getString("nombres"));
                usuario.setApellidoPa(rs.getString("apellido_paterno"));
                usuario.setApellidoMa(rs.getString("apellido_materno"));
                usuario.setDni(rs.getInt("dni"));
                usuario.setSexo(rs.getInt("sexo"));
                usuario.setFechaNac(rs.getDate("fecha_nacimiento"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono(rs.getString("telefono"));
                
                String query2 = "SELECT * FROM usuario WHERE id_persona = ?;";
                try (PreparedStatement pstmt2 = conn.prepareStatement(query2)){
                    pstmt2.setInt(1, usuario.getId_persona());
                    
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        usuario.setId_usuario(rs2.getInt("id_usuario"));
                        usuario.setTipo_Usuario(rs2.getInt("tipo_usuario"));
                        usuario.setNombre_usuario(rs2.getString("nombre_usuario"));
                        usuario.setContrasena(rs2.getString("contrasena"));
                    }
                    
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                
                listUsuario.add(usuario);
            }
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return listUsuario;
    }
    
    public Usuario obtenerUsuarioxId(int id_usuario){
        Usuario usuario = new Usuario();
        String query = "SELECT * FROM usuario WHERE id_usuario = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_usuario);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setTipo_Usuario(rs.getInt("tipo_usuario"));
                usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setId_persona(rs.getInt("id_persona"));
                
                String query2 = "SELECT * FROM persona WHERE id_persona = ?;";
                try (PreparedStatement pstmt2 = conn.prepareStatement(query2)){
                
                    pstmt2.setInt(1, usuario.getId_persona());
                    
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        //usuario.setId_persona(rs.getInt("id_persona"));
                        usuario.setNombre(rs2.getString("nombres"));
                        usuario.setApellidoPa(rs2.getString("apellido_paterno"));
                        usuario.setApellidoMa(rs2.getString("apellido_materno"));
                        usuario.setDni(rs2.getInt("dni"));
                        usuario.setSexo(rs2.getInt("sexo"));
                        usuario.setFechaNac(rs2.getDate("fecha_nacimiento"));
                        usuario.setCorreo(rs2.getString("correo"));
                        usuario.setDireccion(rs2.getString("direccion"));
                        usuario.setTelefono(rs2.getString("telefono"));
                        
                        usuario.setId_usuario(rs2.getInt("id_usuario"));
                        usuario.setTipo_Usuario(rs2.getInt("tipo_usuario"));
                        usuario.setNombre_usuario(rs2.getString("nombre_usuario"));
                        usuario.setContrasena(rs2.getString("contrasena"));
                    }
                    
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return usuario;
    }
    
}
