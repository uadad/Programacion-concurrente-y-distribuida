/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;

import static java.lang.Thread.sleep;

/**
 *
 * @author usuario
 */
public class Coche implements Runnable{
    private Terminal t;

    public Coche(Terminal t) {
        this.t = t;
    }
    
    @Override
    public void run() {
       int id=(int) Thread.currentThread().getId();
        System.out.println("Coche "+id+" : Comienzo.");
        try {
             System.out.println("Coche "+id+" : voy a entrar en el sello.");
              t.entraCoche(id);
              sleep(2000);
              t.saleCoche(id);
                  
              System.out.println("Coche "+id+" : Finalizo.");
        } catch (InterruptedException e) {
        }
         
    }
    
    
}
