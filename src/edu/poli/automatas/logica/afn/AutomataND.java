package edu.poli.automatas.logica.afn;

import edu.poli.automatas.logica.afd.Estado;
import edu.poli.automatas.logica.afd.Transicion;

import java.util.List;

/**
 * Created by juanmartinez on 5/06/15.
 */
public class AutomataND {

    private String descripcion;
    private String alfabeto;
    private List<EstadoAFN> estados;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    public List<EstadoAFN> getEstados() {
        return estados;
    }

    public void setEstados(List<EstadoAFN> estados) {
        this.estados = estados;
    }

    /**
     * Define los estados inicales y finales
     * @param estadoInicial
     * @param estadosAceptaion
     */
    public void definirEstadosInicialYAcepacion(String estadoInicial, List<String> estadosAceptaion) {
        for(EstadoAFN estado: estados) {
            String nombre = estado.getNombre();
            if(nombre.equals(estadoInicial)) {
                estado.setInicial(true);
                if(estadosAceptaion.contains(nombre)){
                    estado.setAceptacion(true);
                }
            } else {
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
    public EstadoAFN darEstadoInicial() {
        EstadoAFN inicial = null;
        for(EstadoAFN estado : estados){
            if(estado.isInicial()){
                inicial = estado;
                break;
            }
        }
        return inicial;
    }

    /**
     * Retorna el estado que coincide por con el nombre enviado por parametro
     * @param nombre
     * @return
     */
    public EstadoAFN darEstadoPorNombre(String nombre) {
        EstadoAFN estadoEncontrado = null;
        for(EstadoAFN estado: estados) {
            if(estado.getNombre().equals(nombre)) {
                estadoEncontrado = estado;
                break;
            }
        }
        return estadoEncontrado;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Descripción --> " + this.descripcion + "\n");
        stringBuffer.append("Alfabeto --> " + this.alfabeto + "\n");
        stringBuffer.append("Estados --> " + "\n" );
        for(EstadoAFN estado : estados){
            stringBuffer.append(" { " + "\n");
            stringBuffer.append("  Nombre -> " + estado.getNombre() + "\n");
            stringBuffer.append("  Es inicial -> " + estado.isInicial() + "\n");
            stringBuffer.append("  Es aceptacion -> " + estado.isAceptacion() + "\n");
            stringBuffer.append("  Transiciones ->\n");
            for(TransicionAFN transicion:estado.getTransiciones()){
                stringBuffer.append("   { " + "\n");
                stringBuffer.append("    Simbolo -> " + transicion.getSimbolo() + "\n");
                stringBuffer.append("    Estado destino -> " + transicion.getEstado().getNombre() + "\n");
                stringBuffer.append("   } " + "\n");
            }
            stringBuffer.append(" } " + "\n");
        }
        return stringBuffer.toString();
    }

    public void limpiarEstados(){
        for(EstadoAFN estado: estados){
            estado.setInicial(false);
            estado.setAceptacion(false);
        }
    }
}
