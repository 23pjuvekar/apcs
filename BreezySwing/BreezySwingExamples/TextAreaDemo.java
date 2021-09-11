/*
File: TextAreaDemo.java

Displays an employee's information in a text area.
*/

import javax.swing.*;
import BreezySwing.*;


public class TextAreaDemo extends GBFrame{
    
   JTextArea outputArea  = addTextArea ("", 1,1,2,5);
   JButton totalHoursBtn = addButton ("Total hours", 6,1,1,1);
   JButton totalPayBtn   = addButton ("Total pay",   6,2,1,1);

   private Employee emp;
   
   public TextAreaDemo(){
      int [] hours = {8, 10, 6, 8, 9};
      emp = new Employee ("Ken", 15.25, hours);
      outputArea.setText (emp.toString());
      outputArea.setEditable (false);
   }

   public void buttonClicked(JButton buttonObj){
      if (buttonObj == totalHoursBtn)
          messageBox ("Total hours: " + emp.getTotalHours());
      else
          messageBox ("Total pay: $" + emp.computePay());
   }
        
   public static void main(String[] args){
      JFrame frm = new TextAreaDemo();
      frm.setSize (200, 150);
      frm.setTitle ("Employee Info");
      frm.setVisible (true);         
   }
}
