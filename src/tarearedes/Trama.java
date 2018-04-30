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
public class Trama {
    
    private Paquete paquete;
    
    public Trama(Paquete paquete){
        this.paquete = paquete;
    }
    

    public String tramaString(){
        String encabezadoTransporte = String.valueOf(this.paquete.getSegmento().getHeader());
        String datos = this.paquete.getSegmento().getDatos();
        String red = this.paquete.getRed();
        String idHostEmisor = String.valueOf(this.paquete.getIdHostEmisor());
        String idHostReceptor = String.valueOf(this.paquete.getIdHostReceptor());
        
        String trama = red+" "+idHostEmisor+" "+red+" "+idHostReceptor+" "+encabezadoTransporte+" "+datos;

        return trama;
    }
    
}
