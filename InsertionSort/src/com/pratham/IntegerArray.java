package com.pratham;

public class IntegerArray {

    private int[] array = null;

    public int[] getArray() {
        return array;
    }

    public int[] getSortedArray() {
        if ( getCount() == 0 ) {
            return null;
        }
        InsertionSort insertionSort = new InsertionSort(array);
        int[] sorted = insertionSort.insertionSort();
        return sorted;
    }

    public int getCount() {
        if ( array == null ) {
            return 0;
        }
        return array.length;
    }

    public void add(int num) {
        if (array == null) {
            array = new int[1]; // add one spot to array
        } else {
            // add one more spot to the array and copy over
            int[] array2 = new int[array.length+1];
            for (int i=0; i<array.length; i++) {
                array2[i] = array[i];
            }
            array = array2; // replace array
        }
        array[array.length-1] = num; // add the new number to end of array
    }

    public void add(String commaSeparatedList) {
        String[] numsArray = commaSeparatedList.split(",");
        for (String s: numsArray) {
            add(Integer.parseInt(s.trim()));
        }
    }

    public void resetAll() {
        array = null; // remove the array
    }

    public double getMean() {
        if ( getCount() == 0 ) {
            return 0; // no numbers
        }

        // calculate the mean (sum/n)
        double mean = 0;
        for (int i=0; i<array.length; i++) {
            mean += array[i]; // add up numbers
        }
        mean = mean/array.length; // divide by count
        return mean;
    }

    public double getMedian() {
        if ( getCount() == 0 ) {
            return 0; // no numbers
        } else if ( getCount() == 1 ) {
            return array[0]; // only one number
        }

        // sort the numbers
        InsertionSort insertionSort = new InsertionSort(array);
        int[] sorted = insertionSort.insertionSort();

        // calculate the median
        double median = 0;
        if (sorted.length % 2 == 1) { //odd count
            median = sorted[sorted.length/2]; // middle number
        } else {
            median = (sorted[sorted.length/2-1] + sorted[sorted.length/2])/2.0; // average of middle numbers
        }
        return median;
    }

    public int[] getMode() {
        if ( getCount() == 0 ) {
            return null; // no numbers
        } else if ( getCount() == 1 ) {
            return null; // only one number
        }

        // sort the numbers
        InsertionSort insertionSort = new InsertionSort(array);
        int[] sorted = insertionSort.insertionSort();

        // calculate frequency of numbers
        int[] frequency = new int[sorted.length];
        for (int i=0; i< sorted.length; ) {
            frequency[i] = 1;
            for(int j=i+1; j< sorted.length; j++) {
                if (sorted[i] == sorted[j]) { // this means repeating
                    frequency[i]++;
                    frequency[j]=0;
                    continue;
                } else {
                    break; // not repeating so stop
                }
            }
            i += frequency[i];
        }

        // calculate the most frequent number count
        int max = 1;
        for ( int i=0; i<frequency.length; i++ ) {
            if (frequency[i] > max) {
                max = frequency[i];
            }
        }
        if ( max == 1 ) {
            return null; // no modes if all repeat only once
        }

        // find how many modes
        int numModes = 0;
        for ( int i=0; i<frequency.length; i++ ) {
            if (frequency[i] == max) {
                numModes++;
            }
        }

        // find and return all modes
        int[] modes = new int[numModes];
        int modeCount = 0;
        for ( int i=0; i<frequency.length; i++ ) {
            if (frequency[i] == max) {
                modes[modeCount] = sorted[i];
                modeCount++;
            }
        }
        return modes;
    }

    public double getStandardDeviation() {
        if ( getCount() == 0 ) {
            return 0; // no numbers
        }

        // calculate the standard deviation
        double stdDev = 0;
        double mean = getMean();
        for (int i=0; i<array.length; i++) {
            stdDev += ((array[i]-mean)*(array[i]-mean)); // add up squares of difference from mean
        }
        stdDev = Math.sqrt(stdDev/array.length); // divide by n then square root
        return stdDev;
    }
}
