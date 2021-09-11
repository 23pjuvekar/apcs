/*
File: GraphicsPanel3.java

A panel that draws the coordinates of mouse presses at those points.
The points are retained in a list, so the panel shows all the points.
*/

import BreezySwing.*;
import java.awt.*;

public class GraphicsPanel3 extends GBPanel{

    private java.util.List<Point> points = new java.util.ArrayList<Point>();

    public GraphicsPanel3(Color color){
        setBackground(color);
    }

    public void paintComponent (Graphics g){
        // Clear the panel and redraw all the points
        super.paintComponent(g);
        Font font = new Font("Verdana", Font.BOLD, 16);
        g.setFont(font);
        g.setColor(Color.blue);
        for (Point point : points){
            int mouseX = (int)Math.round(point.getX());
            int mouseY = (int)Math.round(point.getY());
            g.drawString("(" + mouseX + "," + mouseY + ")", mouseX, mouseY);
        }
    }

    public void mousePressed(int x, int y){
        // Add the coordinates to the list of points and refresh the panel
        Point p = new Point(x, y);
        points.add(p);        
        repaint();
    }
}
