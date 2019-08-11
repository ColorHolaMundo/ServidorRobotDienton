package com.colorholamundo.servidorrobotdienton.movimientos;

import com.colorholamundo.servidorrobotdienton.modelos.SensorDistancia;
import com.colorholamundo.servidorrobotdienton.sincronizacion.MovimientoSincronizado;
import com.colorholamundo.servidorrobotdienton.util.ConversionUnidadesPasos;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class Movimiento {

    private MovimientoSincronizado movimientoSincronizado;

    private int pasosMotor;
    private double perimetroLLantas;
    private double perimetroRobot;
    private double sensorMapa = -1;

    public Movimiento(MovimientoSincronizado componentes) {
        this.movimientoSincronizado = componentes;
        this.pasosMotor = movimientoSincronizado.getPasosMotor();
        this.perimetroLLantas = movimientoSincronizado.getPerimetroLLantas();
        this.perimetroRobot = movimientoSincronizado.getPerimetroRobot();
    }

    public Movimiento(MovimientoSincronizado componentes, double sensorMapa) {
        this.sensorMapa = sensorMapa;
        this.movimientoSincronizado = componentes;
        this.pasosMotor = movimientoSincronizado.getPasosMotor();
        this.perimetroLLantas = movimientoSincronizado.getPerimetroLLantas();
        this.perimetroRobot = movimientoSincronizado.getPerimetroRobot();
    }

    public void movimientoAdelante(double centimetros) {
        int numeroPasos = ConversionUnidadesPasos.centimetrosPasos(perimetroLLantas, centimetros, pasosMotor);

        for (int i = 0; i < numeroPasos; i++) {
            movimientoSincronizado.impulsoAdelante();

            if (SensorDistancia.esUnSensor(sensorMapa)) {
                movimientoSincronizado.tomarMuestraConEnvio(sensorMapa);
            } else {
                movimientoSincronizado.tomarMuestraConEnvio();
            }
        }

    }

    public void movimientoAtras(double centimetros) {
        int numeroPasos = ConversionUnidadesPasos.centimetrosPasos(perimetroLLantas, centimetros, pasosMotor);
        for (int i = 0; i < numeroPasos; i++) {

            movimientoSincronizado.impulsoAtras();

            if (SensorDistancia.esUnSensor(sensorMapa)) {
                movimientoSincronizado.tomarMuestraConEnvio(sensorMapa);
            } else {
                movimientoSincronizado.tomarMuestraConEnvio();
            }
        }

    }

    public void movimientoGiroDerecha(double grados) {
        int numeroPasos = ConversionUnidadesPasos.gradosPasos(perimetroLLantas, grados, pasosMotor, perimetroRobot);
        for (int i = 0; i < numeroPasos; i++) {
            movimientoSincronizado.impulsoDerecha();

            if (SensorDistancia.esUnSensor(sensorMapa)) {
                movimientoSincronizado.tomarMuestraConEnvio(sensorMapa);
            } else {
                movimientoSincronizado.tomarMuestraConEnvio();
            }
        }

    }

    public void movimientoGiroIzquierda(double grados) {
        int numeroPasos = ConversionUnidadesPasos.gradosPasos(perimetroLLantas, grados, pasosMotor, perimetroRobot);
        for (int i = 0; i < numeroPasos; i++) {
            movimientoSincronizado.impulsoIzquierda();

            if (SensorDistancia.esUnSensor(sensorMapa)) {
                movimientoSincronizado.tomarMuestraConEnvio(sensorMapa);
            } else {
                movimientoSincronizado.tomarMuestraConEnvio();
            }
        }

    }

    public double tomarMuestra(int sensor) {
        return movimientoSincronizado.tomarMuestra(sensor);
    }
    
    public Object eventoComponente(){
      return movimientoSincronizado.eventoComonente();
    }

    public void reinicio() {
        movimientoSincronizado.reinicio();
    }

}
