package com.example.calculator.exceptions;


/**
 * Exception thrown when invalid input is provided to the calculator.
 * This includes non-numeric inputs or values outside acceptable ranges.
 */
public class InvalidInputException extends CalculatorException {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a new InvalidInputException with the specified message.
     *
     * @param message the detail message
     */
    public InvalidInputException(String message) {
        super("Invalid input: " + message);
    }
}