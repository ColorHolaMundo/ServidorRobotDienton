package com.colorholamundo.servidorrobotdienton.util;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class TeoremaPitagoras {

	public static double valorX(double h, double angulo) {
		return (coseno(angulo) * h);
	}

	public static double valorY(double h, double angulo) {
		double y = Math.sqrt(Math.pow(h, 2) - Math.pow((coseno(angulo) * h), 2));
		if (angulo > 180 && angulo < 360) {
			y *= -1;
		}
		return y;
	}

	private static double coseno(double angulo) {
		double anguloRadianes = Math.toRadians(angulo);
		double coseno = 0;
		if (angulo == 90 || angulo == 270) {
			coseno = 0;
		} else {
			coseno = Math.cos(anguloRadianes);
		}

		return coseno;
	}
}
