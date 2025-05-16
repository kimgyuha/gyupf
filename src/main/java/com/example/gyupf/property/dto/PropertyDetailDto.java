package com.example.gyupf.property.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PropertyDetailDto {
    private Long propertyNum;
    private String imageUrl;
    private String address;
    private String propertyType;
    private String dealType;
    private BigDecimal amount;
    private Integer maintenanceCost;
    private Boolean elevatorExists;
    private Integer parkingAvailableNum;
    private String options;
    private BigDecimal landArea;
    private String landUse;
    private String landName;
    private String landInfEtc1;
    private String landInfEtc2;
    private BigDecimal buildingArea;
    private BigDecimal buildingFloorArea;
    private Integer buildingGroundFloor;
    private Integer buildingBasementFloor;
    private LocalDate buildingCompletionDt;
    private String buildingInfEtc1;
    private String buildingInfEtc2;
    private String agentInf;
    private String extraField1;
    private String extraField2;
    private String extraField3;
    private String extraField4;
    private String details;
    private String notes;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;
    private String dealStatus;
    private String district;

}