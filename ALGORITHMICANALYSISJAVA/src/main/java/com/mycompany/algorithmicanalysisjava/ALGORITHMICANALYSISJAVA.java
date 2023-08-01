package com.mycompany.algorithmicanalysisjava;

import java.util.Scanner;
import java.util.Random;

public class ALGORITHMICANALYSISJAVA {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        
        System.out.print("Enter number of test cases: ");
        int test_cases = kb.nextInt();
        long[] timeElapsedArray = new long[test_cases];
        int[] arraySize = new int[test_cases];
        
        Random rand = new Random();
        
        for (int t=0; t<test_cases; t++) {
            System.out.printf("Enter number of elements for test case %d: ", t+1);
            int num_elements = kb.nextInt();
            arraySize[t] = num_elements;
            
            int [] arr = new int[num_elements];
            for(int i=0; i<num_elements; i++)
            {
                arr[i] = rand.nextInt(1000);
            }
            
            long startTime = System.nanoTime();
            mergesort(arr, true, true);
            long elapsedTime = System.nanoTime() - startTime;
            timeElapsedArray[t] = elapsedTime;
        }
        
        for(int t=0; t<test_cases; t++) {
            System.out.printf("\nTime complexity for array size %d (in nanoseconds): %d\n", arraySize[t], timeElapsedArray[t]);
        }
    }

    public static void mergesort(int [] arr, boolean sort_ascending, boolean analysis)
    {
        mergeSortHelper(arr, 0, arr.length-1);
    }
    
    public static void mergeSortHelper(int [] arr, int l, int r) 
    { 
        if (l < r) 
        { 
            int m = l+(r-l)/2; 
            mergeSortHelper(arr, l, m); 
            mergeSortHelper(arr, m+1, r); 
            merge(arr, l, m, r); 
        } 
    } 
    
    public static void merge(int arr[], int l, int m, int r) 
    { 
        int i, j, k; 
        int n1 = m - l + 1; 
        int n2 = r - m; 

        int L[] = new int[n1];
        int R[] = new int[n2]; 

        for (i = 0; i < n1; i++) 
            L[i] = arr[l + i]; 
        for (j = 0; j < n2; j++) 
            R[j] = arr[m + 1+ j]; 

        i = 0; 
        j = 0; 
        k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 

        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 

        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
}

