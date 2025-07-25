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
 *
 * @author pedro
 */
public class Tunel {

    private boolean centrallibre = true, Izquierdolibre = true, Derecholibre = true;
    private int esperafurgo = 0, esperacoche = 0;
    ReentrantLock l = new ReentrantLock();
    Condition colacoches=l.newCondition();
    Condition colafurgos=l.newCondition();
    

    public char entraCoche() throws InterruptedException {

        l.lock();
        try {
            esperacoche++;
            while (!centrallibre && !Izquierdolibre && (!Derecholibre)) {
                colacoches.await();
            }
            esperacoche--;
            if (centrallibre) {
                centrallibre = false;
                return 'c';
            } else if (Izquierdolibre) {
                Izquierdolibre = false;
                return 'i';
            } else {
                Derecholibre = false;
                return 'd';
            }
        } finally {
            l.unlock();
        }
    }

    public void salecoche(char donde) {
        l.lock();
        try {

            if (donde == 'd') {
                Derecholibre = true;
                if(esperafurgo>0) colafurgos.signal();
                else colacoches.signal();
                
            } else if (donde == 'i') {
                Izquierdolibre = true;
                if(esperacoche>0) colacoches.signal();
                else colafurgos.signal();
                
            } else {
                centrallibre = true;
                colacoches.signal();

            }
        } finally {
            l.unlock();
        }
    }

    public char entraFurgo() throws InterruptedException {
        l.lock();
        try {

            esperafurgo++;
            while (!Derecholibre && (!Izquierdolibre)) {
                colafurgos.await();
            }
            esperafurgo--;
            if (Izquierdolibre) {
                Izquierdolibre = false;
                return 'i';
            } else {
                Derecholibre = false;
                return 'd';
            }
        } finally {
            l.unlock();
        }

    }

    public void salefurgo(char donde) {
        l.lock();
        try {

            if (donde == 'd') {
                Derecholibre = true;
                if(esperafurgo>0) colafurgos.signal();
                else colacoches.signal();
            } else {
                Izquierdolibre = true;
                 if(esperacoche>0) colacoches.signal();
                else colafurgos.signal();
            }
        } finally {
            l.unlock();
        }
    }
}
