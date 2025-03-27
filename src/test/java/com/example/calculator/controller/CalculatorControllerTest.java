package com.example.calculator.controller;

import com.example.calculator.config.SecurityConfig;
import com.example.calculator.model.Operation;
import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.ChainOperation;
import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
/**
 * Unit tests for the Calculator Controller.
 * Tests REST endpoints using MockMvc.
 */
@WebMvcTest(CalculatorController.class)
@Import(SecurityConfig.class)
public class CalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private ObjectMapper objectMapper;

    
    /**
     * Tests the basic calculation post method endpoint.
     */
    @Test
    @WithMockUser(roles = "USER")
    void testCalculatePost() throws Exception {
        CalculationRequest request = new CalculationRequest();
        request.setOperation(Operation.ADD);
        request.setNum1(2);
        request.setNum2(3);

        when(calculatorService.calculate(any(), any(), any())).thenReturn(5.0);

        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.0));
    }

    /**
     * Tests the chain calculation endpoint.
     */
    @Test
    @WithMockUser(roles = "USER")
    void testChainCalculate() throws Exception {
        ChainOperation op = new ChainOperation();
        op.setOperation(Operation.ADD);
        op.setNumber(3.0);

        when(calculatorService.chainCalculate(any(Number.class), any()))
            .thenReturn(new BigDecimal("8.0"));

        mockMvc.perform(post("/api/v1/calculator/chainCalculate")
                .param("initial", "5.0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Arrays.asList(op)))
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string("8.0"));
    }
}