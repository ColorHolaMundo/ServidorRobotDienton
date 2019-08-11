package com.colorholamundo.servidorrobotdienton.conexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.colorholamundo.comun.datosred.Coordenada;
import com.colorholamundo.comun.datosred.Instruccion;
import com.colorholamundo.servidorrobotdienton.Robot;

/**
 *
 * @author COLOR HOLAMUNDO
 */

public class ServidorRobot extends Conexion implements Runnable {

	private ServerSocket servidor;
	private BufferInstrucciones buffer = new BufferInstrucciones(100);
	private LinkedList<ClienteRobot> clientesConectados = new LinkedList<ClienteRobot>();
	private ExecutorService ejecutorSubprocesos = Executors.newCachedThreadPool();
	private int numeroClientes = 0;
	private final int CLIENTESMAX;

	public ServidorRobot(Robot robot, int puerto, int clintesMax) {
		super(robot);
		CLIENTESMAX = clintesMax;

		try {
			servidor = new ServerSocket(puerto, 1);
		} catch (IOException ex) {
			System.out.println("Error: crear socket servidor");
		}
	}

	public void inicarServidor() {
		ejecutorSubprocesos.execute(this);
		ejecutorSubprocesos.shutdown();
		System.out.println("INICIO SERVIDOR");

		while (true) {
			try {
				if (numeroClientes < CLIENTESMAX) {
					ClienteRobot clienteRobot = new ClienteRobot(this, esperarConexion(), buffer);
					clientesConectados.add(clienteRobot);
					numeroClientes++;

					Thread ejecutorCliente = new Thread(clienteRobot);
					ejecutorCliente.start();
				}
			} catch (IOException ex) {
				System.out.println("Eror: Iniciar Servidor");
			}
		}
	}

	public Socket esperarConexion() throws IOException {
		return servidor.accept();
	}

	public void eliminarCliente(ClienteRobot clienteBorrar) {
		clientesConectados.remove(clienteBorrar);
		numeroClientes--;
	}

	@Override
	public void enviarObstaculos(Coordenada coordenada) {
		for (ClienteRobot cliente : clientesConectados) {
			cliente.enviarCoordenadaObstaculo(coordenada);
		}
	}

	public void ejecutarInstrucciones() {
		while (true) {
			Instruccion instruccion = buffer.sacar();

			switch (instruccion.getOrden()) {
			case (Instruccion.ADELANTE):
				adelanteRobot(instruccion.getUnidad());
				break;
			case (Instruccion.ATRAS):
				atrasRobot(instruccion.getUnidad());
				break;
			case (Instruccion.GIRODERECHA):
				giroDerecha(instruccion.getUnidad());
				break;
			case (Instruccion.GIROIZQUIERDA):
				giroIzquierda(instruccion.getUnidad());
				break;
			case (Instruccion.REINICIO):
				reinicio();
				break;
			case (Instruccion.AUTONOMO):
				autonomo(instruccion.getX(), instruccion.getY());
				break;
			}
		}
	}

	@Override
	public void run() {
		ejecutarInstrucciones();
	}

}
