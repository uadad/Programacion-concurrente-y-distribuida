/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sintarjeta;

import ICajero.ICajero;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class SinTarjeta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException, MalformedURLException, InterruptedException, RemoteException {

        // TODO code application logic here
        ICajero cajero = (ICajero) Naming.lookup("rmi://localhost:2023/cajeroremoto");
        for (int i = 0; i < 15; i++) {
            try {
                int id = i + 16;
                int ale = (new Random(System.currentTimeMillis()).nextInt(5) + 1) * 1000;
                System.out.println("Soy el Efectivista: " + id + " Comienzo");

                System.out.println("Soy el Efectivista: " + id + " entro en La cola");

                cajero.entraefectivo(id);

                System.out.println("Soy el Efectivista: " + id + " entro en el cajero -------------------------->");

                System.out.println("Soy el Efectivista: " + id + " tardo en el cajero --------------------------> " + ale);
                sleep(ale);

                System.out.println("Soy el Efectivista: " + id + " Salgo en el cajero -------------------------->");
                cajero.saleefectivo(id);

                System.out.println("Soy el Efectivista: " + id + " finalizo");
            } catch (RemoteException ex) {
                Logger.getLogger(SinTarjeta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
