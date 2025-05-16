package com.example.gyupf.property.mapper;

import com.example.gyupf.property.dto.PropertyListDto;
import com.example.gyupf.property.dto.PropertyDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PropertyMapper {

    List<PropertyListDto> selectAll();

    List<PropertyListDto> selectPropertyListPaged(@Param("pageSize") int pageSize, @Param("offset") int offset);

    PropertyDetailDto selectById(@Param("propertyNum") Long propertyNum);

    @Options(useGeneratedKeys = true, keyProperty = "propertyNum")
    int insert(PropertyDetailDto property);

    int update(PropertyDetailDto property);

    int delete(@Param("propertyNum") Long propertyNum);

    int countProperties();

    //프로퍼티 테이블에 대표이미지 업데이트(등록시에도)
    void updateImageUrl(PropertyDetailDto dto);
}
