package com.example.gyupf.image.service;

import com.example.gyupf.image.dto.ImageDto;
import com.example.gyupf.image.mapper.ImageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageMapper imageMapper;

    public ImageService(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    public List<ImageDto> getImagesByProperty(Long propertyNum) {
        return imageMapper.selectByPropertyNum(propertyNum);
    }

    public void addImage(ImageDto dto) {
        imageMapper.insert(dto);
    }

    public void deleteImage(Long imageNum) {
        imageMapper.delete(imageNum);
    }
}
