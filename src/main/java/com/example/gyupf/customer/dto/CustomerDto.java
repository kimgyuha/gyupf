package com.example.gyupf.customer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDto {
    private Long customerNum;
    private String customerId;
    private String customerPw;
    private String name;
    private String phoneNumber;
    private String message;
    private LocalDateTime inquiryDt;
    private String agreementYn;
}