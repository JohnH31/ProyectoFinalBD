/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario Local
 */
public class Conector {

    //Declaracion de variables que se utilizaran en la conexion
    private String driver = "com.mysql.jdbc.Driver";
    private String servidor = "127.0.0.1";
    private String usuario = "root";
    private String password = "Jonathan1$";
    private String bd = "bd_webs";
    private String cadena;

    //Declarar el objeto que me permita realizar una conexion
    Connection con;

    //Declarar el objeto que permita realizar consultas SQL inset , update, delete
    Statement st;

    //Metodo para conectar 
    public Connection conectar() {
        this.cadena = "jdbc:mysql://" + this.servidor + "/" + this.bd;
        try {
            Class.forName(this.driver).newInstance();
            this.con = DriverManager.getConnection(this.cadena, this.usuario, this.password);

        } catch (Exception e) {
            System.out.println("Mensaje 1 " + e.getMessage());
        }
        return con;
    }

    //Metodo para desconectar
    public void desconectar() {
        try {
            this.con.close();
        } catch (Exception e) {
            System.out.println("Mensaje 2 " + e.getMessage());
        }
    }

    //Metodo para realizar diversas consultas como inset , update, delete
    public int consultas_multiples(String consulta) {
        int resultado;
        try {
            this.conectar();
            this.st = this.con.createStatement();
            resultado = this.st.executeUpdate(consulta);
        } catch (Exception e) {
            System.out.println("Mensaje 3 " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Este registro, tiene registros que dependen de el, no se puede eliminar por la llave forania");
            return 0;
        } finally {
            this.desconectar();
        }
        return resultado;
    }

    //Metodo para obtener datos de una tabla en base de datos, select
    public ResultSet consulta_datos(String consulta) {
        try {
            this.conectar();
            ResultSet resultado = null;
            this.st = this.con.createStatement();
            resultado = st.executeQuery(consulta);
            return resultado;
        } catch (Exception e) {
            System.out.println("Mensaje 4 " + e.getMessage());

        }
        return null;
    }

      public Connection getcon(){
       return con;
   }

}
