/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Carlos
 */
public class Opcion {
    private int _id_opcion;
    private int _id_perfil;
    private String _perfil;
    private String _nombre;
    
    public Opcion(){
        
    }
    
    public Opcion(int _id_opcion, int _id_perfil, String _perfil, String _nombre){
        this._id_opcion = _id_opcion;
        this._id_perfil = _id_perfil;
        this._perfil = _perfil;
        this._nombre = _nombre;
    }
    
    public Integer getId_opcion(){
        return _id_opcion;
    }
    
    public void setId_campo(int _id_opcion){
        this._id_opcion = _id_opcion;
    }
    
    public Integer getId_perfil(){
        return _id_perfil;
    }
    
    public void setId_perfil(int _id_perfil){
        this._id_perfil = _id_perfil;
    }
    
    public String getPerfil(){
        return _perfil;
    }
    
    public void setPerfil(String _perfil){
        this._perfil  = _perfil;
    }
    
    public String getNombre(){
        return _nombre;
    }
    
    public void setNombre(String _nombre){
        this._nombre  = _nombre;
    }
}
