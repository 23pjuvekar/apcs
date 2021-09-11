/*
File: FahrenheitToCelsius.java

A temperature conversion program (Fahrenheit to Celsius).
*/

import javax.swing.*;
import BreezySwing.*;

public class FahrenheitToCelsius extends GBFrame{

    JLabel degreesFahrenheitLabel      = addLabel ("Degrees Fahrenheit", 1,1,1,1);
    DoubleField degreesFahrenheitField = addDoubleField (32.0,           1,2,1,1);
    JLabel degreesCelsiusLabel         = addLabel ("Degrees Celsius",    2,1,1,1);
    DoubleField degreesCelsiusField    = addDoubleField (0.0,            2,2,1,1);
    JButton convertButton              = addButton ("Convert",           3,1,2,1);

    public void buttonClicked(JButton buttonObj){
        double fahrenheit = degreesFahrenheitField.getNumber();
        double celsius = (fahrenheit  - 32) * 5.0 / 9.0;
        degreesCelsiusField.setNumber (celsius);
    }

    public static void main(String[] args){
        JFrame frm = new FahrenheitToCelsius();
        frm.setTitle ("Fahrenheit to Celsius");
        frm.setSize (500, 100);
        frm.setVisible (true);
   }
}


