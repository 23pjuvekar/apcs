/*
File: CheckBoxDemo.java

Demonstrates the use of checkboxes, radio buttons, and combo boxes.
*/

import javax.swing.*;
import BreezySwing.*;

public class CheckBoxDemo extends GBFrame{

    JLabel checkLabel = addLabel ("Checkbox state",1,1,1,1);
    JLabel radioLabel = addLabel ("Radio button state",2,1,1,1);
    JLabel comboLabel = addLabel ("Combo box state",3,1,1,1);
    JTextField checkField = addTextField("", 1,2,2,1);
    JTextField radioField = addTextField("", 2,2,2,1);
    JTextField comboField = addTextField("", 3,2,2,1);

    JCheckBox cbDriver    = addCheckBox ("Driver", 4,1,1,1);      // Checkboxes
    JCheckBox cbPassenger = addCheckBox ("Passenger", 5,1,1,1);   

    ButtonGroup bgMaritalStatus = new ButtonGroup();              // Radio button group
    JRadioButton rMarried  = addRadioButton ("Married", 4,3,1,1); // Radio buttons  
    JRadioButton rSingle   = addRadioButton ("Single", 5,3,1,1);   
    JRadioButton rDivorced = addRadioButton ("Divorced", 6,3,1,1); 

    JButton getStateBTN = addButton("Get states", 7,1,3,1);

    JComboBox comboHobbies = addComboBox(6,1,1,1);                // Combo box

    public CheckBoxDemo(){
        // Mark the default checkbox and radio button
        cbDriver.setSelected (true);
        rSingle.setSelected (true);

        // Add the radio buttons to the button group
        bgMaritalStatus.add(rMarried);
        bgMaritalStatus.add(rSingle);
        bgMaritalStatus.add(rDivorced);

        // Add items to the combo box
        comboHobbies.addItem ("Swimming");
        comboHobbies.addItem ("Reading");
        comboHobbies.addItem ("Golf");
        comboHobbies.addItem ("Fishing");
        comboHobbies.addItem ("Dusting");
        comboHobbies.addItem ("Cooking");
        comboHobbies.addItem ("Movies");

        comboHobbies.setSelectedIndex (2);               // Select the 3rd string
    }

    public void buttonClicked(JButton buttonObj){
        String cbStr = "", rStr = "";
        if (cbDriver.isSelected())
            cbStr = "Driver";
        if (cbPassenger.isSelected())
            cbStr = cbStr + "Passenger";
        checkField.setText(cbStr);
        if (rMarried.isSelected())
            rStr = "Married";
        else if (rDivorced.isSelected())
            rStr = "Divorced";
        else if (rSingle.isSelected())
            rStr = "Single";
        radioField.setText(rStr);
        comboField.setText((String) comboHobbies.getSelectedItem());
    }

    public static void main(String[] args){
        JFrame frm = new CheckBoxDemo();
        frm.setSize (400, 300);
        frm.setVisible (true);
    }
}


