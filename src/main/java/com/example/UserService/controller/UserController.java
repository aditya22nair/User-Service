package com.example.UserService.controller;

import com.example.UserService.Dto.*;
import com.example.UserService.entity.Merchant;
import com.example.UserService.entity.User;
import com.example.UserService.service.MerchantService;
import com.example.UserService.service.UserService;
import io.swagger.v3.oas.annotations.headers.Header;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    public Boolean addUser(@RequestBody UserDto userDto){
        return userService.addUserData(userDto);
    }
    @GetMapping
    private List<UserResponseDto> getAllUser(){
        Iterable<User>data=userService.getAllUser();
        List<UserResponseDto> responseDtos=new ArrayList<>();
        for(User user:data){
            UserResponseDto userResponseDto=new UserResponseDto();
            BeanUtils.copyProperties(user,userResponseDto);
            responseDtos.add(userResponseDto);
        }
        return responseDtos;

    }
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody AuthenticateDto authenticateDto) {
        String jwtToken= userService.userLogin(authenticateDto);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + jwtToken)
                .body("Login successful");
    }

    @GetMapping("/{user_id}")
    public UserResponseDto getUserById(@RequestParam String userId) {
        return userService.getUserById(userId);
    }
    @GetMapping("/{email}")
    public UserResponseDto getUserByemail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
    @GetMapping("/token")
    public ResponseEntity<String> isTokenValid(@RequestHeader("authorization") String authorizationHeader) {

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            System.out.println("Received token: " + token);

            if (userService.validateJwtToken(token)) {
                return ResponseEntity.ok("Token is valid");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is not valid");
            }
        } else {
            System.out.println("Invalid Authorization header: " + authorizationHeader);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Authorization header");
        }
    }



}
