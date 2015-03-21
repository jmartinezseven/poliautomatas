package edu.poli.automatas.logica;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by juanmartinez on 20/03/15.
 */
public class Estado {

    private String nombre;
    private boolean inicial;
    private boolean aceptacion;
    private TreeSet<Transicion> transiciones;

    public Estado(String nombre, TreeSet<Transicion> transiciones, boolean inicial, boolean aceptacion) {
        this.nombre = nombre;
        this.transiciones = transiciones;
        this.inicial = inicial;
        this.aceptacion = aceptacion;
    }

    public boolean isInicial() {
        return inicial;
    }

    public void setInicial(boolean inicial) {
        this.inicial = inicial;
    }

    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public Set<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(TreeSet<Transicion> transiciones) {
        this.transiciones = transiciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Da la transicion de un estado dependiendo del simbolo enviado por parametro
     * @param simbolo
     * @return
     */
    public Transicion darTransacion(String simbolo) {
        Transicion transicionSeleccionada = null;
        for(Transicion transicion: this.transiciones) {
            if(transicion.getSimbolo().equals(simbolo)){
                transicionSeleccionada = transicion;
                break;
            }
        }
        return transicionSeleccionada;
    }

    /**
     * Agrega una transición al conjunto de transiciones del estado
     * @param transicion
     */
    public void agregarTransicion(Transicion transicion) {
        transiciones.add(transicion);
    }
}
