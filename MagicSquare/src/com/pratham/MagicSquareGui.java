package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.IntegerField;

import javax.swing.*;
import java.awt.*;

public class MagicSquareGui extends GBFrame {

    //Drop down setup
    private JMenuItem twoByTwo;
    private JMenuItem threeByThree;
    private JMenuItem fourByFour;
    private JMenuItem fiveByFive;
    private JMenuItem sixBySix;
    private JMenuItem sevenBySeven;
    private JMenuItem eightByEight;

    private int magicSquareSize;

    private GBPanel magicSquarePanel;
    private IntegerField[][] numberFields = new IntegerField[8][8];

    private IntegerField startField;
    private JButton generateButton;

    private IntegerField constantField;
    private JButton checkButton;

    //Gui constructor
    MagicSquareGui() {
        twoByTwo = addMenuItem("Choose Size","2 x 2");
        threeByThree = addMenuItem("Choose Size","3 x 3");
        fourByFour = addMenuItem("Choose Size","4 x 4");
        fiveByFive = addMenuItem("Choose Size","5 x 5");
        sixBySix = addMenuItem("Choose Size","6 x 6");
        sevenBySeven = addMenuItem("Choose Size","7 x 7");
        eightByEight = addMenuItem("Choose Size","8 x 8");
        magicSquarePanel = addPanel(1,1,1,1);
        JLabel titleField = magicSquarePanel.addLabel("Choose a size for the Magic Square from the dropdown.", 1, 1, 1, 1);
        Font font = new Font("Courier", Font.BOLD,16);
        titleField.setFont(font);
    }

    //Method for generating IntegerFields
    private void makeMagicSquarePanel(GBPanel panel, int size){

        GBPanel titlePanel = panel.addPanel(1, 1, size, 1);
        JLabel titleLabel = titlePanel.addLabel("Magic Square " + size + " x " + size, 1, 1, 1, 1);
        Font font = new Font("Courier", Font.BOLD,20);
        titleLabel.setFont(font);

        GBPanel squarePanel = panel.addPanel(2, 1, size, 1);
        for(int i = 0; i < size; i++){
            for (int  j = 0; j < size; j++){
                numberFields[i][j] = squarePanel.addIntegerField(0, i + 1, j + 1, 1,1);
                numberFields[i][j].setHorizontalAlignment(JTextField.CENTER);
            }
        }

        GBPanel constantPanel = panel.addPanel(3, 1, size, 1);
        constantPanel.addLabel("Constant", 1, 1, 1, 1);
        constantField = constantPanel.addIntegerField(0, 1, 2, 1, 1);
        constantField.setEditable(false);
        checkButton = constantPanel.addButton("Check", 1, 3, 1, 1);

        GBPanel startPanel = panel.addPanel(4, 1, size, 1);
        startPanel.addLabel("Start", 1, 1, 1, 1);
        startField = startPanel.addIntegerField(1, 1, 2, 1, 1);
        generateButton = startPanel.addButton("Generate", 1, 3, 1, 1);

        if ( size == 2 ) {
            generateButton.setEnabled(false); //No generate for 2x2
        }
    }

    //Method for button clicks
    public void buttonClicked(JButton buttonObj){
        if ( buttonObj == checkButton ) {
            MagicSquare ms = new MagicSquare(magicSquareSize);
            for (int i = 0; i< magicSquareSize; i++) {
                for (int j = 0; j< magicSquareSize; j++) {
                    ms.addNumber(i, j, numberFields[i][j].getNumber());
                }
            }
            int constant = ms.isMagicSquare();
            if ( constant != 0 ) {
                constantField.setNumber(constant);
            } else {
                constantField.setText("Not Magic");
            }
        }
        if ( buttonObj == generateButton ) {
            int startNum = 1;
            if ( startField.isValidNumber() ) {
                startNum = startField.getNumber();
            } else {
                startField.setNumber(1);
            }
            MagicSquare ms = new MagicSquare(magicSquareSize);
            ms.createMagicSquare(startNum); //Get starting number
            for (int i = 0; i< magicSquareSize; i++) {
                for (int j = 0; j< magicSquareSize; j++) {
                    numberFields[i][j].setNumber(ms.getNumber(i,j));
                }
            }
        }
    }

    //Method for Drop Down
    public void menuItemSelected(JMenuItem menuItem){
        if ( magicSquarePanel != null )
            this.remove(magicSquarePanel);
        magicSquarePanel = addPanel(1,1,1,1);
        if(menuItem == twoByTwo){
            this.setSize(400,180);
            makeMagicSquarePanel(magicSquarePanel, 2);
            magicSquareSize = 2;
        } else if (menuItem == threeByThree){
            this.setSize(400,220);
            makeMagicSquarePanel(magicSquarePanel,3);
            magicSquareSize = 3;
        } else if (menuItem == fourByFour){
            this.setSize(400,260);
            makeMagicSquarePanel(magicSquarePanel,4);
            magicSquareSize = 4;
        } else if (menuItem == fiveByFive){
            this.setSize(500,300);
            makeMagicSquarePanel(magicSquarePanel,5);
            magicSquareSize = 5;
        } else if (menuItem == sixBySix){
            this.setSize(600,340);
            makeMagicSquarePanel(magicSquarePanel,6);
            magicSquareSize = 6;
        } else if (menuItem == sevenBySeven){
            this.setSize(700,380);
            makeMagicSquarePanel(magicSquarePanel,7);
            magicSquareSize = 7;
        } else if (menuItem == eightByEight){
            this.setSize(800,420);
            makeMagicSquarePanel(magicSquarePanel,8);
            magicSquareSize = 8;
        }
        revalidate();
    }

    //Main function
    public static void main(String[] args) {
        JFrame frm = new MagicSquareGui();
        frm.setTitle("Magic Square");
        frm.setSize (600, 400);
        frm.setVisible (true);
    }
}
