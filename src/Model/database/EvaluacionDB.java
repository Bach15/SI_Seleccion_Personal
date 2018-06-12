/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.database;

import Model.Evaluacion;
import Model.Pregunta;
import Model.Respuesta;
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
public class EvaluacionDB {
    private connectDB _db;
    
    public EvaluacionDB(){
        _db = new connectDB();
    }
    
    public void crearEvaluacion(Evaluacion evaluacion){
        String query = "INSERT INTO evaluacion(id_tipoevaluacion, nombre, descripcion) "
                + "VALUES(?,?,?) RETURNING id_evaluacion;";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, evaluacion.getId_tipoEvaluacion());
            pstmt.setString(2, evaluacion.getNombre());
            pstmt.setString(3, evaluacion.getDescripcion());
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                evaluacion.setId_evaluacion(rs.getInt(1));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
    }
    
    public void crearEvaluacionxPuesto(int id_puesto, int id_evaluacion){
        String query = "INSERT INTO puesto_x_evaluacion(id_puesto, id_evaluacion) "
                + "VALUES(?,?);";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_puesto);
            pstmt.setInt(2, id_evaluacion);
            
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        
    }
    
    
    public List<Evaluacion> obtenerEvaluacionxTipo(int codTipo){
        List<Evaluacion> listEvaluacion = new ArrayList<>();
        String query = "SELECT * FROM evaluacion WHERE id_tipoevaluacion = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, codTipo);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Evaluacion evaluacion = new Evaluacion();
                evaluacion.setId_evaluacion(rs.getInt("id_evaluacion"));
                evaluacion.setId_tipoEvaluacion(rs.getInt("id_tipoevaluacion"));
                evaluacion.setNombre(rs.getString("nombre"));
                evaluacion.setDescripcion(rs.getString("descripcion"));
                listEvaluacion.add(evaluacion);
            }   
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return listEvaluacion;    
    }
    
    public Evaluacion obtenerEvaluacionxPuesto(int codPuesto){
        Evaluacion evaluacion = new Evaluacion();
        String query = "SELECT * FROM puesto_x_evaluacion WHERE id_puesto = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, codPuesto);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                evaluacion = obtenerEvaluacionxId(rs.getInt("id_evaluacion"));
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return evaluacion;
    }
    
    public Evaluacion obtenerEvaluacionxId(int codigo){
        Evaluacion evaluacion = null;
        String query = "SELECT * FROM evaluacion WHERE id_evaluacion = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, codigo);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                evaluacion = new Evaluacion();
                evaluacion.setId_evaluacion(rs.getInt("id_evaluacion"));
                evaluacion.setId_tipoEvaluacion(rs.getInt("id_tipoevaluacion"));
                evaluacion.setNombre(rs.getString("nombre"));
                evaluacion.setDescripcion(rs.getString("descripcion"));
            }          
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return evaluacion;
    }
    
    public void crearPregunta(int idEvaluacion, Pregunta pregunta){
        String query = "INSERT INTO pregunta(id_evaluacion, texto) "
                + "VALUES(?,?) RETURNING id_pregunta;";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, idEvaluacion);
            pstmt.setString(2, pregunta.getTexto());
            pregunta.setId_evaluacion(idEvaluacion);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                pregunta.setId_pregunta(rs.getInt(1));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
    }
    
    public void crearRespuesta(int idPregunta, Respuesta respuesta){
        String query = "INSERT INTO respuesta(id_pregunta, texto) "
                + "VALUES(?,?) RETURNING id_respuesta;";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, idPregunta);
            pstmt.setString(2, respuesta.getTexto());
            respuesta.setId_pregunta(idPregunta);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                respuesta.setId_respuesta(rs.getInt(1));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
    }
    
    public Pregunta obtenerPregunta(int idPregunta){
        Pregunta pregunta = null;
        Respuesta respuesta = null;
        List<Respuesta> listRespuestas = new ArrayList<>();
        String query = "SELECT * FROM pregunta WHERE id_pregunta = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, idPregunta);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                pregunta = new Pregunta();
                pregunta.setId_evaluacion(rs.getInt("id_evaluacion"));
                pregunta.setId_pregunta(rs.getInt("id_pregunta"));
                pregunta.setTexto(rs.getString("texto"));
                
                String query2 = "SELECT * FROM respuesta WHERE id_pregunta = ?;";
                
                try (PreparedStatement pstmt2 = conn.prepareStatement(query2)){
                    pstmt2.setInt(1, idPregunta);
                    
                    ResultSet rs2 = pstmt2.executeQuery();
                    while(rs2.next()){
                        respuesta = new Respuesta();
                        respuesta.setId_pregunta(idPregunta);
                        respuesta.setId_respuesta(rs2.getInt("id_respuesta"));
                        respuesta.setTexto(rs2.getString("texto"));
                        pregunta.agregarRespuesta(respuesta);
                        listRespuestas.add(respuesta);
                    }
                    pregunta.getComboBoxRespuesta(listRespuestas);
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }   
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return pregunta;
    }
    
    public List<Pregunta> obtenerPreguntasxEvaluacion(int idEvaluacion){
        List<Pregunta> listPregunta = new ArrayList<>();
        String query = "SELECT * FROM pregunta WHERE id_evaluacion = ?;";
        
        Connection conn = _db.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, idEvaluacion);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Pregunta pregunta = new Pregunta();
                pregunta = obtenerPregunta(rs.getInt("id_pregunta"));
                listPregunta.add(pregunta);
            }         
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        _db.closeConnection();
        return listPregunta;
    }
    
}
