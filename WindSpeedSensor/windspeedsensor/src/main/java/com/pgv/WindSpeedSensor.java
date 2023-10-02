package com.pgv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WindSpeedSensor {

    public static void main(String[] args) {

        Random random = new Random();
        int contadorFichero = 1;

        while (true) {
            try {
                // Generar un valor de velocidad del viento aleatorio entre 0°Km/h y 45°Km/h
                int viento = random.nextInt(46);

                // Crear el nombre del archivo
                String nombreFichero = "WindSpeedData_" + contadorFichero + ".dat";

                // Crear y escribir en el archivo
                FileWriter writer = new FileWriter(nombreFichero);
                writer.write(Integer.toString(viento));
                writer.close();

                System.out.println("Archivo generado: " + nombreFichero);

                // Incrementar el contador de archivos
                contadorFichero++;

                // Esperar 5 segundos
                Thread.sleep(5000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } // while

    }// main

}// WindSpeedSensor
