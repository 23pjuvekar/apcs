/*
File: TaxCodeDemoV2.java

A GUI-based tax calculator.

Computes and prints the total tax, given the income,
number of dependents, exemption amount (all inputs), and
a flat tax rate of 15%.

Uses a constructor.
*/

import javax.swing.*;
import BreezySwing.*;
import java.awt.Color;
 
public class TaxCodeDemoV2 extends GBFrame{
 
    // Declare the instance variables for the window components
    JLabel incomeLabel;
    DoubleField incomeField;
    JLabel dependentsLabel;
    IntegerField dependentsField;
    JLabel exemptionLabel;
    DoubleField exemptionField;
    JButton convertButton;
    JLabel taxLabel;
    DoubleField taxField;
 
   // The constructor, which adds the window components to the window    
   public TaxCodeDemoV2(){
        incomeLabel     = addLabel ("Income",           1,1,1,1);
        incomeField     = addDoubleField (0.0,          1,2,1,1);
        dependentsLabel = addLabel ("Dependents",       2,1,1,1);
        dependentsField = addIntegerField (0,           2,2,1,1);
        exemptionLabel  = addLabel ("Exemption amount", 3,1,1,1);
        exemptionField  = addDoubleField (0.0,          3,2,1,1);
        convertButton   = addButton ("Compute",         4,1,2,1);
        taxLabel        = addLabel ("Total tax",        5,1,1,1);
        taxField        = addDoubleField (0.0,          5,2,1,1);
        taxField.setPrecision(2);
        dependentsField.setHorizontalAlignment(JTextField.CENTER);
        dependentsField.setForeground(Color.red);
    }
   // The event handler method for the button to compute the tax     
   public void buttonClicked(JButton buttonObj){
        double income = incomeField.getNumber();
        int numDependents = dependentsField.getNumber();
        double exemptionAmount = exemptionField.getNumber();
        double tax = (income - numDependents * exemptionAmount) * .15;
        taxField.setNumber(tax);
    }
 
   // Create and display the window when the app launches
   public static void main(String[] args){
      JFrame frm = new TaxCodeDemoV2();
      frm.setTitle("Tax Calculator");
      frm.setSize (300, 200);
      frm.setVisible (true);
   }
}