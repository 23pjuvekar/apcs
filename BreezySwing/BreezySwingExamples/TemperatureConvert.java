/*
File: TemperatureConvert.java

A temperature conversion program (both directions).
*/

import javax.swing.*;
import BreezySwing.*;

public class TemperatureConvert extends GBFrame{

    JLabel degreesFahrenheitLabel      = addLabel ("Degrees Fahrenheit", 1,1,1,1);
    DoubleField degreesFahrenheitField = addDoubleField (32.0,            1,2,1,1);
    JLabel degreesCelsiusLabel         = addLabel ("Degrees Celsius",    2,1,1,1);
    DoubleField degreesCelsiusField    = addDoubleField (0.0,            2,2,1,1);
    JButton fToCButton                 = addButton ("F to C",            3,1,1,1);
    JButton cToFButton                 = addButton ("C to F",            3,2,1,1);

    // Uses if statement to determine which button is clicked
    public void buttonClicked(JButton buttonObj){
        double fahrenheit, celsius;
        if (buttonObj == fToCButton){
            fahrenheit = degreesFahrenheitField.getNumber();
            celsius = (fahrenheit  - 32) * 5.0 / 9.0;
            degreesCelsiusField.setNumber (celsius);
        } 
        else {
            celsius = degreesCelsiusField.getNumber();
            fahrenheit = celsius * 9.0 / 5.0 + 32;
            degreesFahrenheitField.setNumber (fahrenheit);
        }            
    }

    public static void main(String[] args){
        JFrame frm = new TemperatureConvert();
        frm.setTitle ("Temperature Conversion");
        frm.setSize (500, 100);
        frm.setVisible (true);
   }
}


