package com.colorholamundo.servidorrobotdienton;

import com.colorholamundo.servidorrobotdienton.conexion.ServidorRobot;
import com.colorholamundo.servidorrobotdienton.modelos.MotorPasos28BYJ48;
import com.colorholamundo.servidorrobotdienton.modelos.SensorDistancia;
import com.colorholamundo.servidorrobotdienton.modelos.SensorDistanciaHCSR04;
import com.colorholamundo.servidorrobotdienton.movimientos.AutonomoEscalon;
import com.colorholamundo.servidorrobotdienton.sincronizacion.MovimientoSincronizado;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class ServidorRobotDienton {

	public static void main(String[] args) {
		
		MovimientoSincronizado sincronizacion = new MovimientoSincronizado(512, 6.5, 21.8);

		sincronizacion.addMotorPasos(new MotorPasos28BYJ48(MotorPasos28BYJ48.MOTORDERECHO, 24, 23, 22, 26));
		sincronizacion.addMotorPasos(new MotorPasos28BYJ48(MotorPasos28BYJ48.MOTORIZQUIERDO, 27, 25, 28, 29));

		sincronizacion.addSensor(new SensorDistanciaHCSR04(SensorDistancia.SENSORFRENTE, 7, 0));
		sincronizacion.addSensor(new SensorDistanciaHCSR04(SensorDistancia.SENSORDERECHA, 5, 6));
		sincronizacion.addSensor(new SensorDistanciaHCSR04(SensorDistancia.SENSORIZQUIERDA, 1, 2));

		Robot robot = new Robot(sincronizacion, SensorDistancia.SENSORFRENTE, new AutonomoEscalon());
		ServidorRobot servidor = new ServidorRobot(robot, 10000, 3);
		servidor.inicarServidor();
	}
}
