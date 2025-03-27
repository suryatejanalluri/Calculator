package com.example.calculator.model;

/**
 * Request model for single calculation operations.
 * This class represents the JSON payload for POST requests to the calculator API.
 * 
 * Example JSON:
 * {
 *   "operation": "ADD",
 *   "num1": 5,
 *   "num2": 3
 * }
 */
public class CalculationRequest {
    /**
     * The operation to perform (ADD, SUBTRACT, MULTIPLY, DIVIDE).
     * Must not be null.
     */
    private Operation operation;

    /**
     * The first operand for the calculation.
     * Supports various number formats (Integer, Double, BigDecimal).
     */
    private Number num1;

    /**
     * The second operand for the calculation.
     * Supports various number formats (Integer, Double, BigDecimal).
     * For DIVIDE operations, must not be zero.
     */
    private Number num2;

    // Getters
    public Operation getOperation() {
        return operation;
    }

    public Number getNum1() {
        return num1;
    }

    public Number getNum2() {
        return num2;
    }

    // Setters
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setNum1(Number num1) {
        this.num1 = num1;
    }

    public void setNum2(Number num2) {
        this.num2 = num2;
    }
}