package com.example.gyupf.admin.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponseDto {
	private Long id;
    private String username; 	//id
    private String name;		//이름
    private String role;
    private LocalDateTime lastLoginAt;

}
