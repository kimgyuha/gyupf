package com.example.gyupf.property.service;

import com.example.gyupf.image.dto.ImageDto;
import com.example.gyupf.image.mapper.ImageMapper;
import com.example.gyupf.property.dto.PagedPropertyResponse;
import com.example.gyupf.property.dto.PropertyDetailDto;
import com.example.gyupf.property.dto.PropertyListDto;
import com.example.gyupf.property.mapper.PropertyMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.UUID;

@Service
public class PropertyService {

    private final PropertyMapper propertyMapper;
    private final ImageMapper imageMapper;

    @Value("${upload.path}")
    private String uploadPath;

    public PropertyService(PropertyMapper propertyMapper, ImageMapper imageMapper) {
        this.propertyMapper = propertyMapper;
        this.imageMapper = imageMapper;
    }

    // 매물 저장
    @Transactional
    public void register(PropertyDetailDto dto, @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {
        // 매물 정보 저장
        propertyMapper.insert(dto);
        System.out.println("어드레스? : " + dto.getAddress());
        Long propertyNum = dto.getPropertyNum();
        System.out.println("매물번호? : " + propertyNum);
        // 매물 번호로 폴더 생성
        String propertyDirPath = uploadPath + File.separator + propertyNum;
        File propertyDir = new File(propertyDirPath);
        if (!propertyDir.exists()) {
            propertyDir.mkdirs(); // 디렉토리 생성
        }

        // 3. 이미지 저장 및 DB 기록
        int seq = 0;
        if (images != null) {
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    // 저장할 파일명: 0_원본이름.jpg, 1_원본이름.jpg ...
                    String filename = seq + "_" + file.getOriginalFilename();
                    String fullPath = propertyDirPath + File.separator + filename;

                    File dest = new File(fullPath);
                    file.transferTo(dest);

                    ImageDto imageDto = new ImageDto();
                    imageDto.setPropertyNum(propertyNum);
                    imageDto.setImageUrl("http://localhost:8080/images/" + propertyNum + "/" + filename);
                    imageDto.setImageSeq(seq++);

                    imageMapper.insert(imageDto);
                }
            }
            String firstImagePath = "http://localhost:8080/images/" + propertyNum + "/" + "0_" + images.get(0).getOriginalFilename();
            dto.setImageUrl(firstImagePath);
            propertyMapper.updateImageUrl(dto); // image_url만 업데이트하는 쿼리

        }
    }
    
    //조건 설정한 매물리스트 카운트 & 조건 설정 & 정보 조회
    public PagedPropertyResponse getPropertiesWithCondition(
            int page,
            int size,
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

    public void updateProperty(PropertyDetailDto dto) {
        propertyMapper.update(dto);
    }

    public void deleteProperty(Long propertyNum) {
        propertyMapper.delete(propertyNum);
    }

}