/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;

/**
 *
 * @author usuario
 */
public class ViajeroCon implements Runnable{
    private Terminal t;
     CanvasBus cv;

    public ViajeroCon(Terminal t, CanvasBus cv) {
        this.t = t;
        this.cv=cv;
    }
    
    @Override
    public void run() {
       int id=(int) Thread.currentThread().getId();
        System.out.println("CON "+id+" : Comienzo.");
        try {
            cv.enCon(id);
             System.out.println("CON "+id+" : voy a subir en el bus.");
             t.SubeCon();
             cv.finColaCon(id);
             cv.enBus(id, 'C');
              System.out.println("CON "+id+" : -------------------> estoy en el bus (+2).");

              t.Baja(2);
              cv.finBus(id, 'C');
              System.out.println("CON "+id+" : <------------------- bajp del bus(-2).");
              
              System.out.println("CON "+id+" : Finalizo.");
        } catch (InterruptedException e) {
        }
         
    }
    
    
}
