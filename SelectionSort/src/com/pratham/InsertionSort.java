package com.pratham;

/* A class that sorts an array of ints from smallest to largest using insertion sort */

public class InsertionSort{
    private double[] unsorted = null;
    private double[] sorted = null;

    //constructor
    public InsertionSort(double[] arr)
    {
        unsorted = arr; // store the array
    }

    //Sort array from smallest to largest using insertion sort
    //Precondition: unsorted is an array of ints
    public double[] insertionSort()
    {
        if ( unsorted == null || unsorted.length <= 1 ) {
            sorted = unsorted; // nothing to do
        } else {
            sorted = new double[unsorted.length];
            sorted[0] = unsorted[0];
            for (int i = 1; i < unsorted.length; i++) {
                sorted[i] = unsorted[i];
                for (int j = 0; j < i; j++) {
                    if ( unsorted[i] < sorted[j] ) { // insert at j
                        // move by 1 to create an open spot
                        for (int k = i-1; k >= j; k-- ) {
                            sorted[k+1] = sorted[k];
                        }
                        sorted[j] = unsorted[i]; // insert
                        break; // done inserting so break out of loop
                    }
                }
            }
        }
        return sorted;
    }
}


