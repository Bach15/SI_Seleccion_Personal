/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Carlos
 */
public class Pregunta {
    private int _id_pregunta;
    private int _id_evaluacion;
    private String _texto;
    private List<Respuesta> _listRespuestas;
    private ComboBox<String> _comboRespuesta;
    
    public Pregunta(){
        this._listRespuestas = new ArrayList();
        this._comboRespuesta = new ComboBox<>();
        
    }
    
    public Integer getId_pregunta(){
        return _id_pregunta;
    }
    
    public void setId_pregunta(int _id_pregunta){
        this._id_pregunta = _id_pregunta;
    }
    
    public Integer getId_evaluacion(){
        return _id_evaluacion;
    }
    
    public void setId_evaluacion(int _id_evaluacion){
        this._id_evaluacion = _id_evaluacion;
    }
    
    public String getTexto(){
        return _texto;
    }
    
    public void setTexto(String _texto){
        this._texto  = _texto;
    }
    
    public void agregarRespuesta(Respuesta respuesta){
        this._listRespuestas.add(respuesta);
    }
    
    public List<Respuesta> getListaRespuesta(){
        List<Respuesta> listRespuesta = new ArrayList<>();
        for(int i=0; i<this._listRespuestas.size(); i++){
            listRespuesta.add(this._listRespuestas.get(i));
        }
        return listRespuesta;
    }
    
    public ComboBox<String> getComboBoxRespuesta(List<Respuesta> listRespuestas){
        ObservableList<String> RespuestaObservable = FXCollections.observableArrayList();
        this._comboRespuesta.setEditable(true);
        this._comboRespuesta.setValue("Selecciona");
        for(int i=0; i<listRespuestas.size(); i++){
            RespuestaObservable.add(listRespuestas.get(i).getTexto());
        }
        this._comboRespuesta.getItems().addAll(RespuestaObservable);
        return _comboRespuesta;
    }
}
