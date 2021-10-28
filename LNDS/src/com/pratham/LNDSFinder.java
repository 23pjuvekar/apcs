package com.pratham;

public class LNDSFinder {

    static NumberArray[] findLNDS(NumberArray numbers) {

        if ( numbers.getLength() == 0 ) {
            return null; //no numbers
        }

        //find all non-decreasing sequences
        NumberArray[] ndsArray = new NumberArray[numbers.getLength()];
        int ndsArrayIndex = 0;
        int[] array = numbers.getArray();
        for ( int i=0; i< array.length; i++ ){
            if ( ndsArray[ndsArrayIndex] == null ) {
                ndsArray[ndsArrayIndex] = new NumberArray();
            }
            ndsArray[ndsArrayIndex].addNumber(array[i]);
            if ( i+1 >= array.length ) {
                break; //end of the array
            }
            if ( array[i] > array[i+1] ) {
                ndsArrayIndex++; //end of nds
            }
        }

        //find the length of longest sequence
        int longest = ndsArray[0].getLength();
        for ( int i=0; i<ndsArray.length; i++ ){
            if ( ndsArray[i] == null ) {
                break;
            }
            if ( ndsArray[i].getLength() > longest ) {
                longest = ndsArray[i].getLength();
            }
        }
        if ( longest == 1 ) {
            return null; // no LNDS found
        }

        //find how many longest sequences are there
        int longestCount = 0;
        for ( int i=0; i<ndsArray.length; i++ ){
            if ( ndsArray[i] == null ) {
                break;
            }
            if ( ndsArray[i].getLength() == longest ) {
                longestCount++;
            }
        }

        //return only the longest non-decreasing sequences
        NumberArray[] lndsArray = new NumberArray[longestCount];
        int lndsArrayIndex = 0;
        for ( int i=0; i<ndsArray.length; i++ ) {
            if ( ndsArray[i] == null ) {
                break;
            }
            if ( ndsArray[i].getLength() == longest ) {
                lndsArray[lndsArrayIndex] = ndsArray[i];
                lndsArrayIndex++;
            }
        }

        return lndsArray;
    }
}
