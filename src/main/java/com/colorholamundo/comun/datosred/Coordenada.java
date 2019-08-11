package com.colorholamundo.comun.datosred;

import java.io.Serializable;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class Coordenada implements Serializable {

	private static final long serialVersionUID = -3767023213159403941L;
	
	private double x = 0; //X obstaculo
    private double y = 0; //Y obstaculo

    private double robotX = 0; //X robot 
    private double robotY = 0; //Y Robot

    private double anguloRobot = 0; //Angulo del robot

    public Coordenada(double x, double y, double xRobot, double yRobot, double angulo) {
        this.x = x;
        this.y = y;

        this.robotX = xRobot;
        this.robotY = yRobot;

        this.anguloRobot = angulo;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setXRobot(double x) {
        robotX = x;
    }

    public void setYRobot(double y) {
        robotY = y;
    }

    public void setAnguloRobot(double angulo) {
        this.anguloRobot = angulo;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRobotX() {
        return robotX;
    }

    public double getRobotY() {
        return robotY;
    }

    public double getAnguloRobot() {
        return anguloRobot;
    }
}
