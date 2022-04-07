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

    static public int[] getArrayCopy(int[] array) {
        int[] arrayCopy = new int[array.length];
        for ( int i=0; i<array.length; i++ ) {
            arrayCopy[i] = array[i];
        }
        return arrayCopy;
    }

    public String unsortedArrayAsString() {
        String retMsg = "";
        int[] numbersCopy = getArrayCopy(numbers);
        for ( int n : numbersCopy ) {
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
        int[] numbersCopy = getArrayCopy(numbers);
        MergeSort.sort(numbersCopy, 0, numbersCopy.length-1);
        return arrayToString(numbersCopy);
    }

    public String quickSortedArrayAsString() {
        if ( numbers.length == 0 ) {
            return ""; // empty array, so blank
        }
        int[] numbersCopy = getArrayCopy(numbers);
        QuickSort.sort(numbersCopy, 0, numbersCopy.length-1);
        return arrayToString(numbersCopy);
    }

    public String insertionSortedArrayAsString() {
        if ( numbers.length == 0 ) {
            return ""; // empty array, so blank
        }
        int[] numbersCopy = getArrayCopy(numbers);
        int[] sorted = InsertionSort.sort(numbersCopy);
        return arrayToString(sorted);
    }

    public String getStatistics() {
        Statistics statistics = new Statistics(numbers);
        return statistics.getStatistics();
    }

    public String getSortSpeeds(int[] testArray) {
        String retMsg = "Time Taken to Sort " + numbers.length + " integers:\n\n";

        long t1_start = System.currentTimeMillis();
        int[] copy1 = getArrayCopy(testArray);
        QuickSort.sort(copy1, 0, copy1.length-1);
        long t1_end = System.currentTimeMillis();
        retMsg += String.format("Quick Sort     : %d milliseconds %n", t1_end-t1_start);

        long t2_start = System.currentTimeMillis();
        int[] copy2 = getArrayCopy(testArray);
        MergeSort.sort(copy2, 0, copy2.length-1);
        long t2_end = System.currentTimeMillis();
        retMsg += String.format("Merge Sort     : %d milliseconds %n", t2_end-t2_start);

        long t3_start = System.currentTimeMillis();
        int[] copy3 = getArrayCopy(testArray);
        InsertionSort.sort(copy3);
        long t3_end = System.currentTimeMillis();
        retMsg += String.format("Insertion Sort : %d milliseconds %n%n", t3_end-t3_start);

        retMsg += getStatistics();

        return retMsg;
    }

    static String arrayToString(int arr[]) {
        String retMsg = "";
        for (int n : arr) {
            if ( !retMsg.isEmpty() ) {
                retMsg += ", ";
            }
            retMsg += n;
        }
        return retMsg;
    }
}

