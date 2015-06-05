package edu.poli.automatas.input;

import edu.poli.automatas.logica.afd.Automata;
import edu.poli.automatas.logica.afd.Estado;
import edu.poli.automatas.logica.afd.Transicion;
import edu.poli.automatas.logica.afn.AutomataND;
import edu.poli.automatas.logica.afn.CerraduraEpsilon;
import edu.poli.automatas.logica.afn.EstadoAFN;
import edu.poli.automatas.logica.afn.TransicionAFN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by juanmartinez on 5/06/15.
 */
public class AFNaADF {

    private BufferedReader reader;

    public AFNaADF() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void convertirAFNaAFD(AutomataND automata) {

    }

//    private Estado darCerraduraEpsilonInicial(Estado estadoInicial) {
//
//    }

    /**
     * Calcula la cerradura epsilon de una lista de estados
     * @param estadosArranque
     * @return
     */
    private CerraduraEpsilon calcularCerraduraEpsilon(List<EstadoAFN> estadosArranque) {
        CerraduraEpsilon cerraduraEpsilon = new CerraduraEpsilon();
        List<EstadoAFN> estadosAlcanzables = new ArrayList<EstadoAFN>();
        Queue<EstadoAFN> estadosRevisados = new LinkedList<EstadoAFN>();
        cerraduraEpsilon.setEstadosDeArranque(estadosArranque);
        for(EstadoAFN estadoArranque: estadosArranque) {
            estadosRevisados.add(estadoArranque);
            while (!estadosRevisados.isEmpty()) {
                EstadoAFN estadoActual = estadosRevisados.poll();
                if (!estadoActual.getEstadoVisitado()) {
                    for (TransicionAFN transicionAfn : estadoActual.darTransiciones("e")) {
                        EstadoAFN estadoDestino = transicionAfn.getEstado();
                        if (contieneEstado(estadosAlcanzables, estadoDestino)) {
                            estadosAlcanzables.add(estadoDestino);
                            estadosRevisados.add(estadoDestino);
                        }
                    }
                    estadoActual.setEstadoVisitado(true);
                }
            }
        }
        cerraduraEpsilon.setEstadosConEpsilon(estadosAlcanzables);
        return cerraduraEpsilon;
    }

    private boolean contieneEstado(List<EstadoAFN> estados, EstadoAFN estado) {
        for(EstadoAFN estadoAFN : estados) {
            if(estadoAFN.getNombre().equals(estado.getNombre()))
                return true;
        }
        return false;
    }

}

