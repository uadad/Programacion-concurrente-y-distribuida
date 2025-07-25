/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_frame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author pedro
 */
public class VistaCanvas extends Canvas {

    int[] contadores={0,0};

    public VistaCanvas(int ancho, int alto) {
        setSize(new Dimension(alto, ancho));
        this.setBackground(Color.cyan);
        repaint();
    }
    
    /**
     *
     * @param datos
     */
    public void representa(int[] datos)
    {
        contadores=datos;
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        // Se crea un buffer intermedio para que montar la salida completa
        // y luego pintarla de una vez, evitando el parpadeo
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();

        Font f1 = new Font("Helvetica", Font.ITALIC + Font.BOLD, 20);

        og.setFont(f1);
        og.setColor(Color.red);
        og.fillOval(20, 30, 20, 20);
        og.drawString("Valor de contador 1 --> " + contadores[0], 50, 50);
        og.setColor(Color.blue);
        og.fillOval(20, 80, 20, 20);
        og.drawString("Valor de contador 2 --> " + contadores[1], 50, 100);
        og.drawImage(offscreen, 0, 0, null);
        
        g.drawImage(offscreen, 0, 0, null);
    }

    /* El update original del canvas, borra el canvas y llama a paint. Si queremos 
     sobreescribir  lo que hay pintado, sobrecargamos update y hacemos que llame 
     paint. Así no borra lo anterior, y no se produce parpadeo
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

}

