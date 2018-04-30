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
public class Paquete {
    
    private Segmento segmento;
    private String red;
    private int idHostEmisor;
    private int idHostReceptor;
    
    public Paquete(Segmento segmento, String red, int idHostEmisor, int idHostReceptor){
        this.segmento = segmento;
        this.red = red;
        this.idHostEmisor = idHostEmisor;
        this.idHostReceptor = idHostReceptor;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setPaquete(Segmento segmento) {
        this.segmento = segmento;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public int getIdHostEmisor() {
        return idHostEmisor;
    }

    public void setIdHostEmisor(int idHostEmisor) {
        this.idHostEmisor = idHostEmisor;
    }

    public int getIdHostReceptor() {
        return idHostReceptor;
    }

    public void setIdHostReceptor(int idHostReceptor) {
        this.idHostReceptor = idHostReceptor;
    }
    
    
}
