package com.example.gyupf.admin.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.gyupf.admin.service.AdminPropertyService;
import com.example.gyupf.common.dto.PagedPropertyResponse;
import com.example.gyupf.property.dto.PropertyDetailDto;
import com.example.gyupf.property.dto.PropertyListDto;

@RestController
@RequestMapping("/admin")
public class AdminPropertyController {

    private final AdminPropertyService propertyService;

    public AdminPropertyController(AdminPropertyService propertyService) {
        this.propertyService = propertyService;
    }
    
    // 전체 리스트 페이징
    @GetMapping("/list")
    public PagedPropertyResponse getPagedList(
    	    @RequestParam(name = "page", defaultValue = "1") int page,
    	    @RequestParam(name = "size", defaultValue = "5") int size) {
        return propertyService.getAdminPagedList(page, size);
    }

    // 매물 번호로 조회
    @GetMapping("/find/{propertyNum}")
    public PropertyListDto findByPropertyNum(@PathVariable("propertyNum") Long propertyNum) {
        return propertyService.findByPropertyNum(propertyNum);
    }

    // 매물 상세 페이지
    @GetMapping("/property/{id}")
    public ResponseEntity<PropertyDetailDto> getProperty(@PathVariable("id") Long id) {
        PropertyDetailDto dto = propertyService.getByPropertyNum(id);
        return ResponseEntity.ok(dto);
    }
    // 매물 등록
    @PostMapping("/register")
    public ResponseEntity<String> registerProperty(
            @RequestPart("dto") PropertyDetailDto dto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {

        propertyService.register(dto, images);
        return ResponseEntity.ok("매물 등록 완료");
    }

    // 매물 수정
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateProperty(
            @PathVariable("id") Long id,
            @RequestPart("dto") PropertyDetailDto dto,
            @RequestPart(value = "images", required = false) List<MultipartFile> newImages) throws IOException {

        dto.setPropertyNum(id);
        propertyService.updateProperty(dto, newImages);
        return ResponseEntity.ok("매물 수정 완료");
    }
    // 매물 삭제
    @DeleteMapping("/delete/{propertyNum}")
    public ResponseEntity<String> deleteProperty(@PathVariable("propertyNum") Long propertyNum) {
        propertyService.deleteProperty(propertyNum);
        return ResponseEntity.ok("매물 삭제 완료");
    }

}
