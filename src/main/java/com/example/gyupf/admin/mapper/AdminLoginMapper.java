package com.example.gyupf.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.gyupf.admin.dto.AdminLoginDto;
import com.example.gyupf.admin.dto.AdminResponseDto;

@Mapper
public interface AdminLoginMapper {
	 AdminResponseDto findByIdAndPassword(AdminLoginDto dto);

}
