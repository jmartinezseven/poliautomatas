package edu.poli.automatas.logica.afd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Juan Sebastian Martinez, Esteban Bautista Clavijo on 18/03/2015.
 */
public class Estado {

    private String nombre;
    private boolean inicial;
    private boolean aceptacion;
    private HashSet<Transicion> transiciones;

    public Estado(String nombre, HashSet<Transicion> transiciones, boolean inicial, boolean aceptacion) {
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

    public HashSet<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(HashSet<Transicion> transiciones) {
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

    public List<Transicion> darTransicionesComoLista(){
        List<Transicion> listaTransiciones = new ArrayList<Transicion>();
        for(Transicion trans : transiciones) {
            listaTransiciones.add(trans);
        }
        return listaTransiciones;
    }

//    @Override
//    public String toString() {
//        StringBuffer transiciones = new StringBuffer();
//        for(Transicion transicion: getTransiciones()){
//            transiciones.append(transicion.toString() + "\n");
//        }
//        return "Estado{" +
//                "nombre=" + nombre +
//                ", inicial =" + inicial +
//                ", aceptacion =" + aceptacion +
//                ", transiciones = " + transiciones.toString()+
//                '}';
//    }

    /**
     * Agrega una transición al conjunto de transiciones del estado
     * @param transicion
     */
    public void agregarTransicion(Transicion transicion) {
        transiciones.add(transicion);
    }
}
