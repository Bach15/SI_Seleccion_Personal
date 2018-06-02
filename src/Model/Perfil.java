/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class Perfil {
    private int _id_perfil;
    private String _nombre;
    private String _descripcion;
    private int _id_campo;
    private String _campos;
    private int _id_opcion;
    private String _opciones;
    private int _softwareSeleccionado;
    private int _competenciaSeleccionado;
    private int _PostulanteSelectSoftare;
    private int _PostulanteSelectCompetencia;
    
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
    
    public Integer getId_campo(){
        return _id_campo;
    }
    
    public void setId_campo(int _id_campo){
        this._id_campo = _id_campo;
    }
    
    public String getCampos(){
        return _campos;
    }
    
    public void setCampos(String _campo){
        this._campos  = _campo;
    }
    
    public Integer getId_opciones(){
        return _id_opcion;
    }
    
    public void setId_opcion(int _id_opcion){
        this._id_opcion = _id_opcion;
    }
    
    public String getOpciones(){
        return _opciones;
    }
    
    public void setOpciones(String _opciones){
        this._opciones  = _opciones;
    }
    
    public int getSoftwareSeleccionado(){
        return _softwareSeleccionado;
    }
    
    public void setSoftwareSeleccionado(int _seleccion){
        this._softwareSeleccionado = _seleccion;
    }
    
    public int getCompetenciaSeleccionado(){
        return _competenciaSeleccionado;
    }
    
    public void setCompetenciaSeleccionado(int _seleccion){
        this._competenciaSeleccionado = _seleccion;
    }
    
    public int getPostulanteSelectSoftware(){
        return _PostulanteSelectSoftare;
    }
    
    public void setPostulanteSelectSoftware(int _seleccion){
        this._PostulanteSelectSoftare = _seleccion;
    }
    
    public int getPostulanteSelectCompetencia(){
        return _PostulanteSelectCompetencia;
    }
    
    public void setPostulanteSelectCompetencia(int _seleccion){
        this._PostulanteSelectCompetencia = _seleccion;
    }
    
}
