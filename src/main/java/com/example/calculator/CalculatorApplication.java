package com.example.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Calculator Application.
 * This Spring Boot application provides a REST API for performing
 * mathematical calculations with support for basic arithmetic operations
 * and chain calculations.
 *
 * @see com.example.calculator.controller.CalculatorController
 * @see com.example.calculator.service.CalculatorService
 */
@SpringBootApplication
public class CalculatorApplication {
    /**
     * Main method that starts the calculator application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }
}