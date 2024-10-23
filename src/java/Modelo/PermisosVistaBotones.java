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
public class PermisosVistaBotones {
    String permisos = "";
    String usuarios= "";
    String grupos= "";
    String gruposper= "";
    String clientes= "";
    String recibos= "";
    String reportes="";
    String error="";

    public PermisosVistaBotones() {
    }

    public PermisosVistaBotones(String permisos, String usuarios, String grupos, String gruposper, String clientes,String recibos,String reportes) {
        this.permisos = permisos;
        this.usuarios = usuarios;
        this.grupos = grupos;
        this.gruposper = gruposper;
        this.clientes = clientes;
        this.recibos = recibos;
        this.reportes = reportes;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public String getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    public String getGruposper() {
        return gruposper;
    }

    public void setGruposper(String gruposper) {
        this.gruposper = gruposper;
    }

    public String getClientes() {
        return clientes;
    }

    public void setClientes(String clientes) {
        this.clientes = clientes;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRecibos() {
        return recibos;
    }

    public void setRecibos(String recibos) {
        this.recibos = recibos;
    }

    public String getReportes() {
        return reportes;
    }

    public void setReportes(String reportes) {
        this.reportes = reportes;
    }
    
    
    
    
}
