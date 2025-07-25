/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author wadad
 */
public class Comedero {

    private int numerop;
    private int numerog;
    private int Colap;
    private int Colag;
    private Lock rl = new ReentrantLock();
    private Condition Colaperro = rl.newCondition();
    private Condition Colagato = rl.newCondition();

    public Comedero() {
        numerop = 0;
        numerog = 0;
        Colag = 0;
        Colap = 0;
    }

    public void entraPerro() throws InterruptedException {
        rl.lock();
        try {
            Colap++;
            while (numerog == 3 || (numerog == 1 && numerop == 2) || (numerog + numerop == 4)) {
                Colaperro.await();
            }
            Colap--;
            numerop++;
        } finally {
            rl.unlock();
        }
    }

    public void entraGato() throws InterruptedException {
        rl.lock();
        try {
            Colag++;
            while (numerop == 3 || (numerop == 1 && numerog == 2) || (numerog + numerop == 4)) {
                Colagato.await();
            }
            Colag--;
            numerog++;
        } finally {
            rl.unlock();
        }
    }

    public void saleGato() {
        rl.lock();
        try {
            numerog--;
            if (Colag > 0) {
                Colagato.signal();
            } else {
                Colaperro.signal();
            }
        } finally {
            rl.unlock();
        }
    }

    public void salePerro() {
        rl.lock();
        try {
            numerop--;
            if (Colap > 0) {
                Colaperro.signal();
            } else {
                Colagato.signal();
            }
        } finally {
            rl.unlock();
        }
    }
}
