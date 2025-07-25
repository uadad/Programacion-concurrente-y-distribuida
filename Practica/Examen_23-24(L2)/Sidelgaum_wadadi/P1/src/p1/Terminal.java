/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;

/**
 *
 * @author usuario
 */
public class Terminal {

    private int plaza = 7, esperaSin = 0, hay = 0;
    private boolean llega = false;

    public synchronized void SubeSin() throws InterruptedException {

        while (plaza == 0) {
            esperaSin++;
            wait();
            esperaSin--;
        }

        plaza--;
        notifyAll();
    }

    public synchronized void SubeCon() throws InterruptedException {

        while (plaza < 2 || esperaSin > 0) {
            wait();
        }
        plaza -= 2;
        notifyAll();
    }

    public synchronized void Baja(int i) throws InterruptedException {
        while (!llega || plaza == 7) {
            wait();
        }
        hay += i;
        
    }

    public synchronized void CargaBus() throws InterruptedException {
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
