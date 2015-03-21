package edu.poli.automatas.input;

import edu.poli.automatas.logica.Automata;
import edu.poli.automatas.logica.Estado;
import edu.poli.automatas.logica.Transicion;

import java.io.*;
import java.util.*;

/**
 * Created by jsmartinez on 18/03/2015.
 */
public class FileInput {

    public static void main(String[] args)throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        while ( line != null ) {
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

            StringBuffer resultadoSimulacion = new StringBuffer();
            resultadoSimulacion.append("Lenguaje L: " + descripcion + "\n\n");
            int cadenasLenguaje = Integer.parseInt(reader.readLine());
            for(int i = 0; i < cadenasLenguaje; i++) {
                String cadenaAutomata = reader.readLine();
                resultadoSimulacion.append("Procesando cadena "+i+": \""+cadenaAutomata + "\"\n");
                String simulaionEjecutada = automata.simulacion(cadenaAutomata);
                resultadoSimulacion.append(simulaionEjecutada);
            }
            System.out.print(resultadoSimulacion.toString());
            line = reader.readLine();
        }
    }
}
