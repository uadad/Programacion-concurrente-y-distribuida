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

    public ViajeroSin(Terminal t) {
        this.t = t;
    }
     
     @Override
    public void run() {
       int id=(int) Thread.currentThread().getId();
        System.out.println("Sin "+id+" : Comienzo.");
        try {
             System.out.println("Sin "+id+" : voy a subir en el bus.");
             t.SubeSin();
              System.out.println("Sin "+id+" : -------------------> estoy en el bus (+1).");

              t.Baja(1);
              System.out.println("Sin "+id+" : <------------------- bajp del bus(-1).");
              
              System.out.println("Sin "+id+" : Finalizo.");
        } catch (InterruptedException e) {
        }
         
    }
}
