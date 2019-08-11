package com.colorholamundo.servidorrobotdienton.modelos;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public abstract class MotorPasos {

    public static final int MOTORDERECHO = 1;
    public static final int MOTORIZQUIERDO = 2;

    private int posicionMotor;

    public MotorPasos(int posicionMotor) {
        this.posicionMotor = posicionMotor;
    }

    @Override
    public boolean equals(Object o) {
        int posicion = (int) o;

        if (posicionMotor == posicion) {
            return true;
        }
        return false;
    }

    public abstract void impulsoDerecha();

    public abstract void impulsoIzquierda();
}
