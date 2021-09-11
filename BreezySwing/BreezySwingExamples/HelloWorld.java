/*
File: HelloWorld.java

A GUI-based hello world program.
*/

import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;

public class HelloWorld extends GBFrame{

    // Set up the widget by adding it to a row and column in the window's grid
    JLabel greetingLabel = addLabel ("Hello world!", 1, 1, 1, 1);

    // Create and display the window when the app launches
    public static void main(String[] args){
        JFrame frm = new HelloWorld();
        frm.setSize (100, 100);
        frm.setBackground(Color.red);
        frm.setResizable (false);
        frm.setVisible (true);
    }
}
