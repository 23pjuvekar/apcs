/*
File: HelloWorldV2.java

A GUI-based hello world program with a fancy label (italic red on black).
*/

import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;
import java.awt.Font;

public class HelloWorldV2 extends GBFrame{

    public HelloWorldV2(){
        JLabel greetingLabel = addLabel ("Hello world!", 1, 1, 1, 1);
        greetingLabel.setFont (new Font ("Verdana", Font.ITALIC, 24));
        greetingLabel.setForeground (Color.red);
    }

    // Create and display the window when the app launches
    public static void main(String[] args){
        JFrame frm = new HelloWorldV2();
        frm.setSize (200, 100);
        frm.setBackground(Color.black);
        frm.setResizable (false);
        frm.setVisible (true);
    }
}
