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

    private int coches = 0, furgos = 0, total = 0, esperafurgo = 0, esperacoche = 0;

    CanvasTunel cv;

    public Tunel(CanvasTunel cv) {
        this.cv = cv;
    }

    public synchronized void entraCoche(int id) throws InterruptedException {
        cv.inserta(0, id);
        esperacoche++;
        while (total == 3 || coches == 2 || (esperafurgo > 0 && furgos < 2)) {
            wait();
        }
        cv.quita(0, id);
        cv.atiende(0, id);
        esperacoche--;
        coches++;
        total++;
    }

    public synchronized void salecoche(int id) {
        cv.finalizado(id);
        coches--;
        total--;
        notifyAll();
    }

    public synchronized void entraFurgo(int id) throws InterruptedException {
        cv.inserta(1, id);
        esperafurgo++;
        while (total == 3 || (furgos == 2 && esperacoche > 0)) {
            wait();
        }
        cv.quita(1, id);
        cv.atiende(1, id);

        esperafurgo--;
        total++;
        furgos++;

    }

    public synchronized void salefurgo(int id) {
        cv.finalizado(id);
        total--;
        furgos--;
        notifyAll();
    }
}
