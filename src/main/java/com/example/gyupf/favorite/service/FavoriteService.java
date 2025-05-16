package com.example.gyupf.favorite.service;

import com.example.gyupf.favorite.dto.FavoriteDto;
import com.example.gyupf.favorite.dto.FavoritePropertyDto;
import com.example.gyupf.favorite.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteMapper favoriteMapper;

    public FavoriteService(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    public List<FavoriteDto> getAllFavorites() {
        return favoriteMapper.selectAll();
    }

    public int countFavoritesByProperty(Long propertyNum) {
        return favoriteMapper.countFavoritesByProperty(propertyNum);
    }

    public List<FavoritePropertyDto> getFavoriteProperties(Long customerNum) {
        return favoriteMapper.selectFavoritePropertiesByCustomer(customerNum);
    }

    public List<FavoritePropertyDto> getFavoritesByProperty(Long propertyNum) {
        return favoriteMapper.selectFavoritePropertiesByProperty(propertyNum);
    }

    public void addFavorite(FavoriteDto dto) {
        favoriteMapper.insert(dto);
    }

    public void removeFavorite(Long favoriteNum) {
        favoriteMapper.delete(favoriteNum);
    }

    public void updateFavorite(FavoriteDto dto) {
        favoriteMapper.update(dto);
    }
}
