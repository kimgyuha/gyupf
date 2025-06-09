package com.example.gyupf.property.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.gyupf.common.dto.PagedPropertyResponse;
import com.example.gyupf.image.mapper.ImageMapper;
import com.example.gyupf.property.dto.PropertyDetailDto;
import com.example.gyupf.property.dto.PropertyListDto;
import com.example.gyupf.property.mapper.PropertyMapper;

@Service
public class PropertyService {

    private final PropertyMapper propertyMapper;

    @Value("${upload.path}")
    private String uploadPath;

    public PropertyService(PropertyMapper propertyMapper, ImageMapper imageMapper) {
        this.propertyMapper = propertyMapper;
    }

    //조건 설정한 매물리스트 카운트 & 조건 설정 & 정보 조회
    public PagedPropertyResponse getPropertiesWithCondition(
            int page,
            int size,
            Long propertyNum,
            String propertyType,
            String dealType,
            String amountRange,
            String district,
            String dealStatus
           ) {

        int offset = (page - 1) * size;

        Integer minAmount = null;
        Integer maxAmount = null;

        if (amountRange != null && !amountRange.isEmpty()) {
            try {
                if (amountRange.contains("+")) {
                    // 10억 이상: 예) "100000+"
                    minAmount = Integer.parseInt(amountRange.replace("+", "")) * 10000;
                    maxAmount = null; // 상한 없음
                } else if (amountRange.contains("-")) {
                    String[] parts = amountRange.split("-");
                    minAmount = Integer.parseInt(parts[0]) * 10000;
                    maxAmount = Integer.parseInt(parts[1]) * 10000;
                }
            } catch (NumberFormatException e) {
                System.out.println("금액 파싱 실패: " + amountRange);
            }
        }

        Map<String, Object> params = new HashMap<>();
        params.put("propertyNum", propertyNum);
        params.put("propertyType", propertyType);
        params.put("dealType", dealType);
        params.put("minAmount", minAmount);
        params.put("maxAmount", maxAmount);
        params.put("district", district);
        params.put("dealStatus", dealStatus);
        params.put("pageSize", size);
        params.put("offset", offset);

        List<PropertyListDto> list = propertyMapper.selectPropertyListWithConditionPaged(params);
        int totalCount = propertyMapper.countPropertiesWithCondition(params);

        int totalPages = (int) Math.ceil((double) totalCount / size);
        boolean hasNext = page < totalPages;

        PagedPropertyResponse response = new PagedPropertyResponse();
        response.setProperties(list);
        response.setCurrentPage(page);
        response.setTotalPages(totalPages);
        response.setHasNext(hasNext);

        return response;
    }

    //구별 매물 개수
    public int getPropertyCountByDistrict(String district) {
        return propertyMapper.countSelectedDistrict(district);
    }

    //단일매물 조회
    public PropertyDetailDto getPropertyById(Long propertyNum) {
        return propertyMapper.selectById(propertyNum);
    }


}