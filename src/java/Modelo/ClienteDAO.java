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
public class ClienteDAO {
   PreparedStatement ps;
    ResultSet rs;
    Conector c = new Conector();
    Connection con;

    public List listar() {
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

        }
        return lista;
    }
//    
//    public List listar1() {
//        List<Cliente> lista = new ArrayList<>();
//        List<TipoCliente> listas = new ArrayList<>();
//        String sql = "SELECT c.id_cliente,c.nit,c.nombre,c.apellido,c.direccion,c.telefono,c.fecha_nacimiento,tc.descripcion,tc.id_tipoCliente " +
//"FROM tbl_cliente AS c, tbl_tipo_cliente AS tc " +
//"WHERE c.id_tipoCliente=tc.id_tipoCliente";
//        try {
//            con = c.conectar();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Cliente p = new Cliente();
//                TipoCliente t = new TipoCliente();
//                p.setId(rs.getInt(1));
//                p.setNit(rs.getString(2));
//                p.setNombre(rs.getString(3));
//                p.setApellido(rs.getString(4));
//                p.setDireccion(rs.getString(5));
//                p.setTelefono(rs.getString(6));
//                p.setFecha_nacimiento(rs.getString(7));
//                t.setDescripcion(rs.getString(8));
//                p.setIdTipo(rs.getInt(9));
//
//                lista.add(p);
//                listas.add(t);
//            }
//
//        } catch (Exception e) {
//
//        }
//        return lista;
//    }
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        String sql = "SELECT c.id_cliente as id,c.nit,c.nombre,c.apellido,c.direccion,c.telefono,c.fecha_nacimiento,"
                + "tc.descripcion,tc.id_tipoCliente " + "FROM tbl_cliente AS c, tbl_tipo_cliente AS tc " 
                + "WHERE c.id_tipoCliente=tc.id_tipoCliente";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String encabezado[]={"id","nit","nombre","apellido","direccion","telefono","fechaNacimiento","descripcion","idtipocli"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[]=new String[9];
            while (rs.next()) {
                datos[0]=rs.getString("id");
                datos[1]=rs.getString("nit");
                datos[2]=rs.getString("nombre");
                datos[3]=rs.getString("apellido");
                datos[4]=rs.getString("direccion");
                datos[5]=rs.getString("telefono");
                datos[6]=rs.getString("fecha_nacimiento");
                datos[7]=rs.getString("descripcion");
                datos[8]=rs.getString("id_tipoCliente");
                tabla.addRow(datos);
                
            }
        }catch(Exception e){
            System.out.println("error");
        }
        
        
        return tabla;
    }
    
    public int agregar(Cliente p) {
        int r = 0;
        String sql = "INSERT INTO tbl_Cliente(nit,nombre,apellido,direccion,telefono,fecha_nacimiento,id_tipoCliente)VALUE(?,?,?,?,?,?,?)";
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
            if (r == 1) {
                r = 1;
            } else {
                r = 0;
            }
        } catch (Exception e) {

        }
        return r;
    }

    public Cliente listarId(String id) {
        String sql = "SELECT * FROM tbl_Cliente WHERE id_cliente=" + id;
        Cliente p = new Cliente();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
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

        }
        return p;
    }

    public void actualizar(Cliente p) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "UPDATE tbl_Cliente SET nit='" + p.getNit()
                    + "',nombre='" + p.getNombre()+ "',apellido='" 
                    + p.getApellido()+"',direccion='" + p.getDireccion()
                    + "',telefono='"+p.getTelefono()+"',fecha_nacimiento='"+p.getFecha_nacimiento()
                    + "',id_tipoCliente="+p.getIdTipo()+" WHERE id_cliente=" + p.getId();
            c.consultas_multiples(consulta);
        } catch (Exception e) {
            System.err.println("Mensaje Actualizar " + e.getMessage());
        }
        c.desconectar();
    }

    public void eliminar(Cliente p) {
        Conector c = new Conector();
        try {
            c.conectar();
            String consulta = "DELETE FROM tbl_Cliente WHERE id_cliente =" + p.getId() + ";";
            c.consultas_multiples(consulta);

        } catch (Exception e) {
            System.out.println("Mensaje eliminar " + e.getMessage());
        }
        c.desconectar();
    }
           
   
}

