package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;

import javax.swing.*;
import java.awt.*;

public class ParserGui extends GBFrame {

    private GBPanel mainPanel;
    private JTextField expressionField;
    private JButton evaluateButton;
    private GBPanel displayPanel;
    private JTextField answerField;

    public ParserGui() {

        this.setSize(600, 150);
        mainPanel = addPanel(1, 1, 1, 1);
        GBPanel inputPanel = mainPanel.addPanel(1, 1,1, 1);

        //create the sentence input
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        GBPanel inputSentencePanel = inputPanel.addPanel(1, 1,1, 1);
        inputSentencePanel.addLabel("Expression to evaluate:", 1, 1, 1, 1);
        expressionField = inputSentencePanel.addTextField("", 2, 1, 1, 1);

        //create buttons
        GBPanel inputButtonPanel = inputPanel.addPanel(2, 1,1, 1);
        evaluateButton = inputButtonPanel.addButton("Evaluate", 1, 1, 1, 1);

        //create display area
        displayPanel = mainPanel.addPanel (2,1,1,1 );
        displayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        answerField = displayPanel.addTextField("", 1, 1, 1, 1);
        answerField.setEditable(false);
    }

    private String removeWhitespaces(String expression) {
        String returnValue = "";
        for ( int i=0; i<expression.length(); i++ ) {
            if (!Character.isWhitespace(expression.charAt(i))) {
                returnValue += expression.charAt(i); //keep only non whitespaces
            }
        }
        return returnValue;
    }

    public void buttonClicked(JButton buttonObj) {

        //get sentence entered by user
        String expression = removeWhitespaces(expressionField.getText());
        expressionField.setText(expression);

        if ( buttonObj == evaluateButton) {
            try {
                answerField.setText(new Parser().evaluate(expression));
            } catch (Exception ex) {
                answerField.setText(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ParserGui gui = new ParserGui();
        gui.setTitle("Expression Parser");
        gui.setLocationRelativeTo(null); //this displays JFrame to center position of a screen
        gui.setVisible (true);
    }
}
