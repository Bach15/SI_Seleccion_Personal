/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Carlos
 */
public class ProcesoSeleccion {
    private int _id_proceso;
    private int _id_area;
    private String _area;
    private int _id_puesto;
    private String _puesto;
    private Date _fecha_in;
    private Date _fecha_fin;
    private String _descripcion;
    
    public ProcesoSeleccion(){
        
    }
    
    public Integer getId_proceso(){
        return _id_proceso;
    }
    
    public void setId_proceso(int _id_proceso){
        this._id_proceso = _id_proceso;
    }
    
    public Integer getId_area(){
        return _id_area;
    }
    
    public void setId_area(int _id_area){
        this._id_area = _id_area;
    }
    
    public String getArea(){
        return _area;
    }
    
    public void setArea(String _area){
        this._area = _area;
    }
    
    public Integer getId_puesto(){
        return _id_puesto;
    }
    
    public void setId_puesto(int _id_puesto){
        this._id_puesto = _id_puesto;
    }
    
    public String getPuesto(){
        return _puesto;
    }
    
    public void setPuesto(String _puesto){
        this._puesto = _puesto;
    }
    
    public Date getFechaIn(){
        return _fecha_in;
    }
    
    public void setFechaIn(Date _fecha_in){
        this._fecha_in  = _fecha_in;
    }
    
    public Date getFechaFin(){
        return _fecha_fin;
    }
    
    public void setFechaFin(Date _fecha_fin){
        this._fecha_fin  = _fecha_fin;
    }
    
    public void setDescripcion(String _descripcion){
        this._descripcion = _descripcion;
    }
    
    public String getDecripcion(){
        return _descripcion;
    }
}
