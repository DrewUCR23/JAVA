/*
Drew Gonzales
Professor Paul Conrad
6/3/2023
JAVA DATA STRUCTURES
FINAL PROJECT 
* Objective: This program generates prime numbers up to a given integer N using the Sieve of Eratosthenes algorithm. 
 * It utilizes a queue data structure to store the numbers and efficiently filter out non-prime numbers.
 */

package com.mycompany.javadatastructureproject;
import java.util.LinkedList;
import java.util.Queue; 
import java.util.Scanner; 

public class JAVADATASTRUCTUREPROJECT {
    public static void main(String[] args) {
        boolean verboseOutput = true;

        if (args.length == 1)
            verboseOutput = args[0].equalsIgnoreCase("--VERBOSE");

        Scanner kb = new Scanner(System.in);

        System.out.print("Enter an integer value of N: ");
        int n = kb.nextInt();

        Queue<Integer> queueOfIntegers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            queueOfIntegers.offer(i);
        }

        System.out.println("Content of: queueOfIntegers: " + queueOfIntegers.toString().replaceAll("[\\[\\],]", ""));

        Queue<Integer> queueOfPrimes = new LinkedList<>();
        int p;

        while (!queueOfIntegers.isEmpty()) {
            p = queueOfIntegers.poll();
            queueOfPrimes.offer(p);

            if (verboseOutput) {
                System.out.println("\nDequeuing a prime number: " + p);
                System.out.println("\nContent of: queueOfPrimes: " + queueOfPrimes.toString().replaceAll("[\\[\\],]", ""));
            }

            Queue<Integer> tempQueue = new LinkedList<>();
            while(!queueOfIntegers.isEmpty()){
                int current = queueOfIntegers.poll();
                if(current % p != 0) {
                    tempQueue.offer(current);
                }
            }
            queueOfIntegers = tempQueue;

            if (verboseOutput && !queueOfIntegers.isEmpty()) {
                System.out.println("\nContent of: queueOfIntegers: " + queueOfIntegers.toString().replaceAll("[\\[\\],]", ""));
            }
        }

        if (verboseOutput) {
            System.out.println("\nContent of: queueOfIntegers: "); // The queue is empty at this point
            System.out.println("\nContent of: queueOfPrimes: " + queueOfPrimes.toString().replaceAll("[\\[\\],]", ""));
        }
    }
}
