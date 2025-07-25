/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sinV;

import ITerminal.ITerminal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author wadad
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        for (int i = 0; i < 10; i++) {
            int id = i + 1;
            System.out.println("Sin " + id + " : Comienzo.");
            try {
                ITerminal t = (ITerminal) Naming.lookup("rmi://localhost:2023/terminal");
                System.out.println("Sin " + id + " : voy a subir en el bus.");
                t.SubeSin();
                System.out.println("Sin " + id + " : -------------------> estoy en el bus (+1).");

                t.Baja(1);
                System.out.println("Sin " + id + " : <------------------- bajp del bus(-1).");

                System.out.println("Sin " + id + " : Finalizo.");
            } catch (InterruptedException e) {
            }
        }
    }

}
