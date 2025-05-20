package com.example.gyupf.image.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ImageDto {
    private Long propertyNum;
    private String imageUrl;
    private Long imageNum;
    private Integer imageSeq;
    private LocalDateTime registedDt;
}