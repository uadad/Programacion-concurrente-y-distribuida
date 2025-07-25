/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica10;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author wadad
 */
public class Gato implements Runnable{
    private Any2OneChannel entraGato,saleGato;
    private One2OneChannel permisoGato[];
    private CanvasComedor cv;
    private Random rand=new Random();
     private final int id;
    public Gato(Any2OneChannel entraGato, Any2OneChannel saleGato, One2OneChannel[] permisoGato, CanvasComedor cv,int id) {
        this.id=id;
        this.entraGato = entraGato;
        this.saleGato = saleGato;
        this.permisoGato = permisoGato;
        this.cv = cv;
         rand.setSeed(System.currentTimeMillis() + id);
    }
    
    
    @Override
     public void run(){
        
         
        try {
            System.out.println("Soy el Gato con el id: "+id);
         System.out.println("Gato "+id+": Escribo en el buzon mi id");
         entraGato.out().write(id);
          cv.encolarGato(id);
         System.out.println("Gato "+id+": Espero el perimso del buzon");
         permisoGato[id].in().read();
         cv.desencolarGato(id);
         cv.encolarComedor(id, 'G');
         System.out.println("Gato "+id+": Voy a comer.");
            Thread.sleep(1000 * (rand.nextInt(3) + 1));
            
            System.out.println("Gato "+id+": finalizado de comer.");
            saleGato.out().write(id);
            cv.desencolarComedor(id);
            System.out.println("Gato "+id+": Finalizado.");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
