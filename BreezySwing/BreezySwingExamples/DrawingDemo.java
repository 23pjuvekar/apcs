/*
File: DrawingDemo.java

The main window for the interactive graphics examples.
Allows the user to clear the drawing by clicking a button.
To test a new example, just change the name of the panel class and its color.
*/

import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;

public class DrawingDemo extends GBFrame{

    DrawingPanel1 panel = new DrawingPanel1(Color.pink);

    public DrawingDemo(){
        addPanel(panel, 1,1,1,1);
        addButton("Clear", 2,1,1,1);
    }

    public void buttonClicked(JButton buttonObj){
        panel.clear();
    }

    public static void main (String[] args){
        JFrame frm = new DrawingDemo();
        frm.setSize (300, 300);
        frm.setVisible (true);
    }
}


