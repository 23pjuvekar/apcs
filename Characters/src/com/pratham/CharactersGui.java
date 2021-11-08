package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CharactersGui extends GBFrame {

    private GBPanel mainPanel;
    private GBPanel inputPanel;
    private JTextField sentenceField;
    private JButton analyzeButton;
    private GBPanel analysisPanel;

    public CharactersGui () {

        this.setSize(600, 100);
        mainPanel = addPanel(1, 1, 1, 1);

        //create the input
        inputPanel = mainPanel.addPanel(1, 1,1, 1);
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        inputPanel.addLabel("Sentence to analyze:", 1, 1, 1, 1);
        sentenceField = inputPanel.addTextField("", 2, 1, 1, 1);
        analyzeButton = inputPanel.addButton("Analyze", 3, 1, 1, 1);
    }

    public void buttonClicked(JButton buttonObj) {
        if ( buttonObj == analyzeButton ) {

            //get sentence entered by user
            String sentence = sentenceField.getText();
            if ( sentence.isEmpty() ) {
                messageBox("Sentence cannot be empty for analysis.");
                return;
            }

            //analyze the string
            Characters characters = new Characters();
            characters.analyzeString(sentence);

            //create panel to show analysis
            if ( analysisPanel != null ) {
                mainPanel.remove(analysisPanel);
            }
            analysisPanel = mainPanel.addPanel (2,1,1,1 );
            analysisPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            //size based on how many words to show
            this.setSize(600, 125 + characters.getWordsStored()*25);

            //show the counts for characters and words
            int row = 1;
            analysisPanel.addLabel(""+characters.getNumChars()+" characters", row++, 1, 1, 1).setFont(new Font("Courier", 0, 15));;
            analysisPanel.addLabel(""+characters.getNumWords()+" words", row++, 1, 1, 1).setFont(new Font("Courier", 0, 15));;

            //show the words and their counts
            Word[] words = characters.getWords();
            for ( int i=0; i< characters.getWordsStored(); i++ ) {
                String display = String.format("  %-15s : %d", words[i].getWord(), words[i].getCount());
                analysisPanel.addLabel( display, row++, 1, 1, 1 ).setFont(new Font("Courier", 0, 15));;
            }

            revalidate();
        }
    }

    public static void main(String[] args) {
        CharactersGui gui = new CharactersGui();
        gui.setTitle("Characters and Words Analyzer");
        gui.setLocationRelativeTo(null); //this displays JFrame to center position of a screen
        gui.setVisible (true);
    }
}
