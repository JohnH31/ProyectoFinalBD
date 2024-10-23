/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario Local
 */
public class GruposPer {
    int id;
    int idgrupo;
    int idper;

    public GruposPer() {
    }

    public GruposPer(int id, int idgrupo, int idper) {
        this.id = id;
        this.idgrupo = idgrupo;
        this.idper = idper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(int idgrupo) {
        this.idgrupo = idgrupo;
    }

    public int getIdper() {
        return idper;
    }

    public void setIdper(int idper) {
        this.idper = idper;
    }
    
    
}
