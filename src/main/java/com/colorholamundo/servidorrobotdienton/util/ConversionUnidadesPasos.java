package com.colorholamundo.servidorrobotdienton.util;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class ConversionUnidadesPasos {

	public static int centimetrosPasos(double perimetro, double centimetros, int pasos) {
		double numPasos = (centimetros * pasos) / perimetro;
		return Math.round((float) numPasos);
	}

	public static int gradosPasos(double perimetroLLantas, double grados, int pasos, double perimetroRobot) {
		double distanciaGiro = (grados * perimetroRobot / 360);
		double numPasos = centimetrosPasos(perimetroLLantas, distanciaGiro, pasos);
		return Math.round((float) numPasos);
	}
}
