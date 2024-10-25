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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import static org.codehaus.groovy.ast.tools.GeneralUtils.stmt;

/**
 *
 * @author Usuario Local
 */
public class UsuarioDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conector c = new Conector();
    Connection con;

    public Usuario validar(String user, String pass) {
        Usuario us = new Usuario();
        String sql = "SELECT * FROM tbl_usuarios WHERE usuario_usuarios = ? AND password_usuarios = ?";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                us.setId(rs.getInt("id_codUsuarios"));
                us.setNombre(rs.getString("nombre_usuario"));
                us.setApellido(rs.getString("apellido_usuarios"));
                us.setUser(rs.getString("usuario_usuarios"));
                us.setPass(rs.getString("password_usuarios"));
                us.setEstado(rs.getInt("Estado_usuarios"));
                us.setIdgrupo(rs.getInt("id_grupo_fk"));
            }
        } catch (Exception ex) {
            System.out.println("Error en validar: " + ex.getMessage());
        }
        return us;
    }
    
    public List Validar2(String id) {
            List<Integer> lista1 = new ArrayList<>();
        String sql = "SELECT a.nombre_usuario, b.id_permiso_fk FROM tbl_usuarios a INNER JOIN tbl_gruposPermisos b ON a.id_grupo_fk = b.id_grupo_fk WHERE a.nombre_usuario='"+id+"'";       
        Usuario p = new Usuario();
        GruposPer per = new GruposPer();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setNombre(rs.getString(1));
                per.setIdper(rs.getInt(2));
                lista1.add(per.getIdper());
            }

        } catch (Exception e) {
            System.out.println("error join");
        }
        return lista1;
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_usuarios";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario p = new Usuario();
                p.setId(rs.getInt("ID_USUARIO"));
                p.setNombre(rs.getString("NOMBRE"));
                p.setApellido(rs.getString("APELLIDO"));
                p.setUser(rs.getString("USERNAME"));
                p.setPass(rs.getString("PASSWORD"));
                p.setEstado(rs.getInt("ESTADO"));
                p.setIdgrupo(rs.getInt("ID_GRUPO_FK"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error en listar: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return lista;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        String sql = "SELECT u.ID_CODUSUARIOS AS id, u.NOMBRE_USUARIO, u.APELLIDO_USUARIOS, u.USUARIO_USUARIOS, u.PASSWORD_USUARIOS, u.ESTADO_USUARIOS, g.NOMBRE_GRUPO, g.ID_GRUPO FROM TBL_USUARIOS u, TBL_GRUPOS g WHERE u.ID_GRUPO_FK = g.ID_GRUPO";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String encabezado[] = {"id", "nombre", "apellido", "usuario", "password", "estado", "descripcion", "id_grupo"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while (rs.next()) {
                datos[0] = rs.getString("id");
                datos[1] = rs.getString("NOMBRE_USUARIO");
                datos[2] = rs.getString("APELLIDO_USUARIOS");
                datos[3] = rs.getString("USUARIO_USUARIOS");
                datos[4] = rs.getString("PASSWORD_USUARIOS");
                datos[5] = rs.getString("ESTADO_USUARIOS");
                datos[6] = rs.getString("NOMBRE_GRUPO");
                datos[7] = rs.getString("ID_GRUPO");
                tabla.addRow(datos);
            }
        } catch (Exception e) {
            System.out.println("Error en leer: " + e.getMessage());
        }
        return tabla;
    }

    public int agregar(Usuario p) {
        int r = 0;
        String sql = "INSERT INTO tbl_usuarios (id_codUsuarios, nombre_usuario, apellido_usuarios, usuario_usuarios, password_usuarios, Estado_usuarios, id_grupo_fk) VALUES (seq_usuarios.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getUser());
            ps.setString(4, p.getPass());
            ps.setInt(5, p.getEstado());
            ps.setInt(6, p.getIdgrupo());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en agregar: " + e.getMessage());
        }
        return r;
    }

    public Usuario listarId(String id) {
        Usuario p = new Usuario();
        String sql = "SELECT * FROM tbl_usuarios WHERE id_codUsuarios = ?";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setUser(rs.getString(4));
                p.setPass(rs.getString(5));
                p.setEstado(rs.getInt(6));
                p.setIdgrupo(rs.getInt(7));
            }
        } catch (Exception e) {
            System.out.println("Error en listarId: " + e.getMessage());
        }
        return p;
    }

    public void actualizar(Usuario p) {
        String consulta = "UPDATE tbl_usuarios SET nombre_usuario = ?, apellido_usuarios = ?, usuario_usuarios = ?, password_usuarios = ?, Estado_usuarios = ?, id_grupo_fk = ? WHERE id_codUsuarios = ?";
        try {
            con = c.conectar();
            ps = con.prepareStatement(consulta);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getUser());
            ps.setString(4, p.getPass());
            ps.setInt(5, p.getEstado());
            ps.setInt(6, p.getIdgrupo());
            ps.setInt(7, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en actualizar: " + e.getMessage());
        }
    }

    public void eliminar(Usuario p) {
        String consulta = "DELETE FROM tbl_usuarios WHERE id_codUsuarios = ?";
        try {
            con = c.conectar();
            ps = con.prepareStatement(consulta);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en eliminar: " + e.getMessage());
        }
    }

    public HashMap<String, String> drop_grupo() {
        HashMap<String, String> drop = new HashMap<>();
        String sql = "SELECT id_grupo AS id, nombre_grupo FROM tbl_grupos";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                drop.put(rs.getString("id"), rs.getString("nombre_grupo"));
            }
        } catch (Exception e) {
            System.out.println("Error en drop_grupo: " + e.getMessage());
        }
        return drop;
    }
}
