/*
 * Name: Drew Gonzales
 * Class: 18B
 * Professor: Conrad
 * 
 * Objective: This project is designed to create a simple calculator application using Swing in Java.
 * This application displays a calculator interface to the given user. The user can then input expressions to be evaluated,
 * which will be displayed on the calculator screen. It supports addition, subtraction, multiplication, and division operations.
I've also added sine, cosine, tangent along with the math library that allows me to compute different types of computations accordingly. Thus,
this makes this a much more robust calculator and allows for different types of scientific computation. However, it's to be noted that the functionality is fairly limited
and it doesn't do insane amounts of operations. Also, every evaluated expression and its result are stored in a file named "calculator_history.txt".  
Thus, the 3 primary elements that are used is file stream storage within the respect of creating memory of the computation and advanced string usage along with regex.
Regex allows us to read different patterns and manipulate them to creating meaningufl outputs that are useful for comprehensive and robust GUI systems. 
Concurrency, databases and object serialization are not the implicit focus off this given calculator and finally the theme is a scientific calculator. 
*/
package com.mycompany.javaobjectfinalproject;

// Standard Java libraries for GUI, event handling, file writing, math operations, and regex
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JAVAOBJECTFINALPROJECT {   
    // Main method for the application 
    public static void main(String[] args) {
        // The calculator GUI is created in event dispatch thread 
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true); // show the calculator gui 
        });
    }
}

// Calculator class which is a JFrame and listens to ActionEvents (button clicks)
class Calculator extends JFrame implements ActionListener {
    // TextField to display the entered numbers and results
    private JTextField textField;
    // StringBuilder to build up the mathematical expression as numbers and operators are entered
    private StringBuilder expression;

    // Constructor for the Calculator class
    public Calculator() {
        // Set the properties of the calculator window
        setTitle("Drew's Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(50, 50, 50)); // Dark theme

        // Create and configure the panel for the calculator's display
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        displayPanel.setBackground(new Color(50, 50, 50)); // Dark theme

        // create and configure calculator display 
        textField = new JTextField(20);
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(new Color(50, 50, 50)); // styliszed dark theme 
        textField.setForeground(new Color(255, 255, 255)); // White text

        // Add text fields to the display panel 
        displayPanel.add(textField, BorderLayout.NORTH);

        // Creates and configures the panel for the calculator's buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 5, 5, 5));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(new Color(50, 50, 50)); // Dark theme

        // Define the calculator's buttons, add them to the button panel
        String[] buttons = {"7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "pow", "1", "2", "3", "-", "sin", "0", ".", "=", "+", "cos", "tan"};
        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.addActionListener(this);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(new Color(70, 70, 70)); // Dark theme
            btn.setForeground(new Color(255, 255, 255)); // White text
            btn.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border around buttons
            buttonPanel.add(btn);
        }

        // Add the display and button panels
        add(displayPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        pack(); // Sizes the frame so all its contents are at or above their preferred sizes.

        // Initialize the expression StringBuilder/concept 2 used for string builder
        expression = new StringBuilder();
    }

    // method called when button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand(); // Get the action command from the event defined 

        // If equals is hit evaluate the expression otherwise append it
        if (action.equals("=")) {
            evaluateExpression();
        } else {
            expression.append(action);
            textField.setText(expression.toString());
        }
    }

 
    
    // Method to evaluate the expression and handle possible exceptions
    private void evaluateExpression() {
        String result = "";

        try {
            result = evaluate(expression.toString()); // Evaluate the expression
            saveExpressionToFile(expression.toString(), result); // Save the expression and the result to the file
            expression.setLength(0); // Clear the expression after evaluation
            textField.setText(result); // Display the result
        } catch (Exception e) {
            // Clear the expression and show "Error" on display if the expression is invalid
            expression.setLength(0);
            textField.setText("Error");
        }
    }

    // Method to evaluate a mathematical expressions and scientific notation
    private String evaluate(String expression) {
        Pattern pattern = Pattern.compile("([\\d.]+)\\s*([*/+-])\\s*([\\d.]+)");
        Pattern scientificPattern = Pattern.compile("([a-z]+)\\s*([\\d.]+)");
        Matcher matcher = pattern.matcher(expression);
        Matcher scientificMatcher = scientificPattern.matcher(expression);

        if (scientificMatcher.matches()) {  
            // Evaluate scientific expressions 
            String operator = scientificMatcher.group(1);
            double operand = Double.parseDouble(scientificMatcher.group(2));
            double result = 0.0;

            switch (operator) {
                case "sqrt":
                    result = Math.sqrt(operand);
                    break;
                case "pow":
                    result = Math.pow(operand, 2);
                    break;
                case "sin":
                    result = Math.sin(Math.toRadians(operand));
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(operand));
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(operand));
                    break;
            }

            return String.valueOf(result);
        }

        if (matcher.matches()) {
            // Evaluate simple expressions 
            double operand1 = Double.parseDouble(matcher.group(1));
            String operator = matcher.group(2);
            double operand2 = Double.parseDouble(matcher.group(3));
            double result = 0.0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
            }

            return String.valueOf(result);
        }

        return ""; // Return an empty string if the expression does not match the pattern
    }

    // Method to save the evaluated expression and its result to a file
    private void saveExpressionToFile(String expression, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("calculator_history.txt", true))) {
            writer.write("Expression: " + expression);
            writer.newLine();
            writer.write("Result: " + result);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            // If there's a problem writing to the file, show a message on the console (this can be logged too)
            System.out.println("Error saving expression to file.");
        }
    }
}
 
