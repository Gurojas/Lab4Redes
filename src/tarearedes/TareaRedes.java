/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarearedes;

import java.util.ArrayList;

/**
 *
 * @author Gustavo Rojas
 */
public class TareaRedes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Host emisor = new Host(100,"AA:AA:AA:AA:AA:AA","Hola como estas?");
        Host emisor2 = new Host(50,"CC:CC:CC:CC:CC:CC","oie a que hora entramos mañana?");
        Host emisor3 = new Host(20,"DD:DD:DD:DD:DD:DD","no quiero ir a clases");
        
        ArrayList<Host> emisores = new ArrayList<>();
        emisores.add(emisor);
        emisores.add(emisor2);
        emisores.add(emisor3);
        
        Host receptor = new Host(200,"BB:BB:BB:BB:BB:BB");
        ModeloTCP_IP modeloTCP_IP = new ModeloTCP_IP();
        
        
        modeloTCP_IP.simular(emisores, receptor);
       
        

       
    }
    
}
