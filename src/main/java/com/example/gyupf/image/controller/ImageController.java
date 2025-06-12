package com.example.gyupf.image.controller;

import com.example.gyupf.image.dto.ImageDto;
import com.example.gyupf.image.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{propertyNum}")
    public List<ImageDto> getByProperty(@PathVariable("propertyNum") Long propertyNum) {
        return imageService.getImagesByProperty(propertyNum);
    }

    @PostMapping
    public void create(@RequestBody ImageDto dto) {
        imageService.addImage(dto);
    }

    @DeleteMapping("/{imageNum}")
    public void delete(@PathVariable Long imageNum) {
        imageService.deleteImage(imageNum);
    }
}
