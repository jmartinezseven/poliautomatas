package edu.poli.automatas.logica;

import java.util.List;

/**
 * Created by juanmartinez on 20/03/15.
 */
public class Automata {

    private String descripcion;
    private String alfabeto;
    private List<Estado> estados;

    /**
     * Define los estados inicales y finales
     * @param estadoInicial
     * @param estadosAceptaion
     */
    public void definirEstadosInicialYAcepacion(String estadoInicial, List<String> estadosAceptaion) {
        for(Estado estado: estados) {
            if(estado.getNombre().equals(estadoInicial)) {
                estado.setInicial(true);
            } else {
                String nombre = estado.getNombre();
                if(estadosAceptaion.contains(nombre)){
                    estado.setAceptacion(true);
                }
            }
        }
    }

    /**
     * Retorna el estado inicial del automata
     * @return
     */
    private Estado darEstadoInicial() {
        Estado inicial = null;
        for(Estado estado : estados){
            if(estado.isAceptacion()){
                inicial = estado;
                break;
            }
        }
        return inicial;
    }

    public String simulacion() {
        null;
    }

}
