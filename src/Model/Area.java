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
public class Area {
    private int _id_area;
    private String _nombre;
    private String _descripcion;
    
    
    public Area(){
        
    }
    public Area(int _id_area, String _nombre, String _descripcion){
        this._id_area = _id_area;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
    }
    
    public Integer getId_area(){
        return _id_area;
    }
    
    public void setId_area(int _id_area){
        this._id_area = _id_area;
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
