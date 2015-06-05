package edu.poli.automatas.logica.afn;

import edu.poli.automatas.logica.afd.Transicion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Juan Sebastian Martinez, Esteban Bautista Clavijo on 18/03/2015.
 */
public class EstadoAFN {

    private String nombre;
    private boolean inicial;
    private boolean aceptacion;
    private List<TransicionAFN> transiciones;
    private Boolean estadoVisitado;

    public EstadoAFN(String nombre, List<TransicionAFN> transiciones, boolean inicial, boolean aceptacion, boolean visitado) {
        this.nombre = nombre;
        this.transiciones = transiciones;
        this.inicial = inicial;
        this.aceptacion = aceptacion;
        this.estadoVisitado = visitado;
    }

    public boolean isInicial() {
        return inicial;
    }

    public Boolean getEstadoVisitado() {
        return estadoVisitado;
    }

    public void setEstadoVisitado(Boolean estadoVisitado) {
        this.estadoVisitado = estadoVisitado;
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

    public List<TransicionAFN> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(List<TransicionAFN> transiciones) {
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
    public TransicionAFN darTransacion(String simbolo) {
        TransicionAFN transicionSeleccionada = null;
        for(TransicionAFN transicion: this.transiciones) {
            if(transicion.getSimbolo().equals(simbolo)){
                transicionSeleccionada = transicion;
                break;
            }
        }
        return transicionSeleccionada;
    }

    /**
     *
     * @param simbolo
     * @return
     */
    public List<TransicionAFN> darTransiciones(String simbolo) {
        List<TransicionAFN> transiciones = new ArrayList<TransicionAFN>();
        for(TransicionAFN transicion: this.transiciones) {
            if(transicion.getSimbolo().equals(simbolo)){
                transiciones.add(transicion);
            }
        }
        return transiciones;
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
    public void agregarTransicion(TransicionAFN transicion) {
        transiciones.add(transicion);
    }
}
