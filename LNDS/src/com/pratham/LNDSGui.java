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
    private GBPanel numbersPanel;
    private GBPanel lndsPanel;

    //Drop down setup
    private JMenuItem addNumberMenu;
    private JMenuItem resetNumbersMenu;
    private JMenuItem quitMenu;

    //constructor
    public LNDSGui() {

        //test data
        //int[] testData = {17, 31, 40, 5, 6, 10, 46, 37, 53, 12, 9, 86};
        int[] testData = {17, 31, 40, 5, 6, 10, 3, 37, 53, 12, 9, 86};
        numberArray.addArray(testData);

        //add menu items
        addNumberMenu = addMenuItem("LNDS","Add New Number to List");
        resetNumbersMenu = addMenuItem("LNDS","Reset Number List");
        quitMenu = addMenuItem("LNDS", "Quit");
    }

    public void showMainScreen() {

        NumberArray[] lndsArray = LNDSFinder.findLNDS(numberArray);
        int lndsArraySize = lndsArray==null?0:lndsArray.length;
        this.setSize (800, 125+25*lndsArraySize);

        if ( mainPanel != null ) {
            this.remove(mainPanel);
        }
        mainPanel = addPanel(1, 1, 1, 1);

        numbersPanel = mainPanel.addPanel(1, 1, 1, 1);
        numbersPanel.addLabel("Numbers", 1, 1, 1, 1);
        numbersPanel.addTextField(""+numberArray, 2, 1, 1, 1).setEnabled(false);

        lndsPanel = mainPanel.addPanel(2, 1, 1, 1);
        lndsPanel.addLabel("Longest Non-Decreasing Sequences ("+lndsArraySize+")", 1, 1, 1, 1);
        for ( int i=0; i<lndsArraySize; i++ ) {
            lndsPanel.addTextField( ""+lndsArray[i], 2+i, 1, 1, 1).setEnabled(false); //read only
        }

        revalidate();
    }

    //Method for Drop Down
    public void menuItemSelected(JMenuItem menuItem) {
        if ( menuItem == addNumberMenu ) {
            new AddNumberDialog(this, numberArray).setVisible (true);
        } else if ( menuItem == resetNumbersMenu ) {
            numberArray.reset();
        } else if ( menuItem == quitMenu ) {
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

    public class AddNumberDialog extends GBDialog {

        private NumberArray numberArray;

        IntegerField numberField;
        JButton okBtn;
        JButton cancelBtn;

        public AddNumberDialog(JFrame parent, NumberArray numberArray){

            super (parent);                                 // ** REQUIRED **
            setTitle ("Add a Number");
            setDlgCloseIndicator ("Cancel");
            setLocationRelativeTo(null);
            setSize (300, 200);

            // store the number array
            this.numberArray = numberArray;

            //add the fields and buttons
            addLabel ("Nnumber", 1,1,1,1);
            numberField = addIntegerField (0, 1,2,5,1);
            okBtn = addButton ("OK", 4,2,2,1);
            cancelBtn = addButton ("Cancel", 4,4,2,1);
        }

        public void buttonClicked(JButton buttonObj) {
            if ( buttonObj == okBtn ) {
                numberArray.addNumber(numberField.getNumber());
            }
            dispose();
        }
    }
}
