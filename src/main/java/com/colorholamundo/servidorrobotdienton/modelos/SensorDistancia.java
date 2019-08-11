package com.colorholamundo.servidorrobotdienton.modelos;

/**
 *
 * @author COLOR HOLAMUNDO
 */
abstract public class SensorDistancia {

    public static final double SENSORFRENTE = 90;
    public static final double SENSORATRAS = 270;
    public static final double SENSORDERECHA = 0;
    public static final double SENSORIZQUIERDA = 180;

    private double posicionSensor = 0;

    public SensorDistancia(double posicionSensor) {
        this.posicionSensor = posicionSensor;
    }

    public SensorDistancia(int posicionSensor) {
        this.posicionSensor = posicionSensor;
    }

    public double getAngulo() {
        return posicionSensor;
    }

 
    public double anguloActual(double anguloRobot) {

        return ((posicionSensor + anguloRobot) % 360);
    }

    public static boolean esUnSensor(double sensor) {
        if (sensor == SENSORFRENTE || sensor == SENSORATRAS || sensor == SENSORDERECHA || sensor == SENSORIZQUIERDA) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        SensorDistancia aux = (SensorDistancia) o;
        if (posicionSensor == aux.posicionSensor) {
            return true;
        } else {
            return false;
        }
    }

    //Metodo Abstracto a implementar
    public abstract double medirDistancia();
}
