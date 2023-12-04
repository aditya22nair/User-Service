package com.example.UserService.service.Impl;

import com.example.UserService.Dto.AuthenticateDto;
import com.example.UserService.Dto.MerchantResponseDto;
import com.example.UserService.Dto.UserDto;
import com.example.UserService.Dto.UserResponseDto;
import com.example.UserService.entity.Merchant;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepo;
import com.example.UserService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String userLogin(AuthenticateDto authenticateDto) {
        User user = userRepo.findByEmail(authenticateDto.getEmail());

        if (user != null) {
            String storedHashedPassword = user.getHashedPassword();
            String enteredPlainPassword = authenticateDto.getPassword();
            if (passwordEncoder.matches(enteredPlainPassword, storedHashedPassword)) {
                return generateJwtToken(authenticateDto.getEmail());
            }
        }

        return null;
    }

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SecretKey secretKey;

    @Value("${jwt.expirationMillis}")
    private long expirationMillis;
    @Override
    public Boolean addUserData(UserDto userDto) {
        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        user.setHashedPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserId(UUID.randomUUID().toString());
        User savedUser=userRepo.save(user);
        return Objects.nonNull(savedUser);
    }
    public String generateJwtToken(String useremail) {
        return JwtService.generateJwtToken(useremail, secretKey, expirationMillis);
    }

    @Override
    public boolean validateJwtToken(String token) {
        try {
            return JwtService.validateJwtToken(token, secretKey);
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterable<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public UserResponseDto getUserById(String userId) {
        User user= userRepo.findById(userId).orElse(null);
        UserResponseDto userResponseDto=new UserResponseDto();
        BeanUtils.copyProperties(user,userResponseDto);
        return userResponseDto;
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user=userRepo.findByEmail(email);
        if (user != null) {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);
            return userResponseDto;
        }
        return null;
    }

}
