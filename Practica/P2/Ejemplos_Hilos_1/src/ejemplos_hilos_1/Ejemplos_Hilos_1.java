/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos_hilos_1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Ejemplos_Hilos_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    System.out.println("Soy el hilo tipo C " + Thread.currentThread().getName());
                    Thread.yield();
                }
            }
        };

        HiloA h1 = new HiloA();
        HiloB hilob = new HiloB();
        Thread h2 = new Thread(hilob);
        Thread h4 = new Thread(hilob,"hilob");

        Thread h3 = new Thread(r3);

        System.out.println("Comienza la main");
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        

        try {
            h2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ejemplos_Hilos_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finaliza la main");
    }
}

class HiloA extends Thread {

    @Override
    public void run() { // entry point for thread
        for (int i = 1; i <= 10; i++) {
            setPriority(i);
            System.out.println("Soy el hilo tipo A " + getName() + " con prioridad " + getPriority() + " en estado " + getState());
            Thread.yield();
        }
    }
}

class HiloB implements Runnable {

    @Override
    public void run() { // entry point for thread
        Thread yo = Thread.currentThread();
        for (int i = 1; i < 10; i++) {
            yo.setPriority(i);
            System.out.println("Soy el hilo tipo B " + yo.getName() + " con prioridad " + yo.getPriority() + " en estado " + yo.getState());
            Thread.yield();
        }
    }
}
