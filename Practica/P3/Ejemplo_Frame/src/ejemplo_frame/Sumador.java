/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_frame;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Sumador implements Runnable {

    Recurso c1;
    int cual;

    public Sumador(Recurso c, int cual) {
        c1 = c;
        this.cual = cual;
    }

    @Override
    public void run() {

        try {
            for (int i = 1; i <= 1000; i++) {
                c1.Incrementa(cual);
                System.out.println(Thread.currentThread().getName() + " incrementa " + cual);
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Sumador.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
