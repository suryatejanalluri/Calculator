package com.example.calculator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.calculator.model.Operation;

class CalculatorExceptionTest {
    
    @Test
    void testDivisionByZeroException() {
        DivideByZeroException exception = new DivideByZeroException();
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    void testUnsupportedOperationException() {
        UnsupportedOperationException exception = 
            new UnsupportedOperationException(Operation.ADD);
        assertTrue(exception.getMessage().contains("ADD"));
    }

    @Test
    void testInvalidInputException() {
        InvalidInputException exception = 
            new InvalidInputException("Negative numbers not allowed");
        assertTrue(exception.getMessage().contains("Invalid input"));
    }

    @Test
    void testExceptionWithCause() {
        Throwable cause = new ArithmeticException("Original error");
        DivideByZeroException exception = 
            new DivideByZeroException("Custom message", cause);
        
        assertEquals("Custom message", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}