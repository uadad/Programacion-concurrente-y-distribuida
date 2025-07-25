/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * naximimo dos coches y dos furgos, aunque podrán entrar 3 furgos si no hay
 * coches esperando. Las furgonetas tendrán prioridad de paso sobre los coches,
 * siempre que se cumplan las condiciones.
 *
 * @author pedro
 */
public class Tunel {

    private int coches = 0, furgos = 0, total = 0, esperafurgo = 0, esperacoche = 0;

    CanvasTunel cv;

    ReentrantLock l = new ReentrantLock();
    Condition colacoche = l.newCondition();
    Condition colafurgo = l.newCondition();

    public Tunel(CanvasTunel cv) {
        this.cv = cv;
    }

    public void entraCoche(int id) throws InterruptedException {
        l.lock();
        try {
            cv.inserta(0, id);
            esperacoche++;
            while (total == 3 || coches == 2) {
                colacoche.await();
            }
            cv.quita(0, id);
            cv.atiende(0, id);
            esperacoche--;
            coches++;
            total++;
        } finally {
            l.unlock();
        }

    }

    public void salecoche(int id) {
        l.lock();
        try {
            cv.finalizado(id);
            coches--;
            total--;
            if (esperafurgo > 0 && (furgos < 2 || esperacoche == 0)) {
                colafurgo.signal();
            } else {
                colacoche.signal();
            }
        } finally {
            l.unlock();
        }
    }

    public void entraFurgo(int id) throws InterruptedException {
        l.lock();
        try {
            cv.inserta(1, id);
            esperafurgo++;
            while (total == 3 || (furgos == 2 && esperacoche > 0)) {
                colafurgo.await();
            }
            cv.quita(1, id);
            cv.atiende(1, id);

            esperafurgo--;
            total++;
            furgos++;
        } finally {
            l.unlock();
        }

    }

    public void salefurgo(int id) {
        l.lock();
        try {
            cv.finalizado(id);
            total--;
            furgos--;
            if (esperafurgo > 0 && (furgos < 2 || esperacoche == 0)) {
                colafurgo.signal();
            } else {
                colacoche.signal();
            }
        } finally {
            l.unlock();
        }

    }
}
