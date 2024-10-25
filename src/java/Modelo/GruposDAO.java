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
public class GruposDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conector c = new Conector();
    Connection con;
    
    public List<Grupos> listar() {
        List<Grupos> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_grupos";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Grupos p = new Grupos();
                p.setId(rs.getInt(1));
                p.setGrupo(rs.getString(2));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error en listar: " + e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Grupos p) {
        int r = 0;
        String sql = "INSERT INTO tbl_grupos(id_grupo, nombre_grupo) VALUES(seq_grupos.NEXTVAL, ?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getGrupo());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al agregar: " + e.getMessage());
        }
        return r;
    }
    
    public Grupos listarId(String id) {
        String sql = "SELECT * FROM tbl_grupos WHERE id_grupo = ?";
        Grupos p = new Grupos();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setGrupo(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("Error en listarId: " + e.getMessage());
        }
        return p;
    }

    public void actualizar(Grupos p) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_grupos SET nombre_grupo = ? WHERE id_grupo = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, p.getGrupo());
            ps.setInt(2, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Mensaje Actualizar: " + e.getMessage());
        }
        c.desconectar();
    }

    public void eliminar(Grupos p) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_grupos WHERE id_grupo = ?";
            ps = con.prepareStatement(consulta);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Mensaje eliminar: " + e.getMessage());
        }
        c.desconectar();
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        String sql = "SELECT id_grupo AS id, nombre_grupo FROM tbl_grupos";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String encabezado[] = {"id", "nombre_grupo"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[2];
            while (rs.next()) {
                datos[0] = rs.getString("id");
                datos[1] = rs.getString("nombre_grupo");
                tabla.addRow(datos);
            }
        } catch (Exception e) {
            System.out.println("Error en leer: " + e.getMessage());
        }
        return tabla;
    }
}
