package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.MessageBox;

import javax.swing.*;
import java.awt.*;

public class InsertionSortGui extends GBFrame {

    private IntegerArray numberList = new IntegerArray();

    private GBPanel mainPanel;
    private JTextField numbersToAddField;
    private JButton addButton;
    private JButton clearButton;
    private JButton printStatsButton;
    private JTextArea outputArea;

    public InsertionSortGui() {

        this.setSize(600, 260);
        mainPanel = addPanel(1, 1, 1, 1);

        GBPanel inputPanel = mainPanel.addPanel(1, 1,1, 1);
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //create the number list input
        GBPanel inputNumbersPanel = inputPanel.addPanel(1, 1,1, 1);
        inputNumbersPanel.addLabel("Numbers to add: (e.g. 2, 5 , -3, 11 )", 1, 1, 1, 1);
        numbersToAddField = inputNumbersPanel.addTextField("", 2, 1, 1, 1);

        //create buttons
        GBPanel inputButtonPanel = inputPanel.addPanel(2, 1,1, 1);
        addButton = inputButtonPanel.addButton("Add To List", 1, 1, 1, 1);
        clearButton = inputButtonPanel.addButton( "Clear List", 1, 2, 1, 1);
        printStatsButton = inputButtonPanel.addButton( "Print Statistics", 1, 3, 1, 1);

        GBPanel outputPanel = mainPanel.addPanel(2, 1,1, 1);
        outputPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //create output area
        GBPanel outputStatsPanel = outputPanel.addPanel(1, 1,1, 1);
        outputArea = outputStatsPanel.addTextArea("", 1, 1, 1, 1);
        outputArea.setEnabled(false);

        displayNumberList();
    }

    public void displayNumberList() {
        String displayString = "";

        //display unsorted list
        int[] numArr = numberList.getArray();
        displayString += "Unsorted : ";
        if ( numArr != null ) {
            for (int n : numArr) {
                displayString += (n + " ");
            }
        }
        displayString += "\n";

        //display sorted list
        int[] numSortedArr = numberList.getSortedArray();
        displayString += "Sorted   : ";
        if ( numSortedArr != null ) {
            for (int n : numSortedArr) {
                displayString += (n + " ");
            }
        }
        displayString += "\n";

        outputArea.setText(displayString);
    }

    public void displayNumberListWithStats() {
        String displayString = "";

        //display unsorted list
        int[] numArr = numberList.getArray();
        displayString += "Unsorted : ";
        if ( numArr != null ) {
            for (int n : numArr) {
                displayString += (n + " ");
            }
        }
        displayString += "\n";

        //display sorted list
        int[] numSortedArr = numberList.getSortedArray();
        displayString += "Sorted   : ";
        if ( numSortedArr != null ) {
            for (int n : numSortedArr) {
                displayString += (n + " ");
            }
        }
        displayString += "\n";

        //display statistics
        double mean = numberList.getMean();
        double median = numberList.getMedian();
        int[] mode = numberList.getMode();
        double stdDev = numberList.getStandardDeviation();
        displayString += ("Mean     : " + String.format("%.2f", mean) + "\n");
        displayString += ("Median   : " + String.format("%.2f", median) + "\n");
        displayString += ("Std Dev  : " + String.format("%.2f", stdDev) + "\n");
        displayString += "Mode     : ";
        if ( mode != null ) {
            for (int n : mode) {
                displayString += (n + " ");
            }
        }
        displayString += "\n";

        outputArea.setText(displayString);
    }

    private void showMessageBox(String msg) {
        MessageBox msgBox = new MessageBox( this, msg);
        msgBox.setLocationRelativeTo(null); //center
        msgBox.setVisible(true);
    }

    public void buttonClicked(JButton buttonObj) {

        if ( buttonObj == addButton ) {
            if ( numbersToAddField.getText().trim().isEmpty() ) {
                showMessageBox("Number list cannot be empty\nPlease add a comma separate list");
                numbersToAddField.requestFocus();
                return;
            }
            try {
                numberList.add(numbersToAddField.getText()); //add to the list
            } catch ( Exception ex ) {
                showMessageBox("Bad input for number list\n" + ex.getMessage());
                numbersToAddField.requestFocus();
                return;
            }
            numbersToAddField.setText(""); //clear the input
            displayNumberList(); //display list information
        }
        if ( buttonObj == clearButton ) {
            numberList.resetAll(); //reset the list
            displayNumberList(); //display list information
        }
        if ( buttonObj == printStatsButton ) {
            displayNumberListWithStats(); //display list information with statistics
        }
    }

    public static void main(String[] args) {
        InsertionSortGui gui = new InsertionSortGui();
        gui.setTitle("Insertion Sort");
        gui.setLocationRelativeTo(null); //this displays JFrame to center position of a screen
        gui.setVisible (true);
    }
}
