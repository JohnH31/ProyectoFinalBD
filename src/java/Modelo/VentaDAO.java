/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Usuario Local
 */
public class VentaDAO {
     PreparedStatement ps;
    ResultSet rs;
    Conector c = new Conector();
    Connection con;
    int r;
    
    public int agregarDetalleVenta(Venta p) {
        int r = 0;
        String sql = "INSERT INTO tbl_detalleventa(descripcion,monto,id_ventafk)VALUE (?,?,?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getDescripcion());
            ps.setDouble(2, p.getMonto());
            ps.setInt(3, p.getId());
            r = ps.executeUpdate();
            if (r == 1) {
                r = 1;
            } else {
                r = 0;
            }
        } catch (Exception e) {

        }
        return r;
    }
    
        public int agregarVenta(Venta p) {
        String sql = "INSERT INTO tbl_venta(nofactura_venta,fecha_venta,total_venta,id_codUsuariosfk,id_clientefk)VALUE (?,?,?,?,?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNo_venta());
            ps.setString(2, p.getFecha());
            ps.setDouble(3, p.getTotal());
            ps.setInt(4, p.getVendedor());
            ps.setInt(5, p.getId_cliente());
            ps.executeUpdate();
        } catch (Exception e) {

        }
        return r;
    }
    
    public String GenerarNoFac(){
        String numeroserie="";
        String sql = "SELECT MAX(nofactura_venta) from tbl_venta";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                numeroserie=rs.getString(1);
            }
        } catch (Exception e) {

        }
        return numeroserie;
    }
    public String IdVentas(){
        String idventas="";
        String sql = "SELECT MAX(id_venta) from tbl_venta";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                idventas=rs.getString(1);
            }
        } catch (Exception e) {

        }
        return idventas;
    }
    
    public List listarVenta() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_venta";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta p = new Venta();
                p.setId(rs.getInt(1));
                p.setNo_venta(rs.getString(2));
                p.setFecha(rs.getString(3));
                p.setTotal(rs.getDouble(4));
                p.setVendedor(rs.getInt(5));
                p.setId_cliente(rs.getInt(6));
                lista.add(p);
            }

        } catch (Exception e) {

        }
        return lista;
    }
    
        public List listarDetalleVenta() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_detalleventa";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta p = new Venta();
                p.setIddetalle(rs.getInt(1));
                p.setDescripcion(rs.getString(2));
                p.setMonto(rs.getDouble(3));
                p.setId(rs.getInt(4));
                lista.add(p);
            }

        } catch (Exception e) {

        }
        return lista;
    }
    
public HashMap<String, Integer> ventasPorVendedor() {
    HashMap<String, Integer> ventasPorVendedor = new HashMap<>();
    String sql = "SELECT u.nombre_usuario, COUNT(v.id_venta) AS totalVentas " +
                 "FROM tbl_venta v " +
                 "JOIN tbl_usuarios u ON v.id_codUsuariosfk = u.id_codUsuarios " +
                 "GROUP BY u.nombre_usuario";

    try {
        con = c.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            ventasPorVendedor.put(rs.getString("nombre_usuario"), rs.getInt("totalVentas"));
        }
    } catch (Exception e) {
        e.printStackTrace(); // Manejo de excepciones
    }
    return ventasPorVendedor;
}



    
}
