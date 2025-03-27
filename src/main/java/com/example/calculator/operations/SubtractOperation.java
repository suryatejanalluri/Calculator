package com.example.calculator.operations;

import com.example.calculator.model.Operation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Subtraction operation implementation.
 * This class handles the subtraction of two numbers in the calculator.
 */
@Component
public class SubtractOperation implements MathOperation {
    private static final Logger logger = LoggerFactory.getLogger(SubtractOperation.class);

    /**
     * Performs subtraction of two numbers.
     *
     * @param a the number to subtract from
     * @param b the number to subtract
     * @return the difference (a - b)
     */
    @Override
    public Number apply(Number a, Number b) {
        logger.debug("Performing subtraction: {} - {}", a, b);
        BigDecimal minuend = toBigDecimal(a);
        BigDecimal subtrahend = toBigDecimal(b);
        BigDecimal result = minuend.subtract(subtrahend);
        logger.trace("Subtraction result: {}", result);
        return result;
    }

    /**
     * Gets the operation type (SUBTRACT).
     *
     * @return Operation.SUBTRACT enum value
     */
    @Override
    public Operation getOperation() {
        logger.trace("Returning operation type: {}", Operation.SUBTRACT);
        return Operation.SUBTRACT;
    }
}