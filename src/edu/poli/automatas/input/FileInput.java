package edu.poli.automatas.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jsmartinez on 18/03/2015.
 */
public class FileInput {

    public static void leerArchivo(String archivo)throws FileNotFoundException,IOException {
        String datos ;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);

        while((datos = b.readLine())!=null){
            System.out.print(datos);
        }
        b.close();
    }

    public static void main(String[] args)throws IOException{
        leerArchivo("/src/edu/poli/automatas/input/DocumentoEntrada.txt");
    }



}
