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
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario Local
 */
public class PermisosDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conector c = new Conector();
    Connection con;

    // Método para listar los permisos
    public List<Permisos> listar() {
        List<Permisos> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_permisos";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Permisos p = new Permisos();
                p.setId(rs.getInt("id_permiso")); // Uso de nombres de columna específicos
                p.setPermiso(rs.getString("nombre_permiso"));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al listar permisos: " + e.getMessage());
        }
        return lista;
    }

    // Método para agregar un permiso
    public int agregar(Permisos p) {
        int r = 0;
        String sql = "INSERT INTO tbl_permisos (id_permiso, nombre_permiso) VALUES (seq_permisos.NEXTVAL, ?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getPermiso()); // Se usa secuencia para ID
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al agregar permiso: " + e.getMessage());
        }
        return r;
    }

    // Método para listar un permiso por ID
    public Permisos listarId(String id) {
        String sql = "SELECT * FROM tbl_permisos WHERE id_permiso = ?";
        Permisos p = new Permisos();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("id_permiso"));
                p.setPermiso(rs.getString("nombre_permiso"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener permiso por ID: " + e.getMessage());
        }
        return p;
    }

    // Método para actualizar un permiso
    public void actualizar(Permisos p) {
        String sql = "UPDATE tbl_permisos SET nombre_permiso = ? WHERE id_permiso = ?";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getPermiso());
            ps.setInt(2, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar permiso: " + e.getMessage());
        }
    }

    // Método para eliminar un permiso
    public void eliminar(Permisos p) {
        String sql = "DELETE FROM tbl_permisos WHERE id_permiso = ?";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar permiso: " + e.getMessage());
        }
    }

    // Método para leer la tabla de permisos y mostrarla
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        String sql = "SELECT id_permiso AS id, nombre_permiso FROM tbl_permisos";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String encabezado[] = {"id", "nombre_permiso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[2];
            while (rs.next()) {
                datos[0] = rs.getString("id");
                datos[1] = rs.getString("nombre_permiso");
                tabla.addRow(datos);
            }
        } catch (Exception e) {
            System.out.println("Error al leer permisos: " + e.getMessage());
        }
        return tabla;
    }
}
