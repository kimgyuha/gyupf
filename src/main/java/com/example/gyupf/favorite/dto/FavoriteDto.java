package com.example.gyupf.favorite.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FavoriteDto {
    private Long favoriteNum;
    private Long customerNum;
    private Long propertyNum;
    private LocalDateTime favoriteDt;
    private Boolean isFavorite;
}
