/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;
import ITerminal.ITerminal;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
/**
 *
 * @author usuario
 */
public class Terminal extends UnicastRemoteObject implements ITerminal{

    private int plaza = 7, esperaSin = 0, hay = 0;
    private boolean llega = false;

    public Terminal() throws RemoteException {
    }
    
    
    @Override
    public synchronized void SubeSin() throws InterruptedException,RemoteException {

        while (plaza == 0) {
            esperaSin++;
            wait();
            esperaSin--;
        }

        plaza--;
        notifyAll();
    }

    @Override
    public synchronized void SubeCon() throws InterruptedException ,RemoteException{

        while (plaza < 2 || esperaSin > 0) {
            wait();
        }
        plaza -= 2;
        notifyAll();
    }

    @Override
    public synchronized void Baja(int i) throws InterruptedException,RemoteException {
        while (!llega || plaza == 7) {
            wait();
        }
        hay += i;
        
    }

    public synchronized void CargaBus() throws InterruptedException,RemoteException {
        if (hay == 7) {
            plaza = 7;
            hay = 0;
            llega = false;
            notifyAll();
        }
        while (plaza > 0) {
            wait();
        }
    }

    public synchronized void DescargaBus() throws InterruptedException {
        llega = true;
        notifyAll();
    }
}
