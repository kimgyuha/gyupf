package com.example.gyupf.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.gyupf.property.dto.PropertyDetailDto;
import com.example.gyupf.property.dto.PropertyListDto;

@Mapper
public interface AdminPropertyMapper {
	
	List<PropertyListDto> selectPagedList(@Param("offset") int offset, @Param("size") int size);

    int countPagedList();

    PropertyListDto findById(@Param("propertyNum") Long propertyNum);
    
	PropertyDetailDto selectById(@Param("propertyNum") Long propertyNum);
	
    void insert(PropertyDetailDto dto);

    void update(PropertyDetailDto dto);

    void updateImageUrl(PropertyDetailDto dto);

    void delete(Long propertyNum);

}
