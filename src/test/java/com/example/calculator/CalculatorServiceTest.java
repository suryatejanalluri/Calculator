package com.example.calculator;

import com.example.calculator.model.Operation;
import com.example.calculator.operations.AddOperation;
import com.example.calculator.model.ChainOperation;
import com.example.calculator.service.CalculatorService;
import com.example.calculator.exceptions.CalculatorException;
import com.example.calculator.exceptions.InvalidInputException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the Calculator Service.
 * Tests basic operations and chained calculations.
 */
@SpringBootTest
public class CalculatorServiceTest {
    @Autowired
    private CalculatorService calculatorService;

    /**
     * Tests all basic arithmetic operations.
     * Verifies addition, subtraction, multiplication and division.
     */
    @Test
    void testBasicOperations() {
        assertEquals(5, calculatorService.calculate(Operation.ADD, 2, 3).intValue());
        assertEquals(1, calculatorService.calculate(Operation.SUBTRACT, 3, 2).intValue());
        assertEquals(6, calculatorService.calculate(Operation.MULTIPLY, 2, 3).intValue());
        assertEquals(2, calculatorService.calculate(Operation.DIVIDE, 6, 3).intValue());
        assertEquals(2.5, calculatorService.calculate(Operation.DIVIDE, 6, 2.4).doubleValue());
        assertEquals(5.0, calculatorService.calculate(Operation.ADD, 2.0, 3.0).doubleValue(), 0.001);
        assertEquals(-1.0, calculatorService.calculate(Operation.SUBTRACT, 2.0, 3.0).doubleValue(), 0.001);
        assertEquals(6.0, calculatorService.calculate(Operation.MULTIPLY, 2.0, 3.0).doubleValue(), 0.001);
        assertEquals(2.0, calculatorService.calculate(Operation.DIVIDE, 6.0, 3.0).doubleValue(), 0.001);
   }

    @Test
    void testDifferentNumberTypes() {
        AddOperation operation = new AddOperation();
        
        // Test integers
        assertEquals(new BigDecimal("5"), operation.apply(2, 3));
        
        // Test doubles
        assertEquals(new BigDecimal("5.5"), operation.apply(2.5, 3.0));
        
        // Test BigDecimals
        assertEquals(
            new BigDecimal("5.5"),
            operation.apply(new BigDecimal("2.5"), new BigDecimal("3.0"))
        );
        
        // Test mixed types
        assertEquals(new BigDecimal("5.5"), operation.apply(2, 3.5));
    }

    /**
     * Tests chained operations.
     * Verifies that operations are applied in sequence correctly.
     */
    @Test
    void testChaining() {
        List<ChainOperation> operations = new ArrayList<>();
        
        ChainOperation add = new ChainOperation();
        add.setOperation(Operation.ADD);
        add.setNumber(3);
        
        ChainOperation multiply = new ChainOperation();
        multiply.setOperation(Operation.MULTIPLY);
        multiply.setNumber(2);
        
        operations.add(add);
        operations.add(multiply);
        
        assertEquals(16.0, calculatorService.chainCalculate(5, operations).doubleValue(), 0.001);
    }

    /**
     * Tests error handling for invalid chain input.
     * Verifies that empty chain operations throw exception.
     */
    @Test
    void testInvalidChainingInput() {
        assertThrows(CalculatorException.class, () -> 
            calculatorService.chainCalculate(5, new ArrayList<>()));
    }

    /**
     * Tests error handling for division by zero.
     * Verifies that division by zero throws exception.
     */
    @Test
    void testDivisionByZero() {
        ChainOperation divide = new ChainOperation();
        divide.setOperation(Operation.DIVIDE);
        divide.setNumber(0);
        
        List<ChainOperation> operations = Arrays.asList(divide);
        
        assertThrows(CalculatorException.class, () -> 
            calculatorService.chainCalculate(5, operations));
    }

    /**
     * Tests error handling for unsupported operations.
     * Verifies that null operations throw exception.
     */
    @Test
    void testUnsupportedOperation() {
        assertThrows(InvalidInputException.class, () ->
            calculatorService.calculate(null, 1, 1));
    }
}