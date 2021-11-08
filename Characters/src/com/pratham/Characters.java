package com.pratham;

import static java.lang.Character.*;

public class Characters {

    private int numChars = 0;
    private int numWords = 0;
    private Word[] words = new Word[100];
    private int wordsStored = 0;

    private int start = 0;
    private int end = 0;

    //default constructor
    public Characters() {
    }

    //get number of characters in the string analyzed
    public int getNumChars() {
        return numChars;
    }

    //get number of words in the string analyzed
    public int getNumWords() {
        return numWords;
    }

    //get array of words
    public Word[] getWords() {
        return words;
    }

    //get number of words stored in the array
    public int getWordsStored() {
        return wordsStored;
    }

    //count the characters in the string (ignore space characters)
    private void countCharacters (String str) {
        for (int i = 0; i < str.length(); i++) {
            if ( !isWhitespace (str.charAt(i)) ) {
                numChars++;
            }
        }
    }

    //begins at start and finds the end of next word
    private void findNextWord (String str) {

        //if we reached the end, return
        if ( start >= str.length() ) {
            return;
        }

        //find the start of the word (skip spaces)
        for ( int i=start; i<str.length(); i++ ) {
            char c = str.charAt(i);
            if ( !isLetterOrDigit(c) ) {
                start++;
                end++;
            } else {
                break;
            }
        }

        //if we reached the end, return
        if ( start >= str.length() ) {
            return;
        }

        //find the end of the word (letters and digits)
        for ( int i=start; i<str.length(); i++ ) {
            char c = str.charAt(i);
            if ( !isLetterOrDigit(c) ) {
                break;
            } else {
                end++;
            }
        }

        //get the word and capitalize first letter
        String word = str.substring(start, end);
        start = end;
        word = word.substring(0,1).toUpperCase() + word.substring(1);
        numWords++;

        //if we have stored the word increase count or store as new word
        boolean found = false;
        for ( int i=0; i<wordsStored; i++ ) {
            if ( words[i].getWord().equalsIgnoreCase(word) ) {
                found = true;
                words[i].increaseCount();
                break;
            }
        }
        if ( !found ) {
            words[wordsStored] = new Word(word);
            wordsStored++;
        }

    }

    //count the words in the string
    private void countWords (String str) {
        start = 0;
        end = 0;
        do {
            findNextWord(str);
        } while ( end < str.length() );
    }

    //analyze the string (count characters and words
    public void analyzeString (String str) {
        countCharacters (str);
        countWords (str);
    }
}
