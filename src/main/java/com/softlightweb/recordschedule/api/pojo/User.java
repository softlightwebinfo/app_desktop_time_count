package com.softlightweb.recordschedule.api.pojo;

import java.util.ArrayList;
import org.bson.types.ObjectId;

public class User {

    private ObjectId _id;
    private String nombre;
    private String apellidos;
    private String password;
    private String email;
    private String telefono;
    private String dni;
    private ArrayList<UserRegistro> registros = new ArrayList();
    private tipoRegistro ultimoRegistro;

    /**
     * CONSTRUCTOR
     */
    public User() {
    }

    public User(String nombre, String apellidos,
            String password,
            String email, String telefono, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.dni = dni;
    }

    /**
     * SETTERS
     *
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public void setRegistros(ArrayList<UserRegistro> registros) {
        this.registros = registros;
    }

    public void setUltimoRegistro(tipoRegistro ultimoRegistro) {
        this.ultimoRegistro = ultimoRegistro;
    }
    
    

    /**
     * GETTERS
     */
    
    public ObjectId getId() {
        return _id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDni() {
        return dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public ArrayList<UserRegistro> getRegistros() {
        return registros;
    }

    public tipoRegistro getUltimoRegistro() {
        return ultimoRegistro;
    }
    
}
