package com.colorholamundo.servidorrobotdienton.conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.colorholamundo.comun.datosred.Coordenada;
import com.colorholamundo.comun.datosred.Instruccion;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class ClienteRobot implements Runnable {

	private String ipIdentificacion;
	private ServidorRobot servidor;
	private Socket conexion;

	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private BufferInstrucciones buffer;

	public ClienteRobot(ServidorRobot servidor, Socket conexion, BufferInstrucciones buffer) {
		this.servidor = servidor;
		this.conexion = conexion;
		this.buffer = buffer;
		InetAddress direccion = conexion.getInetAddress();
		ipIdentificacion = direccion.getHostAddress();
	}

	public ClienteRobot(String ip) {
		ipIdentificacion = ip;
	}

	public void obtenerFlujos() throws IOException {
		salida = new ObjectOutputStream(conexion.getOutputStream());
		salida.flush();
		entrada = new ObjectInputStream(conexion.getInputStream());
	}

	public void procesarConexion() throws IOException {
		Instruccion mensaje;
		while (true) {
			try {
				mensaje = (Instruccion) entrada.readObject();
				if (mensaje instanceof Instruccion) {
					buffer.ingresar(mensaje);
				}

			} catch (ClassNotFoundException ex) {
				System.out.println("Instruccion no valido");
			}
		}
	}

	public void enviarCoordenadaObstaculo(Coordenada coordenada) {
		try {
			salida.writeObject(coordenada);
			salida.flush();

		} catch (IOException ex) {
			System.out.println("Error: enviar cordenadas");
		}
	}

	@Override
	public void run() {
		try {
			obtenerFlujos();
			procesarConexion();
		} catch (IOException ex) {
			System.out.println("Cliente Desconectado");
		} finally {
			servidor.eliminarCliente(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		ClienteRobot cliente = (ClienteRobot) o;
		return ipIdentificacion.equals(cliente.ipIdentificacion);
	}

	@Override
	public String toString() {
		return ipIdentificacion;
	}

}
