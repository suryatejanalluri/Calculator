package com.example.calculator.exceptions;

/**
 * Exception thrown when a division by zero is attempted.
 * This is a runtime exception that indicates an invalid mathematical operation.
 */
public class DivideByZeroException extends CalculatorException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DivideByZeroException with default message.
     */
    public DivideByZeroException() {
        super("Division by zero is not allowed");
    }

    /**
     * Constructs a new DivideByZeroException with the specified message.
     *
     * @param message the detail message
     */
    public DivideByZeroException(String message) {
        super(message);
    }

    /**
     * Constructs a new DivideByZeroException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause the cause of this exception
     */
    public DivideByZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}