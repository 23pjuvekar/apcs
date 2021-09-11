/*
File: GraphicsPanel2.java

A panel that draws the coordinates of mouse presses at those points.
*/

import BreezySwing.*;
import java.awt.*;

public class GraphicsPanel2 extends GBPanel{

    // Initial coordinates drawn at startup

    private int mouseX = 100, mouseY = 100;

    public GraphicsPanel2(Color color){
        setBackground(color);
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Font font = new Font("Verdana", Font.BOLD, 16);
        g.setFont(font);
        g.setColor(Color.blue);
        g.drawString("(" + mouseX + "," + mouseY + ")", mouseX, mouseY);
    }

    public void mousePressed(int x, int y){
        // Reset coordinates and refresh the panel
        mouseX = x;
        mouseY = y;
        repaint();
    }
}
