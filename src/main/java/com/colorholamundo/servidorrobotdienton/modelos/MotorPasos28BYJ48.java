package com.colorholamundo.servidorrobotdienton.modelos;

import com.pi4j.wiringpi.Gpio;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class MotorPasos28BYJ48 extends MotorPasos {

    private int tiempo = 3;

    private int A;
    private int B;
    private int C;
    private int D;

    public MotorPasos28BYJ48(int posicionMotor, int A, int B, int C, int D) {
        super(posicionMotor);

        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;

        Gpio.wiringPiSetup();
        Gpio.pinMode(A, Gpio.OUTPUT);
        Gpio.pinMode(B, Gpio.OUTPUT);
        Gpio.pinMode(C, Gpio.OUTPUT);
        Gpio.pinMode(D, Gpio.OUTPUT);
    }

    public void impulsoDerecha() {

        try {
            Gpio.digitalWrite(A, Gpio.HIGH);
            Gpio.digitalWrite(B, Gpio.HIGH);
            Gpio.digitalWrite(C, Gpio.LOW);
            Gpio.digitalWrite(D, Gpio.LOW);

            Thread.sleep(tiempo);

            Gpio.digitalWrite(A, Gpio.LOW);
            Gpio.digitalWrite(B, Gpio.HIGH);
            Gpio.digitalWrite(C, Gpio.HIGH);
            Gpio.digitalWrite(D, Gpio.LOW);

            Thread.sleep(tiempo);

            Gpio.digitalWrite(A, Gpio.LOW);
            Gpio.digitalWrite(B, Gpio.LOW);
            Gpio.digitalWrite(C, Gpio.HIGH);
            Gpio.digitalWrite(D, Gpio.HIGH);

            Thread.sleep(tiempo);

            Gpio.digitalWrite(A, Gpio.HIGH);
            Gpio.digitalWrite(B, Gpio.LOW);
            Gpio.digitalWrite(C, Gpio.LOW);
            Gpio.digitalWrite(D, Gpio.HIGH);

            Thread.sleep(tiempo);

        } catch (InterruptedException ex) {
            Logger.getLogger(MotorPasos28BYJ48.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void impulsoIzquierda() {

        try {
            Gpio.digitalWrite(A, Gpio.HIGH);
            Gpio.digitalWrite(B, Gpio.LOW);
            Gpio.digitalWrite(C, Gpio.LOW);
            Gpio.digitalWrite(D, Gpio.HIGH);

            Thread.sleep(tiempo);

            Gpio.digitalWrite(A, Gpio.LOW);
            Gpio.digitalWrite(B, Gpio.LOW);
            Gpio.digitalWrite(C, Gpio.HIGH);
            Gpio.digitalWrite(D, Gpio.HIGH);

            Thread.sleep(tiempo);

            Gpio.digitalWrite(A, Gpio.LOW);
            Gpio.digitalWrite(B, Gpio.HIGH);
            Gpio.digitalWrite(C, Gpio.HIGH);
            Gpio.digitalWrite(D, Gpio.LOW);

            Thread.sleep(tiempo);

            Gpio.digitalWrite(A, Gpio.HIGH);
            Gpio.digitalWrite(B, Gpio.HIGH);
            Gpio.digitalWrite(C, Gpio.LOW);
            Gpio.digitalWrite(D, Gpio.LOW);

            Thread.sleep(tiempo);

        } catch (InterruptedException ex) {
            Logger.getLogger(MotorPasos28BYJ48.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
