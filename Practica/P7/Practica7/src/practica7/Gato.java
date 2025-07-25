/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wadad
 */
public class Gato implements Runnable {

    private CanvasComedor cv;
    private Comedero c;

    public Gato(CanvasComedor cv, Comedero c) {
        this.cv = cv;
        this.c = c;
    }

    public void run() {
        Random rand = new Random(System.currentTimeMillis());
        int id = (int) Thread.currentThread().getId();

        try {
            System.out.println("Gato "+id+" : Cominzo.");

            cv.encolarGato(id);
             System.out.println("Gato "+id+" : entro en la Cola");
            c.entraGato();
            cv.desencolarGato(id);
             System.out.println("Gato "+id+" : salgo de Cola");
            cv.encolarComedor(id, 'G');
             System.out.println("Gato "+id+" : entro el Comedero");
             
            sleep((rand.nextInt(2) + 1) * 1000);
            c.saleGato();
            System.out.println("Gato "+id+" : salgo el Comedero");
            cv.desencolarComedor(id);
            
            System.out.println("Gato " + id + " : Finalzo.");
        } catch (InterruptedException ex) {

            System.out.println("Error Gato");
        }

    }
}
