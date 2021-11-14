package com.pratham;

import BreezySwing.GBDialog;
import BreezySwing.GBFrame;
import BreezySwing.GBPanel;

import javax.swing.*;
import java.awt.*;

public class CharactersGui extends GBFrame {

    private GBPanel mainPanel;
    private JTextField sentenceField;
    private JButton analyzeButton;
    private JButton barGraphButton;
    private JButton heatMapButton;
    private GBPanel displayPanel;

    public CharactersGui () {

        this.setSize(600, 100);
        mainPanel = addPanel(1, 1, 1, 1);
        GBPanel inputPanel = mainPanel.addPanel(1, 1,1, 1);

        //create the sentence input
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        GBPanel inputSentencePanel = inputPanel.addPanel(1, 1,1, 1);
        inputSentencePanel.addLabel("Sentence to analyze:", 1, 1, 1, 1);
        sentenceField = inputSentencePanel.addTextField("", 2, 1, 1, 1);

        //create buttons
        GBPanel inputButtonPanel = inputPanel.addPanel(2, 1,1, 1);
        analyzeButton = inputButtonPanel.addButton("Analyze", 1, 1, 1, 1);
        barGraphButton = inputButtonPanel.addButton( "Bar Graph", 1, 2, 1, 1);
        heatMapButton = inputButtonPanel.addButton( "Heat Map", 1, 3, 1, 1);
    }

    public String barString(Word w) {
        String barString = "";
        for ( int i=0; i<w.getCount(); i++ ) {
            barString += "█"; //print block
        }
        return barString;
    }

    public void buttonClicked(JButton buttonObj) {

        //get sentence entered by user
        String sentence = sentenceField.getText();
        if ( sentence.isEmpty() ) {
            messageBox("Sentence cannot be empty for analysis.");
            return;
        }

        Characters characters = new Characters();
        characters.analyzeString(sentence);

        if ( buttonObj == analyzeButton ) {

            //create panel to show
            if ( displayPanel != null ) {
                mainPanel.remove(displayPanel);
            }
            displayPanel = mainPanel.addPanel (2,1,1,1 );
            displayPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            //size based on how many words to show
            this.setSize(600, 125 + characters.getWordsStored()*25);

            //show the counts for characters and words
            int row = 1;
            displayPanel.addLabel(""+characters.getNumChars()+" characters", row++, 1, 1, 1).setFont(new Font("Courier", 0, 15));;
            displayPanel.addLabel(""+characters.getNumWords()+" words", row++, 1, 1, 1).setFont(new Font("Courier", 0, 15));;

            //show the words and their counts
            String formatStr = "  %-"+characters.getMaxWordLength()+"s \t%d";
            Word[] words = characters.getWords();
            for ( int i=0; i< characters.getWordsStored(); i++ ) {
                String display = String.format(formatStr, words[i].getWord(), words[i].getCount());
                displayPanel.addLabel( display, row++, 1, 1, 1 ).setFont(new Font("Courier", 0, 15));;
            }

            revalidate();
        }

        if ( buttonObj == barGraphButton ) {

            //create panel to show
            if ( displayPanel != null ) {
                mainPanel.remove(displayPanel);
            }
            displayPanel = mainPanel.addPanel (2,1,1,1 );
            displayPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            //size based on how many words to show
            this.setSize(600, 100 + characters.getWordsStored()*25);

            //show the words and their counts
            int row = 1;
            String formatStr = "  %-"+characters.getMaxWordLength()+"s \t%d  \t%s";
            Word[] words = characters.getWords();
            for ( int i=0; i< characters.getWordsStored(); i++ ) {
                String display = String.format(formatStr, words[i].getWord(), words[i].getCount(), barString(words[i]));
                displayPanel.addLabel( display, row++, 1, 1, 1 ).setFont(new Font("Courier", 0, 15));;
            }

            revalidate();
        }

        if ( buttonObj == heatMapButton ) {

            //create panel to show
            if ( displayPanel != null ) {
                mainPanel.remove(displayPanel);
            }
            displayPanel = mainPanel.addPanel (2,1,1,1 );
            displayPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            //size based on how many words to show
            this.setSize(600, 100 + characters.getNumWords()*25);

            //show the words and their counts
            int row = 1;
            String formatStr = "  %s (%d)";
            Word[] words = characters.getWords();
            for ( int i=0; i< characters.getWordsStored(); i++ ) {
                String display = String.format(formatStr, words[i].getWord(), words[i].getCount());
                displayPanel.addLabel( display, row++, 1, 1, 1 ).setFont(new Font("Courier", 0, 15*words[i].getCount()));;
            }

            revalidate();
        }
    }

    public class HeatMapDialog extends GBDialog {

        private JButton okBtn;

        public HeatMapDialog(JFrame parent, Characters characters) {
            super (parent);                                 // ** REQUIRED **

            setTitle ("Heat Map");
            setDlgCloseIndicator ("Ok");
            setLocationRelativeTo(null);

            //size based on how many words to show
            this.setSize(600, 125 + characters.getWordsStored()*25);

            GBPanel graphPanel = this.addPanel(1, 1, 1, 1);
            int row = 1;
            Word[] words = characters.getWords();
            for ( int i=0; i< characters.getWordsStored(); i++ ) {
                int fontSize = words[i].getCount()*15;
                graphPanel.addLabel( words[i].getWord()+" ("+words[i].getCount()+")", row++, 1, 1, 1 ).setFont(new Font("Courier", 0, fontSize));;
            }

            GBPanel buttonPanel = this.addPanel(2, 1, 1, 1);
            okBtn = buttonPanel.addButton ("OK", 1,1,1,1);
        }

        public void buttonClicked(JButton buttonObj) {
            dispose();
        }
    }

    public static void main(String[] args) {
        CharactersGui gui = new CharactersGui();
        gui.setTitle("Characters and Words Analyzer");
        gui.setLocationRelativeTo(null); //this displays JFrame to center position of a screen
        gui.setVisible (true);
    }
}
