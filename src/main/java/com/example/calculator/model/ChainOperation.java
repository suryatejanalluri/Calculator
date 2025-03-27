package com.example.calculator.model;

/**
 * Represents a single operation in a chain of calculations.
 * Used for sequential calculations where the result of one operation
 * becomes the input for the next operation.
 */
public class ChainOperation {
    private Operation operation;
    private double number;

    /**
     * Gets the operation type.
     *
     * @return the operation to perform
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Sets the operation type.
     *
     * @param operation the operation to set
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    /**
     * Gets the operand number.
     *
     * @return the number to use in the operation
     */
    public double getNumber() {
        return number;
    }

    /**
     * Sets the operand number.
     *
     * @param number the number to set
     */
    public void setNumber(double number) {
        this.number = number;
    }
}