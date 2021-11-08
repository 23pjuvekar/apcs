package com.pratham;

public class Word {

    private String word;
    private int count = 0;

    //default constructor
    public Word(String word) {
        this.word = word;
        count = 1;
    }

    //increase count by one
    public void increaseCount() {
        count++;
    }

    //get Word string
    public String getWord() {
        return word;
    }

    //get Word count
    public int getCount() {
        return count;
    }
}
