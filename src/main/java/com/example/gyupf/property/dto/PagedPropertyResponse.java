package com.example.gyupf.property.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedPropertyResponse {
    private List<PropertyListDto> properties;
    private int currentPage;
    private int totalPages;
    private boolean hasNext;
}
