package com.example.gyupf.property.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PropertyListDto {
    private Long propertyNum;
    private String imageUrl;
    private String address;
    private String propertyType;
    private String dealType;
    private BigDecimal amount;
    private BigDecimal landArea;
    private BigDecimal buildingArea;
    private BigDecimal buildingFloorArea;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;
    private String dealStatus;
    private String district;
}