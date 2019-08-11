package com.colorholamundo.servidorrobotdienton.conexion;

import com.colorholamundo.comun.datosred.Coordenada;
import com.colorholamundo.servidorrobotdienton.Robot;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public abstract class Conexion {

	private Robot robot;

	public Conexion(Robot robot) {
		this.robot = robot;
		robot.asignarServidor(this);

		Thread tareaEnvioObstaculos = new Thread(robot);
		tareaEnvioObstaculos.start();
	}

	public void adelanteRobot(double centimetros) {
		robot.adelanteRobot(centimetros);
	}

	public void atrasRobot(double centimetros) {
		robot.atrasRobot(centimetros);
	}

	public void giroDerecha(double grados) {
		robot.giroDerecha(grados);
	}

	public void giroIzquierda(double grados) {
		robot.giroIzquierda(grados);
	}

	public void reinicio() {
		robot.reinicio();
	}

	public void autonomo(int x, int y) {
		robot.autonomo(x, y);
	}

	public Object eventoComponente() {
		return robot.eventoComponente();
	}

	public abstract void enviarObstaculos(Coordenada coordenada);
}
