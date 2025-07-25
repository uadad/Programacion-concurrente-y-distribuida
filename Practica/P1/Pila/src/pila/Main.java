/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pila;

import java.util.Random;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Pila p=new Pila(5);
        for (int i = 0; i < 10; i++) {
            
        
        Random r=new Random(System.currentTimeMillis());
        if (r.nextInt(100)%2==0) {
            try {
                p.Apila(r.nextInt());
                System.out.println("elemento insertado.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
           
        }
        else {
             Object aux = p.Desapila();
            try {
                 System.out.println( "el elemento extraido." ); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        }
        System.out.println("Fin del programa.");
    }
}
