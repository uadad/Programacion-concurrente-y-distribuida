/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;

/**
 *
 * @author usuario
 */
public class Asistente extends Thread {

    private Terminal t;

    public Asistente(Terminal t) {
        this.t = t;
    }

    @Override
    public void run() {
        System.out.println("     Asistente Comienzo     ");
        try {
           while (true) {
                t.Cargar();
                sleep(5000);
                t.reponer();
            }

        } catch (InterruptedException ex) {
            System.out.println("     Asistente Interrumpido     ");
        }
    }
}
