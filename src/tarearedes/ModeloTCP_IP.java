/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarearedes;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Rojas
 */
public class ModeloTCP_IP {
    

            
    public ModeloTCP_IP(){
       
    }
    
    /* Metodo que empieza la simulacion */
    public void simular(ArrayList<Host> emisores, Host receptor){
        ArrayList<String> datos = capaAplicacion(emisores);
        capaTransporte(datos, emisores, receptor);
        
        receptor.mostrarMensajesHosts();
        
    }
    
    /* Esta capa se encarga de definir el contenido y el formato de las 
        solicitudes entre el cliente y el servidor */
    public ArrayList<String> capaAplicacion(ArrayList<Host> emisores){
        
        ArrayList<String> datosEmisores = new ArrayList<>();
        /* Mensaje de cada */
        //String mensajes[] = {"Hola como estas?","oie lala","nose"};
        
        for (int i = 0; i < emisores.size(); i++) {
            datosEmisores.add(emisores.get(i).getMensaje());
        }
       
        return datosEmisores;
    }
    /* Esta capa administra las conversaciones individuales entre servidores
        y clientes web. TCP divide los mensajes HTTP en partes pequeñas
        llamadas segmentos */
    private void capaTransporte(ArrayList<String> datosEmisores, ArrayList<Host> emisores ,Host receptor){
        
        /* Guardara los segmentos de los emisores */
        ArrayList<ArrayList<Segmento>> segmentos = new ArrayList<>();
         
        int max = 0;
        /* segmento los datos de cada emisor envio*/
        for (String datos : datosEmisores){
            /* Esta lista guardara los segmentos de cada emisor */
            ArrayList<Segmento> segmentosEmisor = new ArrayList<>();
            /* Limite del buffer para enviar los mensajes */
            int maxBuffer = 10;
            String datosEmisor = datos;
            String datosSegmentado = "";
            
            /* En esta parte segmento los mensaje de cada emisor */
            
            int i = 0;
            while (!datosEmisor.equals("")){
                if (datosEmisor.length() < maxBuffer){
                    datosSegmentado = datosEmisor.substring(0);
                    datosEmisor = datosEmisor.substring(datosEmisor.length());
                }
                else{
                    datosSegmentado = datosEmisor.substring(0, maxBuffer);
                    datosEmisor = datosEmisor.substring(maxBuffer);
                            
                }
                /* Agrego los mensajes fragmentados a la lista de segmentos
                correspondiente de cada emisor*/
                segmentosEmisor.add(new Segmento(i,datosSegmentado));
                i++;
            }
            int size = segmentosEmisor.size();
            if (size > max){
                max = size;
            }
            segmentos.add(segmentosEmisor);
        }
        
        /* Ahora tomo los segmentos de cada emisor */
        for (int i = 0; i < max; i++) {
            ArrayList<Segmento> segmentosMultiplexados = new ArrayList<>();
            for (int j = 0; j < segmentos.size(); j++) {
                ArrayList<Segmento> segmentosEmisor = segmentos.get(j);
                int size = segmentosEmisor.size();
                if (i < size){
                    segmentosMultiplexados.add(segmentosEmisor.get(i));
                }
                else{
                    segmentosMultiplexados.add(new Segmento(i,""));
                }
                
            }
            /* Los segmentos de cada emisor los envio hacia la capa de internet */
            capaInternet(segmentosMultiplexados, emisores, receptor);
            
        }

    }
    
    /* Responsable de tomar los paquetes con formato TCP, encapsularlos en
    paquetes, asignarles direcciones adecuadas y enviarlos a traves del mejor
    camino hacia el host de destino */
    private void capaInternet(ArrayList<Segmento> segmentos, ArrayList<Host> emisores, Host receptor){
        
        /* Envio cada paquete con datos hacia la capa de acceso de red */
        for (int i = 0; i < segmentos.size(); i++) {
            Host emisor = emisores.get(i);
            Segmento segmento = segmentos.get(i);
            /* Los segmentos los encapsulo en paquetes y le agrego las ip del 
            emisor y receptor*/
            Paquete paquete = new Paquete(segmento,Host.RED,emisor.getIdHost(),receptor.getIdHost());
            /* Los paquetes viajan hasta la ultima capa */
            capaAccesoRed(paquete, receptor);
        }
        

    }
    /*  */
    private void capaAccesoRed(Paquete paquete, Host receptor){
        /* Encapsulo los paquete en tramas */
        Trama trama = new Trama(paquete);
        /* Convierto los datos en un string para trabajarlo mejor al momento
        de convertirlos en bits*/
        String tramaString = trama.tramaString();
        /* Aqui los datos de la trama se convierten en bits */
        String bitsStream = new BigInteger(tramaString.getBytes()).toString(2);
        /* Ahora el flujo de bits se envia hacia el receptor */
        receptor.reciboMensaje(bitsStream);
          
    }
    
}
