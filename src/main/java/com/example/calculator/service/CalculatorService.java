package com.example.calculator.service;

import com.example.calculator.model.Operation;
import com.example.calculator.model.ChainOperation;
import com.example.calculator.operations.MathOperation;
import com.example.calculator.exceptions.*;
import com.example.calculator.exceptions.UnsupportedOperationException;

import org.springframework.stereotype.Service;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class for handling calculator operations.
 * Provides methods for single calculations and chained operations.
 */
@Service
public class CalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);
    private final Map<Operation, MathOperation> operations;
    
    /**
     * Creates a new calculator service with injected operations.
     * 
     * @param operationList list of available mathematical operations
     */
    public CalculatorService(List<MathOperation> operationList) {
        logger.debug("Initializing CalculatorService with {} operations", operationList.size());
        Map<Operation, MathOperation> ops = new HashMap<>();
        for (MathOperation op : operationList) {
            ops.put(op.getOperation(), op);
            logger.trace("Registered operation: {}", op.getOperation());
        }
        this.operations = Collections.unmodifiableMap(ops);
    }

    /**
     * Performs a single calculation.
     *
     * @param op the operation to perform
     * @param num1 first operand
     * @param num2 second operand
     * @return result of the calculation
     * @throws CalculatorException if operation is not supported
     */
    public Number calculate(Operation op, Number num1, Number num2) {
        logger.info("Performing calculation: {} {} {}", num1, op, num2);
             // Convert to BigDecimal for maximum precision
          
        
        // Validate inputs
        if (op == null) {
            logger.error("Operation cannot be null");
            throw new InvalidInputException("Operation cannot be null");
        }
        if (num1 == null || num2 == null) {
            logger.error("Operands cannot be null");
            throw new InvalidInputException("Operands cannot be null");
        }
          
        if (!operations.containsKey(op)) {
            logger.error("Unsupported operation: {}", op);
            throw new UnsupportedOperationException(op);
        }

        try {
            Number result = operations.get(op).apply(num1, num2);
            logger.debug("Calculation result: {}", result);
            return result;
        } catch (DivideByZeroException e) {
            logger.error("Division by zero error: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Calculation error: {}", e.getMessage(), e);
            throw new RuntimeException("Error performing calculation: " + e);
        }
    }


    /**
     * Performs a chain of calculations.
     *
     * @param initial starting value
     * @param chainOperations list of operations to perform in sequence
     * @return final result after all operations
     * @throws CalculatorException if chain is empty or contains invalid operations
     */
    public Number chainCalculate(Number initial, List<ChainOperation> chainOperations) {
        logger.info("Starting chain calculation with initial value: {}", initial);
        if (chainOperations == null || chainOperations.isEmpty()) {
            logger.error("Empty chain operations list");
            throw new ChainOperationException("Chain operations list cannot be empty");
        }

        Number result = initial;
        for (ChainOperation op : chainOperations) {
            if (op == null || op.getOperation() == null) {
                logger.error("Invalid chain operation encountered");
                throw new ChainOperationException("Invalid chain operation");
            }
            logger.debug("Applying operation: {} with number: {}", op.getOperation(), op.getNumber());
            result = calculate(op.getOperation(), result, op.getNumber());
        }
        logger.info("Chain calculation completed. Final result: {}", result);
        return result;
    }
}