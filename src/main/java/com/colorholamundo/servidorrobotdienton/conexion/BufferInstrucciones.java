package com.colorholamundo.servidorrobotdienton.conexion;

import java.util.concurrent.ArrayBlockingQueue;

import com.colorholamundo.comun.datosred.Instruccion;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class BufferInstrucciones {

	private ArrayBlockingQueue<Instruccion> bufer;

	public BufferInstrucciones(int tamano) {
		bufer = new ArrayBlockingQueue<Instruccion>(tamano);
	}

	public void ingresar(Instruccion instruccion) {
		try {
			bufer.put(instruccion);

		} catch (InterruptedException ex) {
			System.out.println("Error ingresar instruccion");
		}
	}

	public Instruccion sacar() {
		try {
			return bufer.take();
		} catch (InterruptedException ex) {
			System.out.println("Error sacar instruccion");
		}
		return null;
	}
}
