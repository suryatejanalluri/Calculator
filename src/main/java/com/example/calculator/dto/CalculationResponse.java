package com.example.calculator.dto;

public class CalculationResponse {
    private Number result;

    public CalculationResponse() {
    }

    public CalculationResponse(Number result) {
        this.result = result;
    }

    public Number getResult() {
        return result;
    }

    public void setResult(Number result) {
        this.result = result;
    }
}
