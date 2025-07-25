/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7;

import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author wadad
 */
public class Perro extends Thread {

    private CanvasComedor cv;
    private Comedero c;

    public Perro(CanvasComedor cv, Comedero c) {
        this.cv = cv;
        this.c = c;
    }

    public void run() {
        Random rand = new Random(System.currentTimeMillis());
        int id = (int) this.getId();
        try {
            System.out.println("Perro " + id + " : Comienzo");
            cv.encolarPerro(id);
            System.out.println("Perro " + id + " : entro el cola");
            c.entraPerro();
            System.out.println("Perro " + id + " : salgo el cola");
            cv.desencolarPerro(id);

            cv.encolarComedor(id, 'P');
            System.out.println("Perro " + id + " : entro el Comedero");
            sleep((rand.nextInt(2) + 1) * 1000);
            c.salePerro();
            System.out.println("Perro " + id + " : Salgo el Comedero");
            cv.desencolarComedor(id);
            System.out.println("Perro " + id + " : Finalzo.");
        } catch (InterruptedException ex) {
            System.out.println("Error en el perro");
        }

    }
}
