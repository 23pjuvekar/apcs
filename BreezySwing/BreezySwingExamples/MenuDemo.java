/*
File: MenuDemo.java

Demonstrates the use of drop-down menus.
*/

import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;
import java.awt.Font;
 
public class MenuDemo extends GBFrame{
 
    // Add the window components
    JLabel textLabel      = addLabel    ("Text font", 1,1,1,1);
    JMenuItem itemCourier = addMenuItem ("Name", "Courier");
    JMenuItem itemVerdana = addMenuItem ("Name", "Verdana");
    JMenuItem item14      = addMenuItem ("Size", "14");
    JMenuItem item24      = addMenuItem ("Size", "24");
    JMenuItem itemRed     = addMenuItem ("Color", "Red");
    JMenuItem itemBlue    = addMenuItem ("Color", "Blue");
 
   // The event handler method for menu items     
   public void menuItemSelected(JMenuItem menuItem){
       Font font = textLabel.getFont();
       String name = font.getFontName();
       int style = font.getStyle();
       int size = font.getSize();
       Color color = textLabel.getForeground();
       if (menuItem == itemVerdana)
           textLabel.setFont (new Font ("Verdana", style, size));
       else if (menuItem == itemCourier)
           textLabel.setFont (new Font ("Courier", style, size));
       else if (menuItem == item14)
           textLabel.setFont (new Font (name, style, 14));
       else if (menuItem == item24)
           textLabel.setFont (new Font (name, style, 24));
       else if (menuItem == itemRed)
           textLabel.setForeground (Color.red);
       else if (menuItem == itemBlue)
           textLabel.setForeground (Color.blue);
    }
 
   // Create and display the window when the app launches
   public static void main(String[] args){
      JFrame frm = new MenuDemo();
      frm.setTitle("Text Fonts");
      frm.setSize (200, 100);
      frm.setVisible (true);
   }
}