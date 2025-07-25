/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica5;

import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class Centro {

    private boolean Mlibre = true;
    private boolean Rlibre = true;
    private boolean Vlibre = true;
    private int esperandoR = 0;
    //private Random r = new Random(System.currentTimeMillis());

    public synchronized String EntraMasaje() throws InterruptedException {

        // while ((!Mlibre && !Rlibre) || (!Mlibre && esperandoR > 0)) {
        while (!Mlibre && (!Rlibre || esperandoR > 0)) {

            System.out.println("El Masaje esta ocupado espero.");
            wait();
        }
        if (Mlibre) {
            System.out.println("el Masajista " + Thread.currentThread().getName() + " entro en masaje");
            Mlibre = false;
            //SaleMasaje();
            return "Masaje";
        } else {
            System.out.println("el Masajista " + Thread.currentThread().getName() + " entro en rehabilitacion");
            Rlibre = false;
            return "Rehabilitacion";
        }
    }

    public synchronized void SaleMasaje() throws InterruptedException {
        while (!Vlibre) {
            System.out.println(Thread.currentThread().getName() + " El Vestuario esta ocupado espero.");
            wait();
        }
        Vlibre = false;
        Mlibre = true;
        notifyAll();
    }

    public synchronized void EntraRehabilitacion() throws InterruptedException {
        esperandoR++;
        while (!Rlibre) {

            System.out.println(Thread.currentThread().getName() + " La Rehabilitacion esta ocupado espero.");
            wait();
        }
        System.out.println("el rehabilista " + Thread.currentThread().getName() + " entro en rehabilitacion");
        esperandoR--;
        Rlibre = false;
    }

    public synchronized void SaleRehabilitacion() throws InterruptedException {
        while (!Vlibre) {
            System.out.println(Thread.currentThread().getName() + " El Vestuario esta ocupado espero.");
            wait();
        }
        System.out.println("el rehabilista " + Thread.currentThread().getName() + " entro en vestuario");

        Vlibre = false;
        Rlibre = true;
        notifyAll();

    }

    public synchronized void Termina() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " termino");

        Vlibre = true;
        notifyAll();
    }

}
