package com.example.asset.dto;

import com.example.asset.entity.Criticality;
import java.time.LocalDateTime;

public class AssetResponse {
    private Long id;
    private String name;
    private String type;
    private String location;
    private Criticality criticality;
    private Double healthScore;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public AssetResponse() {}

    public AssetResponse(Long id, String name, String type, String location, Criticality criticality,
                         Double healthScore, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.criticality = criticality;
        this.healthScore = healthScore;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public Criticality getCriticality() { return criticality; }
    public Double getHealthScore() { return healthScore; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public LocalDateTime getUpdatedDate() { return updatedDate; }
}