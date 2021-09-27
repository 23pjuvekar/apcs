package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.IntegerField;

import javax.swing.*;
import java.awt.*;

public class MagicSquareGui extends GBFrame {

    //Drop down setup
    JMenuItem TwoByTwo;
    JMenuItem ThreeByThree;
    JMenuItem FourByFour;
    JMenuItem FiveByFive;
    JMenuItem SixBySix;
    JMenuItem SevenBySeven;
    JMenuItem EightByEight;

    int size;

    GBPanel panel;
    IntegerField[][] numberFields = new IntegerField[8][8];

    JLabel startLabel;
    IntegerField startField;
    JButton generateButton;

    JLabel constantLabel;
    IntegerField constantField;
    JButton checkButton;

    //Gui constructor
    MagicSquareGui() {
        TwoByTwo = addMenuItem("Choose Size","2 x 2");
        ThreeByThree = addMenuItem("Choose Size","3 x 3");
        FourByFour = addMenuItem("Choose Size","4 x 4");
        FiveByFive = addMenuItem("Choose Size","5 x 5");
        SixBySix = addMenuItem("Choose Size","6 x 6");
        SevenBySeven = addMenuItem("Choose Size","7 x 7");
        EightByEight = addMenuItem("Choose Size","8 x 8");
        panel = addPanel(1,1,1,1);
        JLabel titleField = panel.addLabel("Choose a size for the Magic Square from the dropdown.", 1, 1, 1, 1);
        Font font = new Font("Courier", Font.BOLD,16);
        titleField.setFont(font);
    }

    //Method for generating IntegerFields
    public void makeMagicSquarePanel(GBPanel panel, int size){

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

        GBPanel panel1 = panel.addPanel(3, 1, size, 1);
        constantLabel = panel1.addLabel("Constant", 1, 1, 1, 1);
        constantField = panel1.addIntegerField(0, 1, 2, 1, 1);
        constantField.setEditable(false);
        checkButton = panel1.addButton("Check", 1, 3, 1, 1);

        GBPanel panel2 = panel.addPanel(4, 1, size, 1);
        startLabel = panel2.addLabel("Start", 1, 1, 1, 1);
        startField = panel2.addIntegerField(1, 1, 2, 1, 1);
        generateButton = panel2.addButton("Generate", 1, 3, 1, 1);

        if ( size == 2 ) {
            generateButton.setEnabled(false); //No generate for 2x2
        }
    }

    //Method for button clicks
    public void buttonClicked(JButton buttonObj){
        if ( buttonObj == checkButton ) {
            MagicSquare ms = new MagicSquare(size);
            for (int i=0; i< size; i++) {
                for (int j=0; j<size; j++) {
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
            MagicSquare ms = new MagicSquare(size);
            ms.createMagicSquare(startNum); //Get starting number
            for (int i=0; i< size; i++) {
                for (int j=0; j<size; j++) {
                    numberFields[i][j].setNumber(ms.getNumber(i,j));
                }
            }
        }
    }

    //Method for Drop Down
    public void menuItemSelected(JMenuItem menuItems){
        if ( panel != null )
            this.remove(panel);
        panel = addPanel(1,1,1,1);
        if(menuItems == TwoByTwo){
            this.setSize(400,180);
            makeMagicSquarePanel( panel, 2);
            size = 2;
        } else if (menuItems == ThreeByThree){
            this.setSize(400,220);
            makeMagicSquarePanel(panel,3);
            size = 3;
        } else if (menuItems == FourByFour){
            this.setSize(400,260);
            makeMagicSquarePanel(panel,4);
            size = 4;
        } else if (menuItems == FiveByFive){
            this.setSize(500,300);
            makeMagicSquarePanel(panel,5);
            size = 5;
        } else if (menuItems == SixBySix){
            this.setSize(600,340);
            makeMagicSquarePanel(panel,6);
            size = 6;
        } else if (menuItems == SevenBySeven){
            this.setSize(700,380);
            makeMagicSquarePanel(panel,7);
            size = 7;
        } else if (menuItems == EightByEight){
            this.setSize(800,420);
            makeMagicSquarePanel(panel,8);
            size = 8;
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
