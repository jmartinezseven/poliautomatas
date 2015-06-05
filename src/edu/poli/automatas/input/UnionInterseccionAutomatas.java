package edu.poli.automatas.input;

import edu.poli.automatas.logica.afd.Automata;
import edu.poli.automatas.logica.afd.Estado;
import edu.poli.automatas.logica.afd.Transicion;

import java.io.*;
import java.util.*;

/**
 * Created by Juan Sebastian Martinez, Esteban Bautista Clavijo on 18/03/2015.
 */
public class UnionInterseccionAutomatas {

    private BufferedReader reader;

    public UnionInterseccionAutomatas(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void unionInterseccionEjercicio() throws IOException {
        Automata automataUno = construirAutomata();
        Automata automataDos = construirAutomata();
        Automata automataUnionInterseccion = new Automata();
        automataUnionInterseccion.setAlfabeto(automataUno.getAlfabeto());

        List<Estado> listaEstadosProductoCruz = new ArrayList<Estado>();

        for(Estado estadoAutomataUno: automataUno.getEstados()){
            for(Estado estadoAutomataDos: automataDos.getEstados()){
                String nombreEstado = estadoAutomataUno.getNombre().concat("-").concat(estadoAutomataDos.getNombre());
                Estado estado = new Estado(nombreEstado, new HashSet<Transicion>(), false, false);
                listaEstadosProductoCruz.add(estado);
            }
        }

        automataUnionInterseccion.setEstados(listaEstadosProductoCruz);

        String estadoInicial = automataUno.darEstadoInicial().getNombre().concat("-").concat(automataDos.darEstadoInicial().getNombre());

        for(Estado estadoAutomataUno: automataUno.getEstados()){
            for(Estado estadoAutomataDos: automataDos.getEstados()){
                String nombreEstado = estadoAutomataUno.getNombre().concat("-").concat(estadoAutomataDos.getNombre());
                Estado estadoEvaluar = automataUnionInterseccion.darEstadoPorNombre(nombreEstado);
                for(int i = 0; i < automataDos.getAlfabeto().length(); i++) {
                    String letraAlfabeto = String.valueOf(automataDos.getAlfabeto().charAt(i));
                    Transicion transicionEstadoAutomataUno = estadoAutomataUno.darTransacion(letraAlfabeto);
                    Transicion transicionEstadoAutomataDos = estadoAutomataDos.darTransacion(letraAlfabeto);

                    String nombreNuevoEstado = transicionEstadoAutomataUno.getEstado().getNombre().concat("-").concat(transicionEstadoAutomataDos.getEstado().getNombre());
                    Estado estadoResultado = automataUnionInterseccion.darEstadoPorNombre(nombreNuevoEstado);
                    Transicion transicion = new Transicion();
                    transicion.setSimbolo(letraAlfabeto);
                    transicion.setEstado(estadoResultado);
                    estadoEvaluar.agregarTransicion(transicion);
                }
            }
        }

        System.out.println("Union de automatas");
        automataUnionInterseccion.setDescripcion(automataUno.getDescripcion() + " o " + automataDos.getDescripcion());
        List<String> estadosAceptacion = darEstadosAceptacionUnion(automataUno, automataDos);
        automataUnionInterseccion.definirEstadosInicialYAcepacion(estadoInicial, estadosAceptacion);
        System.out.println(automataUnionInterseccion.toString());

        automataUnionInterseccion.limpiarEstados();

        System.out.println("Interseccion de automatas");
        automataUnionInterseccion.setDescripcion(automataUno.getDescripcion() + " y " + automataDos.getDescripcion());
        List<String> estadosAceptacionInterseccion = darEstadosAceptacionInterseccion(automataUno, automataDos);
        automataUnionInterseccion.definirEstadosInicialYAcepacion(estadoInicial, estadosAceptacionInterseccion);
        System.out.println(automataUnionInterseccion.toString());
    }

    /**
     * Define la lista de estados de aceptacion para la union
     * @param uno
     * @param dos
     * @return
     */
    private List<String> darEstadosAceptacionUnion(Automata uno, Automata dos){
        List<String> aceptacion = new ArrayList<String>();
        for(Estado estadoUno : uno.getEstados()){
            for(Estado estadoDos : dos.getEstados()){
                if(estadoUno.isAceptacion() || estadoDos.isAceptacion()){
                    String nombreEstadoAceptacion = estadoUno.getNombre().concat("-").concat(estadoDos.getNombre());
                    aceptacion.add(nombreEstadoAceptacion);
                }
            }
        }
        return aceptacion;
    }

    /**
     * Define la lista de estados de aceptacion para la interseccion de automatas
     * @param uno
     * @param dos
     * @return
     */
    private List<String> darEstadosAceptacionInterseccion(Automata uno, Automata dos){
        List<String> aceptacion = new ArrayList<String>();
        for(Estado estadoUno : uno.getEstados()){
            for(Estado estadoDos : dos.getEstados()){
                if(estadoUno.isAceptacion() && estadoDos.isAceptacion()){
                    String nombreEstadoAceptacion = estadoUno.getNombre().concat("-").concat(estadoDos.getNombre());
                    aceptacion.add(nombreEstadoAceptacion);
                }
            }
        }
        return aceptacion;
    }

    private Automata construirAutomata() throws IOException {
        String line = reader.readLine();
        Automata automata = new Automata();
        String descripcion = line;
        automata.setDescripcion(descripcion);

        String alfabeto = reader.readLine();
        automata.setAlfabeto(alfabeto);

        List<Estado> estados = new ArrayList<Estado>();
        String[] estadosArray = reader.readLine().split(",");
        for(String estadoString: estadosArray){
            Estado estado = new Estado(estadoString, new HashSet<Transicion>(), false, false);
            estados.add(estado);
        }
        automata.setEstados(estados);

        String estadoInicial = reader.readLine();
        List<String> estadosDeAceptacionList = Arrays.asList(reader.readLine().split(","));
        automata.definirEstadosInicialYAcepacion(estadoInicial, estadosDeAceptacionList);

        int numeroTransiciones = Integer.parseInt(reader.readLine());
        for(int i = 0; i < numeroTransiciones; i++){
            String[] transicionString = reader.readLine().split(",");
            Estado estado = automata.darEstadoPorNombre(transicionString[0]);
            Estado estadoDestino = automata.darEstadoPorNombre(transicionString[2]);
            Transicion transicion = new Transicion();
            transicion.setSimbolo(transicionString[1]);
            transicion.setEstado(estadoDestino);
            estado.agregarTransicion(transicion);
        }

        return automata;
    }
}
