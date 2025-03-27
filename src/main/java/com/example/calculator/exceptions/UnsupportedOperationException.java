package com.example.calculator.exceptions;

import com.example.calculator.model.Operation;

/**
 * Exception thrown when an unsupported operation is requested.
 * This occurs when attempting to use operations not implemented by the calculator.
 */
public class UnsupportedOperationException extends CalculatorException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UnsupportedOperationException for the specified operation.
     *
     * @param operation the unsupported Operation enum value
     */
    public UnsupportedOperationException(Operation operation) {
        super("Operation not supported: " + operation);
    }
}