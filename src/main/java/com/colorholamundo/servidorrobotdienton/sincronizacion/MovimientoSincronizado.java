package com.colorholamundo.servidorrobotdienton.sincronizacion;

import java.util.LinkedList;

import com.colorholamundo.comun.datosred.Coordenada;
import com.colorholamundo.servidorrobotdienton.modelos.Componente;
import com.colorholamundo.servidorrobotdienton.modelos.MotorPasos;
import com.colorholamundo.servidorrobotdienton.modelos.SensorDistancia;
import com.colorholamundo.servidorrobotdienton.util.TeoremaPitagoras;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class MovimientoSincronizado {

	// El buffer se asigna al ser complemento de Robot
	private BufferObstaculos buffer;
	private LinkedList<MotorPasos> motores = new LinkedList<MotorPasos>();
	private LinkedList<SensorDistancia> sensores = new LinkedList<SensorDistancia>();
	private Componente componente;

	private double robotX = 0.0; // Posicion X robot
	private double robotY = 0.0; // Posicio Y robot

	// Angulo utilizado para calcular el angulo que se enuentran los sensores de
	// distancia
	// anguloActualSensor= (anguloSensor+anguloRobot) % 360
	private double anguloRobot = 0;

	// Angulo utilizado para calculo el desplazamiento de X,Y con respecto a su
	// angulo
	private double anguloPosicionRobot = 90;

	private double anguloGiro; // Es el angulo por cada paso de giro.
	private double distanciaPaso; // Distancia en cm que recorre por paso.

	private int pasosMotor; // Pasos necesarios para un grito completo
	private double perimetroLLantas;
	private double perimetroRobot;

	public MovimientoSincronizado(int pasosMotor, double diametroLLanta, double diametroRobot) {
		this.pasosMotor = pasosMotor;
		this.perimetroRobot = Math.PI * diametroRobot;
		perimetroLLantas = Math.PI * diametroLLanta;

		distanciaPaso = (perimetroLLantas / pasosMotor);

		this.anguloGiro = distanciaPaso * (double) 360 / perimetroRobot;
	}

	public void asignarBufferObstaculos(BufferObstaculos buffer) {
		this.buffer = buffer;
	}

	public void reinicio() {
		robotX = 0;
		robotY = 0;
		anguloRobot = 0;
		anguloPosicionRobot = 90;

	}

	public int getPasosMotor() {
		return pasosMotor;
	}

	public double getPerimetroLLantas() {
		return perimetroLLantas;
	}

	public double getPerimetroRobot() {
		return perimetroRobot;
	}

	public double getRobotX() {
		return robotX;
	}

	public double getRobotY() {
		return robotY;
	}

	public double getAnguloPisicionRobot() {
		return anguloPosicionRobot;
	}

	public void addSensor(SensorDistancia sensor) {
		sensores.add(sensor);
	}

	public void addMotorPasos(MotorPasos motor) {
		motores.add(motor);
	}

	public void addComponente(Componente componente) {
		this.componente = componente;
	}

	public void impulsoAdelante() {
		for (int i = 0; i < motores.size(); i++) {
			MotorPasos motor = motores.get(i);
			if (motor.equals(MotorPasos.MOTORDERECHO)) {
				motor.impulsoDerecha();
			} else if (motor.equals(MotorPasos.MOTORIZQUIERDO)) {
				motor.impulsoIzquierda();
			}

		}

		robotX += TeoremaPitagoras.valorX(distanciaPaso, anguloPosicionRobot);
		robotY += TeoremaPitagoras.valorY(distanciaPaso, anguloPosicionRobot);
	}

	public void impulsoAtras() {
		for (int i = 0; i < motores.size(); i++) {
			MotorPasos motor = motores.get(i);
			if (motor.equals(MotorPasos.MOTORDERECHO)) {
				motor.impulsoIzquierda();
			} else if (motor.equals(MotorPasos.MOTORIZQUIERDO)) {
				motor.impulsoDerecha();
			}
		}

		robotX -= TeoremaPitagoras.valorX(distanciaPaso, anguloPosicionRobot);
		robotY -= TeoremaPitagoras.valorY(distanciaPaso, anguloPosicionRobot);
	}

	public void impulsoDerecha() {
		for (int i = 0; i < motores.size(); i++) {
			MotorPasos motor = motores.get(i);
			if (motor.equals(MotorPasos.MOTORDERECHO)) {
				motor.impulsoIzquierda();
			} else if (motor.equals(MotorPasos.MOTORIZQUIERDO)) {
				motor.impulsoIzquierda();
			}
		}
		moverAnguloDerecha();
	}

	public void impulsoIzquierda() {
		for (int i = 0; i < motores.size(); i++) {
			MotorPasos motor = motores.get(i);
			if (motor.equals(MotorPasos.MOTORDERECHO)) {
				motor.impulsoDerecha();
			} else if (motor.equals(MotorPasos.MOTORIZQUIERDO)) {
				motor.impulsoDerecha();
			}
		}
		moverAnguloIzquierda();
	}

	private void moverAnguloDerecha() {
		if (anguloRobot <= 0) {
			anguloRobot = 360;
		}
		anguloRobot -= anguloGiro;
		if (anguloPosicionRobot <= 0) {
			anguloPosicionRobot = 360;
		}
		anguloPosicionRobot -= anguloGiro;
	}

	private void moverAnguloIzquierda() {
		if (anguloRobot >= 360) {
			anguloRobot = 0;
		}
		anguloRobot += anguloGiro;
		if (anguloPosicionRobot >= 360) {
			anguloPosicionRobot = 0;
		}
		anguloPosicionRobot += anguloGiro;
	}

	public void tomarMuestraConEnvio() {
		double distancia;
		double x = 0, y = 0;
		SensorDistancia sensor;

		for (int i = 0; i < sensores.size(); i++) {
			sensor = sensores.get(i);
			distancia = sensor.medirDistancia();
			if (distancia <= 15) {
				x = TeoremaPitagoras.valorX(distancia, sensor.anguloActual(anguloRobot)) + robotX;
				y = TeoremaPitagoras.valorY(distancia, sensor.anguloActual(anguloRobot)) + robotY;
				buffer.ingresar(new Coordenada(x, y, robotX, robotY, anguloPosicionRobot));
			} else {
				buffer.ingresar(new Coordenada(-1, -1, robotX, robotY, anguloPosicionRobot));
			}
		}
	}

	public void tomarMuestraConEnvio(double posicionSensor) {
		double distancia;
		double x = 0, y = 0;
		SensorDistancia sensor;
		int indice = sensores.indexOf(new SensorDistancia(posicionSensor) {
			@Override
			public double medirDistancia() {
				return 0;
			}
		});

		if (indice >= 0) {
			sensor = sensores.get(indice);
			distancia = sensor.medirDistancia();
			if (distancia <= 15) {
				x = TeoremaPitagoras.valorX(distancia, sensor.anguloActual(anguloRobot)) + robotX;
				y = TeoremaPitagoras.valorY(distancia, sensor.anguloActual(anguloRobot)) + robotY;
				buffer.ingresar(new Coordenada(x, y, robotX, robotY, anguloPosicionRobot));
			} else {
				buffer.ingresar(new Coordenada(-1, -1, robotX, robotY, anguloPosicionRobot));
			}
		}
	}

	public double tomarMuestra(double posicionSensor) {
		double distancia;
		SensorDistancia sensor;
		int indice = sensores.indexOf(new SensorDistancia(posicionSensor) {
			@Override
			public double medirDistancia() {
				return 0;
			}
		});
		if (indice >= 0) {
			sensor = sensores.get(indice);
			distancia = sensor.medirDistancia();
			return distancia;
		}
		return -1;
	}

	public Object eventoComonente() {
		return componente.evento();
	}

}
