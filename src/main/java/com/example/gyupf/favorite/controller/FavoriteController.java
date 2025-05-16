package com.example.gyupf.favorite.controller;

import com.example.gyupf.favorite.dto.FavoriteDto;
import com.example.gyupf.favorite.dto.FavoritePropertyDto;
import com.example.gyupf.favorite.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/all")
    public List<FavoriteDto> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    @GetMapping("/count/{propertyNum}")
    public int countFavorites(@PathVariable Long propertyNum) {
        return favoriteService.countFavoritesByProperty(propertyNum);
    }

    @GetMapping("/customer/{customerNum}")
    public List<FavoritePropertyDto> getFavoritePropertyList(@PathVariable Long customerNum) {
        return favoriteService.getFavoriteProperties(customerNum);
    }

    @GetMapping("/property/{propertyNum}")
    public List<FavoritePropertyDto> getFavoritesByProperty(@PathVariable Long propertyNum) {
        return favoriteService.getFavoritesByProperty(propertyNum);
    }
    @PostMapping
    public void create(@RequestBody FavoriteDto dto) {
        favoriteService.addFavorite(dto);
    }

    @PutMapping("/{favoriteNum}")
    public void update(@PathVariable Long favoriteNum, @RequestBody FavoriteDto dto) {
        dto.setFavoriteNum(favoriteNum);
        favoriteService.updateFavorite(dto);
    }

    @DeleteMapping("/{favoriteNum}")
    public void delete(@PathVariable Long favoriteNum) {
        favoriteService.removeFavorite(favoriteNum);
    }
}

