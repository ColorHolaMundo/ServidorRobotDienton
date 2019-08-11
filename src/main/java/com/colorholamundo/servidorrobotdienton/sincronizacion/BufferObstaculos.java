package com.colorholamundo.servidorrobotdienton.sincronizacion;

import java.util.concurrent.ArrayBlockingQueue;

import com.colorholamundo.comun.datosred.Coordenada;

/**
 *
 * @author COLOR HOLAMUNDO
 */
public class BufferObstaculos {

    private ArrayBlockingQueue<Coordenada> bufer;

    public BufferObstaculos(int tamano) {
        bufer = new ArrayBlockingQueue<Coordenada>(tamano);
    }

    public void ingresar(Coordenada coordenada) {
        try {
            bufer.put(coordenada);

        } catch (InterruptedException ex) {
            System.out.println("Error ingresar coordenada");
        }
    }

    public Coordenada sacar() {
        try {
            return bufer.take();
        } catch (InterruptedException ex) {
            System.out.println("Error sacar coordenada");
        }
        return null;
    }
}
