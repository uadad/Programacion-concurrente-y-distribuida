/*PEDRO
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
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Thread[] vehiculo = new Thread[20];
        Furgos f;
        Tunel tq = new Tunel();
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {

            for (int i = 0; i < 20; i++) {
                if (rnd.nextInt(100) < 50) {
                    vehiculo[i] = new Coche(tq);
                    vehiculo[i].start();
                } else {
                    f = new Furgos(tq);
                    vehiculo[i] = new Thread(f);
                    vehiculo[i].start();
                }
                Thread.sleep((rnd.nextInt(2) + 1) * 10);                
            }
            for (int i = 0; i < 20; i++) {
                vehiculo[i].join();
            }            
        } catch (InterruptedException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);


        }
    }
}
