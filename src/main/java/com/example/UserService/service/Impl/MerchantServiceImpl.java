package com.example.UserService.service.Impl;

import com.example.UserService.Dto.AuthenticateDto;
import com.example.UserService.Dto.MerchantDto;
import com.example.UserService.Dto.MerchantResponseDto;
import com.example.UserService.Dto.UserResponseDto;
import com.example.UserService.entity.Merchant;
import com.example.UserService.entity.User;
import com.example.UserService.repository.MerchantRepo;
import com.example.UserService.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantRepo merchantRepo;


    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public Boolean merchantLogin(AuthenticateDto authenticateDto) {
        Merchant merchant = merchantRepo.findMerchantByEmail(authenticateDto.getEmail());

        if (merchant != null) {
            String storedHashedPassword = merchant.getHashedPassword();
            String enteredPlainPassword = authenticateDto.getPassword();
            if (passwordEncoder.matches(enteredPlainPassword, storedHashedPassword)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean addMercahntData(MerchantDto merchantDto) {
        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDto,merchant);
        merchant.setHashedPassword(passwordEncoder.encode(merchantDto.getPassword()));
        merchant.setMerchantId(UUID.randomUUID().toString());
        Merchant savedMerchant=merchantRepo.save(merchant);
        return Objects.nonNull(savedMerchant);
    }

    @Override
    public Iterable<Merchant> getAllMerchant() {
        return merchantRepo.findAll();
    }

    @Override
    public MerchantResponseDto getMerchantById(String merchantId) {
        Merchant merchant= merchantRepo.findById(merchantId).orElse(null);
        MerchantResponseDto merchantResponseDto=new MerchantResponseDto();
        BeanUtils.copyProperties(merchant,merchantResponseDto);
        return merchantResponseDto;

    }

    @Override
    public MerchantResponseDto getMerchantByEmail(String merchantId) {
        Merchant merchant=merchantRepo.findMerchantByEmail(merchantId);
        if (merchant != null) {
            MerchantResponseDto merchantResponseDto = new MerchantResponseDto();
            BeanUtils.copyProperties(merchant, merchantResponseDto);
            return merchantResponseDto;
        }
        return null;
    }
}
