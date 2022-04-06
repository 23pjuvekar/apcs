package com.pratham;

public class QuickSort {

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // partition with the first element as pivot
    static int partition(int arr[], int low, int high)
    {
        int pivot = arr[low];

        int i = (low+1); // index of smaller element
        for (int j=low+1; j<=high; j++)
        {
            // if current element is greater than pivot then swap
            if (arr[j] >= pivot)
            {
                // swap arr[i] and arr[j]
                swap(arr, i, j);
                i++;
            }
        }

        // swap arr[i-1] and arr[low] (or pivot)
        swap(arr, i-1, low);

        return i-1;
    }

    /* The main function that implements QuickSort()
          arr[] --> Array to be sorted,
          low  --> Starting index,
          high  --> Ending index */
    public static void sort(int arr[], int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }

}
