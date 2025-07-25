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
    private Condition colaMoto = l.newCondition(), colaCoche = l.newCondition(), colaAsistente = l.newCondition();
    private int Tinta = 5, esperacoche = 0, libres = 3;

    public void entraMoto(int id) throws InterruptedException {
        l.lock();
        try {
             System.out.println("Moto "+id+"  EsperaCoche: "+esperacoche+"     lIBRES: "+libres);
            while (libres < 1 || Tinta == 0 || (libres > 1 && esperacoche > 0)) {
                colaMoto.await();
            }
            libres--;
             System.out.println("Moto "+id+" : -------------------> estoy en el sello (-1).");
              Tinta--;
            if (Tinta == 0) {
                    colaAsistente.signal();
            }
        } finally {
            l.unlock();
        }
    }

    public void entraCoche(int id) throws InterruptedException {
        l.lock();
        try {
            esperacoche++;
            while (libres < 2 || Tinta == 0) {
                colaCoche.await();
            }
            esperacoche--;
            libres--;
            libres--;
                      System.out.println("Coche "+id+" : <------------------- entro en el sello (-2).");
            Tinta--;
            if (Tinta == 0) {
                    colaAsistente.signal();
            }

        } finally {
            l.unlock();
        }
    }

    public void saleCoche(int id) throws InterruptedException {
        l.lock();
        try {
            libres += 2;
            System.out.println("Coche "+id+" : <------------------- termino del sello (+2).");
                if (esperacoche > 0) {
                    colaCoche.signal();
                } else {
                    colaMoto.signal();
                    colaMoto.signal();
                }
            
        } finally {
            l.unlock();
        }
    }
    
    public void saleMoto(int id) throws InterruptedException {
        l.lock();
        try {
            
            libres ++;
             System.out.println("Moto "+id+" : <------------------- termino del sello (+1).");
                if (esperacoche > 0 && libres>1) {
                    colaCoche.signal();
                } else {
                    colaMoto.signal();
                }
        } finally {
            l.unlock();
        }
    }

    public void Cargar() throws InterruptedException {
        l.lock();
        try {

            while (Tinta > 0) {
                colaAsistente.await();
            }
            System.out.println("                                                           Tinta==0");
        } finally {
            l.unlock();
        }
    }

    public void reponer() throws InterruptedException {
        l.lock();
        try {
            Tinta += 5;
             System.out.println("                                                           Tinta==5");
            if (libres > 1 && esperacoche > 0) {
                if (libres > 2) {
                    colaCoche.signal();
                    colaMoto.signal();
                } else {
                    colaCoche.signal();
                }
            } else {
                colaMoto.signal();
                colaMoto.signal();
                colaMoto.signal();
            }
        } finally {
            l.unlock();
        }
    }
}
