/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author usuario
 */
public class Terminal {

    private ReentrantLock l = new ReentrantLock();
    private Condition colaSin = l.newCondition(), colaCon = l.newCondition(), colaB = l.newCondition(), colaCarga = l.newCondition();
    private int plaza = 7, esperaSin = 0, hay = 0;
    private boolean llega = false;

    public void SubeSin() throws InterruptedException {
        l.lock();
        try {
            while (plaza == 0) {
                esperaSin++;
                colaSin.await();
                esperaSin--;
            }

            plaza--;
            colaCarga.signal();
        } finally {
            l.unlock();
        }
    }

    public void SubeCon() throws InterruptedException {
        l.lock();
        try {
            while (plaza < 2 || esperaSin > 0) {
                colaCon.await();
            }
            plaza -= 2;
            colaCarga.signal();
        } finally {
            l.unlock();
        }
    }

    public void Baja(int i) throws InterruptedException {
        l.lock();
        try {
            while (!llega || plaza == 7) {
                colaB.await();
            }
            hay += i;   
        } finally {
            l.unlock();
        }
    }

    public void CargaBus() throws InterruptedException {
        l.lock();
        try {
            if (hay == 7) {
                plaza = 7;
                hay = 0;
                llega = false;
             
                if (esperaSin == 0) {
                    colaCon.signalAll();
                } else {
                    colaSin.signalAll();
                    colaCon.signalAll();
                }
            }
            while (plaza > 0) {
                colaCarga.await();
            }
            
        } finally {
            l.unlock();
        }
    }

    public void DescargaBus() throws InterruptedException {
        l.lock();
        try {
            llega = true;
            for (int i = 0; i < 7; i++) {
                colaB.signal();
            }
        } finally {
            l.unlock();
        }
    }
}
