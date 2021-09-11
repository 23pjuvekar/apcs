/*
File: DialogDemo.java

Demonstrates the use dialogs and M/V/C.
*/

import javax.swing.*;
import BreezySwing.*;

public class DialogDemo extends GBFrame{
    
    JTextArea outputArea = addTextArea("", 1,1,3,5);
    JButton totalHoursBtn = addButton ("Total hours", 6,1,1,1);
    JButton totalPayBtn   = addButton ("Total pay",   6,2,1,1);
    JButton editBtn = addButton("Edit", 6,3,1,1);

    private Employee emp;
   
    public DialogDemo(){
        int [] hours = {8, 10, 6, 8, 9};
        emp = new Employee ("Ken", 15.25, hours);
        outputArea.setText (emp.toString());
        outputArea.setEditable (false);
    }
   
    public void buttonClicked(JButton buttonObj){
        if (buttonObj == totalHoursBtn)
            messageBox ("Total hours: " + emp.getTotalHours());
        else if (buttonObj == totalPayBtn)
            messageBox ("Total pay: $" + emp.computePay());
        else{
            EmployeeDialog dlg = new EmployeeDialog(this, emp);
            dlg.setVisible (true);
            if (dlg.getDlgCloseIndicator().equals("OK"))
                outputArea.setText(emp.toString());
        }
    }
     
    public static void main(String[] args){
        JFrame frm = new DialogDemo();
        frm.setSize(250, 200);
        frm.setTitle ("Employee Information");
        frm.setVisible(true);         
    }
}
