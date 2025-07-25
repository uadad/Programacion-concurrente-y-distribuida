/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica5;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Masaje extends Thread {
    // private Canvas cv;

    private Centro c;
    private CentroCanvas canvas;

    public Masaje(Centro c, CentroCanvas canvas) {
        this.c = c;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());

        System.out.println("Soy el Masajista " + getName());
        int id = (int) this.getId();
        int ale = (2 + r.nextInt(3)) * 1000;
        try {
            canvas.encolarMasajista(id);
            String d = c.EntraMasaje();
            canvas.terminaMasajista(id);
            if (d.equals("Rehabilitacion")) {

                canvas.dentroR(id, "M");
                 System.out.println(Thread.currentThread().getName()+" / "+id+" : estoy atendiendo en el rehabilista.");
                Thread.sleep(ale);
                 System.out.println(Thread.currentThread().getName()+" / "+id+" : he finalizado del rehabilista.");
                canvas.finR(id, "M");
                canvas.dentroR(id, "EsperaVM");
                c.SaleRehabilitacion();
                canvas.finR(id, "EsperaVM");
            } else {

                canvas.dentroM(id, "M");
                 System.out.println(Thread.currentThread().getName()+" / "+id+" : estoy atendiendo en el masajista.");
                Thread.sleep(ale);
                //sleep(6000);
                 System.out.println(Thread.currentThread().getName()+" / "+id+" : finalizado de atender del masajista.");
                canvas.finM(id, "M");
                canvas.dentroM(id, "EsperaV");
                c.SaleMasaje();
                canvas.finM(id, "EsperaV");
            }
            System.out.println(Thread.currentThread().getName()+" / "+id+" : estoy atendiendo en el vestuario.");
            canvas.dentroVestuario(id, "M");
            Thread.sleep(2000);
            canvas.finVestuario(id);
            c.Termina();

        } catch (InterruptedException ex) {
            Logger.getLogger(Masaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
