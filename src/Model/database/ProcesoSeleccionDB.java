/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.database;

import Model.ProcesoSeleccion;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ProcesoSeleccionDB {
    private connectDB _db;
    
    public ProcesoSeleccionDB(){
        _db = new connectDB();
    }
    
    public void crearProcesoSeleccion(ProcesoSeleccion proceso){
        String query = "INSERT INTO procesodeseleccion(id_area, id_puesto,"
                + "fecha_in, fecha_fin, descripcion) values(?,?,?,?,?) "
                + "RETURNING id_seleccion";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, proceso.getId_area());
            pstmt.setInt(2, proceso.getId_puesto());
            pstmt.setDate(3, proceso.getFechaIn());
            pstmt.setDate(4, proceso.getFechaFin());
            pstmt.setString(5, proceso.getDecripcion());
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                proceso.setId_proceso(rs.getInt(1));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public List<ProcesoSeleccion> obtenerProceso(){
        List<ProcesoSeleccion> listProceso = new ArrayList<>();
        String query = "SELECT * FROM procesodeseleccion";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                ProcesoSeleccion proceso = new ProcesoSeleccion();
                proceso.setId_proceso(rs.getInt("id_seleccion"));
                proceso.setId_area(rs.getInt("id_area"));
                proceso.setId_puesto(rs.getInt("id_puesto"));
                proceso.setFechaIn(rs.getDate("fecha_in"));
                proceso.setFechaIn(rs.getDate("fecha_fin"));
                proceso.setDescripcion(rs.getString("descripcion"));
                
                String query2 = "SELECT * FROM area WHERE id_area = ?;";
                try (PreparedStatement pstmt2 = conn.prepareStatement(query2)){
                    pstmt2.setInt(1, proceso.getId_area());
                    
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        proceso.setArea(rs2.getString("nombre"));
                    }
                    
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                
                String query3 = "SELECT * FROM puesto WHERE id_puesto = ?;";
                try (PreparedStatement pstmt3 = conn.prepareStatement(query3)){
                    pstmt3.setInt(1, proceso.getId_puesto());
                    
                    ResultSet rs3 = pstmt3.executeQuery();
                    if(rs3.next()){
                        proceso.setPuesto(rs3.getString("nombre"));
                    }
                    
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                listProceso.add(proceso);
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return listProceso;
    }
    
    public ProcesoSeleccion obtenerProcesoxId(int id_proceso){
        ProcesoSeleccion proceso = new ProcesoSeleccion();
        String query = "SELECT * FROM procesodeseleccion WHERE id_seleccion = ?;";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_proceso);    
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                proceso.setId_proceso(rs.getInt("id_seleccion"));
                proceso.setId_area(rs.getInt("id_area"));
                proceso.setId_puesto(rs.getInt("id_puesto"));
                proceso.setFechaIn(rs.getDate("fecha_in"));
                proceso.setFechaIn(rs.getDate("fecha_fin"));
                proceso.setDescripcion(rs.getString("descripcion"));
                
                String query2 = "SELECT * FROM area WHERE id_area = ?;";
                try (PreparedStatement pstmt2 = conn.prepareStatement(query2)){
                    pstmt2.setInt(1, proceso.getId_area());
                    
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        proceso.setArea(rs2.getString("nombre"));
                    }
                    
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                
                String query3 = "SELECT * FROM puesto WHERE id_puesto = ?;";
                try (PreparedStatement pstmt3 = conn.prepareStatement(query3)){
                    pstmt3.setInt(1, proceso.getId_puesto());
                    
                    ResultSet rs3 = pstmt3.executeQuery();
                    if(rs3.next()){
                        proceso.setPuesto(rs3.getString("nombre"));
                    }
                    
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return proceso;
    }
    
    public void agregarPostulantes(int id_proceso, List<Usuario> listPostulantes){
            
        for(int i=0; i<listPostulantes.size(); i++){
            if(postulantesRepetidos(id_proceso, listPostulantes.get(i).getId_usuario())== 0){
                String query = "INSERT INTO procesodeseleccion_x_usuario(id_seleccion, id_usuario, puntaje)"
                        + " values(?,?,?);";
                Connection conn = _db.getConnection();
                try(PreparedStatement pstmt = conn.prepareStatement(query)){
                    pstmt.setInt(1, id_proceso);
                    pstmt.setInt(2, listPostulantes.get(i).getId_usuario());
                    pstmt.setDouble(3, 0.0);

                    ResultSet rs = pstmt.executeQuery();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    
    public void actualizarPuntajePostulante(int id_proceso, int id_postulante, double puntaje){
        String query = "UPDATE procesodeseleccion_x_usuario SET puntaje = ? "
                        + "WHERE id_seleccion=? and id_usuario=?;";
        Connection conn = _db.getConnection();
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, puntaje);
            pstmt.setInt(2, id_proceso);
            pstmt.setInt(3, id_postulante);
            
            pstmt.executeUpdate(); 
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    int postulantesRepetidos(int id_proceso, int id_postulante){
        String query = "SELECT * FROM procesodeseleccion_x_usuario "
                + "WHERE id_seleccion = ?";
        int id_seleccion, id_usuario;
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_proceso);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if(rs.getInt("id_usuario") == id_postulante)
                    return 1;
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }   
        _db.closeConnection();
        return 0;
    }
    
    public List<Usuario> obtenerPostulantes(int id_proceso){
        List<Usuario> listUsuario = new ArrayList<>();
        UsuarioDB usuariodb = new UsuarioDB();
        String query = "SELECT * FROM procesodeseleccion_x_usuario "
                + "WHERE id_seleccion = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_proceso);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario = usuariodb.obtenerUsuarioxId(rs.getInt("id_usuario"));
                usuario.setPuntaje(rs.getDouble("puntaje"));
                listUsuario.add(usuario);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        _db.closeConnection();
        return listUsuario;
    }
    
    public ProcesoSeleccion obtenerProcesoxPostulante(int id_usuario){
        ProcesoSeleccion proceso = new ProcesoSeleccion();
        String query = "SELECT * FROM procesodeseleccion_x_usuario "
                + "WHERE id_usuario = ?;";
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_usuario);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                proceso = obtenerProcesoxId(rs.getInt("id_seleccion"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        _db.closeConnection();
        return proceso;
    }
}
