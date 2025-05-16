package com.example.gyupf.favorite.mapper;

import com.example.gyupf.favorite.dto.FavoriteDto;
import com.example.gyupf.favorite.dto.FavoritePropertyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteMapper {

    List<FavoriteDto> selectAll();

    //각 매물에 찜 횟수 표시
    int countFavoritesByProperty(@Param("propertyNum") Long propertyNum);

    //고객이 자신의 찜목록 불러올때
    List<FavoritePropertyDto> selectFavoritePropertiesByCustomer(@Param("customerNum") Long customerNum);

    //관리자 페이지에 매물별 찜 리스트
    List<FavoritePropertyDto> selectFavoritePropertiesByProperty(@Param("propertyNum") Long propertyNum);

    int insert(FavoriteDto dto);

    int update(FavoriteDto dto);

    int delete(@Param("favoriteNum") Long favoriteNum);
}
