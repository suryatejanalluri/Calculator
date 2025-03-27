package com.example.calculator.operations;

import com.example.calculator.model.Operation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Addition operation implementation.
 * This class handles the addition of two numbers in the calculator.
 */
@Component
public class AddOperation implements MathOperation {
    private static final Logger logger = LoggerFactory.getLogger(AddOperation.class);

    /**
     * Performs addition of two numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the sum of a and b
     */
    @Override
    public Number apply(Number a, Number b) {
        logger.debug("Performing addition: {} + {}", a, b);
        BigDecimal first = toBigDecimal(a);
        BigDecimal second = toBigDecimal(b);
        logger.trace("Addition result: {}", first.add(second));
        return first.add(second);
    }

    /**
     * Gets the operation type (ADD).
     *
     * @return Operation.ADD enum value
     */
    @Override
    public Operation getOperation() {
        logger.trace("Returning operation type: {}", Operation.ADD);
        return Operation.ADD;
    }
}