/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1;

import java.awt.Canvas;
import static java.awt.Color.BLACK;
import static java.awt.Color.ORANGE;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
class Buses {

    int iid;
    char tipo;

    public Buses(int iid, char tipo) {
        this.iid = iid;
        this.tipo = tipo;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

}

public class CanvasBus extends Canvas {

    private ArrayList<Integer> colaSin = new ArrayList<>(), ColaCon = new ArrayList<>();
    private Buses[] bus;

    public CanvasBus() {

        bus = new Buses[7];
        for (int i = 0; i < 7; i++) {
            bus[i] = new Buses(-1, 'N');
        }
        this.setSize(1200,900);
        this.setBackground(RED);
        this.setVisible(true);
        
    }

    public synchronized void enCon(int id) {
        ColaCon.add(id);
        repaint();
    }

    public synchronized void enSin(int id) {
        colaSin.add(id);
        repaint();
    }

    public synchronized void finColaCon(int id) {
        ColaCon.remove((Object)id);
        repaint();
    }

    public synchronized void finColaSin(int id) {
        colaSin.remove((Object) id);
        repaint();
    }

    public void update(Graphics g) {
        paint(g);
        repaint();
    }
   public synchronized void enBus(int id,char t){
       for (int i = 0; i < 7; i++) {
           if(bus[i].getIid()==-1){
               bus[i].setIid(id);
               bus[i].setTipo(t);
               break;
           }
       }
       repaint();
   }
   public synchronized void finBus(int id,char t){
       for (int i = 0; i < 7; i++) {
           if(bus[i].getIid()==id){
               bus[i].setIid(-1);
               bus[i].setTipo('N');
               break;
           }
       }
       repaint();
   }
    public void paint(Graphics g) {
        Font f = new Font("Arial", Font.BOLD, 50);
        Image img = createImage(getWidth(), getHeight());
        Graphics gbuf = img.getGraphics();
        gbuf.setFont(f);
          gbuf.setColor(WHITE);
        gbuf.fillRect(30, 20, 1000, 150);
        gbuf.fillRect(30, 190, 800, 400);
        gbuf.fillRect(30, 620, 1000, 150);
        for (int i = 0; i < colaSin.size(); i++) {
            gbuf.setColor(BLACK);
            gbuf.drawString("S" + colaSin.get(i), 35 + (i * 100), 50);
        }
        for (int i = 0; i < ColaCon.size(); i++) {
            gbuf.setColor(ORANGE);
            gbuf.drawString("C" + ColaCon.get(i), 35 + (i * 100), 680);
        }

        for (int i = 0; i < 7; i++) {
            if (bus[i].getIid() != -1) {
                if (bus[i].getTipo() == 'C') {
                    gbuf.setColor(BLACK);
                    gbuf.drawString("S" + bus[i].getIid(), 50+(i*100), 300);
                }else{
                      gbuf.setColor(ORANGE);
                     gbuf.drawString("C" + bus[i].getIid(), 50+(i*100), 300);
                }
            }
        }
        g.drawImage(img, 0, 0, this);
    }
}
