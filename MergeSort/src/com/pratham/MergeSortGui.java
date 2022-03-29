package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.IntegerField;
import BreezySwing.MessageBox;

import javax.swing.*;

import java.awt.*;

import static java.lang.System.exit;

public class MergeSortGui extends GBFrame {

    NumberArray numberArray = new NumberArray();

    //Main panel
    private GBPanel mainPanel;

    // menus
    private JMenuItem addNumbersMenu;
    private JMenuItem findNumberMenu;
    private JMenuItem showStatisticsMenu;
    private JMenuItem addTestDataMenu;
    private JMenuItem clearAllDataMenu;
    private JMenuItem quitMenu;

    // fields
    private JTextField numberListField;
    private JButton    addNumbersOkButton;
    private JButton    addNumbersCancelButton;
    private IntegerField findNumberField;
    private JButton    findNumberOkButton;
    private JButton    findNumberCancelButton;

    public MergeSortGui() {

        resetScreen();
        createInfoScreen();

        //add menu items
        addNumbersMenu = addMenuItem("Numbers", "Add Numbers");
        findNumberMenu = addMenuItem("Numbers", "Find a Number");
        showStatisticsMenu = addMenuItem("Numbers", "Show Statistics");
        addTestDataMenu = addMenuItem("Numbers", "Add Test Data");
        clearAllDataMenu = addMenuItem("Numbers", "Clear All Data");
        quitMenu = addMenuItem("Numbers", "Quit");
    }

    //create test data
    private void createTestData() {
        numberArray = new NumberArray(); // reset all data
        numberArray.addNumber(33);
        numberArray.addNumber(2);
        numberArray.addNumber(25);
        numberArray.addNumber(16);
        numberArray.addNumber(1);
        numberArray.addNumber(6);
        numberArray.addNumber(2);
        numberArray.addNumber(21);
        numberArray.addNumber(17);
        numberArray.addNumber(25);
    }

    //show general info screen
    private void createInfoScreen() {
        this.setSize(500,200);
        String msg = "";
        msg += "\nThere are currently "+numberArray.getNumbers().length+" numbers in the system.\n\n";
        if ( numberArray.getNumbers().length > 0 ) {
            msg += "Unsorted: ";
            msg += (numberArray.unsortedArrayAsString() + "\n");
            msg += "Sorted:   ";
            msg += (numberArray.mergeSortedArrayAsString() + "\n\n");
        }
        msg += "Choose an operation from the dropdown menu.";
        JTextArea infoField = mainPanel.addTextArea(msg, 1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,16);
        infoField.setFont(font);
        revalidate();
    }

    private void resetScreen() {
        if (mainPanel != null) {
            this.remove(mainPanel); //remove previous screen
        }
        mainPanel = addPanel(1, 1, 1, 1); //add new panel
    }

    private void createAddNumbersScreen() {
        GBPanel inputPanel = mainPanel.addPanel(1, 1, 1, 1);
        inputPanel.addLabel("Enter list of numbers (e.g. 3, 12,5, 6 ,13, 7)", 1, 1, 1, 1);
        numberListField = inputPanel.addTextField("", 2,1, 1, 1);
        GBPanel buttonPanel = mainPanel.addPanel(2, 1, 1, 1);
        addNumbersOkButton = buttonPanel.addButton("Add", 1, 1, 1, 1);
        addNumbersCancelButton = buttonPanel.addButton("Cancel", 1, 2, 1, 1);
    }

    private void createFindNumberScreen() {
        GBPanel curListPanel = mainPanel.addPanel(1, 1, 1, 1);
        curListPanel.addLabel(numberArray.unsortedArrayAsString(), 1, 1, 1, 1);
        GBPanel inputPanel = mainPanel.addPanel(2, 1, 1, 1);
        inputPanel.addLabel("Enter number to find", 1, 1, 1, 1);
        findNumberField = inputPanel.addIntegerField(0, 1,2, 1, 1);
        GBPanel buttonPanel = mainPanel.addPanel(3, 1, 1, 1);
        findNumberOkButton = buttonPanel.addButton("Find", 1, 1, 1, 1);
        findNumberCancelButton = buttonPanel.addButton("Cancel", 1, 2, 1, 1);
    }

