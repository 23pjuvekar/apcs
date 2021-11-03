package com.pratham;

import BreezySwing.GBDialog;
import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.IntegerField;

import javax.swing.*;

import static java.lang.System.exit;

public class LNDSGui extends GBFrame {

    private NumberArray numberArray = new NumberArray();

    //Panels
    private GBPanel mainPanel;

    //Buttons for operations
    private JButton inputButton;
    private JButton outputButton;
    private JButton resetButton;
    private JButton exitButton;

    public void showMainScreen() {

        this.setSize (600, 125);
        if ( mainPanel != null ) {
            this.remove(mainPanel);
        }
        mainPanel = addPanel(1, 1, 1, 1);

        GBPanel numbersPanel = mainPanel.addPanel(1, 1, 1, 1);
        numbersPanel.addLabel("Numbers", 1, 1, 1, 1);
        String numberArrayString = numberArray.toString();
        if ( numberArrayString.isEmpty() ) {
            numberArrayString = "no numbers - use the Input button to add";
        }
        numbersPanel.addTextField(numberArrayString, 2, 1, 1, 1).setEnabled(false);

        GBPanel buttonsPanel = mainPanel.addPanel(2, 1, 1, 1);
        inputButton = buttonsPanel.addButton("Input", 1, 1, 1, 1);
        outputButton = buttonsPanel.addButton("Output", 1, 2, 1, 1);
        resetButton = buttonsPanel.addButton("Reset", 1, 3, 1, 1);
        exitButton = buttonsPanel.addButton("Exit", 1, 4, 1, 1);

        revalidate();
    }

    //Method for Drop Down
    public void buttonClicked(JButton buttonObj) {
        if ( buttonObj == inputButton ) {
            GBDialog dialog = new InputDialog(this, numberArray);
            dialog.setVisible(true);
        } else if ( buttonObj == outputButton ) {
            GBDialog dialog = new OutputDialog(this, numberArray);
            dialog.setVisible(true);
        } else if ( buttonObj == resetButton ) {
            numberArray.reset();
        } else if ( buttonObj == exitButton ) {
            exit(0);
        }
        showMainScreen();
        revalidate();
    }

    public static void main(String[] args) {
        LNDSGui gui = new LNDSGui();
        gui.setTitle("Longest Non-decreasing Sequence");
        gui.showMainScreen();
        gui.setLocationRelativeTo(null); //this displays JFrame to center position of a screen
        gui.setVisible (true);
    }

    public class InputDialog extends GBDialog {

        private NumberArray numberArray;

        JTextField numbersField;
        JButton okBtn;
        JButton cancelBtn;

        public InputDialog(JFrame parent, NumberArray numberArray){

            super (parent);                                 // ** REQUIRED **
            setTitle ("Input");
            setDlgCloseIndicator ("Cancel");
            setLocationRelativeTo(null);
            setSize (400, 100);

            // store the number array
            this.numberArray = numberArray;

            //add the fields and buttons
            addLabel ("Numbers to add (comma separated e.g. 5, 6, 10)", 1,1,4,1);
            numbersField = addTextField ("", 2,1,4,1);
            okBtn = addButton ("OK", 3,1,1,1);
            cancelBtn = addButton ("Cancel", 3,2,1,1);
        }

        //parse numbers into array to add
        public int[] getNumbers() throws Exception {
            String[] numberStrings = numbersField.getText().split(",");
            int[] numbers = new int[numberStrings.length];
            for (int i=0; i<numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i].trim());
            }
            return numbers;
        }

        public void buttonClicked(JButton buttonObj) {
            if ( buttonObj == okBtn ) {
                try {
                    numberArray.addArray(getNumbers());
                } catch (Exception ex) {
                    messageBox("Error parsing input: " + ex.getMessage());
                    numbersField.requestFocus();
                    return;
                }
            }
            dispose();
        }
    }

    public class OutputDialog extends GBDialog {

        JButton okBtn;

        public OutputDialog(JFrame parent, NumberArray numberArray){

            super (parent);                                 // ** REQUIRED **

            setTitle ("Output");
            setDlgCloseIndicator ("Cancel");
            setLocationRelativeTo(null);
            setSize (600, 100);

            //add the fields and buttons
            NumberArray[] lndsArray = LNDSFinder.findLNDS(numberArray);
            int lndsArraySize = lndsArray==null?0:lndsArray.length;
            if ( lndsArraySize == 0 ) {
                addLabel("There are no non decreasing sequences", 1, 1, 1, 1);
            } else {
                addLabel("Longest Non-Decreasing Sequences (" + lndsArraySize + ")", 1, 1, 1, 1);
                for (int i = 0; i < lndsArraySize; i++) {
                    addTextField("" + lndsArray[i], 2+i, 1, 1, 1).setEnabled(false); //read only
                }
            }
            NumberArray[] ldsArray = LNDSFinder.findLDS(numberArray);
            int ldsArraySize = ldsArray==null?0:ldsArray.length;
            if ( ldsArraySize == 0 ) {
                addLabel("There are no decreasing sequences", 2+lndsArraySize, 1, 1, 1);
            } else {
                addLabel("Longest Decreasing Sequences (" + ldsArraySize + ")", 2+lndsArraySize, 1, 1, 1);
                for (int i = 0; i < ldsArraySize; i++) {
                    addTextField("" + ldsArray[i], 3+lndsArraySize+i, 1, 1, 1).setEnabled(false); //read only
                }
            }
            setSize (600, 100+25*lndsArraySize+25*ldsArraySize);
            okBtn = addButton ("OK", 4+lndsArraySize+ldsArraySize,1,1,1);
        }

        public void buttonClicked(JButton buttonObj) {
            dispose();
        }
    }
}
