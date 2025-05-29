package com.example.gyupf.admin.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminPropertyListDto {
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
