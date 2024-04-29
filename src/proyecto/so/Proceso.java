/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.so;
/**
 *Podemos interpretar esto como un nodo
 * con todos los datos de cada proveso
 * su nombre, tiempo de llegada, cantidad de rafagas, y prioridad
 */
public class Proceso {

    public Proceso(String nombre, int tiempo_De_Llegada, int cantidad_De_Rafagas, int prioridad) {
        this.nombre = nombre;
        this.tiempoLlegada = tiempo_De_Llegada;
        this.cantRafagas = cantidad_De_Rafagas;
        this.prioridad = prioridad;
    }
    

    public String getNombre() {
        return nombre;
    }

    public int getTiempo_De_Llegada() {
        return tiempoLlegada;
    }

    public int getCantidad_De_Rafagas() {
        return cantRafagas;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempo_De_Llegada(int tiempo_De_Llegada) {
        this.tiempoLlegada = tiempo_De_Llegada;
    }

    public void setCantidad_De_Rafagas(int cantidad_De_Rafagas) {
        this.cantRafagas = cantidad_De_Rafagas;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    //Compara por nombre.
    public boolean equals(Proceso A,Proceso B){      
        return A.nombre.equals(B.nombre);
    }
    
    public String[] getString(){
        String[] cadena = {getNombre(), getTiempo_De_Llegada()+""
        , getCantidad_De_Rafagas()+"", getPrioridad()+""};
        
        return cadena;
    }
    
    private String nombre;
    private int tiempoLlegada;
    private int cantRafagas;
    private int prioridad;
}
