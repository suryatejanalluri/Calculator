package com.example.calculator.exceptions;

/**
 * Exception thrown when there is an error in chain calculations.
 */
public class ChainOperationException extends CalculatorException {
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a new chain operation exception with the specified message.
     *
     * @param message the detail message
     */
    public ChainOperationException(String message) {
        super(message);
    }
}