/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author usuario
 */
public class conTarjeta implements Callable<Integer> {

    private Cajero cajero;
    private CanvasCajero cv;

    public conTarjeta(Cajero cajero, CanvasCajero cv) {
        this.cajero = cajero;
        this.cv = cv;
    }

    @Override
    public Integer call() throws InterruptedException {

        int id = (int) Thread.currentThread().getId();
        int ale=(new Random(System.currentTimeMillis()).nextInt(5)+1)*1000;
        System.out.println("Soy el Tarjetista: " + id + " Comienzo");

        System.out.println("Soy el Tarjetista: " + id + " entro en La cola");
        
        cv.entraTarjeta(id);
        cajero.entratarjeta();
        cv.saleTarjeta(id);
        cv.enCajero(id, 'T');
        
        System.out.println("Soy el Tarjetista: " + id + " entro en el cajero -------------------------->");
       
        System.out.println("Soy el Tarjetista: " + id + " tardo en el cajero --------------------------> "+ale);
        sleep(ale);
        
        System.out.println("Soy el Tarjetista: " + id + " Salgo en el cajero -------------------------->");
        cv.finCajero(id);
        cajero.saletarjeta();
        
        System.out.println("Soy el Tarjetista: " + id + " finalizo");
        return ale;
    }
}
