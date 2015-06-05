package edu.poli.automatas.input;

import edu.poli.automatas.logica.afn.EstadoAFN;

import java.io.IOException;

/**
 * Created by jsmartinez on 01/05/2015.
 */
public class Menu {

    public static void main(String[] args) {

        try {
            new UnionInterseccionAutomatas().unionInterseccionEjercicio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
