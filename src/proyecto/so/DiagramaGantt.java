/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.so;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DiagramaGantt {

    private ArrayList<Proceso> listaProcesos;
    private int quantum;

    public DiagramaGantt(ArrayList<Proceso> listaProcesos, int quantum) {
        this.listaProcesos = listaProcesos;
        this.quantum = quantum;
    }

    // Método para generar el diagrama de Grantt para el algoritmo SJF con desalojo
    public void generarSJFDesalojo() {
        // Ordenar la lista de procesos por su tiempo de llegada
        Collections.sort(listaProcesos, Comparator.comparing(Proceso::getTiempo_De_Llegada));

        int tiempoActual = 0;
        int totalRafagas = listaProcesos.stream().mapToInt(Proceso::getCantidad_De_Rafagas).sum();
        ArrayList<Proceso> procesosListos = new ArrayList<>();
        ArrayList<Integer> tiempos = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();

        while (tiempoActual < totalRafagas) {
            // Verificar si hay procesos que llegaron al tiempo actual
            for (Proceso p : listaProcesos) {
                if (p.getTiempo_De_Llegada() == tiempoActual && !procesosListos.contains(p)) {
                    procesosListos.add(p);
                }
            }

            // Si hay procesos listos, elegir el que tenga menor cantidad de rafagas restantes
            if (!procesosListos.isEmpty()) {
                Proceso procesoElegido = procesosListos.get(0);
                for (Proceso p : procesosListos) {
                    if (p.getCantidad_De_Rafagas() < procesoElegido.getCantidad_De_Rafagas()) {
                        procesoElegido = p;
                    }
                }

                // Agregar el tiempo actual, el nombre y la duración de la rafaga del proceso elegido
                tiempos.add(tiempoActual);
                nombres.add(procesoElegido.getNombre());
                procesoElegido.setCantidad_De_Rafagas(procesoElegido.getCantidad_De_Rafagas() - 1);
                if (procesoElegido.getCantidad_De_Rafagas() == 0) {
                    procesosListos.remove(procesoElegido);
                }

            } else { // Si no hay procesos listos, agregar tiempo sin procesos
                tiempos.add(tiempoActual);
                nombres.add("-");
            }

            tiempoActual++;
        }

        // Mostrar el diagrama de Grantt
        mostrarDiagrama(tiempos, nombres);
    }

    // Método para generar el diagrama de Grantt para el algoritmo SJF sin desalojo
    public void generarSJFSinDesalojo() {
        // Ordenar la lista de procesos por su tiempo de llegada y su cantidad de rafagas
        Collections.sort(listaProcesos, Comparator.comparing(Proceso::getTiempo_De_Llegada).thenComparing(Proceso::getCantidad_De_Rafagas));

        int tiempoActual = 0;
        int totalRafagas = listaProcesos.stream().mapToInt(Proceso::getCantidad_De_Rafagas).sum();

        ArrayList<Proceso> procesosListos = new ArrayList<>();
        ArrayList<Integer> tiempos = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();

        while (tiempoActual < totalRafagas) {
            // Verificar si hay procesos que llegaron al tiempo actual
            for (Proceso p : listaProcesos) {
                if (p.getTiempo_De_Llegada() == tiempoActual && !procesosListos.contains(p)) {
                    procesosListos.add(p);
                }
            }

            // Si hay procesos listos, elegir el que tenga menor cantidad de rafagas
            if (!procesosListos.isEmpty()) {
                Proceso procesoElegido = procesosListos.get(0);
                for (Proceso p : procesosListos) {
                    if (p.getCantidad_De_Rafagas() < procesoElegido.getCantidad_De_Rafagas()) {
                        procesoElegido = p;
                    }
                }

                // Agregar el tiempo actual, el nombre y la duración de la rafaga del proceso elegido
                tiempos.add(tiempoActual);
                nombres.add(procesoElegido.getNombre());
                procesoElegido.setCantidad_De_Rafagas(procesoElegido.getCantidad_De_Rafagas() - 1);

            } else { // Si no hay procesos listos, agregar tiempo sin procesos
                tiempos.add(tiempoActual);
                nombres.add("-");
            }

            tiempoActual++;
        }

        // Mostrar el diagrama de Grantt
        mostrarDiagrama(tiempos, nombres);
    }

// Método para generar el diagrama de Grantt para el algoritmo Round Robin
    public void generarRoundRobin() {
        // Ordenar la lista de procesos por su tiempo de llegada
        Collections.sort(listaProcesos, Comparator.comparing(Proceso::getTiempo_De_Llegada));

        int tiempoActual = 0;
        int totalRafagas = listaProcesos.stream().mapToInt(Proceso::getCantidad_De_Rafagas).sum();
        ArrayList<Proceso> procesosListos = new ArrayList<>();
        ArrayList<Integer> tiempos = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();

        while (tiempoActual < totalRafagas) {
            // Verificar si hay procesos que llegaron al tiempo actual
            for (Proceso p : listaProcesos) {
                if (p.getTiempo_De_Llegada() == tiempoActual && !procesosListos.contains(p)) {
                    procesosListos.add(p);
                }
            }

            // Si hay procesos listos, elegir el primer proceso de la lista
            if (!procesosListos.isEmpty()) {
                Proceso procesoElegido = procesosListos.get(0);

                // Agregar el tiempo actual, el nombre y la duración de la rafaga del proceso elegido
                tiempos.add(tiempoActual);
                nombres.add(procesoElegido.getNombre());
                procesoElegido.setCantidad_De_Rafagas(procesoElegido.getCantidad_De_Rafagas() - 1);

                // Si el proceso no ha terminado, agregarlo al final de la lista
                if (procesoElegido.getCantidad_De_Rafagas() > 0) {
                    procesosListos.remove(procesoElegido);
                    procesosListos.add(procesoElegido);
                }

            }
        }
        mostrarDiagrama(tiempos, nombres);
    }

    private void mostrarDiagrama(ArrayList<Integer> tiempos, ArrayList<String> nombres) {
        System.out.println("Diagrama de Grantt:");
        for (int i = 0; i < tiempos.size(); i++) {
            System.out.print("+---");
        }
        System.out.println("+");
        for (int i = 0; i < tiempos.size(); i++) {
            System.out.printf("| %s ", nombres.get(i));
        }
        System.out.println("|");
        for (int i = 0; i < tiempos.size(); i++) {
            System.out.print("+---");
        }
        System.out.println("+");
        for (int i = 0; i < tiempos.size(); i++) {
            System.out.printf("%-3d ", tiempos.get(i));
        }
        System.out.println();
    }

}


