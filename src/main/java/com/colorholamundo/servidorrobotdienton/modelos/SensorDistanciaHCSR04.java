package com.colorholamundo.servidorrobotdienton.modelos;

import com.pi4j.wiringpi.Gpio;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class SensorDistanciaHCSR04 extends SensorDistancia {

    private int echo;
    private int trig;

    public SensorDistanciaHCSR04(double posicionSensor, int echo, int trig) {
        super(posicionSensor);
        this.echo = echo;
        this.trig = trig;

        Gpio.wiringPiSetup();
        Gpio.pinMode(trig, Gpio.OUTPUT);
        Gpio.pinMode(echo, Gpio.INPUT);

        medirDistancia();
        medirDistancia();
    }

    public double medirDistancia() {

        long tiempoInicio, tiempoFin;
        double tiempoTotal;
        double distania, microSegundos;

        Gpio.digitalWrite(trig, Gpio.HIGH);
        Gpio.delayMicroseconds(20);
        Gpio.digitalWrite(trig, Gpio.LOW);

        while (Gpio.digitalRead(echo) == Gpio.LOW);

        tiempoInicio = System.nanoTime();
        while (Gpio.digitalRead(echo) == Gpio.HIGH);
        tiempoFin = System.nanoTime();

        tiempoTotal = tiempoFin - tiempoInicio;
        microSegundos = tiempoTotal * 0.001;
        distania = (microSegundos * 0.03432) / 2;

        return distania;
    }

}
