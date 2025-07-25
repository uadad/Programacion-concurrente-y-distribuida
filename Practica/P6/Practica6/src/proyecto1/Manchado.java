/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Manchado implements Runnable {

    private Semaphore sLeche;
    private Semaphore sCafe;
    private Semaphore Salacafe;
    private Semaphore SalaLeche;
    private Semaphore Papelera;
    private CavasCongreso cv;

    public Manchado(Semaphore sCafe, Semaphore sLeche, Semaphore SalaLeche, Semaphore Salacafe, Semaphore Papelera, CavasCongreso cv) {
        this.sCafe = sCafe;
        this.sLeche = sLeche;
        this.Papelera = Papelera;
        this.SalaLeche = SalaLeche;
        this.Salacafe = Salacafe;
        this.cv = cv;

    }

    public void run() {
        long id = Thread.currentThread().getId();
        Random rand = new Random(System.currentTimeMillis());

        System.out.println("Manchado: Soy el hilo " + id);

        try {
            cv.encolacafe((int) id, 'M', 2, 1);

            System.out.println("El hilo " + id + " : voy a entrar en la sala de cafe.");
            Salacafe.acquire();

            cv.fincolacafe((int) id, 'M', 2, 1);
            cv.ensalacafe((int) id, 'M', 2, 1);

            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : voy a coger una dosis de cafe.");
            sCafe.acquire();

            cv.finsalacafe((int) id, 'M', 2, 0);
            Salacafe.release();
            cv.encolaleche((int) id, 'M', 2, 0);

            System.out.println("                         <------------------------------Dosis de cafe -1");
            System.out.println("**************************************************************************");
            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : voy a entrar en la sala de leche.");
            SalaLeche.acquire();

            cv.fincolaleche((int) id, 'M', 2, 0);
            cv.ensalaleche((int) id, 'M', 2, 0);

            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : voy a coger una dosis de Leche.");
            sLeche.acquire();

            cv.ensalaleche((int) id, 'M', 1, 0);

            System.out.println("                         <------------------------------Dosis Leche -1");
            System.out.println("**************************************************************************");
            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : voy a coger otra dosis de Leche.");
            sLeche.acquire();

            cv.finsalaleche((int) id, 'M', 0, 0);
            SalaLeche.release();
            cv.ensalon((int) id, 'M', 0, 0);

            System.out.println("                         <------------------------------Dosis Leche -1");
            System.out.println("**************************************************************************");
            System.out.println("                          <-----------------------------------");

            System.out.println("El hilo " + id + " : Me voy Al salon.");
            sleep((rand.nextInt(3) + 1) * 1000);
            cv.finsalon((int) id, 'M', 0, 0);
            cv.encolapapelera((int) id, 'M', 0, 0);

            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : Me voy Al papalera.");
            Papelera.acquire();

            cv.fincolapapelera((int) id, 'M', 0, 0);
            cv.enpapelera((int) id, 'M', 0, 0);

            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : entro a la papalera.");
            sleep(1000);
            System.out.println("                          <-----------------------------------");

            cv.finpapelera((int) id, 'M', 0, 0);
            Papelera.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Manchado.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Manchado: El hilo " + id + " Finaliza.");
    }

}
