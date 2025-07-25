/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.concurrent.Semaphore;

/**
 *
 * @author usuario
 */
public class Camarero extends Thread {

    private Semaphore sLeche;
    private Semaphore sCafe;
    private CavasCongreso cv;

    public Camarero(Semaphore sCafe, Semaphore sLeche, CavasCongreso cv) {
        this.sCafe = sCafe;
        this.sLeche = sLeche;
        this.cv = cv;

    }

    public void run() {
        System.out.println("Camarero: Comienzo.");
        try {
            while (true) {

                for (int i = 0; i < 5; i++) {
                    cv.camarero('L');
                    sLeche.release();
                   // sleep(1000);
                }
                cv.fincamarero();
                
                System.out.println("Camarero:                      <------------------------------  Cargo leche  +5");
                System.out.println("********************************************************************************");
                for (int i = 0; i < 5; i++) {
                    cv.camarero('C');
                    sCafe.release();
                    //sleep(1000);

                }
                cv.fincamarero();
                
                System.out.println("Camarero:                     <------------------------------ Cargo Cafe  +5");
                System.out.println("********************************************************************************");
                sleep(6000);
            }
        } catch (InterruptedException e) {
            System.out.println("Camarero: Finaliza.");
        }

    }
}
