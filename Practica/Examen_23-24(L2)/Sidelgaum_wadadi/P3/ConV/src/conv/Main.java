/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conv;

import ITerminal.ITerminal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wadad
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int id = i + 15;
            System.out.println("CON " + id + " : Comienzo.");

            try {
                ITerminal t = (ITerminal) Naming.lookup("rmi://localhost:2023/terminal");

                System.out.println("CON " + id + " : voy a subir en el bus.");
                t.SubeCon();
                System.out.println("CON " + id + " : -------------------> estoy en el bus (+2).");

                t.Baja(2);
                System.out.println("CON " + id + " : <------------------- bajp del bus(-2).");

                System.out.println("CON " + id + " : Finalizo.");
            } catch (InterruptedException e) {
            } catch (RemoteException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
