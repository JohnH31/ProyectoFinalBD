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
public class UsuarioDAO {

    PreparedStatement ps;
    ResultSet rs;
    Conector c = new Conector();
    Connection con;

    public Usuario validar(String user, String pass) {
        Usuario us = new Usuario();
        String sql = "select * from tbl_usuarios where usuario_usuarios=? and password_usuarios=?";
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
                us.setEstado(rs.getString("Estado_usuarios"));
                us.setIdgrupo(rs.getInt("id_grupo_fk"));
            }
        } catch (Exception ex) {

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
    

    public List listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_usuarios";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario p = new Usuario();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setUser(rs.getString(4));
                p.setPass(rs.getString(5));
                p.setEstado(rs.getString(6));
                p.setIdgrupo(rs.getInt(7));

                lista.add(p);
            }

        } catch (Exception e) {

        }
        return lista;
    }
    
        public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        String sql = "SELECT u.id_codUsuarios as id,u.nombre_usuario,u.apellido_usuarios,u.usuario_usuarios,u.password_usuarios,u.Estado_usuarios,g.nombre_grupo,g.id_grupo\n " 
                + "FROM tbl_usuarios AS u, tbl_grupos AS g\n " 
                + "WHERE u.id_grupo_fk = g.id_grupo";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String encabezado[]={"id","nombre","apellido","usuario","password","estado","descripcion","id_grupo"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[]=new String[8];
            while (rs.next()) {
                datos[0]=rs.getString("id");
                datos[1]=rs.getString("nombre_usuario");
                datos[2]=rs.getString("apellido_usuarios");
                datos[3]=rs.getString("usuario_usuarios");
                datos[4]=rs.getString("password_usuarios");
                datos[5]=rs.getString("Estado_usuarios");
                datos[6]=rs.getString("nombre_grupo");
                datos[7]=rs.getString("id_grupo");
                tabla.addRow(datos);
                
            }
        }catch(Exception e){
            System.out.println("error"+ e.getMessage());
        }
        
        
        return tabla;
    }

    public int agregar(Usuario p) {
        int r = 0;
        String sql = "INSERT INTO tbl_usuarios(id_codUsuarios,nombre_usuario,apellido_usuarios,usuario_usuarios,password_usuarios,Estado_usuarios,id_grupo_fk)VALUE(?,?,?,?,?,?,?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getUser());
            ps.setString(5, p.getPass());
            ps.setString(6, p.getEstado());
            ps.setInt(7, p.getIdgrupo());
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

    public Usuario listarId(String id) {
        String sql = "SELECT * FROM tbl_usuarios WHERE id_codUsuarios=" + id;
        Usuario p = new Usuario();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setUser(rs.getString(4));
                p.setPass(rs.getString(5));
                p.setEstado(rs.getString(6));
                p.setIdgrupo(rs.getInt(7));
            }

        } catch (Exception e) {

        }
        return p;
    }

    public void actualizar(Usuario p) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_usuarios SET nombre_usuario='" + p.getNombre()
                    + "',apellido_usuarios='" + p.getApellido()+ "',usuario_usuarios='" 
                    + p.getUser()+ "',password_usuarios='" + p.getPass()
                    + "',Estado_usuarios=" + p.getEstado()
                    + ",id_grupo_fk=" + p.getIdgrupo()+ " WHERE id_codUsuarios=" + p.getId();
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.err.println("Mensaje Actualizar " + e.getMessage());
        }
        c.desconectar();
    }

    public void eliminar(Usuario p) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_usuarios WHERE id_codUsuarios =" + p.getId() + ";";
            c.consultas_multiples(consulta);

        } catch (Exception e) {
            System.out.println("Mensaje eliminar " + e.getMessage());
        }
        c.desconectar();
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
}
