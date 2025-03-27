package com.example.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests for the main Calculator Application class.
 * Verifies application context loading and main method execution.
 */
@SpringBootTest
class CalculatorApplicationTest {

    @Test
    void contextLoads() {
        // Test that Spring context loads successfully
        CalculatorApplication.main(new String[]{});
    }

    @Test
    void mainMethodWithArgs() {
        // Test main method with command line arguments
        CalculatorApplication.main(new String[]{"--server.port=8081"});
    }
}