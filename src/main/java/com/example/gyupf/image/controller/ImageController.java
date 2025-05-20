package com.example.gyupf.image.controller;

import com.example.gyupf.favorite.dto.FavoriteDto;

import com.example.gyupf.image.dto.ImageDto;
import com.example.gyupf.image.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{propertyNum}")
    public List<ImageDto> getByProperty(@PathVariable Long propertyNum) {
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
