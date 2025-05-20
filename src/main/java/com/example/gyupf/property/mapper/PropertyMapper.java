package com.example.gyupf.property.mapper;

import com.example.gyupf.property.dto.PropertyListDto;
import com.example.gyupf.property.dto.PropertyDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PropertyMapper {

    List<PropertyListDto> selectAll();

    //조건 적용한 매물 카운트 & 리스트
    int countPropertiesWithCondition(Map<String, Object> params);
    
    List<PropertyListDto> selectPropertyListWithConditionPaged(Map<String, Object> params);

    //구별 매물 개수
    int countSelectedDistrict(String district);

    //상세 매물 조회
    PropertyDetailDto selectById(@Param("propertyNum") Long propertyNum);

    @Options(useGeneratedKeys = true, keyProperty = "propertyNum")
    int insert(PropertyDetailDto property);

    int update(PropertyDetailDto property);

    int delete(@Param("propertyNum") Long propertyNum);


    //프로퍼티 테이블에 대표이미지 업데이트(등록시에도)
    void updateImageUrl(PropertyDetailDto dto);
}
