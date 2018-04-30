/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarearedes;

/**
 *
 * @author Gustavo Rojas
 */
public class Segmento {
    
     private String datos;
     private int header;
     
     public Segmento(int header, String datos){
         this.header = header;
         this.datos = datos;
     }

    public String getDatos() {
        return datos;
    }

    public int getHeader() {
        return header;
    }
     
     
    
}
