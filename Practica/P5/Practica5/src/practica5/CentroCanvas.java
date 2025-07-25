/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica5;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author wadad
 */
class Cliente {

    int id;
    String tipo;

    public Cliente() {

    }

    public Cliente(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        //  this.t = t;
    }

    public void setid(int id) {
        this.id = id;

    }

    /*public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }*/
    public void settipo(String tipo) {
        this.tipo = tipo;

    }

    public int getid() {
        return id;
    }

    public String gettipo() {
        return tipo;
    }

}

public class CentroCanvas extends Canvas {

    private Cliente[] clientes = new Cliente[2]; //clientes[0]=Masaje, clientes[1]=rehabilita
    private Cliente vestuario;
    Image masajeimg, rehabilistaimg, clienteRimg, clienteMimg;
    private ArrayList<Integer> colaMasajistas = new ArrayList();
    private ArrayList<Integer> colaRehabilistas = new ArrayList();
    private ArrayList<Boolean> rehabilistade = new ArrayList();
    private ArrayList<Boolean> masajistade = new ArrayList();

    public CentroCanvas(int x, int y) throws InterruptedException {
        this.setSize(x, y);
        this.setBackground(Color.GRAY);

        clientes[0] = new Cliente(-1, "N");
        clientes[1] = new Cliente(-1, "N");
        vestuario = new Cliente(-1, "N");

        masajeimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/masaje1.png"));
        rehabilistaimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/fisio1.png"));
        clienteRimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/rehabilista1.png"));
        clienteMimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/masajista1.png"));
        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(masajeimg, 0);
        dibu.waitForID(0);
        dibu.addImage(rehabilistaimg, 1);
        dibu.waitForID(1);

    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Image imagen = createImage(getWidth(), getHeight());
        Font f1 = new Font("Arial", Font.BOLD, 30);
        Font f2 = new Font("Arial", Font.BOLD, 15);
        Graphics buffer = imagen.getGraphics();
        buffer.setFont(f1);
        buffer.setColor(Color.white);
        buffer.fillRect(30, 50, 820, 120);
        buffer.fillRect(30, 220, 350, 350);
        buffer.fillRect(390, 220, 350, 350);
        buffer.fillRect(750, 220, 180, 350);
        buffer.fillRect(30, 620, 820, 120);
        buffer.setColor(Color.black);

        buffer.drawString("COLA-Rehabilista", 300, 40);
        buffer.drawString("COLA-Masajista", 300, 605);
        buffer.drawString("SALA-M", 120, 250);
        buffer.drawString("SALA-R", 500, 250);
        buffer.drawString("SALA-V", 780, 250);

        buffer.drawImage(masajeimg, 150, 340, null);
        buffer.drawImage(rehabilistaimg, 500, 340, null);

        buffer.setFont(f2);
        for (int i = 0; i < colaRehabilistas.size(); i++) {
            buffer.drawString(" " + colaRehabilistas.get(i), 55 + 70 * i, 80);
            buffer.drawImage(clienteRimg, 40 + 70 * i, 80, null);
        }

        for (int i = 0; i < colaMasajistas.size(); i++) {
            buffer.drawString(" " + colaMasajistas.get(i), 55 + 70 * i, 640);
            buffer.drawImage(clienteMimg, 40 + 70 * i, 640, null);
        }

        if (vestuario.getid() != -1) {
            if (vestuario.gettipo().equals("R")) {
                //buffer.setColor(Color.red);
                buffer.drawImage(clienteRimg, 760, 420, null);

            } else {
                // buffer.setColor(Color.blue);
                buffer.drawImage(clienteMimg, 760, 420, null);
            }

            buffer.drawString("" + vestuario.getid(), 775, 420);
        }

        if (clientes[0].getid() != -1) {
            if (clientes[0].gettipo().equals("M")) {
                // buffer.setColor(Color.red);
                buffer.drawString("" + clientes[0].getid(), 85, 420);
                buffer.drawImage(clienteMimg, 70, 420, null);

            } else {
                buffer.drawString("" + clientes[0].getid(), 355, 420);
                buffer.drawImage(clienteMimg, 340, 420, null);
            }

        }

        if (clientes[1].getid() != -1) {
            if (clientes[1].gettipo().equals("M")) {
                //buffer.setColor(Color.red);
                buffer.drawString("" + clientes[1].getid(), 505, 420);
                buffer.drawImage(clienteMimg, 490, 420, null);
            } else if (clientes[1].gettipo().equals("R")) {
                buffer.drawString("" + clientes[1].getid(), 505, 420);
                buffer.drawImage(clienteRimg, 490, 420, null);
            } else if(clientes[1].gettipo().equals("EsperaVM")) {
                buffer.drawString("" + clientes[1].getid(), 645, 420);
                buffer.drawImage(clienteMimg, 630, 420, null);
            }
            else{
                buffer.drawString("" + clientes[1].getid(), 645, 420);
                buffer.drawImage(clienteRimg, 630, 420, null);
            }
        }

        g.drawImage(imagen, 0, 0, this);
    }

    public synchronized void encolarMasajista(int id) {
        colaMasajistas.add(id);
        repaint();
    }

    public synchronized void encolarRehabilista(int id) {
        colaRehabilistas.add(id);
        repaint();
    }

    public synchronized void terminaMasajista(int id) {
        colaMasajistas.remove((Object) id);
        repaint();
    }

    public synchronized void terminaRehabilista(int id) {
        colaRehabilistas.remove((Object) id);
        repaint();
    }

    public synchronized void dentroM(int id, String tipo) {
        if (clientes[0].getid() == -1) {
            clientes[0].setid(id);
            clientes[0].settipo(tipo);
        }
        repaint();
    }

    public synchronized void finM(int id, String tipo) {
        if (clientes[0].getid() == id) {
            clientes[0].setid(-1);
            clientes[0].settipo("N");
        }
        repaint();
    }

    public synchronized void dentroR(int id, String tipo) {
        if (clientes[1].getid() == -1) {
            clientes[1].setid(id);
            clientes[1].settipo(tipo);
        }
        repaint();
    }

    public synchronized void finR(int id, String tipo) {
        if (clientes[1].getid() == id) {
            clientes[1].setid(-1);
            clientes[1].settipo("N");
        }
        repaint();
    }

    public synchronized void dentroVestuario(int id, String tipo) {
        vestuario.setid(id);
        vestuario.settipo(tipo);
        repaint();
    }

    public synchronized void finVestuario(int id) {
        vestuario.setid(-1);
        vestuario.settipo("N");
        repaint();
    }
}
