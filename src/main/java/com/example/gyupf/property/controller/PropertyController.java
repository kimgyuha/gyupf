package com.example.gyupf.property.controller;

import com.example.gyupf.property.dto.PagedPropertyResponse;
import com.example.gyupf.property.dto.PropertyDetailDto;
import com.example.gyupf.property.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //매물 조회(+조건)
    @GetMapping("/search")
    public PagedPropertyResponse searchProperties(
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) String dealType,
            @RequestParam(required = false) String amountRange,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String dealStatus,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return propertyService.getPropertiesWithCondition(page, size, propertyType, dealType, amountRange, district, dealStatus);
    }

    // 단일 매물 상세 조회
    @GetMapping("/search/{id}")
    public PropertyDetailDto detail(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    // 구별 개수 조회
    @GetMapping("/districtCount")
    public int getCountByDistrict(@RequestParam String district) {
        return propertyService.getPropertyCountByDistrict(district);
    }

    // 매물 등록
    @PostMapping("/register")
    public ResponseEntity<String> registerProperty(
            @RequestPart("dto") PropertyDetailDto dto,
            @RequestPart("images") List<MultipartFile> images) throws IOException {

        propertyService.register(dto, images);
        return ResponseEntity.ok("등록 완료");
    }

    // 매물 수정
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody PropertyDetailDto dto) {
        dto.setPropertyNum(id);
        propertyService.updateProperty(dto);
    }

    // 매물 삭제
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }
}