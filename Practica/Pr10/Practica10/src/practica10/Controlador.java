/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica10;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author wadad
 */
public class Controlador extends Thread {

    private Any2OneChannel entraPerro, entraGato, salePerro, saleGato;
    private One2OneChannel permisoPerro[], permisoGato[];
    public Controlador(Any2OneChannel entraPerro, Any2OneChannel entraGato, Any2OneChannel salePerro, Any2OneChannel saleGato, One2OneChannel[] permisoPerro, One2OneChannel[] permisoGato) {
        this.entraPerro = entraPerro;
        this.entraGato = entraGato;
        this.salePerro = salePerro;
        this.saleGato = saleGato;
        this.permisoPerro = permisoPerro;
        this.permisoGato = permisoGato;
    }

    @Override
    public void run() {
        int perrosdentro = 0, gatosdentro = 0;

        Guard[] guarda = new Guard[4];
        guarda[0] = entraPerro.in();
        guarda[1] = entraGato.in();
        guarda[2] = salePerro.in();
        guarda[3] = saleGato.in();

        boolean[] preCondition = new boolean[guarda.length];
        int id;
        while (true) {

            preCondition[0] = !(perrosdentro + gatosdentro==4 || gatosdentro == 3  || (perrosdentro==2 && gatosdentro==1));
            preCondition[1] = !(perrosdentro + gatosdentro==4  || perrosdentro == 3 || (perrosdentro==1 && gatosdentro==2));
            preCondition[2] = true;
            preCondition[3] = true;
            
            Alternative selector = new Alternative(guarda);
            int i = selector.select(preCondition);
            switch (i) {
                case 0:
                    id = (int) entraPerro.in().read();
                    perrosdentro++;
                    permisoPerro[id].out().write(-1);
                    break;
                case 1:
                    id = (int) entraGato.in().read();
                    gatosdentro++;
                    permisoGato[id].out().write(-1);
                    break;
                case 2:
                      perrosdentro--;
                      salePerro.in().read();
                    break;
                case 3:
                      gatosdentro--;
                      saleGato.in().read();
                    break;
                default:
                     System.out.println("Error");
            }
        }
    }

}
