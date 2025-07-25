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
public class Perro extends Thread {

    private Any2OneChannel entraPerro, salePerro;
    private One2OneChannel permisoPerro[];
     private CanvasComedor cv;
     private Random rand=new Random();
     private final int id;
    public Perro(Any2OneChannel entraPerro, Any2OneChannel salePerro, One2OneChannel[] permisoPerro, CanvasComedor cv,int id) {
        this.id=id;
        this.entraPerro = entraPerro;
        this.salePerro = salePerro;
        this.permisoPerro = permisoPerro;
        this.cv = cv;
        rand.setSeed(System.currentTimeMillis() + id);
    }

     

    @Override
    public void run() {
        
        try {
            System.out.println("Soy el Perro con el id: " + id);
            System.out.println("Perro " + id + ": Escribo en el buzon mi id");
            entraPerro.out().write(id);
            cv.encolarPerro(id);
            System.out.println("Perro " + id + ": Espero el perimso del buzon");
            permisoPerro[id].in().read();
            cv.desencolarPerro(id);
            cv.encolarComedor(id, 'P');
            System.out.println("Perro " + id + ": Voy a comer.");
            Thread.sleep(1000 * (rand.nextInt(3) + 1));
            System.out.println("Perro " + id + ": finalizado de comer.");
            salePerro.out().write(id);
            cv.desencolarComedor(id);
            System.out.println("Perro " + id + ": Finalizado.");
        }  catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
