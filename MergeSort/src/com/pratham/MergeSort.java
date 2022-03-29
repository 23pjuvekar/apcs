package com.pratham;

public class MergeSort {

    static void merge(int arr[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = l + (r-l)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public static int binarySearch(int arr[], int l, int r, int n)
    {
        if ( l <= r) {

            int mid = (l+r)/2;

            if (arr[mid] == n)
                return mid; // number found

            // search in the left half
            if (arr[mid] > n)
                return binarySearch(arr, l, mid - 1, n);

            // search in the right half
            return binarySearch(arr, mid + 1, r, n);
        }

        // this means number is not found
        return -1;
    }

    static String arrayToString(int arr[])
    {
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

