package com.example.UserService.Dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserResponseDto {
    private String userId;
    private String user_name;
    private String email;
    private String phone_no;
    private String address;
}
