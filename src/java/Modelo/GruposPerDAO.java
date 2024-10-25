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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario Local
 */
public class GruposPerDAO {

    PreparedStatement ps;
    ResultSet rs;
    Conector c = new Conector();
    Connection con;

    public List listar() {
        List<GruposPer> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_gruposPermisos";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GruposPer p = new GruposPer();
                p.setId(rs.getInt(1));
                p.setIdgrupo(rs.getInt(2));
                p.setIdper(rs.getInt(3));

                lista.add(p);
            }

        } catch (Exception e) {

        }
        return lista;
    }

    public int agregar(GruposPer p) {
        int r = 0;
        String sql = "INSERT INTO tbl_gruposPermisos(id_grupoPer,id_grupo_fk,id_permiso_fk) VALUES(seq_gruposPermisos.NEXTVAL,?,?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getIdgrupo());
            ps.setInt(2, p.getIdper());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al agregar: " + e.getMessage());
        }
        return r;
    }

    public GruposPer listarId(String id) {
        String sql = "SELECT * FROM tbl_gruposPermisos WHERE id_grupoPer=" + id;
        GruposPer p = new GruposPer();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setIdgrupo(rs.getInt(2));
                p.setIdper(rs.getInt(3));
            }

        } catch (Exception e) {

        }
        return p;
    }

    public void actualizar(GruposPer p) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_gruposPermisos SET id_grupo_fk=" + p.getIdgrupo() + ",id_permiso_fk=" + p.getIdper() + " WHERE id_grupoPer=" + p.getId();
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.err.println("Mensaje Actualizar " + e.getMessage());
        }
        c.desconectar();
    }

    public void eliminar(GruposPer p) {
        String sql = "DELETE FROM tbl_gruposPermisos WHERE id_grupoPer = ?";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Mensaje eliminar " + e.getMessage());
        }
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        String sql = "SELECT gp.id_grupoPer as id, g.nombre_grupo, p.nombre_permiso, g.id_grupo, p.id_permiso "
                + "FROM tbl_permisos p, tbl_grupos g, tbl_grupospermisos gp "
                + "WHERE gp.id_grupo_fk = g.id_grupo AND gp.id_permiso_fk = p.id_permiso";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String encabezado[] = {"id", "nombre_grupo", "nombre_permiso", "id_grupo", "id_permiso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[5];
            while (rs.next()) {
                datos[0] = rs.getString("id");
                datos[1] = rs.getString("nombre_grupo");
                datos[2] = rs.getString("nombre_permiso");
                datos[3] = rs.getString("id_grupo");
                datos[4] = rs.getString("id_permiso");
                tabla.addRow(datos);

            }
        } catch (Exception e) {
            System.out.println("error GrupoPerDao " + e.getMessage());
        }

        return tabla;
    }

    public HashMap drop_grupo() {
        HashMap<String, String> drop = new HashMap();
        String sql = "Select id_grupo as id,nombre_grupo from tbl_grupos";
        Grupos p = new Grupos();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                drop.put(rs.getString("id"), rs.getString("nombre_grupo"));
                p.setId(rs.getInt(1));
                p.setGrupo(rs.getString(2));
            }

        } catch (Exception e) {

        }
        return drop;
    }

    public HashMap drop_permiso() {
        HashMap<String, String> drop = new HashMap();
        String sql = "Select id_permiso as id,nombre_permiso from tbl_permisos";
        Permisos p = new Permisos();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                drop.put(rs.getString("id"), rs.getString("nombre_permiso"));
                p.setId(rs.getInt(1));
                p.setPermiso(rs.getString(2));
            }

        } catch (Exception e) {

        }
        return drop;
    }
}
