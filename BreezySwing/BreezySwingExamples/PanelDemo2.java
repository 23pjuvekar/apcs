/*
File: PanelDemo2.java

Demonstrates the coloring of panels, where the panels color themselves
in response to mouse presses.
*/


import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;

public class PanelDemo2 extends GBFrame{

   GBPanel northWest = addPanel(new ColorPanel(Color.red), 1,1,1,1);
   GBPanel southWest = addPanel(new ColorPanel(Color.green), 1,2,1,1);
   GBPanel northEast = addPanel(new ColorPanel(Color.blue), 2,1,1,1);
   GBPanel southEast = addPanel(new ColorPanel(Color.yellow), 2,2,1,1);
      
   public static void main (String[] args){
      JFrame frm = new PanelDemo2();
      frm.setSize (200, 200);
      frm.setTitle("Random Colors");
      frm.setVisible (true);
   }
}


