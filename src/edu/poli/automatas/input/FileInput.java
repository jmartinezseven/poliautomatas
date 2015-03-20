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
        System.out.print("fsdgsdf");
//        String datos ;
//        FileReader f = new FileReader(archivo);
//        BufferedReader b = new BufferedReader(f);
//
//        while((datos = b.readLine())!=null){
//            System.out.print(datos);
//        }
//        b.close();
    }

    public static void main(String[] args)throws IOException{
        leerArchivo("DocumentoEntrada.txt");
    }



}
