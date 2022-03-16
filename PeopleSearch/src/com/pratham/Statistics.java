package com.pratham;

public class Statistics {

    int[] ages = new int[0];

    public Statistics(int[] ages) {
        this.ages = ages;
    }

    public int getCount() {
        return ages.length;
    }

    public int[] getAges() {
        return ages;
    }

    public void setAges(int[] ages) {
        this.ages = ages;
    }

    public String getStatistics() {

        String output = "";

        if ( getCount() == 0 ) {
            output = "There are no ages in the list";
            return output;
        }

        output += String.format("Mean:     %.2f %n",getMean());
        output += String.format("Median:   %.0f %n",getMedian());
        output += String.format("Mode:     ");
        double[] mode = getMode();
        if ( mode == null || mode.length == 0 ) {
            output += "None\n";
        } else {
            for (int i=0; i<mode.length; i++) {
                output += String.format("%.0f ", mode[i]);
            }
            output += "\n";
        }
        output += String.format("Std Dev:  %.2f %n",getStandardDeviation());

        return output;
    }

    private double getMean() {
        if ( getCount() == 0 ) {
            return 0; // no numbers
        }

        // calculate the mean (sum/n)
        double mean = 0;
        for (int i = 0; i< ages.length; i++) {
            mean += ages[i]; // add up numbers
        }
        mean = mean/ ages.length; // divide by count
        return mean;
    }

    private double getMedian() {
        if ( getCount() == 0 ) {
            return 0; // no numbers
        } else if ( getCount() == 1 ) {
            return ages[0]; // only one number
        }

        // sort the numbers
        InsertionSort insertionSort = new InsertionSort(ages);
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

    private double[] getMode() {
        if ( getCount() == 0 ) {
            return new double[0]; // no numbers
        } else if ( getCount() == 1 ) {
            return new double[0]; // only one number
        }

        // sort the numbers
        InsertionSort insertionSort = new InsertionSort(ages);
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
        double[] modes = new double[numModes];
        int modeCount = 0;
        for ( int i=0; i<frequency.length; i++ ) {
            if (frequency[i] == max) {
                modes[modeCount] = sorted[i];
                modeCount++;
            }
        }
        return modes;
    }

    private double getStandardDeviation() {
        if ( getCount() == 0 ) {
            return 0; // no numbers
        }

        // calculate the standard deviation
        double stdDev = 0;
        double mean = getMean();
        for (int i = 0; i< ages.length; i++) {
            stdDev += ((ages[i]-mean)*(ages[i]-mean)); // add up squares of difference from mean
        }
        stdDev = Math.sqrt(stdDev/ ages.length); // divide by n then square root
        return stdDev;
    }

}
