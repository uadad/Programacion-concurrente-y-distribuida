/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

/**
 *
 * @author pedro
 */
public class Tunel {

    private int Lavadolibre = 3, secadolibre = 2, furgoslavando = 0, esperafurgo = 0;

    public synchronized void lavaCoche() throws InterruptedException {

        while (Lavadolibre == 0 || (esperafurgo > 0 && furgoslavando < 2)) {
            wait();
        }
        Lavadolibre--;
    }

    public synchronized void secaCoche() throws InterruptedException {
        while (secadolibre == 0) {
            wait();
        }
        Lavadolibre++;
        secadolibre--;
        notifyAll();
    }

    public synchronized void lavaFurgo() throws InterruptedException {
        esperafurgo++;
        while (Lavadolibre == 0 || furgoslavando > 1) {
            wait();
        }
        esperafurgo--;
        Lavadolibre--;
        furgoslavando++;
    }

    public synchronized void secaFurgo() throws InterruptedException {
        while (secadolibre == 0) {
            wait();
        }
        furgoslavando--;
        Lavadolibre++;
        secadolibre--;
        notifyAll();
    }

    public synchronized void salir() {
        secadolibre++;
        notifyAll();
    }

}
