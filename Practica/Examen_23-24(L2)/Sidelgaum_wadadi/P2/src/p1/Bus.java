/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;


/**
 *
 * @author usuario
 */
public class Bus extends Thread {

    private Terminal t;

    public Bus(Terminal t) {
        this.t = t;
    }

    @Override
    public void run() {
        System.out.println("     BUS Comienzo     ");
        try {

            while (true) {
                System.out.println("       Bus voy a cargar       ");

                t.CargaBus();
                 System.out.println("       Bus ya he cargado      ");
                sleep(2000);
                System.out.println("       Bus voy a descargar       ");

                t.DescargaBus();
                  System.out.println("       Bus ya he descargar       ");
                sleep(2000);
            }

        } catch (InterruptedException ex) {
            System.out.println("     BUS Interrumpido     ");
        }
    }
}
