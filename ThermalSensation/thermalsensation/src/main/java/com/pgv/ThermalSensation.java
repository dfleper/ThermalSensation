package com.pgv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ThermalSensation {

    public static void main(String[] args) {

        // Iniciar los archivos JAR TemperatureSensor y WindSpeedSensor al inicio
        System.out.println(" ");
        System.out.println("ThermalSensation");
        startJar("TemperatureSensor.jar");
        System.out.println("Iniciando TemperatureSensor...");
        startJar("WindSpeedSensor.jar");
        System.out.println("Iniciando WindSpeedSensor...");

        // Esperar 5 segundos antes de continuar para que los sensores generen el primer
        // dato
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int contador = 0;
        while (true) {
            String nombreFicheroTemp = "TemperatureData_" + contador + ".dat";
            String nombreFicheroViento = "WindSpeedData_" + contador + ".dat";

            File ficheroTemp = new File(nombreFicheroTemp);
            File ficheroViento = new File(nombreFicheroViento);

            if (ficheroTemp.exists() && ficheroViento.exists()) {
                try (BufferedReader leeTemp = new BufferedReader(new FileReader("./" + ficheroTemp));
                        BufferedReader leeViento = new BufferedReader(new FileReader("./" + ficheroViento))) {

                    // Leer los valores de temperatura y velocidad del viento
                    double temperatura = Double.parseDouble(leeTemp.readLine());
                    double velocidadViento = Double.parseDouble(leeViento.readLine());

                    // Calcular la sensación térmica
                    double sensacionTer = calculo(temperatura, velocidadViento);

                    // Mostrar los valores por la consola
                    System.out.println("------------------------------------------");
                    System.out.println("Temperatura: " + temperatura + " C");
                    System.out.println("Velocidad del viento: " + velocidadViento + " Km/h");
                    System.out.println("Sensacion termica: " + sensacionTer + " C");
                    System.out.println("------------------------------------------");

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // Eliminar los archivos
                    ficheroTemp.delete();
                    ficheroViento.delete();
                }
            } else {
                // Archivos no encontrados, esperar antes de verificar el siguiente archivo
                System.out.println(" ");
            }
            contador++;
            try {
                Thread.sleep(5000); // Esperar 5 segundo antes de verificar el siguiente archivo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }// main

    private static double calculo(double temperatura, double velocidadViento) {
        double sensacionTer = (0.045 * (5.27 * Math.sqrt(velocidadViento) + 10.45 - 0.28 * velocidadViento)
                * (temperatura - 33) + 33);
        // Redondear a dos decimales
        return Math.round(sensacionTer * 100.0) / 100.0;
    }

    private static void startJar(String ficheroJar) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", ficheroJar);
            pb.directory(new File("./")); // Reemplaza con la ruta del directorio de los archivos JAR
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}// ThermalSensation
