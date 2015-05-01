package edu.poli.automatas.logica;

import java.util.List;

/**
 * Created by juanmartinez on 20/03/15.
 */
public class Automata {

    private String descripcion;
    private String alfabeto;
    private List<Estado> estados;

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

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    /**
     * Define los estados inicales y finales
     * @param estadoInicial
     * @param estadosAceptaion
     */
    public void definirEstadosInicialYAcepacion(String estadoInicial, List<String> estadosAceptaion) {
        for(Estado estado: estados) {
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
    public Estado darEstadoInicial() {
        Estado inicial = null;
        for(Estado estado : estados){
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
    public Estado darEstadoPorNombre(String nombre) {
        Estado estadoEncontrado = null;
        for(Estado estado: estados) {
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
        for(Estado estado : estados){
            stringBuffer.append(" { " + "\n");
            stringBuffer.append("  Nombre -> " + estado.getNombre() + "\n");
            stringBuffer.append("  Es inicial -> " + estado.isInicial() + "\n");
            stringBuffer.append("  Es aceptacion -> " + estado.isAceptacion() + "\n");
            stringBuffer.append("  Transiciones ->\n");
            for(Transicion transicion:estado.getTransiciones()){
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
        for(Estado estado: estados){
            estado.setInicial(false);
            estado.setAceptacion(false);
        }
    }

    public String simulacion(String cadena) {
        String resultado = "";
        Estado estadoActual = this.darEstadoInicial();
        if(cadena.isEmpty())
            resultado += "La cadena \""+cadena+"\" no pertenece al lenguaje L.\n\n";
        else {
            for (int i = 0; i < cadena.length(); i++) {
                Transicion transicion = estadoActual.darTransacion(String.valueOf(cadena.charAt(i)));
                if (transicion == null) {
                    estadoActual = null;
                    break;
                } else {
                    resultado += "(" + estadoActual.getNombre() + "," + transicion.getSimbolo() + ") => " + transicion.getEstado().getNombre() + "\n";
                    estadoActual = transicion.getEstado();
                }
            }
            if (estadoActual != null && estadoActual.isAceptacion())
                resultado += "La cadena \"" + cadena + "\" pertenece al lenguaje L.\n\n";
            else
                resultado += "La cadena \"" + cadena + "\" no pertenece al lenguaje L.\n\n";
        }
        return resultado;
    }

}
