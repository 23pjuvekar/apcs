package com.pratham;

/* A class that sorts an array of ints from smallest to largest using insertion sort */

public class InsertionSort{

    //Sort array from smallest to largest using insertion sort
    //Precondition: unsorted is an array of ints
    public static int[] sort(int[] arr)
    {
        int[] sorted;
        if ( arr == null || arr.length <= 1 ) {
            sorted = arr; // nothing to do
        } else {
            sorted = new int[arr.length];
            sorted[0] = arr[0];
            for (int i = 1; i < arr.length; i++) {
                sorted[i] = arr[i];
                for (int j = 0; j < i; j++) {
                    if ( arr[i] > sorted[j] ) { // insert at j
                        // move by 1 to create an open spot
                        for (int k = i-1; k >= j; k-- ) {
                            sorted[k+1] = sorted[k];
                        }
                        sorted[j] = arr[i]; // insert
                        break; // done inserting so break out of loop
                    }
                }
            }
        }
        return sorted;
    }
}
