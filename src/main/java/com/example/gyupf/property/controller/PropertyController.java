package com.example.gyupf.property.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gyupf.common.dto.PagedPropertyResponse;
import com.example.gyupf.property.dto.PropertyDetailDto;
import com.example.gyupf.property.service.PropertyService;


@RestController
@RequestMapping("/")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //매물 조회(+조건)S
    @GetMapping("/") 
    public PagedPropertyResponse searchProperties(
    		@RequestParam(name = "propertyNum", required = false) Long propertyNum,
    	    @RequestParam(name = "propertyType", required = false) String propertyType,
    	    @RequestParam(name = "dealType", required = false) String dealType,
    	    @RequestParam(name = "amountRange", required = false) String amountRange,
    	    @RequestParam(name = "district", required = false) String district,
    	    @RequestParam(name = "dealStatus", required = false) String dealStatus,
    	    @RequestParam(name = "page", defaultValue = "1") int page,
    	    @RequestParam(name = "size", defaultValue = "10") int size
    	) {

        return propertyService.getPropertiesWithCondition(page, size, propertyNum, propertyType, dealType, amountRange, district, dealStatus);
    }

    // 단일 매물 상세 조회
    @GetMapping("/{id}")
    public PropertyDetailDto detail(@PathVariable("id") Long id) {
    	
        return propertyService.getPropertyById(id);
    }

    // 구별 개수 조회
    @GetMapping("/districtCount")
    public int getCountByDistrict(@RequestParam("district") String district) {
        return propertyService.getPropertyCountByDistrict(district);
    }
}