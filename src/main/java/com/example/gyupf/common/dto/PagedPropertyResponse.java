package com.example.gyupf.common.dto;

import java.util.List;

import com.example.gyupf.property.dto.PropertyListDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedPropertyResponse {
    private List<PropertyListDto> properties;
    private int currentPage;
    private int totalPages;
    private boolean hasNext;
}
