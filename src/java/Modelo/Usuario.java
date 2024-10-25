/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario Local
 */
public class Usuario {
    int id;
    String nombre;
    String apellido;
    String user;
    String pass;
    int estado;
    int idgrupo;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String user, String pass, int estado, int idgrupo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
        this.pass = pass;
        this.estado = estado;
        this.idgrupo = idgrupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(int idgrupo) {
        this.idgrupo = idgrupo;
    }
    
    
    
    
}
