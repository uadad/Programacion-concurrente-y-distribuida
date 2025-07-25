/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;

/**
 *
 * @author usuario
 */
public class Moto extends Thread{
     private Terminal t;

    public Moto(Terminal t) {
        this.t = t;
    }
     
     @Override
    public void run() {
       int id=(int) Thread.currentThread().getId();
        System.out.println("Moto "+id+" : Comienzo.");
        try {
             System.out.println("Moto "+id+" : voy a subir en el sello.");
             t.entraMoto(id);
             sleep(3000);
             t.saleMoto(id);
              
              System.out.println("Moto "+id+" : Finalizo.");
        } catch (InterruptedException e) {
        }
         
    }
}
