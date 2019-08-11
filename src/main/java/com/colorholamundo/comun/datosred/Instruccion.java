package com.colorholamundo.comun.datosred;

import java.io.Serializable;

/**
 *
 * @author COLOR HOLAMUNDO
 */


public class Instruccion implements Serializable {

	private static final long serialVersionUID = 7760760292167932579L;
	
	public static final int ADELANTE = 1;
    public static final int ATRAS = 2;
    public static final int GIRODERECHA = 3;
    public static final int GIROIZQUIERDA = 4;
    public static final int REINICIO = 5;
    public static final int AUTONOMO = 6;

    private int orden;  //Intruccion a ejecutar
    private double unidad; // Medida de desplazamiento
    private int x = 0; //Coordenada X movimiento autonomo
    private int y = 0; //Coordenada Y movimiento autonomo

    public Instruccion(int orden, double unidad) {
        this.orden = orden;
        this.unidad = unidad;
    }

    public Instruccion(int orden, int x, int y) {
        this.orden = orden;
        this.x = x;
        this.y = y;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public double getUnidad() {
        return unidad;
    }

    public void setUnidad(double unidad) {
        this.unidad = unidad;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
