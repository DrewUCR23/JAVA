package com.mycompany.labinheritance17b;
/**
 * Drew Gonzales Professor Paul Conrad 3/31/2023 Abstract classes Introduction
 * to Java Advanced Objects
 *
 * Objective: This particular assignment is doing research and programming. I
 * want you to do a research report of the following: Ways that abstract classes
 * promotes software reusability, decreases time programming, and helps reduce
 * the number of errors in software. Research the using abstract classes and
 * interfaces. I would like for you to be creative and come up with your own
 * example of abstract classes and interfaces in Java by writing your own
 * abstract class and interface, demonstrating the abstract class and interface
 * in a test program (let your imagination and creativity go wild!) To be
 * submitted:
 */
import java.util.Scanner;
public class LABINHERITANCE17B {

    public static class TYPEOFCARS {

        public int NAMES = 6;
        public String[] CARNAMES = {"Chevy", "Buick", "Chrysler", "Mitsubishi", "Lamborghini", "RAM"};

        public void displayCarBrand(String input) {
            boolean found = false;
            for (int i = 0; i < NAMES; i++) {
                if (input.equalsIgnoreCase(CARNAMES[i])) {
                    System.out.println("The car brand you have chosen is " + CARNAMES[i]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("This is not a valid name on the car menu, please try again");
            }
        }

        public void displayFeatures(String input, Scanner userInput) {
            System.out.println("Features for your selected car brand:");
            FEATURESFORCAR featuresForCar = new FEATURESFORCAR();
            String[] pickedFeatures = featuresForCar.POSSIBLEFEATURES(input, userInput);
            System.out.println("You have chosen the following features:");
            for (String feature : pickedFeatures) {
                System.out.println(feature);
            }
        }
    }

    public static class FEATURESFORCAR extends TYPEOFCARS {

        String[] POSSIBLEFEATURES(String input, Scanner USERCHOICE) {
            int counter = 0;
            String[] CUSTOMFEATURES = {"Custom Seats", "Custom Paint", "Chrome Wheels", "High end Stereo System", "discount on all future car purchases"};
            String[] CUSTOMFEATURES2 = {"Premium Leather", "Velvet", "GPS System", "BlueTooth Speakers", "Sunroof", "RollBack Shades"};
            String[] PICKEDVALUES = new String[3];
            String[] CURRENT_FEATURES;

            if (input.equalsIgnoreCase("Chevy") || input.equalsIgnoreCase("Buick") || input.equalsIgnoreCase("Mitsubishi")) {
                System.out.println("The following features you have available for these are:");
                for (String feature : CUSTOMFEATURES) {
                    System.out.println(feature);
                }
                CURRENT_FEATURES = CUSTOMFEATURES;
            } else if (input.equalsIgnoreCase("Chrysler") || input.equalsIgnoreCase("Lamborghini") || input.equalsIgnoreCase("RAM")) {
                System.out.println("The following features you have available for these are:");
                for (String feature : CUSTOMFEATURES2) {
                    System.out.println(feature);
                }
                CURRENT_FEATURES = CUSTOMFEATURES2;
            } else {
                System.out.println("Invalid car brand.");
                return PICKEDVALUES;
            }

            System.out.println("Please pick 3 features and input them below");

            while (counter < 3) {
                String USERCHOICE1 = USERCHOICE.nextLine();

                if (isValidFeature(USERCHOICE1, CURRENT_FEATURES)) {
                    PICKEDVALUES[counter++] = USERCHOICE1;
                } else {
                    System.out.println("Invalid feature, please try again.");
                }
            }

            return PICKEDVALUES;
        }

        boolean isValidFeature(String userChoice, String[] availableFeatures) {
            for (String feature : availableFeatures) {
                if (userChoice.equalsIgnoreCase(feature)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("This program will use inheritance and extend various types of car values that will be called into the main and that will be instantiated accordingly");
        Scanner userInput = new Scanner(System.in);
        System.out.println("What is the brand name of the car you want?");
        String input = userInput.nextLine();
        // Instantiate the child class
        FEATURESFORCAR featuresForCar = new FEATURESFORCAR();
        // Call the car brand name from the if statement
        featuresForCar.displayCarBrand(input);
        // Call the displayFeatures method from the instantiated child class
        featuresForCar.displayFeatures(input, userInput);
    }

}
