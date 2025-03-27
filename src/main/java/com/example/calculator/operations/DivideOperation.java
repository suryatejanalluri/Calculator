package com.example.calculator.operations;

import com.example.calculator.model.Operation;
import org.springframework.stereotype.Component;
import com.example.calculator.exceptions.DivideByZeroException;  // Add this import

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Division operation implementation.
 * This class handles the division of two numbers in the calculator.
 */
@Component
public class DivideOperation implements MathOperation {
    private static final Logger logger = LoggerFactory.getLogger(DivideOperation.class);
    private static final int DECIMAL_SCALE = 10;

    /**
     * Performs division of two numbers.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient (a / b)
     * @throws DivideByZeroException if the divisor is zero
     */
    @Override
    public Number apply(Number a, Number b) {
        logger.debug("Performing division: {} / {}", a, b);
        BigDecimal dividend = toBigDecimal(a);
        BigDecimal divisor = toBigDecimal(b);
        
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            logger.error("Division by zero attempted with dividend: {}", a);
            throw new DivideByZeroException("Division by zero is not allowed");
        }
        BigDecimal result = dividend.divide(divisor, DECIMAL_SCALE, RoundingMode.HALF_UP);;
        logger.trace("Division result: {}", result);
        return result;
    }

    /**
     * Gets the operation type (DIVIDE).
     *
     * @return Operation.DIVIDE enum value
     */
    @Override
    public Operation getOperation() {
        logger.trace("Returning operation type: {}", Operation.DIVIDE);
        return Operation.DIVIDE;
    }
}