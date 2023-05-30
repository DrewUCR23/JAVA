package com.mycompany.lababstractclasses18b;

import java.util.Scanner;

/**
 * Drew Gonzales
 * Professor Paul Conrad
 * Abstract classes
 * Introduction to Java Advanced Objects
 * 4/19/2023
 * Objective:
 * This particular assignment is doing research and programming. I want you to do a research report of the following:
 * Ways that abstract classes promotes software reusability, decreases time programming, and helps reduce the number of errors in software.
 * Research the using abstract classes and interfaces.
 * I would like for you to be creative and come up with your own example of abstract classes and interfaces in Java by writing your own abstract class and interface, demonstrating the abstract class and interface in a test program (let your imagination and creativity go wild!)
 * To be submitted:
 */

interface AnimalCharacteristics {
    void animalQualities();
}

abstract class Animal implements AnimalCharacteristics {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void animalSound();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void animalSound() {
        System.out.println("The dog " + getName() + " gives a big woof woof since he is a dog");
    }

    @Override
    public void animalQualities() {
        System.out.println("The dog " + getName() + " has four legs, fur, long tongue and is known as man's best friend.");
    }
}

public class LABABSTRACTCLASSES18B {
    public static void main(String[] args) {
        System.out.println("This project will work to demonstrate the utility value of abstract classes within the respect of the following:!");
        System.out.println("Abstract data classes assure consistency of method implementation, assure code reusability, assure ease of debugging, easier code readability and much more are the benefits of abstract classes");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter what you want to call the dog!");
        String dogName = scanner.nextLine();

        // Make some class objects to test whether the abstract classes work or not
        Dog whiteyMighty = new Dog(dogName, 16);

        // Call the various methods along with the data types for the names of the animals and the animal qualities
        System.out.println(whiteyMighty.getName() + " is " + whiteyMighty.getAge() + " years");
        whiteyMighty.animalSound();
        whiteyMighty.animalQualities();
    }
}
