/*
 * Project: Java Console App
 * Name: Drew Gonzales
 * Class: 17B
 * Professor: Paul Conrad
 *
 * Description: A Java app that reads ordered pairs from a text file, calculates various sums and products, and displays the results.
 *
 * Objective: Develop a Java console application that prompts the user for a text file, reads the ordered pairs, calculates sums and products, and outputs the results.
 */
package com.mycompany.leastsquarejava;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class to store data for each file
class FileData {
    String filename; // name of the file
    double sumX = 0, sumY = 0, sumXSq = 0, sumYSq = 0, sumXY = 0; // sums needed for calculations
    int count = 0; // count of data points in the file

    // Constructor takes filename as parameter
    public FileData(String filename) {
        this.filename = filename;
    }
}

public class LEASTSQUAREJAVA {

    // List to store data for all files
    static List<FileData> fileDataList = new ArrayList<>();

    // Function to print the menu
    public static void PRINTMENU() {
        System.out.println("This is Drew's LEASTSQUARE PROGRAM ");
        System.out.println("OUTPUT MENU");
        System.out.println("1. To see the Sum of X");
        System.out.println("2. To see the Sum of Y");
        System.out.println("3. To see the sum of squares for X");
        System.out.println("4. To see the sum of squares for Y");
        System.out.println("5. To see the sum of products of X and Y");
        System.out.println("6. Output The Equation of for the least square line equation accordingly");
        System.out.println("0. To end the program");
        System.out.println("Please enter the name of 2 files you want to open: ");
    }

    // Main function
    public static void main(String[] args) {
        Scanner value = new Scanner(System.in);

        // Print the menu
        PRINTMENU();

        // Process the first file
        String fileInput1 = value.nextLine();
        processFile(fileInput1);

        // Process the second file
        String fileInput2 = value.nextLine();
        processFile(fileInput2);

        int userInput;
        do {
            // Read user input
            userInput = value.nextInt();

            // Output data for each file
            for (FileData fileData : fileDataList) {
                System.out.println("\nData for file: " + fileData.filename);
                switch (userInput) {
                    case 0:
                        System.out.println("You have logged off");
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println("This is the sum of X: " + fileData.sumX);
                        break;
                    case 2:
                        System.out.println("This is the sum of Y: " + fileData.sumY);
                        break;
                    case 3:
                        System.out.println("This is the sum of squares for X: " + fileData.sumXSq);
                        break;
                    case 4:
                        System.out.println("This is the sum of squares for Y: " + fileData.sumYSq);
                        break;
                    case 5:
                        System.out.println("This is the sum of products of X and Y: " + fileData.sumXY);
                        break;
                    case 6:
                        double slope = (fileData.count * fileData.sumXY - fileData.sumX * fileData.sumY) / (fileData.count * fileData.sumXSq - fileData.sumX * fileData.sumX);
                        double intercept = (fileData.sumY - slope * fileData.sumX) / fileData.count;
                        System.out.println("The equation of the least square line is: y = " + slope + "x + " + intercept);
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                }
            }
        } while (userInput != 0);
    }

    // Function to process each file
    static void processFile(String filename) {
        FileData fileData = new FileData(filename);
        fileDataList.add(fileData);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                fileData.count++;
                String[] splitLine = line.split(" ");  // Assuming ordered pairs are separated by a space
                if (splitLine.length >= 2) {
                    double x = Double.parseDouble(splitLine[0]);
                    double y = Double.parseDouble(splitLine[1]);
                    fileData.sumX += x;
                    fileData.sumY += y;
                    fileData.sumXSq += x * x;
                    fileData.sumYSq += y * y;
                    fileData.sumXY += x * y;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("There was an error reading the file: " + filename);
        }
    }
}
