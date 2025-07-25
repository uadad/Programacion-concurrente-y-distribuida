/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author pedro
 */
public class CanvasTunel extends Canvas {

    private class encola {

        int id;
        int tipo;

        public encola(int elid, int eltipo) {
            this.id = elid;
            this.tipo = eltipo;
        }
    }

    private final int tipocoche = 0, tipofurgo = 1;
    private final List colacoche = new LinkedList();
    private final List colarepara = new LinkedList();
    private final MediaTracker dibu = new MediaTracker(this);
    Image cocheimg, furgoimg;
    private final Random rnd = new Random(System.currentTimeMillis());
    encola[] puesto = new encola[3];

    public CanvasTunel(int ancho, int alto) throws InterruptedException {

        super.setSize(ancho, alto);
        super.setBackground(Color.white);

        cocheimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/coche2.jpeg"));
        furgoimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/van2.jpeg"));

        dibu.addImage(cocheimg, 0);
        dibu.waitForID(0);
        dibu.addImage(furgoimg, 1);
        dibu.waitForID(1);
        for(int i=0;i<3;i++) {
            puesto[i]= new encola(-1,0);
        }

    }

    public synchronized void inserta(int donde, int id) {
        encola nuevo = new encola(id, donde);
        if (donde == tipocoche) {
            colacoche.add(nuevo);
        } else {
            colarepara.add(nuevo);
        }
        this.repaint();
    }

    public synchronized void quita(int donde, int id) {
        List cola;
        if (donde == tipocoche) {
            cola = colacoche;
        } else {
            cola = colarepara;
        }

        Iterator iter = cola.iterator();
        int i = 0;
        while (iter.hasNext()) {
            encola e = (encola) iter.next();
            if (e.id == id) {
                break;
            }
            i++;
        }
        if (i < cola.size()) {
            cola.remove(i);
        }
        this.repaint();

    }

    public synchronized void atiende(int tipo, int id) {
        encola vehiculo;
        if (tipo == tipocoche) {
            vehiculo = new encola(id, tipocoche);
        } else {
            vehiculo = new encola(id, tipofurgo);
        }

        if (puesto[0].id == -1) {
            puesto[0] = vehiculo;
        } else if (puesto[1].id == -1) {
            puesto[1] = vehiculo;
        } else {
            puesto[2] = vehiculo;
        }

        this.repaint();
    }

    public void finalizado(int id) {
        if (puesto[0].id == id) {
            puesto[0].id = -1;
        } else if (puesto[1].id == id) {
            puesto[1].id = -1;
        } else {
            puesto[2].id = -1;
        }
        repaint();

    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public synchronized void paint(Graphics g) {
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), ColorModel.OPAQUE);
        Font f1 = new Font("Arial", Font.BOLD, 12);
        Font f2 = new Font("Sans", Font.BOLD, 20);
        Graphics gbuf = imagen.getGraphics();
        gbuf.setColor(Color.GRAY);
        gbuf.fillRect(0, 0, getWidth(), getHeight());
        gbuf.setFont(f1);

        gbuf.setColor(Color.white);
        gbuf.fillRect(40, 20, getWidth() - 80, 100);
        gbuf.drawString("Cola de Coches", 40, 15);

        /*
        gbuf.setColor(Color.white);
        gbuf.fillRect(40, 140, (getWidth() - 100) / 2, 250);
        gbuf.setFont(f2);
        gbuf.setColor(Color.BLUE);
        gbuf.drawString("MECANICO", 230, 200);
        gbuf.drawImage(empleadoA, 230, 220, null);

        gbuf.setColor(Color.white);
        gbuf.fillRect(getWidth() / 2 + 10, 140, (getWidth() - 100) / 2, 250);
        gbuf.setColor(Color.RED);
        gbuf.drawString("VENDEDOR", getWidth() / 2 + 230, 200);
        gbuf.drawImage(empleadoB, 600, 220, null);
         */
        gbuf.setFont(f1);
        gbuf.setColor(Color.white);
        gbuf.fillRect(40, 420, getWidth() - 80, 100);
        gbuf.drawString("Cola de Furgos", 40, 415);

        gbuf.setColor(Color.black);

        Iterator iter = colacoche.iterator();
        int i = 0;
        while (iter.hasNext()) {
            encola e = (encola) iter.next();
            gbuf.drawString("     " + e.id + "   ", 60 + 70 * i, 40);
            gbuf.drawImage(cocheimg, 60 + 70 * i, 40, null);
            i++;
        }

        iter = colarepara.iterator();
        i = 0;
        while (iter.hasNext()) {
            encola e = (encola) iter.next();
            gbuf.drawString("     " + e.id + "   ", 60 + 70 * i, 440);
            gbuf.drawImage(furgoimg, 60 + 70 * i, 440, null);
            i++;
        }
        for (i = 0; i < 3; i++) {
            if (puesto[i].id != -1) {
                gbuf.drawString("     " + puesto[i].id + "   ", 60 + 100 * i, 230);
                if (puesto[i].tipo == tipocoche) {
                    gbuf.drawImage(cocheimg, 60 + 100 * i, 230 , null);
                } else {
                    gbuf.drawImage(furgoimg, 60 + 100 * i, 230 , null);
                }
            }
        }
        /*
        if (mecanico != null) {
            if (mecanico.tipo == Tienda.COMPRADOR) {
                
            } else {
                gbuf.drawString("     " + mecanico.id + "   ", 60, 230);
                gbuf.drawImage(repararimg[mecanico.id%7], 60, 230, null);

            }
        }

        if (vendedor != null) {
            if (vendedor.tipo == Tienda.COMPRADOR) {
                gbuf.drawString("     " + vendedor.id + "   ", 450, 230);
                gbuf.drawImage(compradorimg[vendedor.id%7], 450, 230, null);
            } else {
                gbuf.drawString("     " + vendedor.id + "   ", 450, 230);
                gbuf.drawImage(repararimg[vendedor.id%7], 450, 230, null);

            }
        }
         */
        g.drawImage(imagen, 0, 0, this);
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(CanvasTunel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
