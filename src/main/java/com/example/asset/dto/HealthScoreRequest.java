package com.example.asset.dto;

import jakarta.validation.constraints.NotNull;

public class HealthScoreRequest {

    @NotNull(message = "healthScore is required")
    private Double healthScore;

    public HealthScoreRequest() {}

    public Double getHealthScore() { return healthScore; }
    public void setHealthScore(Double healthScore) { this.healthScore = healthScore; }
}