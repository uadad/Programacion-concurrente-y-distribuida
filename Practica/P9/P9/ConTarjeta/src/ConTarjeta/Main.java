/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ConTarjeta;

import ICajero.ICajero;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, InterruptedException {

        ICajero cajero = (ICajero) Naming.lookup("rmi://localhost:2023/cajeroremoto");
        for (int i = 0; i < 15; i++) {
            int id = i;
        int ale = (new Random(System.currentTimeMillis()).nextInt(5) + 1) * 1000;
        System.out.println("Soy el Tarjetista: " + id + " Comienzo");

        System.out.println("Soy el Tarjetista: " + id + " entro en La cola");
        cajero.entratarjeta(id);

        System.out.println("Soy el Tarjetista: " + id + " entro en el cajero -------------------------->");

        System.out.println("Soy el Tarjetista: " + id + " tardo en el cajero --------------------------> " + ale);
        sleep(ale);

        System.out.println("Soy el Tarjetista: " + id + " Salgo en el cajero -------------------------->");
        cajero.saletarjeta(id);

        System.out.println("Soy el Tarjetista: " + id + " finalizo");
        }
        
    }

}
