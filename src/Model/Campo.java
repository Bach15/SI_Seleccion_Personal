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
public class Campo {
    private int _id_campo;
    private int _id_perfil;
    private String _perfil;
    private String _nombre;
    
    public Campo(){
        
    }
    
    public Campo(int _id_campo, int _id_perfil, String _perfil, String _nombre){
        this._id_campo = _id_campo;
        this._id_perfil = _id_perfil;
        this._perfil = _perfil;
        this._nombre = _nombre;
    }
    
    public Integer getId_campo(){
        return _id_campo;
    }
    
    public void setId_campo(int _id_campo){
        this._id_campo = _id_campo;
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
