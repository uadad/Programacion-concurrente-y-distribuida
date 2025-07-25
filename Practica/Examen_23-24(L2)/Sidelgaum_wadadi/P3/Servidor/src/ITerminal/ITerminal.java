/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ITerminal;

import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 *
 * @author wadad
 */
public interface ITerminal extends Remote{

    void Baja(int i) throws InterruptedException, RemoteException;

    void SubeCon() throws InterruptedException, RemoteException;

    void SubeSin() throws InterruptedException, RemoteException;
    
}
