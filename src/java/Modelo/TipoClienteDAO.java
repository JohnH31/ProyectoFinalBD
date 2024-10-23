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
public class TipoClienteDAO {
  PreparedStatement ps;
    ResultSet rs;
    Conector c = new Conector();
    Connection con;

    public List listar() {
        List<TipoCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_tipo_cliente";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoCliente p = new TipoCliente();
                p.setId(rs.getInt(1));
                p.setDescripcion(rs.getString(2));

                lista.add(p);
            }

        } catch (Exception e) {

        }
        return lista;
    }
    
     public TipoCliente listarId(int id) {
        String sql = "SELECT * FROM tbl_tipo_cliente WHERE id_tipoCliente=" + id;
        TipoCliente p = new TipoCliente();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setDescripcion(rs.getString(2));
            }

        } catch (Exception e) {

        }
        return p;
    }
     
      public HashMap drop_tipocliente() {
        HashMap<String, String> drop = new HashMap();
        String sql = "Select id_tipoCliente as id,descripcion from tbl_tipo_cliente";
        TipoCliente p = new TipoCliente();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                drop.put(rs.getString("id"), rs.getString("descripcion"));
                p.setId(rs.getInt(1));
                p.setDescripcion(rs.getString(2));
            }

        } catch (Exception e) {

        }
        return drop;
    }
    
}
