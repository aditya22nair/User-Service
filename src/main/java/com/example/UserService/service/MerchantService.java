package com.example.UserService.service;

import com.example.UserService.Dto.AuthenticateDto;
import com.example.UserService.Dto.MerchantDto;
import com.example.UserService.Dto.MerchantResponseDto;
import com.example.UserService.entity.Merchant;

import java.util.List;

public interface MerchantService {
    Boolean addMercahntData(MerchantDto merchantDto);
    public Iterable<Merchant> getAllMerchant();
    MerchantResponseDto getMerchantById(String merchantId);
    MerchantResponseDto getMerchantByEmail(String merchantId);
    public Boolean merchantLogin(AuthenticateDto authenticateDto);
}
