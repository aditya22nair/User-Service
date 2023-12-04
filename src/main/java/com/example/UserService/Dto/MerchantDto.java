package com.example.UserService.Dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;

@Data
public class MerchantDto {
    private String merchant_name;
    private String merchant_email;
    private String password;
    private String merchant_no;
    private String merchant_address;
}
