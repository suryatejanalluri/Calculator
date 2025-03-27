package com.example.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller that handles the welcome page and root endpoint of the application.
 */
@RestController
@RequestMapping("/")
public class WelcomeController {
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    /**
     * Handles the root endpoint and returns welcome message.
     *
     * @return welcome message with API endpoints information
     */
    @GetMapping
    public String welcome() {
        logger.info("Accessing welcome page");
        return "Welcome to Calculator API! Available endpoints:\n" +
               "- GET /api/v1/calculator/calculate\n" +
               "- POST /api/v1/calculator/chainCalculate\n" +
               "- GET /swagger-ui/index.html (API Documentation)";
    }
}