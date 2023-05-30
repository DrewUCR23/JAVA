/*
Drew Gonzales
Profesor Paul Conrad
5/11/2023
Introduction To Java Data Structures
Objective: The objective of this project willl be to perform big o notation analysis on this merge sort code. The objective will be to provie it's time complexity
such that it is O(nlgn) time complexity in it's worst case. Thus it willneed to read arrays of various types of lengths in order to be computed and understood accordingly
 */

package com.mycompany.mergesortproject;

import java.util.Scanner;

public class MERGESORTPROJECT {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter number of elements for array: ");
        int num_elements = kb.nextInt();
        if (num_elements <= 0) {
            System.out.println("Must be a positive number of elements!");
        } else {
            int[] arr = new int[num_elements];
            for (int i = 0; i < num_elements; i++) {
                System.out.printf("Enter value for arr[%d]: ", i);
                arr[i] = kb.nextInt();
            }
            System.out.println("\nOriginal array contents:");
            display_array(arr);
            System.out.printf("\n");
            mergesort(arr, true, true);
            System.out.printf("\nArray after mergesort (ascending):");
            display_array(arr);
            System.out.printf("\n");
        }
    }

    public static void display_array(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.print(a[a.length - 1]);
    }

    public static void merge(int arr[], int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }
        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
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

    public static void mergesort(int[] arr, boolean sort_ascending, boolean analysis) {
        mergeSortHelper(arr, 0, arr.length - 1, sort_ascending, analysis);
    }

    public static void mergeSortHelper(int[] arr, int l, int r, boolean sort_ascending, boolean analysis) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortHelper(arr, l, m, sort_ascending, analysis);
            mergeSortHelper(arr, m + 1, r, sort_ascending, analysis);
                       merge(arr, l, m, r);
        }
    }
}

