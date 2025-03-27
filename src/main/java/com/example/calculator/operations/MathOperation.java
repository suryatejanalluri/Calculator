package com.example.calculator.operations;

import java.math.BigDecimal;

import com.example.calculator.model.Operation;

/**
 * Interface for mathematical operations in the calculator.
 * Implementations of this interface should provide specific arithmetic operations.
 */
public interface MathOperation {
    /**
     * Applies the mathematical operation to two numbers.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the result of the operation
     */
    Number apply(Number a, Number b);

    /**
     * Gets the operation type this implementation handles.
     *
     * @return the Operation enum value
     */
    Operation getOperation();
        /**
     * Utility method to convert Number to BigDecimal
     *
     * @param number the number to convert
     * @return BigDecimal representation
     */
    default BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        return new BigDecimal(number.toString());
    }
}