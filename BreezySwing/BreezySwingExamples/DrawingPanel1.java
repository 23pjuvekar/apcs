/*
File: DrawingPanel1.java

A panel that allows the user to draw ovals by dragging the mouse from
one corner of the oval to another (upper left to lower right)
*/

import BreezySwing.*;
import java.awt.*;
import java.awt.geom.*;

public class DrawingPanel1 extends GBPanel{

    private java.util.List<Shape> shapes = new java.util.ArrayList<Shape>();
    private int mouseX = 0, mouseY = 0;  // Track the position of a mouse press

    public DrawingPanel1(Color color){
        setBackground(color);
    }

    public void paintComponent (Graphics g){
        // Clear the panel and redraw all the points
        super.paintComponent(g);
        g.setColor(Color.blue);
        for (Shape s : shapes)
            ((Graphics2D)g).fill(s);
    }

    public void mousePressed(int x, int y){
        // Remember the coordinates of the press event
        mouseX = x;
        mouseY = y;
    }

    public void mouseReleased(int x, int y){
        // Add the oval to the list of shapes and refresh the panel
        if (x <= mouseX || y <= mouseY)
            return;
        int width = x - mouseX;
        int height = y - mouseY;
        shapes.add(new Ellipse2D.Double(mouseX, mouseY, width, height));
        repaint();
    }

    public void clear(){
        // Clear the list of shapes and refresh the panel
        shapes = new java.util.ArrayList<Shape>();
        repaint();
    }
}
