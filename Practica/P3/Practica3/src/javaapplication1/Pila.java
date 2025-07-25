/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;
import javaapplication1.CanvasPila;
import static java.lang.Thread.sleep;
/**
 *
 * @author usuario
 */
public class Pila implements IPila{

    private int numelementos;
    private int cima;
    private  int capacidad;
    private volatile Object datos[];
    private CanvasPila canvas;
    
    public Pila(int capacidad,CanvasPila canvas){
        datos=new Object[capacidad];
        this.capacidad=capacidad;
        this.canvas=canvas;
        cima=0;
        numelementos=-1;
    }
    @Override
   public int GetNum(){
       return numelementos;
   }
   
    @Override
   public synchronized void Apila(Object elemento) throws Exception{
       numelementos++;
       if(!pilaLlena()){
           
           datos[numelementos]=elemento;
           
           sleep(100);
           cima++;
           canvas.representa(datos, cima, numelementos);
           sleep(100);
       }
       else {
         
           throw new Exception("no es posible insertar este elemento dado que la pila esta llena.");
       }
   }
    @Override
    public synchronized Object Desapila() throws Exception{
        if(!pilaVacia()){
           Object aux= datos[numelementos];
           sleep(100);
            numelementos--;
            cima--;
            canvas.representa(datos, cima, numelementos);
            sleep(100);
            return aux;
            
        }
        else{ 
            throw new Exception("la pila esta vacia.");
        }
    }
    @Override
     public Object Primero() throws Exception{
         if(numelementos!=0){   
            return datos[cima];
        }
        else{
            throw new Exception("la pila esta vacia.");
        }
     }
     public boolean pilaVacia(){
         if(numelementos==-1) {
             canvas.avisa("PILA VACIA");
             return true;
         }
         else return false;
     }
     public boolean pilaLlena(){
         if(cima==capacidad) {
             canvas.avisa("PILA LLENA");
             return true;
         }
         else return false;
     }
}
