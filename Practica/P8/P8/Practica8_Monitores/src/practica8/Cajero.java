/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author usuario
 */
public class Cajero {

    private int numCajerostotales = 4;
    private int numCajeros = 0;
    private boolean libreEfectivo = true;
    private int efectivoEsperando = 0;
    private ReentrantLock cola = new ReentrantLock();
    private Condition colaEfectivo = cola.newCondition();
    private Condition colaTarjeta = cola.newCondition();

    public void entraefectivo() throws InterruptedException {
        cola.lock();
        try {
            efectivoEsperando++;
            while (!libreEfectivo || numCajeros == numCajerostotales) {

                colaEfectivo.await();
            }
            efectivoEsperando--;
            libreEfectivo = false;
            numCajeros++;
        } finally {
            cola.unlock();
        }
    }

    public void entratarjeta() throws InterruptedException {
        cola.lock();
        try {
            while (numCajeros == numCajerostotales || (efectivoEsperando > 0 && libreEfectivo)) {
                colaTarjeta.await();
            }
            numCajeros++;
        } finally {
            cola.unlock();
        }
    }

    public void saleefectivo() {
        cola.lock();
        try {
            libreEfectivo = true;
            numCajeros--;
            if (efectivoEsperando > 0) {
                colaEfectivo.signal();
            } else {
                colaTarjeta.signal();
            }
        } finally {
            cola.unlock();
        }
    }

    public void saletarjeta() {
        cola.lock();
        try {
            numCajeros--;
            if (efectivoEsperando > 0 && libreEfectivo) {
                colaEfectivo.signal();
            } else {
                colaTarjeta.signal();
            }
        } finally {
            cola.unlock();
        }
    }
}
