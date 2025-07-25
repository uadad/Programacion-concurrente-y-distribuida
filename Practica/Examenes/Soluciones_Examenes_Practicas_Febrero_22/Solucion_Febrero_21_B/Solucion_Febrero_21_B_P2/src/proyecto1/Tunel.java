/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pedro
 */
public class Tunel {

    private int Lavadolibre = 3, secadolibre = 2, furgoslavando = 0, esperafurgo = 0;
    ReentrantLock l = new ReentrantLock();
    Condition colacoche = l.newCondition();
    Condition colafurgo = l.newCondition();
    Condition colaseca = l.newCondition();

    public void lavaCoche() throws InterruptedException {
        l.lock();
        try {
            while (Lavadolibre == 0) {
                colacoche.await();
            }
            Lavadolibre--;
        } finally {
            l.unlock();
        }
    }

    public void secaCoche() throws InterruptedException {
        l.lock();
        try {
            while (secadolibre == 0) {
                colaseca.await();
            }
            Lavadolibre++;
            secadolibre--;
            if (esperafurgo > 0 && furgoslavando < 2) {
                colafurgo.signal();
            } else {
                colacoche.signal();
            }
        } finally {
            l.unlock();
        }
    }

    public void lavaFurgo() throws InterruptedException {
        l.lock();
        try {
            esperafurgo++;
            while (Lavadolibre == 0 || furgoslavando > 1) {
                colafurgo.await();
            }
            esperafurgo--;
            Lavadolibre--;
            furgoslavando++;
        } finally {
            l.unlock();
        }
    }

    public void secaFurgo() throws InterruptedException {
        l.lock();
        try {
            while (secadolibre == 0) {
                colaseca.await();
            }
            furgoslavando--;
            Lavadolibre++;
            secadolibre--;
            if (esperafurgo > 0) {
                colafurgo.signal();
            } else {
                colacoche.signal();
            }
        } finally {
            l.unlock();
        }
    }

    public void salir() {
        l.lock();
        try {
            secadolibre++;
            colaseca.signal();
        } finally {
            l.unlock();
        }
    }

}
