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
public class sinTarjeta implements Callable<Integer> {

    private Cajero cajero;
    private CanvasCajero cv;

    public sinTarjeta(Cajero cajero,CanvasCajero cv) {
        this.cajero = cajero;
        this.cv=cv;
    }

    @Override
    public Integer call() throws InterruptedException {
        int id = (int) Thread.currentThread().getId();
        int ale=(new Random(System.currentTimeMillis()).nextInt(5)+1)*1000;
        System.out.println("Soy el Efectivista: " + id + " Comienzo");

        System.out.println("Soy el Efectivista: " + id + " entro en La cola");
        
        cv.entraEfectivo(id);
        cajero.entraefectivo();
        cv.saleEfectivo(id);
        
        cv.enCajero(id, 'E');
        System.out.println("Soy el Efectivista: " + id + " entro en el cajero -------------------------->");
       
        System.out.println("Soy el Efectivista: " + id + " tardo en el cajero --------------------------> "+ale);
        sleep(ale);
        
        System.out.println("Soy el Efectivista: " + id + " Salgo en el cajero -------------------------->");
        cv.finCajero(id);
        cajero.saleefectivo();
        
        
        System.out.println("Soy el Efectivista: " + id + " finalizo");
        return ale;
    }
}
