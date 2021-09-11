/*
File: PanelDemo.java

Demonstrates the coloring of panels.
*/


import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;
import java.util.*;

public class PanelDemo1 extends GBFrame{

   GBPanel northWest = addPanel(new GBPanel(), 1,1,1,1);
   GBPanel southWest = addPanel(new GBPanel(), 1,2,1,1);
   GBPanel northEast = addPanel(new GBPanel(), 2,1,1,1);
   GBPanel southEast = addPanel(new GBPanel(), 2,2,1,1);

   JButton changeColorBtn = addButton("Change color", 3,1,2,1);

   private java.util.List<GBPanel> panels = new ArrayList<GBPanel>();
   private Random numbers = new Random();

   public PanelDemo1(){
      northWest.setBackground(Color.red);
      southWest.setBackground(Color.green);
      northEast.setBackground(Color.blue);
      southEast.setBackground(Color.yellow);
      panels.add(northWest);
      panels.add(southWest);
      panels.add(northEast);
      panels.add(southEast);
  }

   public void buttonClicked(JButton buttonObj){
      // Reset all backgrounds to random colors
      for (GBPanel panel : panels)
         panel.setBackground(new Color(numbers.nextInt(255), 
                                       numbers.nextInt(255), 
                                       numbers.nextInt(255)));
   }
      
   public static void main (String[] args){
      JFrame frm = new PanelDemo1();
      frm.setSize (200, 200);
      frm.setTitle("Random Colors");
      frm.setVisible (true);
   }
}


