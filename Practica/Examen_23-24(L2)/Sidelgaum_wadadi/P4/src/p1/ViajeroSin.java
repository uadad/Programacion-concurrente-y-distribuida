/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;

/**
 *
 * @author usuario
 */
public class ViajeroSin extends Thread{
     private Terminal t;
     CanvasBus cv;
    public ViajeroSin(Terminal t, CanvasBus cv) {
        this.t = t;
        this.cv=cv;
    }
     
     @Override
    public void run() {
       int id=(int) Thread.currentThread().getId();
        System.out.println("Sin "+id+" : Comienzo.");
        try {
            cv.enSin(id);
             System.out.println("Sin "+id+" : voy a subir en el bus.");
             t.SubeSin();
             cv.finColaSin(id);
             cv.enBus(id, 'S');
              System.out.println("Sin "+id+" : -------------------> estoy en el bus (+1).");

              t.Baja(1);
              cv.finBus(id, 'S');
              System.out.println("Sin "+id+" : <------------------- bajp del bus(-1).");
              
              System.out.println("Sin "+id+" : Finalizo.");
        } catch (InterruptedException e) {
        }
         
    }
}
