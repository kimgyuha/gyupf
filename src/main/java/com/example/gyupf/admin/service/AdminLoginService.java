package com.example.gyupf.admin.service;

import com.example.gyupf.admin.dto.AdminLoginDto;
import com.example.gyupf.admin.dto.AdminResponseDto;
import com.example.gyupf.admin.mapper.AdminLoginMapper;
import com.example.gyupf.config.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {

    private final AdminLoginMapper adminLoginMapper;

    public AdminLoginService(AdminLoginMapper adminLoginMapper) {
        this.adminLoginMapper = adminLoginMapper;
    }

    public String login(AdminLoginDto dto) {
        AdminResponseDto admin = adminLoginMapper.findByIdAndPassword(dto);
        if (admin != null) {
            return JwtUtil.generateToken(admin);
        } else {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }
} 