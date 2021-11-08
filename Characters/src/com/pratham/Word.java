package com.pratham;

public class Word {

    private String word;
    private int count = 0;

    public Word(String word) {
        this.word = word;
        count = 1;
    }

    public void increaseCount() {
        count++;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }
}
