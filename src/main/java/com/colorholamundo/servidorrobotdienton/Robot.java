package com.colorholamundo.servidorrobotdienton;

import com.colorholamundo.comun.datosred.Coordenada;
import com.colorholamundo.servidorrobotdienton.conexion.Conexion;
import com.colorholamundo.servidorrobotdienton.movimientos.Movimiento;
import com.colorholamundo.servidorrobotdienton.movimientos.MovimientoAutonomo;
import com.colorholamundo.servidorrobotdienton.sincronizacion.BufferObstaculos;
import com.colorholamundo.servidorrobotdienton.sincronizacion.MovimientoSincronizado;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class Robot implements Runnable {

	BufferObstaculos bufferoBstaculo = new BufferObstaculos(400);
	Movimiento movimientoRobot;
	Conexion servidor;
	MovimientoAutonomo autonomo;

	public Robot(MovimientoSincronizado movimientosincronizado) {
		movimientosincronizado.asignarBufferObstaculos(bufferoBstaculo);
		movimientoRobot = new Movimiento(movimientosincronizado);
	}

	public Robot(MovimientoSincronizado movimientosincronizado, MovimientoAutonomo autonomo) {
		movimientosincronizado.asignarBufferObstaculos(bufferoBstaculo);
		movimientoRobot = new Movimiento(movimientosincronizado);
		this.autonomo = autonomo;
		autonomo.asignarRobot(this, movimientosincronizado);
	}

	public Robot(MovimientoSincronizado movimientosincronizado, double sensorMapa) {
		movimientosincronizado.asignarBufferObstaculos(bufferoBstaculo);
		movimientoRobot = new Movimiento(movimientosincronizado, sensorMapa);
	}

	public Robot(MovimientoSincronizado movimientosincronizado, double sensorMapa, MovimientoAutonomo autonomo) {
		movimientosincronizado.asignarBufferObstaculos(bufferoBstaculo);
		movimientoRobot = new Movimiento(movimientosincronizado, sensorMapa);
		this.autonomo = autonomo;
		autonomo.asignarRobot(this, movimientosincronizado);
	}

	public void asignarServidor(Conexion servidor) {
		this.servidor = servidor;
	}

	public void adelanteRobot(double centimetros) {
		movimientoRobot.movimientoAdelante(centimetros);
	}

	public void atrasRobot(double centimetros) {
		movimientoRobot.movimientoAtras(centimetros);
	}

	public void giroDerecha(double grados) {
		movimientoRobot.movimientoGiroDerecha(grados);
	}

	public void giroIzquierda(double grados) {
		movimientoRobot.movimientoGiroIzquierda(grados);
	}

	public void reinicio() {
		movimientoRobot.reinicio();
	}

	public void autonomo(int x, int y) {
		autonomo.movimientoAutonomo(x, y);
	}

	public Object eventoComponente() {
		return movimientoRobot.eventoComponente();
	}

	@Override
	public void run() {
		while (true) {
			Coordenada coordenada = bufferoBstaculo.sacar();
			servidor.enviarObstaculos(coordenada);
			if (autonomo != null) {
				autonomo.ingresarMuestra(coordenada);
			}
		}
	}

}
