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

            Thread h= Thread.currentThread();
            System.out.println("                  Soy el furgo "+h.getName());
            t.lavaFurgo();
            System.out.println("                 ----> furgo "+h.getName()+" Lavando");
            Thread.sleep((rnd.nextInt(3)+1)*2000);
            System.out.println("                 <---- FIN furgo lavado "+h.getName());
            t.secaFurgo();
            System.out.println("                 ----> furgo "+h.getName()+" secando");
             Thread.sleep((rnd.nextInt(3)+1)*2000);
            System.out.println("                 <---- FIN furgo secado "+h.getName());
            t.salir();


        } catch (InterruptedException ex) {
            
        }
    }
}
