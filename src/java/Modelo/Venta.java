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
public class Venta {
    int id;
    int iddetalle;
    String no_venta;
    String fecha;
    String descripcion;
    double monto;
    double total;
    int vendedor;
    int id_cliente;
    int conteo;

    public Venta() {
    }

    public Venta(int id, int iddetalle, String no_venta, String fecha, String descripcion, double monto, double total, int vendedor, int id_cliente) {
        this.id = id;
        this.iddetalle = iddetalle;
        this.no_venta = no_venta;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.total = total;
        this.vendedor = vendedor;
        this.id_cliente = id_cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public String getNo_venta() {
        return no_venta;
    }

    public void setNo_venta(String no_venta) {
        this.no_venta = no_venta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getVendedor() {
        return vendedor;
    }

    public void setVendedor(int vendedor) {
        this.vendedor = vendedor;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    
    
    
}
