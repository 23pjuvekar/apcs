/*
File: GraphicsPanel1.java

A panel that draws the coordinates of its center point at that point.
*/

import BreezySwing.*;
import java.awt.*;

public class GraphicsPanel1 extends GBPanel{

    public GraphicsPanel1(Color color){
        setBackground(color);
    }

    public void paintComponent (Graphics g){
        // Clear the panel and redraw the coordinates
        super.paintComponent(g);
        int mouseX = getWidth() / 2;
        int mouseY = getHeight() / 2;
        g.drawString("(" + mouseX + "," + mouseY + ")", mouseX, mouseY);
    }
}
