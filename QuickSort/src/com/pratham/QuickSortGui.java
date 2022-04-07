package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.IntegerField;
import BreezySwing.MessageBox;

import javax.swing.*;

import java.awt.*;

import static java.lang.System.exit;

public class QuickSortGui extends GBFrame {

    NumberArray numberArray = new NumberArray();

    //Main panel
    private GBPanel mainPanel;

    // menus
    private JMenuItem addNumbersMenu;
    private JMenuItem showNumbersMenu;
    private JMenuItem showStatisticsMenu;
    private JMenuItem compareSortSpeedsMenu;
    private JMenuItem addTestDataMenu;
    private JMenuItem clearAllDataMenu;
    private JMenuItem quitMenu;

    // fields
    private JTextField numberListField;
    private JButton    addNumbersOkButton;
    private JButton    addNumbersCancelButton;

    public QuickSortGui() {

        resetScreen();
        createInfoScreen();

        //add menu items
        addNumbersMenu = addMenuItem("Numbers", "Add Numbers");
        showNumbersMenu = addMenuItem("Numbers", "Show Numbers");
        showStatisticsMenu = addMenuItem("Numbers", "Show Number Statistics");
        addTestDataMenu = addMenuItem("Numbers", "Add Test Numbers");
        clearAllDataMenu = addMenuItem("Numbers", "Clear All Numbers");
        compareSortSpeedsMenu = addMenuItem("Numbers", "Compare Sort Speeds");
        quitMenu = addMenuItem("Numbers", "Quit");
    }

    //create test data
    private void createTestData() {
        numberArray = new NumberArray(); // reset all data
        for (int i=0; i<10; i++) {
            int random = (int)(Math.random()*100);
            numberArray.addNumber(random);
        }
    }

    //show general info screen
    private void createInfoScreen() {
        this.setSize(600,300);
        String msg = "";
        msg += "\nThere are currently "+numberArray.getNumbers().length+" numbers in the system.\n\n";
        if ( numberArray.getNumbers().length > 0 ) {
            msg += "Unsorted:  ";
            msg += (numberArray.unsortedArrayAsString() + "\n");
            msg += "Quick:     ";
            msg += (numberArray.quickSortedArrayAsString() + "\n");
            msg += "Merge:     ";
            msg += (numberArray.mergeSortedArrayAsString() + "\n");
            msg += "Insertion: ";
            msg += (numberArray.insertionSortedArrayAsString() + "\n\n");
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
        this.setSize(600,150);
        GBPanel inputPanel = mainPanel.addPanel(1, 1, 1, 1);
        inputPanel.addLabel("Enter list of numbers (e.g. 3, 12,5, 6 ,13, 7)", 1, 1, 1, 1);
        numberListField = inputPanel.addTextField("", 2,1, 1, 1);
        GBPanel buttonPanel = mainPanel.addPanel(2, 1, 1, 1);
        addNumbersOkButton = buttonPanel.addButton("Add", 1, 1, 1, 1);
        addNumbersCancelButton = buttonPanel.addButton("Cancel", 1, 2, 1, 1);
    }

    private void createShowStatisticsScreen() {
        this.setSize(600,200);
        JTextArea infoField = mainPanel.addTextArea(
                numberArray.getStatistics(),
                1,1,2,1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        revalidate();
    }

    private NumberArray getTestArray() {
        NumberArray testArray = new NumberArray();
        for (int i=0; i<20000; i++) {
            int random = (int)(Math.random()*100);
            testArray.addNumber(random);
        }
        return testArray;
    }

    private void createCompareSortSpeedScreen() {
        this.setSize(600,250);
        NumberArray testArray = getTestArray();
        JTextArea infoField = mainPanel.addTextArea(
                testArray.getSortSpeeds(testArray.getNumbers()),
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
        }
    }

    public void menuItemSelected(JMenuItem menuItem) {
        resetScreen();
        if (menuItem == addNumbersMenu) {
            createAddNumbersScreen();
        } else if (menuItem == showNumbersMenu) {
            createInfoScreen();
        } else if (menuItem == showStatisticsMenu) {
            createShowStatisticsScreen();
        } else if (menuItem == compareSortSpeedsMenu) {
            createCompareSortSpeedScreen();
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
        JFrame frm = new QuickSortGui();
        frm.setTitle("QuickSort");
        frm.setSize (600, 200);
        frm.setLocationRelativeTo(null); // this displays JFrame to center position of a screen
        frm.setVisible (true);
    }
}

