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
public class TipoEvaluacion {
    private int _id_tipoEvaluacion;
    private String _nombre;
    private String _descripcion;
    
    public TipoEvaluacion(){
        
    }
    public TipoEvaluacion(int _id_tipoEvaluacion, String _nombre, String _descripcion){
        this._id_tipoEvaluacion = _id_tipoEvaluacion;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
    }
    
    public Integer getId_tipoEvaluacion(){
        return _id_tipoEvaluacion;
    }
    
    public void setId_tipoEvaluacion(int _id_tipoEvaluacion){
        this._id_tipoEvaluacion = _id_tipoEvaluacion;
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
