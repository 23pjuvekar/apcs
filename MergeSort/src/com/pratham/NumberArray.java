package com.pratham;

public class NumberArray {

    int[] numbers = new int[0]; // initialize empty array

    public int[] getNumbers() {
        return numbers;
    }

    public void addNumber(int num) {

        // make new array with one more spot
        int[] newNumbers = new int[numbers.length+1];
        for (int i=0; i<numbers.length; i++) {
            newNumbers[i] = numbers[i]; // copy over
        }
        numbers = newNumbers; // replace old array with new one

        numbers[numbers.length-1] = num; // add to the end
    }

    public String unsortedArrayAsString() {
        String retMsg = "";
        for ( int n : numbers ) {
            if ( !retMsg.isEmpty() ) {
                retMsg += ", ";
            }
            retMsg += n;
        }
        return retMsg;
    }

    public String mergeSortedArrayAsString() {
        if ( numbers.length == 0 ) {
            return ""; // empty array, so blank
        }
        MergeSort.sort(numbers, 0, numbers.length-1);
        return MergeSort.arrayToString(numbers);
    }

    public int searchNumber(int num) {
        if ( numbers.length == 0 ) {
            return -1;
        }
        MergeSort.sort(numbers, 0, numbers.length-1);
        return MergeSort.binarySearch(numbers, 0, numbers.length-1, num);
    }

    public String getStatistics() {
        Statistics statistics = new Statistics(numbers);
        return statistics.getStatistics();
    }
}
