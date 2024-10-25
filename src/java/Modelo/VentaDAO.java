/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

    // Método para agregar un detalle de venta
    public int agregarDetalleVenta(Venta p) {
        int r = 0;
        String sql = "INSERT INTO tbl_detalleventa (ID_DETALLE_VENTA, descripcion, monto, id_ventafk) VALUES (seq_detalleventa.NEXTVAL, ?, ?, ?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getDescripcion());            
            ps.setDouble(2, p.getMonto());
            ps.setInt(3, p.getId());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones
        } finally {
            cerrarRecursos();
        }
        return r;
    }

    // Método para agregar una venta
    public int agregarVenta(Venta p) {
    int r = 0;
    String sql = "INSERT INTO tbl_venta (id_venta, nofactura_venta, fecha_venta, total_venta, id_codUsuariosfk, id_clientefk) " +
                 "VALUES (seq_venta.NEXTVAL, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";
    
    try {
        con = c.conectar();
        ps = con.prepareStatement(sql);
        
        ps.setString(1, p.getNo_venta());
        
        // Cambiamos el formato de fecha a uno simple, ya que la cadena está en "yyyy-MM-dd"
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = format.parse(p.getFecha()); // Parseamos sin hora

        // Convertimos java.util.Date a java.sql.Date para la base de datos
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        // Establecemos la fecha en el PreparedStatement
        ps.setDate(2, sqlDate); // Usamos setDate para pasar un java.sql.Date
        ps.setDouble(3, p.getTotal());
        ps.setInt(4, p.getVendedor());
        ps.setInt(5, p.getId_cliente());
        r = ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(); // Manejo de excepciones
    } finally {
        cerrarRecursos();
    }
    return r;
}

    // Método para generar el número de factura
    public String GenerarNoFac() {
        String numeroserie = "";
        String sql = "SELECT MAX(nofactura_venta) FROM tbl_venta";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                numeroserie = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones
        } finally {
            cerrarRecursos();
        }
        return numeroserie;
    }

    // Método para obtener el ID de ventas
    public String IdVentas() {
        String idventas = "";
        String sql = "SELECT MAX(id_venta) FROM tbl_venta";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                idventas = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones
        } finally {
            cerrarRecursos();
        }
        return idventas;
    }

    // Método para listar ventas
    public List<Venta> listarVenta() {
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
            e.printStackTrace(); // Manejo de excepciones
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Método para listar detalles de ventas
    public List<Venta> listarDetalleVenta() {
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
            e.printStackTrace(); // Manejo de excepciones
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Método para obtener ventas por vendedor
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
        } finally {
            cerrarRecursos();
        }
        return ventasPorVendedor;
    }

    // Método para cerrar recursos
    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}

