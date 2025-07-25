/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Productor extends Thread{
    private Pila lapila;
    
   public Productor(Pila p){
       lapila=p;
   }
    @Override
    public void run(){
        
        for (int i = 0; i < 10; i++) {
         Random r=new Random(System.currentTimeMillis());
                 int x=r.nextInt(100);
                try {
                lapila.Apila(x);
                    System.out.println("Soy el hilo " + getName()+ " y he insertado elemento "+ x +" correcto.");
                } catch (Exception ex) {
                 System.out.println( ex.getMessage());
                }
        }
    }
    
}
