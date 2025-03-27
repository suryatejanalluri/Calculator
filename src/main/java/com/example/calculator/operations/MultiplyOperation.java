package com.example.calculator.operations;

import com.example.calculator.model.Operation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Multiplication operation implementation.
 * This class handles the multiplication of two numbers in the calculator.
 */
@Component
public class MultiplyOperation implements MathOperation {
    private static final Logger logger = LoggerFactory.getLogger(MultiplyOperation.class);

    /**
     * Performs multiplication of two numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the product of a and b
     */
    @Override
    public Number apply(Number a, Number b) {
        logger.debug("Performing multiplication: {} * {}", a, b);
        BigDecimal first = toBigDecimal(a);
        BigDecimal second = toBigDecimal(b);
        BigDecimal result = first.multiply(second);
        logger.trace("Multiplication result: {}", result);
        return result;
    }

    /**
     * Gets the operation type (MULTIPLY).
     *
     * @return Operation.MULTIPLY enum value
     */
    @Override
    public Operation getOperation() {
        logger.trace("Returning operation type: {}", Operation.MULTIPLY);
        return Operation.MULTIPLY;
    }
}