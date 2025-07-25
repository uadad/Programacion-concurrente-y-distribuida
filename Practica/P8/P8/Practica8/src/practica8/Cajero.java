/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8;

/**
 *
 * @author usuario
 */
public class Cajero {

    private int numCajerostotales = 4;
    private int numCajeros = 0;
    private boolean libreEfectivo=true;
    private int efectivoEsperando=0;
    public synchronized void entraefectivo() throws InterruptedException {
      efectivoEsperando++;
        while(!libreEfectivo || numCajeros==numCajerostotales){
            
            wait();
        }
        efectivoEsperando--;
        libreEfectivo=false;
        numCajeros++;
    }

    public synchronized void entratarjeta() throws InterruptedException {
        while(numCajeros==numCajerostotales || (efectivoEsperando>0 && libreEfectivo)){
            wait();
        }
        numCajeros++;
    }

    public synchronized void saleefectivo() {
      libreEfectivo=true;
      numCajeros--;
      notifyAll();
    }

    public synchronized void saletarjeta() {
       numCajeros--;
       notifyAll();
    }
}
