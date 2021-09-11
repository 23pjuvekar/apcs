/* EmployeeSystem.java

1) This program maintains a list of employees and displays their names
   in a scrolling list control.
2) Single clicking on a name displays the employee's information.
3) Double clicking on a name allows the employee's information to be
   changed.
4) Other operations are supported by means of an Edit menu with the options:
   Add    -- adds a new employee to the end of the list and selects the
             name in the scrolling list
   Modify -- modifies the selected employee
   Delete -- deletes the selected employee
5) The selected employee is the one corresponding to the highlighted
   name in the scrolling list control. 
*/

import javax.swing.*;
import java.util.*;
import BreezySwing.*;

public class EmployeeSystem extends GBFrame{

   // Window objects
   JLabel namesLB              = addLabel ("Names"               ,1,1,1,1);
   JLabel detailedInfoLB       = addLabel ("Detailed Information",1,2,1,1);

   JList nameList              = addList (                        2,1,1,5);
   JTextArea detailedInfoField = addTextArea (""                 ,2,2,1,5);

   JMenuItem addMenuItem    = addMenuItem ("Edit", "Add");
   JMenuItem modifyMenuItem = addMenuItem ("Edit", "Modify");
   JMenuItem deleteMenuItem = addMenuItem ("Edit", "Delete");

   // Instance variables
   private java.util.List<Employee> employeeList; // List of employee objects
   private Employee selectedEmployee;             // Selected employee if there is one

   public EmployeeSystem(){
   // Constructor
   //  Preconditions  -- none
   //  Postconditions -- title of window set
   //                 -- instance variables initialized
   
      setTitle ("Employee Maintenance System");
      employeeList = new ArrayList<Employee>();  // Instantiate a new list
      selectedEmployee = null;                   // No employee is selected
   }

   public void listItemSelected (JList listObj){
   // Displays the information for the employee whose name is selected.
   //  Preconditions  -- a name in the name list is single clicked
   //  Postconditions -- the employee corresponding to the name
   //                    is selected and her info is displayed
   
      int index = nameList.getSelectedIndex();
      selectedEmployee = employeeList.get(index);
      displaySelectedEmployee();
   }

   public void listDoubleClicked (JList listObj, String itemClicked){
   // Opens a modify dialog on the employee whose name is selected. 
   // Note: double clicking on a name automatically triggers the single click
   //      event first.
   //  Preconditions  -- a name in the list is double clicked
   //  Postconditions -- see the modifySelectedEmployee method
   
      modifySelectedEmployee();
   }

   public void menuItemSelected (JMenuItem mi){
   // Responds to a menu selection
   //  Preconditions  -- none
   //  Postconditions -- see the methods corresponding to the menu items
   
      if      (mi == addMenuItem)    addNewEmployee();
      else if (mi == modifyMenuItem) modifySelectedEmployee();
      else if (mi == deleteMenuItem) deleteSelectedEmployee();
   }

   private void addNewEmployee(){
   // Adds a new employee.
   //  Preconditions  -- none
   //  Postconditions -- if the user cancels the dialog, then no change
   //                 -- else the new employee is selected
   //                      she is added to the end of the employee list
   //                      her name is added to the end of the name list 
   //                      her info is displayed
   //                      she becomes the selected item in both lists   
      int index;

      Employee tempEmp = new Employee();
      EmployeeDialog employeeDialog
                     = new EmployeeDialog (this, tempEmp);
      employeeDialog.setVisible (true);
      if (employeeDialog.getDlgCloseIndicator().equals ("OK")){
         selectedEmployee = tempEmp;
         employeeList.add (selectedEmployee);
         DefaultListModel model = (DefaultListModel) nameList.getModel();
         model.addElement (selectedEmployee.getName());
         index = employeeList.size() - 1;
         nameList.setSelectedIndex (index);
         displaySelectedEmployee();
      }
   }

   private void modifySelectedEmployee(){
   // Allows modifications to the selected employee.
   //  Preconditions  -- none
   //  Postconditions -- if there is no selected employee, then 
   //                      display an error message 
   //                 -- if the user cancels the dialog, then no change
   //                 -- else the employee data are changed
   //                      her name, which may be modified, overwrites her
   //                      previous name in the name list and the name
   //                      is selected 
   //                      her info is redisplayed 
   
      int index;
      String name;

      if (selectedEmployee == null){
         messageBox ("SORRY: must select before modify");
         return;
      }
      EmployeeDialog employeeDialog
                     = new EmployeeDialog (this, selectedEmployee);
      employeeDialog.setVisible (true);
      if (employeeDialog.getDlgCloseIndicator().equals ("OK")){
         index = nameList.getSelectedIndex();
         name = selectedEmployee.getName();
         DefaultListModel model = (DefaultListModel) nameList.getModel();
         model.set (index, name);
         nameList.setSelectedIndex (index);
         displaySelectedEmployee();
      }
   }

   private void deleteSelectedEmployee(){
   // Deletes the selected employee.
   //  Preconditions  -- none
   //  Postconditions -- if there is no selected employee, then 
   //                      display an error message 
   //                 -- else the employee is removed from the employee list
   //                      her name is removed from the name list
   //                      no name is highlighted in the name list 
   //                      no employee is selected in the employee list
   //                      the info area is cleared 
   
      if (selectedEmployee == null){
         messageBox ("SORRY: must select before delete");
         return;
      }
      employeeList.remove (selectedEmployee);
      int index = nameList.getSelectedIndex();
      DefaultListModel model = (DefaultListModel) nameList.getModel();
      model.remove (index);
      selectedEmployee = null;
      displaySelectedEmployee();
   }

   private void displaySelectedEmployee(){
   // Displays the selected employee's info in the info area
   //  Preconditions  -- none
   //  Postconditions -- if there is no selected employee, then no change
   //                 -- else the selected employee's info is displayed
   
      String str = "";
      if (selectedEmployee != null)
         str = selectedEmployee.toString();
      detailedInfoField.setText (str);
   }

   public static void main (String[] args){
   // Instantiates, sizes, and displays the application's main window
   
      JFrame frm = new EmployeeSystem();
      frm.setSize (300, 300);
      frm.setVisible (true);
   }
}
