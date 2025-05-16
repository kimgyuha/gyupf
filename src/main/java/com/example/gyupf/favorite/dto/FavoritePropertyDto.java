package com.example.gyupf.favorite.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class FavoritePropertyDto {
    private Long favoriteNum;
    private Long customerNum;
    private Long propertyNum;
    private LocalDateTime favoriteDt;
    private Boolean isFavorite;

    private String imageUrl;
    private String address;
    private String propertyType;
    private String dealType;
    private BigDecimal amount;
    private String details;
    private BigDecimal landArea;
    private BigDecimal buildingArea;
    private LocalDateTime createdDt;
}
