package com.example.calculator.exceptions;

/**
 * Base exception class for calculator-specific errors.
 * Provides a hierarchy for different types of calculation errors.
 */
public abstract class CalculatorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new calculator exception with the specified message.
     *
     * @param message the detail message
     */
    protected CalculatorException(String message) {
        super(message);
    }

    /**
     * Constructs a new calculator exception with the specified message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    protected CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }
}