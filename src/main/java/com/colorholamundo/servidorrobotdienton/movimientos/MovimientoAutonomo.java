package com.colorholamundo.servidorrobotdienton.movimientos;

import java.util.Vector;

import com.colorholamundo.comun.datosred.Coordenada;
import com.colorholamundo.servidorrobotdienton.Robot;
import com.colorholamundo.servidorrobotdienton.sincronizacion.MovimientoSincronizado;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public abstract class MovimientoAutonomo {

    private Robot robot;
    private MovimientoSincronizado movimiento;
    private Vector<Coordenada> muestras = new Vector<Coordenada>();

    public void asignarRobot(Robot robot, MovimientoSincronizado movimiento) {
        this.robot = robot;
        this.movimiento = movimiento;
    }

    //----Movimiento ------------------------------
    public void adelanteRobot(double centimetros) {
        robot.adelanteRobot(centimetros);
    }

    public void atrasRobot(double centimetros) {
        robot.atrasRobot(centimetros);
    }

    public void giroDerecha(double grados) {
        robot.giroDerecha(grados);
    }

    public void giroIzquierda(double grados) {
        robot.giroIzquierda(grados);
    }

    public double tomarMuestra(double posicionSensor) {
        return movimiento.tomarMuestra(posicionSensor);
    }

    public Object eventoComponente() {
        return robot.eventoComponente();
    }

    //---- Informacion Posicion-----------------------------
    public double getRobotX() {
        return movimiento.getRobotX();
    }

    public double getRobotY() {
        return movimiento.getRobotY();
    }

    public double getAnguloPisicionRobot() {
        return movimiento.getAnguloPisicionRobot();
    }
    //---------Espacio Muestras------------------------

    public void ingresarMuestra(Coordenada coordenada) {
        muestras.add(coordenada);
    }

    public Coordenada sacarMuestra(int indice) {
        return muestras.get(indice);
    }

    public int tamanoMuestras() {
        return muestras.size();
    }
    //-------------------------------------------

    public abstract void movimientoAutonomo(int xTarea, int yTarea);

}
