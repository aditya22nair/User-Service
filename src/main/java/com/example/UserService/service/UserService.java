package com.example.UserService.service;

import com.example.UserService.Dto.*;
import com.example.UserService.entity.Merchant;
import com.example.UserService.entity.User;

public interface UserService {
    Boolean addUserData(UserDto userDto);
    public Iterable<User> getAllUser();
    UserResponseDto getUserById(String userId);
    UserResponseDto getUserByEmail(String email);
    String userLogin(AuthenticateDto authenticateDto);
    public boolean validateJwtToken(String token);
}
