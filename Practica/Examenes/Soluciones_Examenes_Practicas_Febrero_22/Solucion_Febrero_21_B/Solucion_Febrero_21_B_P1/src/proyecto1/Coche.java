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
public class Coche extends Thread{
    
    Tunel t;
    public Coche(Tunel tl){
        t=tl;
    }
    
    @Override
    public void run(){
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        try {
            
            System.out.println("Soy el coche "+getName());
            t.lavaCoche();
            System.out.println("----> coche "+getName()+" Lavando");
            Thread.sleep((rnd.nextInt(3)+1)*2000);
            System.out.println(" <---- FIN coche lavado "+getName());
            t.secaCoche();
            System.out.println("----> coche "+getName()+" secando");
             Thread.sleep((rnd.nextInt(3)+1)*2000);
            System.out.println("<---- FIN coche secado "+getName());
            t.salir();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
