/*
File: ImageDemo.java

Displays an image and a caption.
*/

import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;
import java.awt.Font;

public class ImageDemo extends GBFrame{

    public ImageDemo(Icon image){
        JLabel imageLabel = addLabel ("", 1, 1, 1, 1);
        imageLabel.setIcon (image);
        JLabel captionLabel = addLabel ("Smokey the Cat", 2,1,1,1);
        captionLabel.setFont (new Font ("Verdana", Font.ITALIC, 24));
        captionLabel.setForeground (Color.blue);
    }

    // Create and display the window when the app launches
    public static void main(String[] args){
        Icon image = new ImageIcon ("smokey.jpg");
        JFrame frm = new ImageDemo (image);
        // Set the window's dimensions, leaving room for the title bar and caption
        frm.setSize (image.getIconWidth(), image.getIconHeight() + 50);
        frm.setVisible (true);
    }
}
