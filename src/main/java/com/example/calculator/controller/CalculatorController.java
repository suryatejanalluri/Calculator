package com.example.calculator.controller;

import com.example.calculator.model.Operation;
import com.example.calculator.model.ChainOperation;
import com.example.calculator.service.CalculatorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.calculator.dto.CalculationResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.calculator.model.CalculationRequest;
/**
 * REST controller for calculator operations.
 * Provides endpoints for basic calculations and chain operations.
 */
@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
    private final CalculatorService calculatorService;

    /**
     * Creates a new calculator controller with the required service.
     *
     * @param calculatorService service to handle calculations
     */
    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        logger.debug("Initializing CalculatorController");
        this.calculatorService = calculatorService;
    }

        /**
     * POST endpoint for single calculations using JSON body.
     * Example: {"operation": "ADD", "num1": 5, "num2": 3}
     *
     * @param request calculation request containing operation and operands
     * @return result of the calculation
     */
    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculatePost(@RequestBody CalculationRequest request) {
        logger.info("Received POST calculation request: {}", request);
        Number result = calculatorService.calculate(
            request.getOperation(),
            request.getNum1(),
            request.getNum2()
        );
    CalculationResponse response = new CalculationResponse(result);

    // Return the response as ResponseEntity
    return ResponseEntity.ok(response);
    }


    /**
     * Endpoint for chain calculations.
     *
     * @param initial starting value
     * @param operations list of operations to perform
     * @return final result after all operations
     */
    @PostMapping("/chainCalculate")
    public Number chainCalculate(@RequestParam Number initial, 
                               @RequestBody List<ChainOperation> operations) {
        logger.info("Received chain calculation request with initial value: {}", initial);
        logger.debug("Chain operations: {}", operations);
        Number result = calculatorService.chainCalculate(initial, operations);
        logger.debug("Returning chain result: {}", result);
        return result;
    }
}