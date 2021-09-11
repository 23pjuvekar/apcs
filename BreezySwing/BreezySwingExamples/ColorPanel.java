/*
File: ColorPanel.java

A panel that colors itself in response to a mouse press.
*/


import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;
import java.util.Random;

public class ColorPanel extends GBPanel{

    private Random numbers = new Random();
        
    public ColorPanel(Color color){
        setBackground(color);
    }

    public void mousePressed(int x, int y){
        setBackground(new Color(numbers.nextInt(255), 
                                numbers.nextInt(255), 
                                numbers.nextInt(255)));
    }
}


