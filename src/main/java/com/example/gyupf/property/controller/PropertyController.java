package com.example.gyupf.property.controller;

import com.example.gyupf.property.dto.PagedPropertyResponse;
import com.example.gyupf.property.dto.PropertyDetailDto;
import com.example.gyupf.property.service.PropertyService;
import com.example.gyupf.property.dto.PropertyListDto;
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

    // 매물 등록
    @PostMapping("/register")
    public ResponseEntity<String> registerProperty(

            @RequestPart("dto") PropertyDetailDto dto,
            @RequestPart("images") List<MultipartFile> images) throws IOException {

        propertyService.register(dto, images);
        return ResponseEntity.ok("등록 완료");
    }


    // 무한스크롤용 전체 리스트 조회
    @GetMapping("/all")
    public PagedPropertyResponse list(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        return propertyService.getPagedProperties(page, size);
    }

    // 단일 매물 상세 조회
    @GetMapping("/{id}")
    public PropertyDetailDto detail(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
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