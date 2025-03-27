package com.example.calculator.config;

import com.example.calculator.exceptions.CalculatorException;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Global exception handler for the calculator application.
 * Handles all exceptions and converts them to appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles calculator-specific exceptions.
     *
     * @param e the calculator exception
     * @return ResponseEntity with error message
     */
    @ExceptionHandler(CalculatorException.class)
    public ResponseEntity<String> handleCalculatorException(CalculatorException e) {
        logger.error("Calculator error: {}", e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * Handles all other exceptions.
     *
     * @param e the exception
     * @return ResponseEntity with error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        logger.error("Unexpected error: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError().body("An unexpected error occurred");
    }

    /**
     * Handles validation exceptions from request body and parameters.
     * Occurs when @Valid validation fails on controller methods.
     *
     * @param e the validation exception containing binding errors
     * @return ResponseEntity with validation error message and 400 status code
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        logger.error("Validation error: {}", e.getMessage());
        return ResponseEntity
            .badRequest()
            .body("Invalid request: " + e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * Handles JsonParseException caused by leading zeroes in numbers.
     *
     * @param e the JsonParseException
     * @return ResponseEntity with custom error message for leading zeroes
     */
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<String> handleJsonParseException(JsonParseException e) {
        // Check if the error message contains "Leading zeroes not allowed"
        logger.error("JsonParseException caught: {}", e.getMessage(), e);  // Log the exception to check if it's caught
        if (e.getMessage().contains("Leading zeroes not allowed")) {
            logger.error("Invalid numeric value: Leading zeroes not allowed in the JSON request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Invalid number format: Leading zeroes are not allowed.");
        }

        // Handle other JSON parsing errors
        logger.error("Invalid JSON format: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Invalid JSON format: " + e.getMessage());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("HttpMessageNotReadableException caught: {}", e.getMessage(), e);
        if (e.getMessage().contains("Leading zeroes not allowed")) {
            logger.error("Invalid numeric value: Leading zeroes not allowed in the JSON request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Invalid number format: Leading zeroes are not allowed.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Invalid request body or malformed JSON.");
    }
}
