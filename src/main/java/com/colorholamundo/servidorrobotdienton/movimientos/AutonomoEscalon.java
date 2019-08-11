package com.colorholamundo.servidorrobotdienton.movimientos;

import com.colorholamundo.servidorrobotdienton.modelos.SensorDistancia;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class AutonomoEscalon extends MovimientoAutonomo {

	private int centimetrosAvance = 1;
	private boolean ejeY = true; // Indica si el robot se encuentra mirando en el eje Y
	private double sensorEspacion = SensorDistancia.SENSORFRENTE;

	@Override
	public void movimientoAutonomo(int xTarea, int yTarea) {
		posicionInicial(xTarea, yTarea);
		calcularEjeRobot();

		int robotX = (int) Math.round(getRobotX());
		int robotY = (int) Math.round(getRobotY());

		while (xTarea != robotX || yTarea != robotY) {
			if (yTarea == robotY && ejeY) {
				girarRobotEje(xTarea, yTarea);
			} else if (xTarea == robotX && !ejeY) {
				girarRobotEje(xTarea, yTarea);
			}

			calcularEjeRobot();

			// Movimiento si se encuentra en el eje Y
			if (yTarea != robotY && ejeY) {
				adelanteRobot(centimetrosAvance);
			} // Movimiento si se encuentra en el x
			else if (xTarea != robotX && !ejeY) {
				adelanteRobot(centimetrosAvance);
			}
			if (!existeEspacio()) {
				girarRobotEje(xTarea, yTarea);
			}
			robotX = (int) Math.round(getRobotX());
			robotY = (int) Math.round(getRobotY());
		}
	}

	// Calcula y gra el robot al eje que sea cercano a la tarea
	public void girarRobotEje(int xTarea, int yTarea) {

		int yRobot = (int) Math.round(getRobotY());
		int xRobot = (int) Math.round(getRobotX());
		int anguloRobot = (int) Math.round(getAnguloPisicionRobot());

		int anguloFinal = -1;
		if (yTarea == yRobot) {
			if (xTarea < xRobot) {
				anguloFinal = 180;
			} else {
				anguloFinal = 0;
			}
		} else if (xTarea == xRobot) {
			if (yTarea < yRobot) {
				anguloFinal = 270;
			} else {
				anguloFinal = 90;
			}
		} else {
			double pendiente = (double) (yTarea - yRobot) / (double) (xTarea - xRobot);
			if (pendiente > 0) {

				if (yTarea > yRobot) {
					if (anguloRobot == 90) {
						anguloFinal = 0;
					} else {
						anguloFinal = 90;
					}
				} else if (yTarea < yRobot) {

					if (anguloRobot == 270) {
						anguloFinal = 180;
					} else {
						anguloFinal = 270;
					}
				}
			} else if (pendiente < 0) {

				if (yTarea > yRobot) {

					if (anguloRobot == 90) {
						anguloFinal = 180;
					} else {
						anguloFinal = 90;
					}
				} else if (yTarea < yRobot) {

					if (anguloRobot == 0) {
						anguloFinal = 270;
					} else {
						anguloFinal = 0;
					}
				}
			}
		}

		girarParaPosicionInicial(anguloFinal, -1);
	}

	public boolean existeEspacio() {
		double promedio = 0;
		for (int i = 0; i < 7; i++) {
			promedio += tomarMuestra(sensorEspacion);
		}
		promedio /= 7;
		return (promedio < 5) ? false : true;
	}

	private void calcularEjeRobot() {
		double angulorobot = getAnguloPisicionRobot();
		if ((angulorobot < 350 && angulorobot > 200) || ((angulorobot > 10 && angulorobot < 160))) {
			ejeY = true;
		} else {
			ejeY = false;
		}
	}

	// Gira el robot para apuntar a la tarea (x,y)
	private void posicionInicial(int xTarea, int yTarea) {
		if (yTarea > getRobotY()) {
			if (xTarea < getRobotX()) {
				girarParaPosicionInicial(90, 180);

			} else if (xTarea > getRobotX()) {
				girarParaPosicionInicial(90, 0);

			} else if (xTarea == getRobotX()) {
				girarParaPosicionInicial(90, -1);
			}
		} else if (yTarea < getRobotY()) {

			if (xTarea < getRobotX()) {
				girarParaPosicionInicial(180, 270);

			} else if (xTarea > getRobotX()) {
				girarParaPosicionInicial(270, 0);

			} else if (xTarea == getRobotX()) {

				girarParaPosicionInicial(270, -1);
			}
		} else if (yTarea == getRobotY()) {

			if (xTarea < getRobotX()) {
				girarParaPosicionInicial(180, -1);

			} else {
				girarParaPosicionInicial(0, -1);
			}
		}
	}

	// Realiza el gira para acomodar al robot en el angul indicado
	private void girarParaPosicionInicial(int angulo1, int angulo2) {
		double anguloIzquierda = 9999;
		double anguloDerecha = 9999;

		double anguloBase = (360 - getAnguloPisicionRobot());
		if (anguloBase == 360) {
			anguloBase = 0;
		}
		if (angulo1 >= 0) {
			anguloIzquierda = (anguloBase + angulo1) % 360;
			anguloDerecha = 360 - anguloIzquierda;
		}
		if (angulo2 >= 0) {
			double anguloIzquierda2 = (anguloBase + angulo2) % 360;
			double anguloDerecha2 = 360 - anguloIzquierda2;

			anguloIzquierda = (anguloIzquierda < anguloIzquierda2) ? anguloIzquierda : anguloIzquierda2;
			anguloDerecha = (anguloDerecha < anguloDerecha2) ? anguloDerecha : anguloDerecha2;
		}

		if (anguloDerecha < anguloIzquierda) {
			giroDerecha(anguloDerecha);
		} else {
			giroIzquierda(anguloIzquierda);
		}
	}
}
