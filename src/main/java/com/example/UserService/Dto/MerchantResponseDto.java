package com.example.UserService.Dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;

@Data
public class MerchantResponseDto {
    private String merchantId;
    private String merchant_name;
    private String merchant_email;
    private String merchant_no;
    private String merchant_address;
}
