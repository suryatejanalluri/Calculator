package com.example.calculator.health;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.health.Health;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Calculator Health Indicator.
 * Verifies health check functionality for the calculator service.
 */
class CalculatorHealthIndicatorTest {

    @Test
    void testHealthCheck() {
        CalculatorHealthIndicator healthIndicator = new CalculatorHealthIndicator();
        Health health = healthIndicator.health();
        
        assertNotNull(health, "Health should not be null");
        assertEquals(Status.UP, health.getStatus(), "Calculator service should be UP");
        assertTrue(health.getDetails().containsKey("version"), "Health should include version");
        assertNotNull(health.getDetails().get("version"), "Version should not be null");
    }

    @Test
    void testHealthDetails() {
        CalculatorHealthIndicator healthIndicator = new CalculatorHealthIndicator();
        Health health = healthIndicator.health();
        
        assertEquals("1.0", health.getDetails().get("version"), "Version should be 1.0");
        assertEquals("OK", health.getDetails().get("status"), "Status should be OK");
    }
}