/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ICajero;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author usuario
 */
public interface ICajero extends Remote{

    void entraefectivo(int id) throws InterruptedException, RemoteException;

    void entratarjeta(int id) throws InterruptedException, RemoteException;

    void saleefectivo(int id)  throws RemoteException;;

    void saletarjeta(int id)  throws RemoteException;;
    
}
