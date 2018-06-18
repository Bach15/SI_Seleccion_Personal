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
    
    public Perfil obtenerPerfilXId(int id_perfil,String tipo){
        Perfil perfil = null;
        if(tipo.equals("Estudios")){
            String query = "SELECT * FROM estudios WHERE id_estudios = ?;";
            Connection conn = _db.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id_perfil);
            
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    perfil = new Perfil();
                    perfil.setId_opcion(rs.getInt("id_estudios"));
                    perfil.setOpciones(rs.getString("opcion"));
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        if(tipo.equals("Dominio del idioma")){
            String query = "SELECT * FROM idioma WHERE id_idioma = ?;";
            Connection conn = _db.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id_perfil);
            
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    perfil = new Perfil();
                    perfil.setId_opcion(rs.getInt("id_idioma"));
                    perfil.setOpciones(rs.getString("opcion"));
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        if(tipo.equals("Manejo de Software")){
            String query = "SELECT * FROM softwares WHERE id_software = ?;";
            Connection conn = _db.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id_perfil);
            
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    perfil = new Perfil();
                    perfil.setId_campo(rs.getInt("id_software"));
                    perfil.setCampos(rs.getString("tipo"));
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        if(tipo.equals("Competencias")){
            String query = "SELECT * FROM competencias WHERE id_competencia= ?;";
            Connection conn = _db.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id_perfil);
            
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    perfil = new Perfil();
                    perfil.setId_campo(rs.getInt("id_competencia"));
                    perfil.setCampos(rs.getString("tipo"));
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
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
                    perfilOp.setOpciones(rs.getString("opciones"));
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
                    perfilOp.setOpciones(rs.getString("opciones"));
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
                    perfilOp.setOpciones(rs.getString("opciones"));
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
                    perfilOp.setOpciones(rs.getString("opciones"));
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
                    perfilOp.setId_campo(rs.getInt("id_software"));
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
                    perfilOp.setId_campo(rs.getInt("id_competencia"));
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
    
    public List<Perfil> llenarGrillaxPuesto(String tipo, int id_puesto){
        List<Perfil> listCampos = new ArrayList<>();
        if(tipo.equals("Manejo de Software")){
            String query = "SELECT * FROM puesto_x_software WHERE id_puesto = ?;";
            
            Connection conn = _db.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id_puesto);
            
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    Perfil perfilOp = new Perfil();
                    perfilOp.setId_campo(rs.getInt("id_software"));
                    perfilOp = obtenerPerfilXId(perfilOp.getId_campo(), tipo);
                    perfilOp.setNivel(rs.getInt("software_min"));          
                    listCampos.add(perfilOp);
                }
                
            }  catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }
        else if(tipo.equals("Competencias")){
            String query = "SELECT * FROM puesto_x_competencia WHERE id_puesto = ?";
            
            Connection conn = _db.getConnection();
            try (PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id_puesto);
            
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    Perfil perfilOp = new Perfil();
                    perfilOp.setId_campo(rs.getInt("id_competencia"));                   
                    perfilOp = obtenerPerfilXId(perfilOp.getId_campo(), tipo);
                    perfilOp.setNivel(rs.getInt("competencia_min"));
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
    
    public void agregarEstudioXPuesto(int id_puesto, String tipo){
        String query = "INSERT INTO puesto_x_estudio(id_puesto, id_estudios, estudios_min) VALUES(?,?,?);";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_puesto);
            pstmt.setInt(2, 1); //id_perfil = estudios
            if(tipo.equals("Ninguna")) pstmt.setInt(3, 0);
            else if(tipo.equals("Secundaria completa")) pstmt.setInt(3, 1);
            else if(tipo.equals("Estudiantes ultimos ciclos")) pstmt.setInt(3, 2);
            else if(tipo.equals("Egresado")) pstmt.setInt(3, 3);
            else if(tipo.equals("Bachiller")) pstmt.setInt(3, 4);
            else if(tipo.equals("Titulo")) pstmt.setInt(3, 5);
            
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();    
    }
    
    public void agregarIdiomaXPuesto(int id_puesto, String habla, String escritura, String lectura){
        String query = "INSERT INTO puesto_x_idioma(id_puesto, id_idioma, habla_min, escritura_min, lectura_min)"
                + " VALUES(?,?,?,?,?);";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_puesto);
            pstmt.setInt(2, 2); //id_perfil = idioma
            pstmt.setInt(3, identificarIdioma(habla));
            pstmt.setInt(4, identificarIdioma(escritura));
            pstmt.setInt(5, identificarIdioma(lectura));
            
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();  
    }
    
    public int identificarIdioma(String nivel){
        if(nivel.equals("Ninguno")) return 0;
        else if(nivel.equals("Basico")) return 1;
        else if(nivel.equals("Intermedio")) return 2;
        else if(nivel.equals("Avanzado")) return 3;
        return 0;
    }
    
    public void agregarSoftwareXPuesto(int id_puesto, List<Perfil>listSoftware){
        
        for(int i=0; i<listSoftware.size(); i++){
            if(listSoftware.get(i).getSoftwareSeleccionado()>0){
                String query = "INSERT INTO puesto_x_software(id_puesto, id_software, software_min) "
                        + "VALUES(?,?,?);";
                Connection conn = _db.getConnection();
                try(PreparedStatement pstmt = conn.prepareStatement(query)){
                   pstmt.setInt(1, id_puesto); 
                   pstmt.setInt(2, listSoftware.get(i).getId_campo()); //id_perfil = software
                   pstmt.setInt(3, listSoftware.get(i).getSoftwareSeleccionado());
                   
                   ResultSet rs = pstmt.executeQuery();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }    
        }    
        _db.closeConnection();
    }
    
    public void agregarCompetenciaXPuesto(int id_puesto, List<Perfil>listCompetencia){
        
        for(int i=0; i<listCompetencia.size(); i++){
            if(listCompetencia.get(i).getCompetenciaSeleccionado()>0){
                String query = "INSERT INTO puesto_x_competencia(id_puesto, id_competencia, competencia_min) "
                        + "VALUES(?,?,?);";
                Connection conn = _db.getConnection();
                try(PreparedStatement pstmt = conn.prepareStatement(query)){
                   pstmt.setInt(1, id_puesto); 
                   pstmt.setInt(2, listCompetencia.get(i).getId_campo()); //id_perfil = software
                   pstmt.setInt(3, listCompetencia.get(i).getCompetenciaSeleccionado());
                   
                   ResultSet rs = pstmt.executeQuery();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }    
        }    
        _db.closeConnection();
    }
    
    public void agregarEstudioXUsuario(int id_usuario, int tipo){  //String tipo
        String query = "INSERT INTO usuario_x_estudio(id_usuario, id_estudios, estudios_min) VALUES(?,?,?);";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_usuario);
            pstmt.setInt(2, 1); //id_perfil = estudios
//            if(tipo.equals("Ninguna")) pstmt.setInt(3, 1);
//            else if(tipo.equals("Secundaria completa")) pstmt.setInt(3, 2);
//            else if(tipo.equals("Estudiantes ultimos ciclos")) pstmt.setInt(3, 3);
//            else if(tipo.equals("Egresado")) pstmt.setInt(3, 4);
//            else if(tipo.equals("Bachiller")) pstmt.setInt(3, 5);
//            else if(tipo.equals("Titulo")) pstmt.setInt(3, 6);
            pstmt.setInt(3, tipo);

            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();    
    }
    
    public void agregarIdiomaXUsuario(int id_usuario, int habla, int escritura, int lectura){ //String habla, String escritura, String lectura
        String query = "INSERT INTO usuario_x_idioma(id_usuario, id_idioma, habla_min, escritura_min, lectura_min)"
                + " VALUES(?,?,?,?,?);";
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_usuario);
            pstmt.setInt(2, 2); //id_perfil = idioma
//            pstmt.setInt(3, identificarIdioma(habla));
//            pstmt.setInt(4, identificarIdioma(escritura));
//            pstmt.setInt(5, identificarIdioma(lectura));
            pstmt.setInt(3, habla);
            pstmt.setInt(4, escritura);
            pstmt.setInt(5, lectura);
            
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();  
    }
    
    public void agregarSoftwareXUsuario(int id_usuario, List<Perfil>listSoftware){
        
        for(int i=0; i<listSoftware.size(); i++){
            String query = "INSERT INTO usuario_x_software(id_usuario, id_software, software_min) "
                    + "VALUES(?,?,?);";
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query)){
               pstmt.setInt(1, id_usuario); 
               pstmt.setInt(2, listSoftware.get(i).getId_campo()); //id_perfil = software
               pstmt.setInt(3, listSoftware.get(i).getPostulanteSelectSoftware());

               ResultSet rs = pstmt.executeQuery();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }    
        _db.closeConnection();
    }
    
    public void agregarCompetenciaXUsuario(int id_usuario, List<Perfil>listCompetencia){
        
        for(int i=0; i<listCompetencia.size(); i++){
            String query = "INSERT INTO usuario_x_competencia(id_usuario, id_competencia, competencia_min) "
                    + "VALUES(?,?,?);";
            Connection conn = _db.getConnection();
            try(PreparedStatement pstmt = conn.prepareStatement(query)){
               pstmt.setInt(1, id_usuario); 
               pstmt.setInt(2, listCompetencia.get(i).getId_campo()); //id_perfil = software
               pstmt.setInt(3, listCompetencia.get(i).getPostulanteSelectCompetencia());

               ResultSet rs = pstmt.executeQuery();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }  
        }    
        _db.closeConnection();
    }
    
    public int ObtenerEstudioXUsuario(int id_usuario){
        int estudios=-1;
        String query = "SELECT * FROM usuario_x_estudio WHERE id_usuario = ?;";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_usuario);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                estudios = rs.getInt("estudios_min");
            }          
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();  
        return estudios;
    }
    
    public void ObtenerIdiomaXUsuario(int id_usuario, int habla, int escritura, int lectura){
        String query = "SELECT * FROM usuario_x_idioma WHERE id_usuario = ?;";

        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_usuario);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                habla = rs.getInt("habla_min");
                escritura = rs.getInt("escritura_min");
                lectura = rs.getInt("lectura_min");
            }            
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();  
    }
    
    public List<Perfil> ObtenerSoftwareXUsuario(int id_usuario){
        List<Perfil> listSoftware = new ArrayList<>();          
        String query = "SELECT * FROM usuario_x_software WHERE id_usuario = ?;";

        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
           pstmt.setInt(1, id_usuario); 

           ResultSet rs = pstmt.executeQuery();
           while(rs.next()){
               Perfil software = new Perfil();
               software.setNivel(rs.getInt("software_min"));
               software.setId_campo(rs.getInt("id_software"));

               String query2 = "SELECT * FROM softwares WHERE id_software = ?;";
               try(PreparedStatement pstmt2 = conn.prepareStatement(query2)){
                   pstmt2.setInt(1, software.getId_campo());

                   ResultSet rs2 = pstmt2.executeQuery();
                   if(rs2.next()){
                       software.setCampos(rs2.getString("tipo"));
                   }
               }
               listSoftware.add(software);
           }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }  
        _db.closeConnection();
        return listSoftware;
    }
    
    public List<Perfil> obtenerCompetenciaXUsuario(int id_usuario){
        List<Perfil> listCompetencias = new ArrayList<>();  
        String query = "SELECT * FROM usuario_x_competencia WHERE id_usuario = ?;";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
           pstmt.setInt(1, id_usuario); 
           
           ResultSet rs = pstmt.executeQuery();
           while(rs.next()){
               Perfil competencia = new Perfil();
               competencia.setNivel(rs.getInt("competencia_min"));
               competencia.setId_campo(rs.getInt("id_competencia"));
               
               String query2 = "SELECT * FROM competencias WHERE id_competencia = ?;";
               try(PreparedStatement pstmt2 = conn.prepareStatement(query2)){
                   pstmt2.setInt(1, competencia.getId_campo());

                   ResultSet rs2 = pstmt2.executeQuery();
                   if(rs2.next()){
                       competencia.setCampos(rs2.getString("tipo"));
                   }
               }
               listCompetencias.add(competencia);
           }         
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }  
 
        _db.closeConnection();
        return listCompetencias;
    }
    
    
    public int ObtenerEstudioXPuesto(int id_puesto){
        int estudios=-1;
        String query = "SELECT * FROM puesto_x_estudio WHERE id_puesto = ?;";
        
        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_puesto);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                estudios = rs.getInt("estudios_min");
            }          
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();  
        return estudios;
    }
    
    public void ObtenerIdiomaXPuesto(int id_puesto, int habla, int escritura, int lectura){
        String query = "SELECT * FROM puesto_x_idioma WHERE id_puesto = ?;";

        Connection conn = _db.getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, id_puesto);
            
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                habla = rs.getInt("habla_min");
                escritura = rs.getInt("escritura_min");
                lectura = rs.getInt("lectura_min");
            }            
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        _db.closeConnection();  
    }
    
}
