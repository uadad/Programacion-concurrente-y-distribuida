/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Practica2;

/**
 *
 * @author usuario
 */
public interface IPila {
    public int GetNum();
    public void Apila(Object elemento) throws Exception;
     public Object Desapila() throws Exception;
     public Object Primero() throws Exception;
     
}
