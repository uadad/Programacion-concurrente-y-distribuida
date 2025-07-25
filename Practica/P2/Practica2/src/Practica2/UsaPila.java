/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica2;

import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class UsaPila {
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Pila p=new Pila(20);
         
      /*  System.out.println("INICIO DEL Programa.");
        for (int i = 0; i < 10; i++) {
            p.Apila(i+1);
            System.out.println("elemento insertado correctamento.");
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("He desapilado "+p.Desapila());
        }*/
      
        Productor p1=new Productor(p);
        Productor p2=new Productor(p);
        
        Thread c1=new Thread(new Consumidor(p));
        Thread c2=new Thread(new Consumidor(p));
        
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        
      
        p1.join();
        p2.join();
        c1.join();
        c2.join();
        
        System.out.println("Fin del programa.");
    }
}
