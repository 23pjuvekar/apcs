/*
File: GraphicsDemo.java

The main window for the graphics examples.
To test a new example, just change the name of the panel class and its color.
*/

import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;

public class GraphicsDemo extends GBFrame{

    GBPanel panel = addPanel(new GraphicsPanel3(Color.pink), 1,1,1,1);

    public static void main (String[] args){
        JFrame frm = new GraphicsDemo();
        frm.setSize (300, 300);
        frm.setVisible (true);
    }
}


