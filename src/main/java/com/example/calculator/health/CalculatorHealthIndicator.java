package com.example.calculator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Health indicator for the Calculator service.
 * Implements Spring Boot Actuator's HealthIndicator to provide health status information.
 */
@Component
public class CalculatorHealthIndicator implements HealthIndicator {
    private static final String VERSION = "1.0";

    @Override
    public Health health() {
        return Health.up()
                    .withDetail("version", VERSION)
                    .withDetail("status", "OK")
                    .build();
    }
}