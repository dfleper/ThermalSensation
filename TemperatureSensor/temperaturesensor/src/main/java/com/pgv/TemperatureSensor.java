package com.pgv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TemperatureSensor {

	public static void main(String[] args) {

		Random random = new Random();
		int contadorFichero = 1;

		while (true) {
			try {
				// Generar un valor de temperatura aleatorio entre 0°C y 45°C
				int temperatura = random.nextInt(46);

				// Crear el nombre del archivo
				String nombreFichero = "TemperatureData_" + contadorFichero + ".dat";

				// Crear y escribir en el archivo
				FileWriter writer = new FileWriter(nombreFichero);
				writer.write(Integer.toString(temperatura));
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
}// TemperatureSensor
