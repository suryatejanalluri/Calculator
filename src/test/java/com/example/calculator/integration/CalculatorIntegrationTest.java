package com.example.calculator.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for the Calculator API.
 * Tests end-to-end functionality including HTTP endpoints,
 * request/response handling, and service integration.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testEndToEndCalculation() throws Exception {
        String requestBody = "{\n" +
                "    \"operation\": \"ADD\",\n" +
                "    \"num1\": 2,\n" +
                "    \"num2\": 3\n" +
                "}";

        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\": 5}"));  // Example of expected response body
    }
    /*@Test
    void testEndToEndCalculation() throws Exception {
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .param("operation", "ADD")
                .param("num1", "2")
                .param("num2", "3"))
                .andExpect(status().isOk());
    }*/
}