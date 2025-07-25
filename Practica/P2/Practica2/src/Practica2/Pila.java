/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Practica2;
import static java.lang.Thread.sleep;
/**
 *
 * @author usuario
 */
public class Pila implements IPila{

    private int numelementos;
    private int cima;
    private int capacidad;
    private volatile Object datos[];
    
    public Pila(int capacidad){
        datos=new Object[capacidad];
        this.capacidad=capacidad;
        cima=1;
        numelementos=-1;
    }
   public int GetNum(){
       return numelementos;
   }
   
    @Override
   public synchronized void Apila(Object elemento) throws Exception{
       numelementos++;
       if(numelementos!=this.capacidad){
           
           datos[numelementos]=elemento;
           sleep(100);
           cima++;
           
           sleep(100);
       }
       else {
           throw new Exception("no es posible insertar este elemento dado que la pila esta llena.");
       }
   }
    public synchronized Object Desapila() throws Exception{
        if(!pilaVacia()){
           Object aux= datos[numelementos];
           sleep(100);
            numelementos--;
            cima--;
            sleep(100);
            return aux;
            
        }
        else{
            throw new Exception("la pila esta vacia.");
        }
    }
     public Object Primero() throws Exception{
         if(numelementos!=0){   
            return datos[cima];
        }
        else{
            throw new Exception("la pila esta vacia.");
        }
     }
     public boolean pilaVacia(){
         if(numelementos==-1) return true;
         else return false;
     }
     public boolean pilaLlena(){
         if(numelementos==capacidad) return true;
         else return false;
     }
}