    private void createShowStatisticsScreen() {
        this.setSize(500,200);
        JTextArea infoField = mainPanel.addTextArea(
                numberArray.getStatistics(),
                1,1,2,1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        revalidate();
    }

    private boolean checkNumberList() {
        String numberList = numberListField.getText().trim();
        if ( numberList.isEmpty() ) {
            showMessageBox("No numbers entered\nPlease enter a list of numbers to add.");
            return false;
        }
        String[] numberStrArray = numberList.split(",");
        for ( String nStr: numberStrArray ) {
            nStr = nStr.trim();
            try {
                Integer.parseInt(nStr);
            } catch (Exception e) {
                showMessageBox("Bad number value "+nStr+"\nPlease fix the input to add");
                return false;
            }
        }
        return true;
    }

    private void addNumbersToArray() {
        String numberList = numberListField.getText().trim();
        String[] numberStrArray = numberList.split(",");
        for ( String nStr: numberStrArray ) {
            nStr = nStr.trim();
            int n = Integer.parseInt(nStr);
            numberArray.addNumber(n);
        }
    }

    private boolean checkFindNumber() {
        if ( !findNumberField.isValidNumber() ) {
            showMessageBox("Please provide a valid number.");
            return false;
        }
        return true;
    }

    private String printMarker(String list, int pos) {
        String marker = "";
        String[] numbersStrArray = list.split(",");
        int len = 1;
        for ( int i=0; i<pos; i++ ) {
            len += numbersStrArray[i].trim().length();
            len += 2; // for comma+space
        }
        return String.format("%"+len+"s","^");
    }

    private void createFindNumberResultScreen() {
        int findNumber = findNumberField.getNumber();
        int pos = numberArray.searchNumber(findNumber);
        this.setSize(500,200);
        String msg = "Searching for "+findNumber+" in\n";
        msg += numberArray.mergeSortedArrayAsString()+"\n";
        if ( pos < 0 ) {
            msg += "Could not find number in the list.\n";
        } else {
            msg += printMarker(numberArray.mergeSortedArrayAsString(), pos)+"\n";
            msg += "Found number at position " + (pos+1) + "\n";
        }
        JTextArea infoField = mainPanel.addTextArea(msg, 1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,16);
        infoField.setFont(font);
        revalidate();
    }

    //Method for button clicks
    public void buttonClicked(JButton buttonObj) {

        if (buttonObj == addNumbersOkButton) {
            if (!checkNumberList()) {
                numberListField.requestFocus();
                return;
            }
            addNumbersToArray();
            resetScreen();
            createInfoScreen();
        } else if (buttonObj == addNumbersCancelButton) {
            resetScreen();
            createInfoScreen();
        } else if (buttonObj == findNumberOkButton) {
            if (!checkFindNumber()) {
                findNumberField.requestFocus();
                return;
            }
            resetScreen();
            createFindNumberResultScreen();
        } else if (buttonObj == findNumberCancelButton) {
            resetScreen();
            createInfoScreen();
        }
    }

    public void menuItemSelected(JMenuItem menuItem) {
        resetScreen();
        if (menuItem == addNumbersMenu) {
            createAddNumbersScreen();
        } else if (menuItem == findNumberMenu) {
            createFindNumberScreen();
        } else if (menuItem == showStatisticsMenu) {
            createShowStatisticsScreen();
        } else if (menuItem == addTestDataMenu) {
            createTestData();
            createInfoScreen();
        } else if (menuItem == clearAllDataMenu) {
            numberArray = new NumberArray(); //create new array to clear
            createInfoScreen();
        }  else if (menuItem == quitMenu) {
            exit(0);
        }
        revalidate();
    }

    private void showMessageBox(String msg) {
        MessageBox msgBox = new MessageBox( this, msg);
        msgBox.setSize(400, 100);
        msgBox.setLocationRelativeTo(null); //center
        msgBox.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frm = new MergeSortGui();
        frm.setTitle("Merge Sort & Binary Search");
        frm.setSize (500, 200);
        frm.setLocationRelativeTo(null); // this displays JFrame to center position of a screen
        frm.setVisible (true);
    }
}
