package com.example.UserService.Dto;

import lombok.Data;

@Data
public class AuthenticateDto {
    private String email;
    private String password;
}
