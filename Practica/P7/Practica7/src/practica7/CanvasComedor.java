/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7;

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
public class CanvasComedor extends Canvas {

    private final Image Gatoimg, Perroimg;
    private ArrayList<Integer> Colagato;
    private ArrayList<Integer> Colaperro;
    private Animal[] ColaComedor;

    class Animal {

        char tipo;
        int id;

        public Animal(int id, char tipo) {
            this.tipo = tipo;
            this.id = id;
        }

        public char getTipo() {
            return tipo;
        }

        public int getId() {
            return id;
        }

        public void setTipo(char tipo) {
            this.tipo = tipo;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Animal other = (Animal) obj;
            return this.id == other.id;
        }

    }

    public CanvasComedor() throws InterruptedException {

        this.setSize(1000, 700);
        this.setBackground(Color.GRAY);

        Colagato = new ArrayList<>();
        Colaperro = new ArrayList<>();
        ColaComedor = new Animal[4];
        for (int i = 0; i < 4; i++) {
            ColaComedor[i] = new Animal(-1, 'N');
        }

        Gatoimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graficas/gato.png"));
        Perroimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graficas/perro.png"));
        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(Gatoimg, 0);
        dibu.waitForID(0);
        dibu.addImage(Perroimg, 1);
        dibu.waitForID(1);

    }

    public synchronized void encolarGato(int id) {
        Colagato.add(id);
        repaint();
    }

    public synchronized void desencolarGato(int id) {
        Colagato.remove((Object) id);
        repaint();
    }

    public synchronized void encolarPerro(int id) {
        Colaperro.add(id);
        repaint();
    }

    public synchronized void desencolarPerro(int id) {
        Colaperro.remove((Object) id);
        repaint();
    }

    public synchronized void encolarComedor(int id, char tipo) {
        if (ColaComedor[0].getId() == -1) {
            ColaComedor[0].setId(id);
            ColaComedor[0].setTipo(tipo);
        } else if (ColaComedor[1].getId() == -1) {
            ColaComedor[1].setId(id);
            ColaComedor[1].setTipo(tipo);
        } else if (ColaComedor[2].getId() == -1) {
            ColaComedor[2].setId(id);
            ColaComedor[2].setTipo(tipo);
        } else if (ColaComedor[3].getId() == -1) {
            ColaComedor[3].setId(id);
            ColaComedor[3].setTipo(tipo);
        }
        repaint();
    }

    public synchronized void desencolarComedor(int id) {
        if (ColaComedor[0].getId() == id) {
            ColaComedor[0].setId(-1);
            ColaComedor[0].setTipo('N');
        } else if (ColaComedor[1].getId() == id) {
            ColaComedor[1].setId(-1);
            ColaComedor[1].setTipo('N');
        } else if (ColaComedor[2].getId() == id) {
            ColaComedor[2].setId(-1);
            ColaComedor[2].setTipo('N');
        } else if (ColaComedor[3].getId() == id) {
            ColaComedor[3].setId(-1);
            ColaComedor[3].setTipo('N');
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        Font f1 = new Font("Arial", Font.BOLD, 24);
        Image img = createImage(getWidth(), getHeight());
        Graphics gbuf = img.getGraphics();
        gbuf.setFont(f1);

        //Cola Perro
        gbuf.setColor(Color.white);
        gbuf.fillRect(30, 30, 950, 150);
        //Cola gato
        gbuf.fillRect(30, 470, 950, 150);
        //Comedores
        gbuf.fillRect(30, 220, 200, 200);
        gbuf.fillRect(250, 220, 200, 200);
        gbuf.fillRect(480, 220, 200, 200);
        gbuf.fillRect(700, 220, 200, 200);

        gbuf.setColor(Color.black);
        for (int i = 0; i < Colagato.size(); i++) {
            gbuf.drawImage(Gatoimg, 35 + (120 * i), 490, null);
            gbuf.drawString("" + Colagato.get(i), 35 + (120 * i), 480);
        }

        for (int i = 0; i < Colaperro.size(); i++) {
            gbuf.drawImage(Perroimg, 35 + (120 * i), 70, null);
            gbuf.drawString("" + Colaperro.get(i), 35 + (120 * i), 50);
        }

        for (int i = 0; i < ColaComedor.length; i++) {
            if (ColaComedor[i].getId() != -1) {
                if (ColaComedor[i].getTipo() == 'G') {
                    gbuf.drawImage(Gatoimg, 35 + (240 * i), 260, null);
                    gbuf.drawString("" + ColaComedor[i].getId(), 35 + (240 * i), 240);
                } else if (ColaComedor[i].getTipo() == 'P') {
                    gbuf.drawImage(Perroimg, 35 + (240 * i), 260, null);
                    gbuf.drawString("" + ColaComedor[i].getId(), 35 + (240 * i), 240);
                }
            }
        }
        g.drawImage(img, 0, 0, this);
    }

    @Override

    public void update(Graphics g) {
        paint(g);
        repaint();
    }
}
