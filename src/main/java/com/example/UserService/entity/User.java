package com.example.UserService.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @javax.persistence.Id
    private String userId;
    private String user_name;

    @Column(name = "email", unique = true,nullable = false)
    private String email;
    @Column(name="hashed_password",nullable = false)
    private String hashedPassword;
    private String phone_no;
    private String address;
}
