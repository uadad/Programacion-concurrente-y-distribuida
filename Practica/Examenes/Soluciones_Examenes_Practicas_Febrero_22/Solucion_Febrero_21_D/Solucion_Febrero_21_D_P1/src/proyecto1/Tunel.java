/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

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

    private int coches = 0, furgos = 0, total = 0, esperafurgo = 0, esperacoche=0;

    public synchronized void entraCoche() throws InterruptedException {
        esperacoche++;
        while (total == 3 || coches == 2 || (esperafurgo > 0 && furgos < 2)) {
            wait();
        }
        esperacoche--;
        coches++;
        total++;
    }

    public synchronized void salecoche() {
        coches--;
        total--;
        notifyAll();
    }

    public synchronized void entraFurgo() throws InterruptedException {
        esperafurgo++;
        while (total == 3 || (furgos == 2 && esperacoche>0)) {
            wait();
        }
        esperafurgo--;
        total++;
        furgos++;

    }

    public synchronized void salefurgo() {
        total--;
        furgos--;
        notifyAll();
    }
}

