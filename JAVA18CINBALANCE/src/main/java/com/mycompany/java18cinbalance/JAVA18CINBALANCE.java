/* 

// F Drew
// Gonzales
// Paul Conrad 17C

// Objective of the Assignment:
// Write a Java application program to read in from the keyboard any expression that contains (), [], {}.
// The program should determine if the parentheses, square brackets, and curly braces are balanced or not.
// If the symbols are balanced, the program outputs true. If they are not balanced, the program outputs false.
// The program should utilize a custom stack class (you can use the stack class provided on GitHub), and you are not allowed to use the Stack class built into Java.
*/
package com.mycompany.java18cinbalance;


import java.util.Scanner;

public class JAVA18CINBALANCE {

   public static void main(String[] args) {
        System.out.println("Hello World!");

        Scanner userInput = new Scanner(System.in);
        boolean continueChecking = true;

        while (continueChecking) {
            System.out.println("Enter an expression to test if it is balanced (or 'exit' to quit):");
            String input = userInput.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                continueChecking = false;
            } else {
                boolean isBalanced = checkBalancedExpression(input);
                System.out.println(isBalanced);
            }
        }

        System.out.println("Goodbye!");
    }

    public static boolean checkBalancedExpression(String expression) {
        char[] brackets = expression.toCharArray();
        Stack stack = new Stack();

        for (char bracket : brackets) {
            if (isOpeningBracket(bracket)) {
                stack.push(bracket);
            } else if (isClosingBracket(bracket)) {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), bracket)) {
                    return false; // Unbalanced expression
                }
            }
        }

        return stack.isEmpty(); // If stack is empty, expression is balanced
    }

    public static boolean isOpeningBracket(char bracket) {
        return bracket == '(' || bracket == '[' || bracket == '{';
    }

    public static boolean isClosingBracket(char bracket) {
        return bracket == ')' || bracket == ']' || bracket == '}';
    }

    public static boolean isMatchingPair(char openingBracket, char closingBracket) {
        return (openingBracket == '(' && closingBracket == ')') ||
                (openingBracket == '[' && closingBracket == ']') ||
                (openingBracket == '{' && closingBracket == '}');
    }

    // Custom Stack implementation
    private static class Stack {
        private Node top;

        public void push(char data) {
            Node newNode = new Node(data);
            newNode.next = top;
            top = newNode;
        }

        public char pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }

            char data = top.data;
            top = top.next;
            return data;
        }

        public boolean isEmpty() {
            return top == null;
        }

        private class Node {
            private char data;
            private Node next;

            public Node(char data) {
                this.data = data;
            }
        }
    }
}