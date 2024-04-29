/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.so;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Lenovo
 */
public class Correr {
        public static void main(String[] args) {
        LeerArchivo csv = new LeerArchivo();


        ArrayList<Proceso> datos = new ArrayList();
        datos = csv.leerArchivo("C:\\Users\\PC\\Desktop\\procesos1.csv");
        ArrayList<Proceso> datosAuxiliar = new ArrayList();
        DiagramaGantt dg = new DiagramaGantt(datos, 1);

        for (int i = 0; i < datos.size(); i++) {
            datosAuxiliar.add(datos.get(i));
        }

        Collections.sort(datosAuxiliar, new ProcesoComparator());
        ArrayList<String> nombresProcesos = new ArrayList();
        ListaTiempos lista = new ListaTiempos();
        
        //SJF(datos,datosAuxiliar,nombresProcesos,lista);
       // dg.generarSJFSinDesalojo();
        //SJFe(datos, datosAuxiliar, nombresProcesos, lista);
        dg.generarSJFDesalojo();
        //ROUND_ROBIN(datos, datosAuxiliar, nombresProcesos, lista);
        //dg.generarRoundRobin();
        
        
                                                  
    }
        
        
        
        
        
   public static void SJF(ArrayList<Proceso> datos, ArrayList<Proceso> datosAuxiliar, ArrayList<String> nombresProcesos,ListaTiempos lista ){
       System.out.println("--------------- SJF No EXPULSIVO --------------------------");
        SJFNoExpulsivo sjfne = new SJFNoExpulsivo();
        sjfne.SJFNoExpulsivo(datos);
        nombresProcesos = sjfne.getSJFNoExpulsivo(); 
        
            System.out.println("Tiempo Espera| Tiempo Ejecucion| Tiempo respuesta");
        lista.ListaTiempos(datosAuxiliar, nombresProcesos);
        lista.retornarDatos();

        sjfne.imprimir();
   }     
    
   public static void SJFe(ArrayList<Proceso> datos, ArrayList<Proceso> datosAuxiliar, ArrayList<String> nombresProcesos,ListaTiempos lista ){
       System.out.println("--------------- SJF EXPULSIVO --------------------------");
       SJFExpulsivo sjfe = new SJFExpulsivo();
        sjfe.SJFExpulsivo(datos);
        nombresProcesos = sjfe.getNombresSJFExpulsivo(); 
            System.out.println("Tiempo Espera| Tiempo Ejecucion| Tiempo respuesta");
        lista.ListaTiempos(datosAuxiliar, nombresProcesos);
        lista.retornarDatos();

        sjfe.imprimir(); 
   }
   
   
   
       public static void ROUND_ROBIN(ArrayList<Proceso> datos, ArrayList<Proceso> datosAuxiliar, ArrayList<String> nombresProcesos,ListaTiempos lista ){
       System.out.println("------------- ROUND ROBIN -------------------------");
         RoundRobin rr = new RoundRobin();
        
        rr.roundRobin(datos, 4);//El valor 4 puede cambiar EJ: 2/6/8/10....
        nombresProcesos = rr.getNombresRoundRobin(); 

            System.out.println("Tiempo Espera| Tiempo Ejecucion| Tiempo respuesta");
        lista.ListaTiempos(datosAuxiliar, nombresProcesos);
        lista.retornarDatos();

        rr.imprimir();
   }  
        
        
        
}
