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
public class Usuario {
    private int _id_usuario;
    private int _id_persona;
    private String _nombre;
    private String _apellidoPa;
    private String _apellidoMa;
    private int _dni;
    private String _correo;
    private String _direccion;
    private String _telefono;
    private int _sexo;
    private Date _fecha_nac;
    private int _tipo_usuario;
    private String _nombre_usuario;
    private String _contrasena;
    private int _seleccionProceso;
    
    public Usuario(){
        
    }
    
    public Integer getId_usuario(){
        return _id_usuario;
    }
    
    public void setId_usuario(int _id_usuario){
        this._id_usuario = _id_usuario;
    }
    
    public Integer getId_persona(){
        return _id_persona;
    }
    
    public void setId_persona(int _id_persona){
        this._id_persona = _id_persona;
    }
    
    public String getNombre(){
        return _nombre;
    }
    
    public void setNombre(String _nombre){
        this._nombre  = _nombre;
    }
    
    public String getApellidoPa(){
        return _apellidoPa;
    }
    
    public void setApellidoPa(String _apellidoPa){
        this._apellidoPa  = _apellidoPa;
    }
    
    public String getApellidoMa(){
        return _apellidoMa;
    }
    
    public void setApellidoMa(String _apellidoMa){
        this._apellidoMa  = _apellidoMa;
    }
    
    public Integer getDni(){
        return _dni;
    }
    
    public void setDni(int _dni){
        this._dni = _dni;
    }
    
    public String getCorreo(){
        return _correo;
    }
    
    public void setCorreo(String _correo){
        this._correo  = _correo;
    }
    
    public Integer getSexo(){
        return _sexo;
    }
    
    public void setSexo(int _sexo){
        this._sexo = _sexo;
    }
    
    public Date getFechaNac(){
        return _fecha_nac;
    }
    
    public void setFechaNac(Date _fechaNac){
        this._fecha_nac  = _fechaNac;
    }
    
    public String getDireccion(){
        return _direccion;
    }
    
    public void setDireccion(String _direccion){
        this._direccion  = _direccion;
    }
    
    public String getTelefono(){
        return _telefono;
    }
    
    public void setTelefono(String _telefono){
        this._telefono  = _telefono;
    }
    
    public Integer getTipo_Usuario(){
        return _tipo_usuario;
    }
    
    public void setTipo_Usuario(int _tipo_usuario){
        this._tipo_usuario  = _tipo_usuario;
    }
    
    public String getNombre_usuario(){
        return _nombre_usuario;
    }
    
    public void setNombre_usuario(String _nombre_usuario){
        this._nombre_usuario = _nombre_usuario;
    }
    
    public String getContrasena(){
        return _contrasena;
    }
    
    public void setContrasena(String _contrasena){
        this._contrasena  = _contrasena;
    }
    
    public int getSeleccionProceso(){
        return _seleccionProceso;
    }
    
    public void setSeleccionProceso(int _seleccionProceso){
        this._seleccionProceso = _seleccionProceso;
    }
}
