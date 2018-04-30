/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarearedes;

import java.math.BigInteger;
import java.util.HashMap;

/**
 *
 * @author Gustavo Rojas
 */
public class Host {
    
    public static final String RED = "192.168.1.";

    private int idHost;
    private String direccionMAC;
    private String mensaje;
    
    private HashMap<String,String> mensajesOriginalesEmisores;

    /* Constructor para el emisor */
    public Host(int idHost, String direccionMAC, String mensaje){
        this.idHost = idHost;
        this.direccionMAC = direccionMAC;
        this.mensaje = mensaje;
        
        this.mensajesOriginalesEmisores = new HashMap<>();
    }
    /* Constructor para el receptor */
    public Host(int idHost, String direccionMAC){
        this.idHost = idHost;
        this.direccionMAC = direccionMAC;
        
        this.mensajesOriginalesEmisores = new HashMap<>();
    }
    
    
    public void reciboMensaje(String bits){
        String trama = new String(new BigInteger(bits,2).toByteArray());
        
        String ocurrencia = " "; // los mensajes de la trama se separan por un espacio
        String dato = "";
       
        int i = 0;
        
        String ipEmisor =  "";
        String ipReceptor = "";
        int numeroTrama = 0;
        String datos = "";
        int elementosTrama = 5; // los elementos de la trama tienen 5 elementos
        while (i < elementosTrama){
            int index = trama.indexOf(ocurrencia);
            dato = trama.substring(0, index);
            
            if (i == 0 || i == 1){
                ipEmisor = ipEmisor + dato;
            }
            else if (i == 2 || i == 3){
                ipReceptor = ipReceptor + dato;
            }
            else if (i == 4){
                numeroTrama = Integer.valueOf(dato);
            }
            
            trama = trama.substring(index+1);
            i++;
        }
        
        datos = trama;
        if (!datos.equals("")){
            System.out.println("Envio desde: "+ipEmisor);
            System.out.println("Hacia: "+ipReceptor);
            System.out.println("Numero paquete: "+numeroTrama);
            System.out.println("Contenido: "+datos);
            System.out.println(" ------------------------------- ");

            if (!this.mensajesOriginalesEmisores.containsKey(ipEmisor)){
                this.mensajesOriginalesEmisores.put(ipEmisor, datos);
            }
            else{
                String elemento = this.mensajesOriginalesEmisores.get(ipEmisor);
                elemento = elemento + datos;
                this.mensajesOriginalesEmisores.put(ipEmisor, elemento);
            }
        }
        
    }
    
    public void mostrarMensajesHosts(){
        System.out.println("--------- Mensajes originales enviados ----------");
        for(String ipEmisor : this.mensajesOriginalesEmisores.keySet()){
            System.out.println("Envio desde: "+ipEmisor);
            System.out.println("Hacia: "+Host.RED+this.idHost);
            System.out.println("Contenido: "+this.mensajesOriginalesEmisores.get(ipEmisor));
            System.out.println(" ------------------------------- ");
        }
    }
   
    public int getIdHost() {
        return this.idHost;
    }

    public String getDireccionMAC() {
        return direccionMAC;
    }
    
    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }
    
    public String getMensaje(){
        return this.mensaje;
    }
    

}

/*
        System.out.println("Envio desde: "+ipEmisor);
        System.out.println("Hacia: "+ipReceptor);
        System.out.println("Numero paquete: "+numeroTrama);
        System.out.println("Contenido: "+datos);
        System.out.println(" ------------------------------- ");
        
        if (!this.mensajesOriginalesEmisores.containsKey(ipEmisor)){
            this.mensajesOriginalesEmisores.put(ipEmisor, datos);
        }
        else{
            String elemento = this.mensajesOriginalesEmisores.get(ipEmisor);
            elemento = elemento + datos;
            this.mensajesOriginalesEmisores.put(ipEmisor, elemento);
        }
        */