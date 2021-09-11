/*
File: DrawingPanel2.java

A panel that allows the user to draw freehand figures
*/

import BreezySwing.*;
import java.awt.*;
import java.awt.geom.*;

public class DrawingPanel2 extends GBPanel{

    private java.util.List<Shape> shapes = new java.util.ArrayList<Shape>();
    private int mouseX = 0, mouseY = 0;  // Track the position of a mouse press

    public DrawingPanel2(Color color){
        setBackground(color);
    }

    public void paintComponent (Graphics g){
        // Clear the panel and redraw all the line segments
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

    public void mouseDragged(int x, int y){
        // Add a line segment to the list, remember the new mouse position, and refresh the panel
        shapes.add(new Line2D.Double(mouseX, mouseY, x, y));
        mouseX = x;
        mouseY = y;
        repaint();
    }

    public void clear(){
        // Clear the list of shapes and refresh the panel
        shapes = new java.util.ArrayList<Shape>();
        repaint();
    }
}
