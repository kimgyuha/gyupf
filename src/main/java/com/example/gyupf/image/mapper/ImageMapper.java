package com.example.gyupf.image.mapper;

import com.example.gyupf.image.dto.ImageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageMapper {

    List<ImageDto> selectByPropertyNum(@Param("propertyNum") Long propertyNum);

    int insert(ImageDto dto);

    int delete(@Param("propertyNum") Long propertyNum);
}
