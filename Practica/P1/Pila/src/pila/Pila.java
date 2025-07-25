/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pila;

/**
 *
 * @author usuario
 */
public class Pila implements IPila{

    private int numelementos;
    private int cima;
    private int capacidad;
    private Object datos[];
    
    public Pila(int capacidad){
        datos=new Object[capacidad];
        this.capacidad=capacidad;
        cima=0;
        numelementos=0;
    }
   public int GetNum(){
       return numelementos;
   }
   
    @Override
   public void Apila(Object elemento) throws Exception{
       if(numelementos!=this.capacidad){
           cima++;
           datos[numelementos]=elemento;
           numelementos++;
       }
       else {
           throw new Exception("no es posible insertar este elemento dado que la pila esta llena.");
       }
   }
    public Object Desapila() throws Exception{
        if(numelementos!=0){
           Object aux= datos[cima];
            numelementos--;
            cima--;
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
}
