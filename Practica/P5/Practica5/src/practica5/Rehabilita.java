/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica5;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Rehabilita implements Runnable {

    private Centro c;
    private CentroCanvas canvas;

    public Rehabilita(Centro c, CentroCanvas canvas) {
        this.c = c;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        int id = (int) Thread.currentThread().getId();
        System.out.println("Soy el Rehabilista " + Thread.currentThread().getName() + " con id: "+id);
        int ale = (2 + r.nextInt(3)) * 1000;
        
        try {
            canvas.encolarRehabilista(id);
            c.EntraRehabilitacion();
            canvas.terminaRehabilista(id);
            canvas.dentroR(id, "R");
            System.out.println(Thread.currentThread().getName()+" / "+id+" : estoy atendiendo en el rehabilista.");
            Thread.sleep(ale); 
             System.out.println(Thread.currentThread().getName()+" / "+id+" : he finalizado en el rehabilista.");
            canvas.finR(id, "R");
            canvas.dentroR(id, "EsperaV");
            c.SaleRehabilitacion();
            canvas.finR(id, "EsperaV");
             System.out.println(Thread.currentThread().getName()+" / "+id+" : estoy atendiendo en el vestuario.");
            canvas.dentroVestuario(id, "R");
            Thread.sleep(2000);
            canvas.finVestuario(id);
            c.Termina();

        } catch (InterruptedException ex) {
            Logger.getLogger(Masaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
