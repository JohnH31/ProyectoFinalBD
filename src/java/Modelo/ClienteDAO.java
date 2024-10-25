/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario Local
 */
public class ClienteDAO {

    PreparedStatement ps;
    ResultSet rs;
    Conector c = new Conector();
    Connection con;

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_Cliente";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente p = new Cliente();
                p.setId(rs.getInt(1));
                p.setNit(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setApellido(rs.getString(4));
                p.setDireccion(rs.getString(5));
                p.setTelefono(rs.getString(6));
                p.setFecha_nacimiento(rs.getString(7));
                p.setIdTipo(rs.getInt(8));

                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error en listar: " + e.getMessage());
        }
        return lista;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        String sql = "SELECT c.id_cliente as id, c.nit, c.nombre, c.apellido, c.direccion, c.telefono, "
                + "c.fecha_nacimiento, tc.descripcion, tc.id_tipoCliente "
                + "FROM tbl_cliente c, tbl_tipo_cliente tc "
                + "WHERE c.id_tipoCliente = tc.id_tipoCliente";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String encabezado[] = {"id", "nit", "nombre", "apellido", "direccion", "telefono", "fechaNacimiento", "descripcion", "idtipocli"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            while (rs.next()) {
                datos[0] = rs.getString("id");
                datos[1] = rs.getString("nit");
                datos[2] = rs.getString("nombre");
                datos[3] = rs.getString("apellido");
                datos[4] = rs.getString("direccion");
                datos[5] = rs.getString("telefono");
                datos[6] = rs.getString("fecha_nacimiento");
                datos[7] = rs.getString("descripcion");
                datos[8] = rs.getString("id_tipoCliente");
                tabla.addRow(datos);
            }
        } catch (Exception e) {
            System.out.println("Error en leer: " + e.getMessage());
        }
        return tabla;
    }

    public int agregar(Cliente p) {
        int r = 0;
        String sql = "INSERT INTO tbl_Cliente (id_cliente, nit, nombre, apellido, direccion, telefono, fecha_nacimiento, id_tipoCliente) "
                + "VALUES (seq_cliente.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNit());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getDireccion());
            ps.setString(5, p.getTelefono());
            ps.setString(6, p.getFecha_nacimiento());
            ps.setInt(7, p.getIdTipo());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en agregar: " + e.getMessage());
        }
        return r;
    }

    public Cliente listarId(String id) {
        String sql = "SELECT * FROM tbl_Cliente WHERE id_cliente = ?";
        Cliente p = new Cliente();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNit(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setApellido(rs.getString(4));
                p.setDireccion(rs.getString(5));
                p.setTelefono(rs.getString(6));
                p.setFecha_nacimiento(rs.getString(7));
                p.setIdTipo(rs.getInt(8));
            }
        } catch (Exception e) {
            System.out.println("Error en listarId: " + e.getMessage());
        }
        return p;
    }

public void actualizar(Cliente p) {
    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = c.conectar();
        String sql = "UPDATE tbl_Cliente SET nit = ?, nombre = ?, apellido = ?, direccion = ?, telefono = ?, "
                   + "fecha_nacimiento = TO_DATE(?, 'YYYY-MM-DD'), id_tipoCliente = ? WHERE id_cliente = ?";

        ps = con.prepareStatement(sql);
        ps.setString(1, p.getNit());
        ps.setString(2, p.getNombre());
        ps.setString(3, p.getApellido());
        ps.setString(4, p.getDireccion());
        ps.setString(5, p.getTelefono());

        // Obtener la fecha como cadena
        String fechaString = p.getFecha_nacimiento();

        // Cambia el formato a yyyy-MM-dd HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Intenta analizar la fecha
        java.util.Date utilDate = format.parse(fechaString); 

        // Convierte java.util.Date a java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        // Cambia el formato de salida a 'YYYY-MM-DD' para TO_DATE
        SimpleDateFormat oracleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = oracleDateFormat.format(sqlDate);

        // Establece la fecha formateada en el PreparedStatement
        ps.setString(6, fechaFormateada); // Usamos setString para el TO_DATE

        ps.setInt(7, p.getIdTipo());
        ps.setInt(8, p.getId());
        ps.executeUpdate();
    } catch (ParseException e) {
        System.out.println("Error en el formato de la fecha: " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("Error en actualizar: " + e.getMessage());
    } finally {
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error cerrando recursos: " + e.getMessage());
        }
    }
}








    public void eliminar(Cliente p) {
        try {
            con = c.conectar();
            String consulta = "DELETE FROM tbl_Cliente WHERE id_cliente = ?";
            ps = con.prepareStatement(consulta);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en eliminar: " + e.getMessage());
        }
    }
}
