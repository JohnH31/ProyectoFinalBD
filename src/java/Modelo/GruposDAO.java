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
    
    public List listar(){
        List<Grupos>lista=new ArrayList<>();
        String sql = "SELECT * FROM tbl_grupos";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Grupos p = new Grupos();
                p.setId(rs.getInt(1));
                p.setGrupo(rs.getString(2));
                lista.add(p);
            }
            
            
        } catch (Exception e){
            
            
        }
        return lista;
    }
    
    public int agregar(Grupos p){
        int r=0;
        String sql = "INSERT INTO tbl_grupos(id_grupo,nombre_grupo)VALUE(?,?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getGrupo());
            r=ps.executeUpdate();
            if(r==1){
                r=1;
            }else{
                r=0;
            }
        } catch (Exception e){
            
        }
        return r;
    }
    
    public Grupos listarId(String id){
        String sql="SELECT * FROM tbl_grupos WHERE id_grupo="+id;
        Grupos p = new Grupos();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                p.setId(rs.getInt(1));
                p.setGrupo(rs.getString(2));
            }
          
        } catch (Exception e){
              
        }
        return p;
    }
    
//    public int actualizar(Permisos p){
//        int r=0;
//        String sql2 = "UPDATE tbl_permisos SET nombre_permiso=? WHERE id_permiso=?";
//        try {
//            con = c.conectar();
//            ps = con.prepareStatement(sql2);
//            ps.setInt(1, p.getId());
//            ps.setString(2, p.getPermiso());
//            System.out.println(p.getId());
//            System.out.println(p.getPermiso());
//            System.out.println(r);
//            r = ps.executeUpdate();  
//            System.out.println(r);
//            if(r==1){
//                r=1;
//            }else{
//                r=0;
//            }
//        } catch (Exception e){
//            
//        }
//        return r;
//    }
    public void actualizar(Grupos p) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_grupos SET nombre_grupo='" + p.getGrupo()+ "' WHERE id_grupo=" + p.getId();
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.err.println("Mensaje Actualizar " + e.getMessage());
        }
        c.desconectar();
    }
public void eliminar(Grupos p) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_grupos WHERE id_grupo =" + p.getId() + ";";
            c.consultas_multiples(consulta);

        } catch (Exception e) {
            System.out.println("Mensaje eliminar " + e.getMessage());
        }
        c.desconectar();
    }

 public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        String sql = "SELECT id_grupo as id,nombre_grupo FROM tbl_grupos";
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
            System.out.println("error GrupoPerDao " + e.getMessage());
        }

        return tabla;
    }
}
