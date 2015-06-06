package edu.poli.automatas.input;

import edu.poli.automatas.logica.afd.Automata;
import edu.poli.automatas.logica.afd.Estado;
import edu.poli.automatas.logica.afd.Transicion;
import edu.poli.automatas.logica.afn.AutomataND;
import edu.poli.automatas.logica.afn.CerraduraEpsilon;
import edu.poli.automatas.logica.afn.EstadoAFN;
import edu.poli.automatas.logica.afn.TransicionAFN;

import java.io.BufferedReader;
import java.io.IOException;
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

    public void convertirAFNaAFD() throws IOException{

        AutomataND automata = construirAutomata();
        List<Estado> estadosAfn = new LinkedList<Estado>();
        Automata afd = new Automata();
        EstadoAFN estadoInicial = automata.darEstadoInicial();
        CerraduraEpsilon epsilonInicial = darCerraduraEpsilonInicial(estadoInicial);
        Estado nuevoEstadoInicial = new Estado(epsilonInicial.getNombreEstado(), null, true, false);
        estadosAfn.add(nuevoEstadoInicial);

        for(int i = 0; i < estadosAfn.size(); i++){
            String[] alfabeto = automata.getAlfabeto().split(",");
            Estado estadoNuevo = new Estado("",null,false,false);
            HashSet<Transicion> transiciones = new HashSet<Transicion>();
            for(String letra : alfabeto) {
                Transicion transicion = new Transicion();
                transicion.setSimbolo(letra);
                String[] estadosArranqueString = estadoNuevo.getNombre().replace("{","").replace("}", "").replace("u", "").split(",");
                List<EstadoAFN> estadosVerify = getEstadosArranque(automata, estadosArranqueString);
                List<EstadoAFN> estadosArranque = new ArrayList<EstadoAFN>();
                for(EstadoAFN estAfn : estadosVerify){
                    List<TransicionAFN> trrafns = estAfn.darTransiciones(letra);
                    for(TransicionAFN trns : trrafns){
                        estadosArranque.add(trns.getEstado());
                    }
                }
                CerraduraEpsilon epsilon = calcularCerraduraEpsilon(estadosVerify);
                Estado estadoDestino = new Estado(epsilon.getNombreEstado(), null, false,false);
                transicion.setEstado(estadoDestino);
                transiciones.add(transicion);
            }
            if(contieneEstadoAfd(estadosAfn, estadoNuevo)) {
                estadosAfn.add(estadoNuevo);
            }
            estadoNuevo.setTransiciones(transiciones);
            afd.setEstados(estadosAfn);
            afd.setAlfabeto(automata.getAlfabeto());
            afd.setDescripcion(automata.getDescripcion() + " En automata finito determinista");
        }
    }

    private List<EstadoAFN> getEstadosArranque(AutomataND automata, String[] estados){
        List<EstadoAFN> estadosArranque = new ArrayList<EstadoAFN>();
        for(String nombre : estados){
            estadosArranque.add(automata.darEstadoPorNombre(nombre));
        }
        return estadosArranque;
    }

    private CerraduraEpsilon darCerraduraEpsilonInicial(EstadoAFN estadoInicial) {
        List<EstadoAFN> listaEstados = new ArrayList<EstadoAFN>();
        listaEstados.add(estadoInicial);
        CerraduraEpsilon epsilonInicial = calcularCerraduraEpsilon(listaEstados);
        return epsilonInicial;
    }

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

    private boolean contieneEstadoAfd(List<Estado> estados, Estado estado) {
        for(Estado estado_1 : estados) {
            if(estado_1.getNombre().equals(estado.getNombre()))
                return true;
        }
        return false;
    }

    private AutomataND construirAutomata() throws IOException {
        String line = reader.readLine();
        AutomataND automata = new AutomataND();
        String descripcion = line;
        automata.setDescripcion(descripcion);

        String alfabeto = reader.readLine();
        automata.setAlfabeto(alfabeto);

        List<EstadoAFN> estados = new ArrayList<EstadoAFN>();
        String[] estadosArray = reader.readLine().split(",");
        for(String estadoString: estadosArray){
            EstadoAFN estado = new EstadoAFN(estadoString, new ArrayList<TransicionAFN>(), false, false, false);
            estados.add(estado);
        }
        automata.setEstados(estados);

        String estadoInicial = reader.readLine();
        List<String> estadosDeAceptacionList = Arrays.asList(reader.readLine().split(","));
        automata.definirEstadosInicialYAcepacion(estadoInicial, estadosDeAceptacionList);

        int numeroTransiciones = Integer.parseInt(reader.readLine());
        for(int i = 0; i < numeroTransiciones; i++){
            String[] transicionString = reader.readLine().split(",");
            EstadoAFN estado = automata.darEstadoPorNombre(transicionString[0]);
            EstadoAFN estadoDestino = automata.darEstadoPorNombre(transicionString[2]);
            TransicionAFN transicion = new TransicionAFN();
            transicion.setSimbolo(transicionString[1]);
            transicion.setEstado(estadoDestino);
            estado.agregarTransicion(transicion);
        }

        return automata;
    }

}

