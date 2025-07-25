/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8;

import ICajero.ICajero;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author usuario
 */
public class Cajero extends UnicastRemoteObject implements ICajero {

    private int numCajerostotales = 4;
    private int numCajeros = 0;
    private boolean libreEfectivo=true;
    private int efectivoEsperando=0;
    private CanvasCajero cv;

    public Cajero(CanvasCajero cv) throws RemoteException{
        this.cv = cv;
    }
    
    @Override
    public synchronized void entraefectivo(int id) throws InterruptedException, RemoteException {
        cv.entraEfectivo(id);
      efectivoEsperando++;
        while(!libreEfectivo || numCajeros==numCajerostotales){
            
            wait();
        }
        efectivoEsperando--;
        libreEfectivo=false;
        numCajeros++;
         cv.saleEfectivo(id);
         cv.enCajero(id, 'E');
    }

    @Override
    public synchronized void entratarjeta(int id)throws InterruptedException, RemoteException {
         cv.entraTarjeta(id);
        while(numCajeros==numCajerostotales || (efectivoEsperando>0 && libreEfectivo)){
            wait();
        }
        numCajeros++;
        cv.saleTarjeta(id);
        cv.enCajero(id, 'T');
    }

    @Override
    public synchronized void saleefectivo(int id)throws RemoteException {
      libreEfectivo=true;
      numCajeros--;
      notifyAll();
      cv.finCajero(id);
    }

    @Override
    public synchronized void saletarjeta(int id)throws RemoteException {
       numCajeros--;
       notifyAll();
       cv.finCajero(id);
      
    }
}
