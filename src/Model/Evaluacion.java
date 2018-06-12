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
public class Evaluacion {
    private int _id_evaluacion;
    private int _id_tipoEvaluacion;
    private String _tipoEvaluacion;
    private String _nombre;
    private String _descripcion;
    
    public Evaluacion(){
        
    }
    
    public Integer getId_evaluacion(){
        return _id_evaluacion;
    }
    
    public void setId_evaluacion(int _id_evaluacion){
        this._id_evaluacion = _id_evaluacion;
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
    
    public Integer getId_tipoEvaluacion(){
        return _id_tipoEvaluacion;
    }
    
    public void setId_tipoEvaluacion(int _id_tipoEvaluacion){
        this._id_tipoEvaluacion = _id_tipoEvaluacion;
    }
    
    public String getTipoEvaluacion(){
        return _tipoEvaluacion;
    }
    
    public void setTipoEvaluacion(String _tipoEvaluacion){
        this._tipoEvaluacion  = _tipoEvaluacion;
    }
}
