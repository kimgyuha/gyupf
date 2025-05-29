package com.example.gyupf.admin.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.gyupf.admin.dto.AdminPropertyDetailDto;
import com.example.gyupf.admin.mapper.AdminPropertyMapper;
import com.example.gyupf.common.dto.PagedPropertyResponse;
import com.example.gyupf.image.dto.ImageDto;
import com.example.gyupf.image.mapper.ImageMapper;
import com.example.gyupf.property.dto.PropertyDetailDto;
import com.example.gyupf.property.dto.PropertyListDto;

@Service
public class AdminPropertyService {

    private final AdminPropertyMapper adminPropertyMapper;
    private final ImageMapper imageMapper;

    @Value("${upload.path}")
    private String uploadPath;

    public AdminPropertyService(AdminPropertyMapper adminPropertyMapper, ImageMapper imageMapper) {
        this.adminPropertyMapper = adminPropertyMapper;
        this.imageMapper = imageMapper;
    }

    public PagedPropertyResponse getAdminPagedList(int page, int size) {
        int offset = (page - 1) * size;
        // 매물 리스트 가져오기
        List<PropertyListDto> properties = adminPropertyMapper.selectPagedList(offset, size);
        int totalCount = adminPropertyMapper.countPagedList();
        
        int totalPages = (int) Math.ceil((double) totalCount / size);
        boolean hasNext = page < totalPages;

        // 생성자 대신 setter 방식으로 값 세팅
        PagedPropertyResponse response = new PagedPropertyResponse();
        response.setProperties(properties);
        response.setCurrentPage(page);
        response.setTotalPages(totalPages);
        response.setHasNext(hasNext);
        
        return response;
    }

    public PropertyListDto findByPropertyNum(Long propertyNum) {
        return adminPropertyMapper.findById(propertyNum);
    }

    // 매물 번호로 단건 조회
    public PropertyDetailDto getByPropertyNum(Long propertyNum) {
        return adminPropertyMapper.selectById(propertyNum);
    }
    // 매물 저장
    @Transactional
    public void register(PropertyDetailDto dto, List<MultipartFile> images) throws IOException {
        adminPropertyMapper.insert(dto);
        Long propertyNum = dto.getPropertyNum();

        String propertyDirPath = uploadPath + File.separator + propertyNum;
        File propertyDir = new File(propertyDirPath);
        if (!propertyDir.exists()) {
            propertyDir.mkdirs();
        }

        int seq = 0;
        if (images != null) {
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    String filename = seq + "_" + file.getOriginalFilename();
                    String fullPath = propertyDirPath + File.separator + filename;

                    File dest = new File(fullPath);
                    file.transferTo(dest);

                    ImageDto imageDto = new ImageDto();
                    imageDto.setPropertyNum(propertyNum);
                    imageDto.setImageUrl("http://114.207.245.30/images/" + propertyNum + "/" + filename);
                    imageDto.setImageSeq(seq++);
                    imageMapper.insert(imageDto);
                }
            }

            String firstImagePath = "http://114.207.245.30/images/" + propertyNum + "/" + "0_" + images.get(0).getOriginalFilename();
            dto.setImageUrl(firstImagePath);
            adminPropertyMapper.updateImageUrl(dto);
        }
    }

    // 매물 수정
    @Transactional
	public void updateProperty(PropertyDetailDto dto, List<MultipartFile> newImages) throws IOException {
        adminPropertyMapper.update(dto);
        Long propertyNum = dto.getPropertyNum();

        if (newImages != null && !newImages.isEmpty()) {
            imageMapper.delete(propertyNum);

            String propertyDirPath = uploadPath + File.separator + propertyNum;
            File propertyDir = new File(propertyDirPath);
            if (!propertyDir.exists()) {
                propertyDir.mkdirs();
            }

            int seq = 0;
            for (MultipartFile file : newImages) {
                if (!file.isEmpty()) {
                    String filename = seq + "_" + file.getOriginalFilename();
                    String fullPath = propertyDirPath + File.separator + filename;

                    File dest = new File(fullPath);
                    file.transferTo(dest);

                    ImageDto imageDto = new ImageDto();
                    imageDto.setPropertyNum(propertyNum);
                    imageDto.setImageUrl("http://114.207.245.30/images/" + propertyNum + "/" + filename);
                    imageDto.setImageSeq(seq++);
                    imageMapper.insert(imageDto);
                }
            }

            String firstImagePath = "http://114.207.245.30/images/" + propertyNum + "/" + "0_" + newImages.get(0).getOriginalFilename();
            dto.setImageUrl(firstImagePath);
            adminPropertyMapper.updateImageUrl(dto);
        }
    }
    
}
