package com.pratham;

public class NumberArray {

    private int[] array;

    public NumberArray() {
        array = null;
    }

    public NumberArray(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public int getLength() {
        if ( array == null ) {
            return 0;
        }
        return array.length;
    }

    public void addNumber(int number) {
        int[] newArray = new int[getLength()+1];
        for ( int i=0; i<getLength(); i++ ) {
            newArray[i] = array[i];
        }
        newArray[getLength()] = number;
        this.array = newArray;
    }

    public void addArray(int[] addArray) {
        int[] newArray = new int[getLength()+addArray.length];
        for ( int i=0; i<getLength(); i++ ) {
            newArray[i] = array[i];
        }
        for ( int i=getLength(); i<getLength()+addArray.length; i++) {
            newArray[i] = addArray[i-getLength()];
        }
        this.array = newArray;
    }

    public void reset() {
        array = null;
    }

    public String toString() {
        String strToReturn = "";
        for ( int i=0; i<getLength(); i++ ) {
            if ( i > 0 ) {
                strToReturn += ", ";
            }
            strToReturn += array[i];
        }
        return strToReturn;
    }

}
