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
public class Perfil {
    private int _id_perfil;
    private String _nombre;
    private String _descripcion;
    
    public Perfil(){
        
    }
    
    public Perfil(int _id_perfil, String _nombre, String _descripcion){
        this._id_perfil = _id_perfil;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
    }
    
    public Integer getId_perfil(){
        return _id_perfil;
    }
    
    public void setId_perfil(int _id_perfil){
        this._id_perfil = _id_perfil;
    }
    
    public String getNombre(){
        return _nombre;
    }
    
    public void setNombre(String _nombre){
        this._nombre  = _nombre;
    }
    
    public String getDescripcion(){
        return _descripcion;
    }
    
    public void setDescripcion(String _descripcion){
        this._descripcion  = _descripcion;
    }
}
