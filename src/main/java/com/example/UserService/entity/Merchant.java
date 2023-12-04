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
@Table(name="merchant")
public class Merchant {
    @javax.persistence.Id
    private String merchantId;
    private String merchant_name;
    @Column(name = "merchant_email", unique = true,nullable = false)
    private String merchant_email;
    @Column(name="hashed_password",nullable = false)
    private String hashedPassword;
    private String merchant_no;
    private String merchant_address;
}
