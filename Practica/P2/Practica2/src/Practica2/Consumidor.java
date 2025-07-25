/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Consumidor implements Runnable {
  private Pila lapila;
    
   public Consumidor(Pila p){
       lapila=p;
   }
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            try {
                Object aux=lapila.Desapila();
                System.out.println("Soy el hilo " + Thread.currentThread().getName()+" y elemento desapilado " + aux +" correctamente.");
                
            } catch (Exception ex) {
                 System.out.println("soy el hilo: "+ Thread.currentThread().getName()+" "+ex.getMessage());
            }
        }
    }
}
