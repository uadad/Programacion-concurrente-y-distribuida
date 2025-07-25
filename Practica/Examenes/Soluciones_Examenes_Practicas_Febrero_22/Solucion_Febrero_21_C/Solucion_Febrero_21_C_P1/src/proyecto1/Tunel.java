/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Tunel {

    private boolean centrallibre = true, Izquierdolibre = true, Derecholibre = true;
    private int esperafurgo = 0, esperacoche=0;

    public synchronized char entraCoche() throws InterruptedException {

        esperacoche++;
        while (!centrallibre && !Izquierdolibre && (!Derecholibre || esperafurgo > 0)) {
            wait();
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
    }

    public synchronized void salecoche(char donde) {
        switch (donde) {
            case 'd':
                Derecholibre = true;
                break;
            case 'i':
                Izquierdolibre = true;
                break;
            default:
                centrallibre = true;
                break;
        }
        notifyAll();
    }

    public synchronized char entraFurgo() throws InterruptedException {
        esperafurgo++;
        while (!Derecholibre && (!Izquierdolibre || esperacoche>0)) {
            wait();
        }
        esperafurgo--;
        if (Izquierdolibre) {
            Izquierdolibre = false;
            return 'i';
        } else {
            Derecholibre = false;
            return 'd';
        }
    }

    public synchronized void salefurgo(char donde) {
        if (donde == 'd') {
            Derecholibre = true;
        } else {
            Izquierdolibre = true;
        }
        notifyAll();
    }
}
