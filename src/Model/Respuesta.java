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
public class Respuesta {
    private int _id_respuesta;
    private int _id_pregunta;
    private String _texto;
    
    public Respuesta(){
        
    }
    
    public Integer getId_respuesta(){
        return _id_respuesta;
    }
    
    public void setId_respuesta(int _id_respuesta){
        this._id_respuesta = _id_respuesta;
    }
    
    public Integer getId_pregunta(){
        return _id_pregunta;
    }
    
    public void setId_pregunta(int _id_pregunta){
        this._id_pregunta = _id_pregunta;
    }
    
    public String getTexto(){
        return _texto;
    }
    
    public void setTexto(String _texto){
        this._texto  = _texto;
    }
    
}
