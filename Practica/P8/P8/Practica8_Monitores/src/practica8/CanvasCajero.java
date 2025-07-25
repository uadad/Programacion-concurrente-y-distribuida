/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author wadad
 */
class CanvasCajero extends Canvas {

    class Cajeros {

        private int id;
        private char tipo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public char getTipo() {
            return tipo;
        }

        public void setTipo(char tipo) {
            this.tipo = tipo;
        }

        public Cajeros(int id, char tipo) {
            this.id = id;
            this.tipo = tipo;
        }

    }
    private ArrayList<Integer> colaTarjeta, colaEfectivo;
    private Cajeros[] colaCajeros;
    private Image cajero, efectivo, tarjeta;

    public CanvasCajero() throws InterruptedException {
        
        this.setSize(2000, 1000);
        this.setBackground(Color.GRAY);

        colaCajeros = new Cajeros[4];
        colaTarjeta = new ArrayList<>();
        colaEfectivo = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            colaCajeros[i] = new Cajeros(-1, 'N');
        }

        cajero = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Images/Cajero.png"));
        efectivo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Images/Efectivo.png"));
        tarjeta = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Images/Tarjeta.png"));

        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(cajero, 0);
        dibu.waitForID(0);
        dibu.addImage(efectivo, 1);
        dibu.waitForID(1);
        dibu.addImage(tarjeta, 2);
        dibu.waitForID(2);
    }

    public synchronized void entraTarjeta(int id) {
        colaTarjeta.add(id);
        repaint();
    }

    public synchronized void saleTarjeta(int id) {
        colaTarjeta.remove((Object) id);
        repaint();
    }

    public synchronized void entraEfectivo(int id) {
        colaEfectivo.add(id);
        repaint();
    }

    public synchronized void saleEfectivo(int id) {
        colaEfectivo.remove((Object) id);
        repaint();
    }

    public synchronized void enCajero(int id, char tipo) {
        for (int i = 0; i < 4; i++) {
            if (colaCajeros[i].getId() == -1) {
                colaCajeros[i].setId(id);
                colaCajeros[i].setTipo(tipo);
                break;
            }
        }
        repaint();
    }

    public synchronized void finCajero(int id) {
        for (int i = 0; i < 4; i++) {
            if (colaCajeros[i].getId() == id) {
                colaCajeros[i].setId(-1);
                colaCajeros[i].setTipo('N');
                break;
            }
        }
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Font f1 = new Font("Arial", Font.BOLD, 25);
        Image img = createImage(getWidth(), getHeight());
        Graphics gbuf = img.getGraphics();
        gbuf.setFont(f1);

        gbuf.setColor(Color.white);
        gbuf.fillRect(30, 50, 1900, 200);
        gbuf.fillRect(30, 620, 1900, 200);
        gbuf.fillRect(30, 280, 200, 300);
        gbuf.fillRect(240, 280, 200, 300);
        gbuf.fillRect(450, 280, 200, 300);
        gbuf.fillRect(670, 280, 200, 300);

        gbuf.setColor(Color.black);
        for (int i = 0; i < colaTarjeta.size(); i++) {
            gbuf.drawString("" + colaTarjeta.get(i), 35 + (i * 150), 80);
            gbuf.drawImage(tarjeta, 35 + (i * 150), 100, null);
        }
        for (int i = 0; i < colaEfectivo.size(); i++) {
            gbuf.drawString("" + colaEfectivo.get(i), 35 + (i * 150), 640);
            gbuf.drawImage(efectivo, 35 + (i * 150), 660, null);
        }

        for (int i = 0; i < colaCajeros.length; i++) {
            //gbuf.drawImage(cajero,50, 420, null);
            if (colaCajeros[i].getId() != -1) {
                if (colaCajeros[i].getTipo() == 'T') {
                    gbuf.drawString("" + colaCajeros[i].getId(), 35 + (i * 220), 300);
                    gbuf.drawImage(tarjeta, 35 + (i * 220), 320, null);
                } else if(colaCajeros[i].getTipo()=='E') {

                    gbuf.drawString("" + colaCajeros[i].getId(),35 + (i * 220), 300);
                    gbuf.drawImage(efectivo, 35 + (i * 220), 320, null);
                    
                }
            }
        }
        g.drawImage(img, 0, 0, this);
    }

}
