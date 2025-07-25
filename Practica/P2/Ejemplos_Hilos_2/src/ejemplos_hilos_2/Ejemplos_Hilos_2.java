/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos_hilos_2;

/**
 *
 * @author pedro
 */
public class Ejemplos_Hilos_2 {
    public static void main(String[] args) {

         // TODO code application logic here
        compartido ob1 = new compartido(0);
        Thread hilo1 = new Thread(new sumador(ob1));
        Thread hilo2 = new Thread(new sumador(ob1));
        Thread hilo3 = new Thread(new sumador(ob1));
        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (Exception ex) {
            System.out.println("Capturada execpcion de join " + ex.getMessage());
        }
        //solo a veces valdrá el triple
        System.out.println("Tras finalizar los hilos, el contador vale "+ ob1.Getnum());
    }
}

class sumador implements Runnable {

   compartido c1;

   public sumador(compartido c){
       c1=c;
   }
   
    @Override
    public void run() {

        for (int i = 1; i <= 100000; i++) {
            c1.Incrementa();
        }
    }

}

class compartido{
    int contador;
    
    public compartido(int inicial){
        contador=inicial;
    }
    
    public int Getnum(){
        return contador;
    }
    
    public  void Incrementa(){
        contador ++;
    }
}
