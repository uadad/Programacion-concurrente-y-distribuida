/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author usuario
 */
public class Cortado extends Thread {
    private Semaphore sLeche;
    private  Semaphore sCafe;
     private Semaphore Salacafe;
    private  Semaphore SalaLeche;
     private  Semaphore Papelera;
     private CavasCongreso cv;
    
    public Cortado( Semaphore sCafe,Semaphore sLeche, Semaphore SalaLeche, Semaphore Salacafe,Semaphore Papelera, CavasCongreso cv) {
       this.sCafe=sCafe;
       this.sLeche=sLeche;
       this.Papelera=Papelera;
       this.SalaLeche=SalaLeche;
       this.Salacafe=Salacafe;
       this.cv=cv;
       
    }
    
    
    public void run(){
        long id=this.getId();
        Random rand=new Random(System.currentTimeMillis());
        
        System.out.println("Cortado: Soy el hilo " + id);

        try {
            cv.encolaleche((int)id, 'C',1 ,2 );
            System.out.println("El hilo " + id + " : voy a entrar en la sala de Leche.");
            SalaLeche.acquire();
            
            cv.fincolaleche((int)id, 'C',1 ,2);
            cv.ensalaleche((int)id, 'C',1 ,2);
            
            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : voy a coger una dosis de leche.");
            sLeche.acquire();
            
            cv.finsalaleche((int)id, 'C',0 ,2);
            SalaLeche.release();
             cv.encolacafe((int)id, 'C',0 ,2 );
             
            System.out.println("                         <------------------------------Dosis de Leche -1");
            System.out.println("**************************************************************************");
            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : voy a entrar en la sala de Cafe.");
            Salacafe.acquire();
           
            
            cv.fincolacafe((int)id, 'C',0 ,2);
            cv.ensalacafe((int)id, 'C',0 ,2);
            
            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : voy a coger una dosis de Cafe.");
            
            sCafe.acquire();
            cv.ensalacafe((int)id, 'C',0 ,1);
            
            System.out.println("                         <------------------------------Dosis Cafe -1");
            System.out.println("**************************************************************************");
            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : voy a coger otra dosis de Cafe.");
            sCafe.acquire();
            System.out.println("                         <------------------------------Dosis Cafe -1");
            System.out.println("**************************************************************************");
            System.out.println("                          <-----------------------------------");
            
            cv.finsalacafe((int) id, 'C', 0, 0);
            Salacafe.release();
            cv.ensalon((int) id, 'C', 0, 0);
            
            System.out.println("El hilo " + id + " : Me voy Al salon.");
            sleep((rand.nextInt(3)+1)*1000);
            System.out.println("                          <-----------------------------------");
            
            cv.finsalon((int)id, 'C',0 ,0);
            cv.encolapapelera((int)id, 'C',0 ,0);
       
            System.out.println("El hilo " + id + " : Me voy Al papalera.");
            Papelera.acquire();
           
            
            cv.fincolapapelera((int)id, 'C',0 ,0);
            cv.enpapelera((int)id, 'C',0 ,0);
            
            System.out.println("                          <-----------------------------------");
            System.out.println("El hilo " + id + " : entro a la papalera.");
            sleep(1000);
            cv.finpapelera((int)id, 'C',0 ,0); 
            Papelera.release();
            System.out.println("                          <-----------------------------------");

        } catch (Exception ex) {
            Logger.getLogger(Cortado.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Cortado: El hilo " + id + " Finaliza.");
    }
   }
