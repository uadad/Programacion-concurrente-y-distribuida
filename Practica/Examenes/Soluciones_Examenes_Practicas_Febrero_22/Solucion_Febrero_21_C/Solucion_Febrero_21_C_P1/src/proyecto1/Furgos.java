/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Furgos implements Runnable {

    Tunel t;

    public Furgos(Tunel tl) {
        t = tl;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {

            System.out.println("Soy furgo " + Thread.currentThread().getName());
            char donde=t.entraFurgo();
            System.out.println("                                    ----> furgo " + Thread.currentThread().getName() + " ATENDIDO POR ");

            Thread.sleep((rnd.nextInt(3) + 1) * 2000);
            System.out.println("                                       <---- FIN furgo " + Thread.currentThread().getName());
            t.salefurgo(donde);

        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
