package edu.poli.automatas.input;

import java.io.*;

/**
 * Created by jsmartinez on 18/03/2015.
 */
public class FileInput {

    public static void main(String[] args)throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        while ( line != null ) {
            String descripcion = line;
            String alfabeto = reader.readLine();
            
        }
    }



}
